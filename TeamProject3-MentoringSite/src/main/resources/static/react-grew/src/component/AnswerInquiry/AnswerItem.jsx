import React, { useEffect, useState } from "react";
import "../../css/styles.css";
import * as answerApi from "../../api/answerApi";
import * as categoryApi from "../../api/categoryApi";
import { useNavigate } from "react-router-dom";
import { useMemberAuth } from "../../util/AuthContext";
import ReportModal from "../Report/ReportModal";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faThumbsDown, faThumbsUp } from "@fortawesome/free-regular-svg-icons";
import { faMedal } from "@fortawesome/free-solid-svg-icons";

export default function AnswerItem({ answer }) {
  const { token, member } = useMemberAuth();
  const [inquiry, setInquiry] = useState(0);
  const [voteCount, setVoteCount] = useState(0);
  const [mentorProfile, setMentorProFile] = useState([]);
  const [category, setCategories] = useState([]);
  const [isReportHovered, setIsReportHovered] = useState(false);
  const [isUpVoteHovered, setIsUpVoteHovered] = useState(false);
  const [isDownVoteHovered, setIsDownVoteHovered] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [report, setreport] = useState({});

  const navigate = useNavigate();
  // 버튼 클릭 시 팝업 창을 토글하는 함수
  const handleProfile = () => {
    navigate(`/mentor-profile/${mentorProfile.mentorProfileNo}`);
  };
  const fetchCategories = async () => {
    const response = await categoryApi.getCategory(mentorProfile.categoryNo);
    setCategories(response.data);
  };

  const fetchMentorProfile = async () => {
    const response = await answerApi.getMentorProfileByMemberNo(
      answer.memberNo
    );
    setMentorProFile(response.data);
  };

  async function fetchData() {
    try {
      const response = await answerApi.findInquiry(answer.inquiryNo);
      setInquiry(response.data);
      const responseJsonObject = await answerApi.countVote(answer.answerNo);
      setVoteCount(responseJsonObject.data);
    } catch (error) {
      console.error("오류 발생:", error);
    }
  }
  useEffect(() => {
    fetchData();
  }, [voteCount]);
  useEffect(() => {
    fetchMentorProfile();
  }, []);
  useEffect(() => {
    if (mentorProfile?.categoryNo) {
      fetchCategories();
    }
  }, [mentorProfile]); // mentorProfile가 업데이트된 후에 fetchCategories 실행

  //답변 수정 버튼
  const handleModify = async () => {
    navigate(`/answer/modify/${answer.answerNo}`);
  };
  const handleUpvote = async () => {
    try {
      const response = await answerApi.upVote(answer.answerNo, token); // API 호출
      if (response.status === 6000) {
        fetchData(); // 추천 성공 상태 확인
      } else if (token == null) {
        alert("로그인이 필요한 서비스입니다");
      } else {
        alert("이미 추천 혹은 비추천을 누르셨습니다"); // 실패 시 에러 로그
      }
    } catch (error) {
      console.error(
        "API 호출 중 오류 발생:",
        error.response ? error.response.data : error
      ); // 오류 로그
      alert("API 호출 중 오류 발생: " + error.message); // 사용자에게 오류 메시지 표시
    }
  };

  //답변 삭제 버튼
  const handleRemoveAnswer = async () => {
    try {
      if (!window.confirm("답변을 삭제하시겠습니까?")) return;
      await answerApi.deleteAnswer(answer.answerNo, token);
      window.location.reload();
    } catch (error) {
      console.log("답변 삭제 실패", error);
    }
  };

  const handleDownvote = async () => {
    try {
      const response = await answerApi.downVote(answer.answerNo, token); // API 호출
      if (response.status === 6000) {
        fetchData(); // 추천 성공 상태 확인
      } else if (token == null) {
        alert("로그인이 필요한 서비스입니다");
      } else {
        alert("이미 추천 혹은 비추천을 누르셨습니다"); // 실패 시 에러 로그
      }
    } catch (error) {
      console.error("API 호출 중 오류 발생:", error); // 오류 처리
    }
  };
  //답변채택
  const handleAccept = async () => {
    const response = await answerApi.acceptAnswer(answer.answerNo);
    if (response.status === 6400) {
      alert("채택이 완료되었습니다.");
    } else {
      alert("이미 다른 답변을 채택하였습니다.");
    }
    console.log(response);
  };

  //신고 하기 창 열고 닫기
  const handleOpenModal = () => {
    setIsModalOpen(true);
    setreport({
      type: "ANSWER",
      target: answer.answerNo,
    });
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  return (
    <>
      <div className="answer-container">
        {answer.answerAccept == 2 ? (
          <div className="answer-accept-status">
            <FontAwesomeIcon icon={faMedal} />
            <span>채택된 답변</span>
          </div>
        ) : (
          <div></div>
        )}

        {member &&
        member.memberNo == inquiry.memberNo &&
        answer.answerAccept == 1 ? (
          <div className="answer-accept">
            <button className="accept-btn" onClick={handleAccept}>
              채택하기
            </button>
          </div>
        ) : (
          <div></div>
        )}
        <button className="answer-member" onClick={handleProfile}>
          <div className="answer-img">
            <img
              src={
                mentorProfile?.mentorImage ||
                "/images/mentor-profile/defaultimg.jpeg"
              }
              alt="Mentor Profile"
            />
          </div>
          <div className="answer-mentor-info">
            <div className="answer-member-category">
              {category.categoryName}
            </div>
            <div className="answer-member-name">{answer.memberName} 멘토</div>
          </div>
        </button>

        <div
          className="answer-content"
          dangerouslySetInnerHTML={{
            __html: answer.answerContent.replace(/\n/g, "<br/>"),
          }}
        >
        </div>

        <div className="answer-date">{answer.answerDate.substring(0, 10)}</div>
        <div className="answer-vote">
          <button
            className="answer-upvote-btn"
            onClick={handleUpvote}
            onMouseEnter={() => setIsUpVoteHovered(true)} // 마우스가 버튼 위에 올라갔을 때
            onMouseLeave={() => setIsUpVoteHovered(false)} // 마우스가 버튼을 벗어났을 때
          >
            {isUpVoteHovered ? (
              <FontAwesomeIcon icon={faThumbsUp} />
            ) : (
              <FontAwesomeIcon icon={faThumbsUp} />
            )}
          </button>
          {voteCount}
          <button
            className="answer-downvote-btn"
            onClick={handleDownvote}
            onMouseEnter={() => setIsDownVoteHovered(true)} // 마우스가 버튼 위에 올라갔을 때
            onMouseLeave={() => setIsDownVoteHovered(false)} // 마우스가 버튼을 벗어났을 때
          >
            {isDownVoteHovered ? (
              <FontAwesomeIcon icon={faThumbsDown} />
            ) : (
              <FontAwesomeIcon icon={faThumbsDown} />
            )}
          </button>
        </div>

        {/* 답변 수정 삭제 신고 버튼 */}
        <div className="inquiry-view-answer-btn">
          {member && member.memberNo == answer.memberNo ? (
            <div className="modify-delete-btn">
              <button onClick={handleModify}>수정</button>
              <button onClick={handleRemoveAnswer}>삭제</button>
            </div>
          ) : (
            <div></div>
          )}
          {isModalOpen && (
            <ReportModal onClose={handleCloseModal} report={report} />
          )}
          <button
            onMouseEnter={() => setIsReportHovered(true)} // 마우스가 버튼 위에 올라갔을 때
            onMouseLeave={() => setIsReportHovered(false)} // 마우스가 버튼을 벗어났을 때
            className={`hover-button ${isReportHovered ? "hovered" : ""}`}
            onClick={handleOpenModal}
          >
            <img
              src={
                isReportHovered
                  ? "https://img.icons8.com/?size=100&id=jy7dy2jsJ5UR&format=png&color=ed1515"
                  : "https://img.icons8.com/?size=100&id=t5aOnHwCycmN&format=png&color=000000"
              }
              alt="Button Image"
              className="button-image"
            />
          </button>
        </div>
      </div>
    </>
  );
}
