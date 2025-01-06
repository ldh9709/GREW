import { useMemberAuth } from "../../util/AuthContext"  // 인증객체,쿠키를 가져오기 위한 import
import React, { useEffect, useState } from 'react'; // React와 필요한 훅들 import
import { useNavigate, useParams } from 'react-router-dom'; // useParams import
import { getMentorBoardDetail, increaseViewCount, deleteMentorBoard } from '../../api/mentorBoardApi'; // API 함수 import
import * as mentorProfileApi from '../../api/mentorProfileApi'
import * as followApi from "../../api/followApi";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import '../../css/mentorBoard.css'; // 스타일 import
import { faHeartCirclePlus,faHeart } from "@fortawesome/free-solid-svg-icons";

const MentorBoardDetail = () => {
  const { mentorBoardNo } = useParams(); // URL 파라미터에서 mentorBoardNo 가져오기
  const { token, member } = useMemberAuth(); // 토큰과 멤버 선언하여 Context에 담긴 정보 가져오기
  const navigate = useNavigate(); // 페이지 이동을 위한 useNavigate
  const [board, setBoard] = useState(null);
  const [mentor, setMentor] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isFollow, setIsfollow] = useState(false);

  const calculateRelativeDate = (dateString) => {
    const now = new Date();
    const postDate = new Date(dateString);
    const diffInSeconds = Math.floor((now - postDate) / 1000);

    if (diffInSeconds < 60) return "방금 전";
    if (diffInSeconds < 3600) return `${Math.floor(diffInSeconds / 60)}분 전`;
    if (diffInSeconds < 86400) return `${Math.floor(diffInSeconds / 3600)}시간 전`;
    if (diffInSeconds < 604800) return `${Math.floor(diffInSeconds / 86400)}일 전`;
    if (diffInSeconds < 2419200) return `${Math.floor(diffInSeconds / 604800)}주 전`;
    return `${Math.floor(diffInSeconds / 2419200)}개월 전`;
  };
  
  const fetchBoardDetail = async () => {
    try {
      setLoading(true);
      const response = await getMentorBoardDetail(mentorBoardNo);
      console.log(response);
      setBoard(response.data); // API 응답 데이터 저장
    } catch (err) {
      setError('멘토 보드 상세 데이터를 가져오는 중 오류가 발생했습니다.');
    } finally {
      setLoading(false);
    }
  };
  
  const fetchMentorInfo = async() => {
    try {
      const response = await mentorProfileApi.getMentorProfileByMemberNo(board.memberNo);
      setMentor(response.data)
      console.log('mentor',mentor)
    } catch (error) {
      setError('멘티 정보를 가져오는 중 오류가 발생했습니다.');
    }
  }

  const handleViewCount = async () => {
    const lastViewedTime = localStorage.getItem(`mentorBoard-${mentorBoardNo}-lastViewed`);
    const now = Date.now();

    // 10분 제한 체크
    if (!lastViewedTime || now - lastViewedTime > 10 * 60 * 1000) {
      await increaseViewCount(mentorBoardNo); // API 호출
      localStorage.setItem(`mentorBoard-${mentorBoardNo}-lastViewed`, now);
    }
  };

  useEffect(() => {
    fetchBoardDetail();
    handleViewCount(); // 조회수 증가 로직 추가
    checkFollow();
  }, [mentorBoardNo])

  useEffect(()=>{
    if (board!=null) {
      fetchMentorInfo();
    }
  },[board])

  const handleEdit = () => {
    if (!board) {
      alert("게시글 정보를 불러오는 중입니다. 잠시 후 다시 시도해주세요.");
      return;
    }
  
    // 게시글 작성자와 현재 로그인한 유저의 memberNo 비교
    if (member.memberNo !== board.memberNo) {
      alert("게시글 작성자만 수정할 수 있습니다.");
      return;
    }

    navigate(`/mentor-board/update/${mentorBoardNo}`); // 수정 페이지로 이동
  };

  const handleDelete = async () => {
    if (!board) {
      alert("게시글 정보를 불러오는 중입니다. 잠시 후 다시 시도해주세요.");
      return;
    }
  
    // 게시글 작성자와 현재 로그인한 유저의 memberNo 비교
    if (member.memberNo !== board.memberNo) {
      alert("게시글 작성자만 삭제할 수 있습니다.");
      return;
    }
  
    if (window.confirm("정말 삭제하시겠습니까?")) {
      try {
        await deleteMentorBoard(token, mentorBoardNo); // 삭제 API 호출
        alert("삭제가 완료되었습니다.");
        navigate("/mentor-board/list"); // 목록 페이지로 이동
      } catch (err) {
        alert("삭제 중 오류가 발생했습니다.");
        console.error(err);
      }
    }
  };

  //팔로우 여부 체크
  const checkFollow = async() => {
    const response = await followApi.isExistFollow(token, mentor.memberNo);
    console.log(response)
    setIsfollow(response.data);
  }

  //팔로우 등록
  const handleFollowClick = async() => {
    if(token && member.memberRole ==='ROLE_MENTEE'){
      if (!isFollow) {
          const follow = {
          menteeMemberNo:member.memberNo,
          mentorMemberNo:mentor.memberNo
        }
          await followApi.addfollow(token, follow);
          setIsfollow(true);
        } else {
          await followApi.deleteFollow(token, mentor.memberNo);
          setIsfollow(false);
      } 
    }else{
      alert("멘티 회원만 가능한 서비스입니다.")
    }
  }


  if (loading) return <p>로딩 중입니다...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="mentor-board-detail-container">
    {/* 카테고리 및 제목 */}
    <div className="mentor-board-detail-header">
      <span className="category-badge">{board.categoryName}</span>
      <h1 className="mentor-board-title">{board.mentorBoardTitle || "제목 없음"}</h1>
    </div>
  
    {/* 작성자 정보 */}
    <div className="mentor-board-author">
      <img
        src={mentor.mentorImage || ""}
        alt="멘토 프로필 이미지"
        className="author-image"
      />
      <div className="author-info">
        <span className="author-name">{mentor.memberName || ""} 멘토</span>
        <span className="author-meta">
        {(board?.mentorBoardDate).substring(0,10)} · {calculateRelativeDate(board?.mentorBoardDate)}
        </span>
      </div>
    </div>
  
    {/* 보드 이미지 */}
    <div className="mentor-board-image-wrapper">
        <img
        src={board.mentorBoardImage || "default.jpg" }
        alt="보드 이미지"
        className="mentor-board-image"
      />
    </div>
  
    {/* 콘텐츠 */}
    <div className="mentor-board-content">
      <pre>{board?.mentorBoardContent || "내용 없음"}</pre>

      {/* 수정 및 삭제 버튼 */}
      {member.memberNo === mentor.memberNo ? (
        <div className="mentor-board-actions">
          <button className="board-edit-button" onClick={handleEdit}>
            수정
          </button>
          <button className="board-delete-button" onClick={handleDelete}>
            삭제
          </button>
        </div>
      ) : (
        <div></div>
      )}
    </div>
      
    <div
      className="mentor-info-card"
      role="button" // 접근성을 위한 역할 추가
      tabIndex={0} // 키보드 접근 가능
      onClick={() => navigate(`/mentor-profile/${mentor.mentorProfileNo}`)} // 클릭 시 네비게이션
    >
      <img
        src={mentor.mentorImage || "/default-profile.png"}
        alt="멘토 프로필 이미지"
        className="mentor-profile-image"
      />
      <div className="mentor-info">
        <h2 className="mentor-name">
          {mentor.memberName} <span className="mentor-name">멘토</span>
        </h2>
        <div className="mentor-headline">
          {mentor.mentorHeadline}
        </div>
      </div>
      <div className="mentor-follow">
       <button
          className={`follow-button ${isFollow ? 'follow-isexist' : ''}`}
          onClick={(e) => {
            e.stopPropagation(); // 이벤트 전파 차단
            handleFollowClick();
          }}
        >
          {isFollow ? (
            <FontAwesomeIcon icon={faHeart}/>
          ): (
            <FontAwesomeIcon icon={faHeartCirclePlus} />
          )}
          팔로우
        </button>
      </div>
    </div>
  </div>
  );
};

export default MentorBoardDetail; // 반드시 default export 추가
