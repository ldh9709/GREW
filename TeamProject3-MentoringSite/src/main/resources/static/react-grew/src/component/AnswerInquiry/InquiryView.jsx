import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import * as inquiryApi from "../../api/inquiryApi";
import * as answerApi from "../../api/answerApi";
import AnswerItem from "./AnswerItem";
import { getCookie } from "../../util/cookieUtil";
import "../../css/styles.css";
function InquiryView() {
  const memberCookie = getCookie("member");
  const navigate = useNavigate();
  const { inquiryNo } = useParams(); //path에서 받아오는거임! app.js의 경로와 관련있음
  const [inquiry, setInquiry] = useState({
    inquiryNo: 0,
    inquiryTitle: "",
    inquiryContent: "",
    inquiryDate: "",
    memberName: "",
  });
  const [answer, setAnswer] = useState([]);
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
  const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
  const [itemsPerPage] = useState(5); // 페이지당 항목 수 (예: 한 페이지에 5개 항목)
  const [sortType, setSortType] = useState("latest"); // 기본적으로 'latest'로 설정
  console.log(memberCookie.memberRole);
  useEffect(() => {
    (async () => {
      const responseJsonObject = await inquiryApi.viewInquiry(inquiryNo);
      console.log(responseJsonObject);

      if (
        responseJsonObject.status === 5500 &&
        responseJsonObject.data.inquiryStatus === 1
      ) {
        setInquiry(responseJsonObject.data);
      } else {
        alert("게시물이 존재하지 않습니다");
        navigate("/inquiry");
      }
    })();
  }, [inquiryNo, navigate]);

  const fetchAnswers = async (inquiryNo, page, size, sortButton) => {
    try {
      let responseJsonObject;
      if (sortButton === "latest") {
        responseJsonObject = await answerApi.AnswerByDate(
          inquiryNo,
          page,
          size
        );
      } else if (sortButton === "vote") {
        responseJsonObject = await answerApi.AnswerByVote(
          inquiryNo,
          page,
          size
        );
      }
      console.log(responseJsonObject.data);
      setAnswer(responseJsonObject.data.content); // 답변 목록 상태에 저장
      setTotalPages(responseJsonObject.data.totalPages);
    } catch (error) {
      // 새로운 API로 답변 목록 가져오기
      console.error("답변 목록 가져오기 실패:", error);
    }
  };

  //질문삭제
  const inquiryRemoveAction = async () => {
    const responseJsonObject = await inquiryApi.deleteInquiry(inquiryNo);
    if (responseJsonObject.status === 5200) {
      navigate("/inquiry");
    } else {
      alert("실패");
    }
  };

  // 페이지 변경 시 데이터 갱신
  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  // 페이지 로드 시 데이터 가져오기
  useEffect(() => {
    fetchAnswers(inquiryNo, currentPage - 1, itemsPerPage, sortType);
  }, [currentPage, sortType]);
  // 버튼 클릭시 호출 함수
  const handleRadioChange = (e) => {
    setSortType(e.target.value); //버튼 밸류로 sorttype변경
  };
  // 페이지네이션 버튼 표시 (10개씩 끊어서 표시)
  const pageNumbers = [];
  const pagesToShow = 5; // 한 번에 보여줄 페이지 수
  const startPage =
    Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;
  const endPage = Math.min(startPage + pagesToShow - 1, totalPages);

  for (let i = startPage; i <= endPage; i++) {
    pageNumbers.push(i);
  }
  const handleWriteButton = ()=>{
    console.log(memberCookie)
    if(memberCookie){
      navigate(`/answer/answerWrite/${inquiryNo}`);
    }else{
      alert('로그인이 필요한 서비스입니다.');
      return;
    }
  }
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <div style={{ paddingLeft: 10 }}>
        <form name="f" method="post">
          <input type="hidden" name="inquiryNo" value={inquiry.inquiryNo} />

          {/* 카테고리에 맞는 멘토만 보이는조건 */}

          {/* {memberCookie.memberRole == "ROLE_MENTOR" ? ( */}
            <div className="answer-write">
                <button className="answer-notify-btn" onClick={handleWriteButton}>
                  <img
                    src="https://img.icons8.com/?size=100&id=P1bJzKUoOQYz&format=png&color=000000"
                    style={{
                      width: "20px",
                      height: "20px",
                      marginRight: "5px",
                      marginLeft: "-5px",
                      marginBottom: "-3px",
                    }}
                  />
                  답변하기
                </button>
            </div>
          {/* // ) : (
          //   <div style={{ marginBottom: "50px" }}></div>
          // )} */}
          {/* 카테고리에 맞는 멘토만 보이는조건 */}
          <div className="inquiry-container-inview">
            <div>
              <div className="inquiry-title">{inquiry.inquiryTitle}</div>
            </div>
            <div className="inquiry-desc">
              <div>{inquiry.categoryName}</div>
              <div>
                {inquiry.memberName} | 조회수 {inquiry.inquiryViews} |{" "}
                {inquiry.inquiryDate.substring(0, 10)}
              </div>
            </div>
            <div className="inquiry-content">
              <div>{inquiry.inquiryContent}</div>
            </div>

            <br />
            {memberCookie.memberNo == inquiry.memberNo ? (
              <div>
                <Link to={`/inquiry/modify/${inquiryNo}`}>
                  <button>수정</button>
                </Link>

                <button
                  onClick={(e) => {
                    e.preventDefault(); // 폼 제출 방지
                    inquiryRemoveAction(); // 삭제 액션 실행
                  }}
                >
                  삭제
                </button>
              </div>
            ) : (
              <div></div>
            )}
          </div>
        </form>
      </div>
      <div style={{ marginTop: "20px" }}>
        <div className="radio-container">
          {/* 라디오 버튼 */}
          <label style={{ marginRight: "10px" }}>
            <input
              type="radio"
              name="sortType"
              value="latest"
              checked={sortType === "latest"}
              onChange={handleRadioChange}
            />
            최신순
          </label>
          <label>
            <input
              type="radio"
              name="sortType"
              value="vote"
              checked={sortType === "vote"}
              onChange={handleRadioChange}
            />
            추천순
          </label>
        </div>
        {answer && answer.length > 0 ? (
          answer.map((answer) => (
            <AnswerItem key={answer.answerNo} answer={answer} /> // 한 질문에 대한 답변(조회수 정렬)
          ))
        ) : (
          <div className="inquiry-write-btn">
            <div>아직 등록된 답변이 없습니다.</div>
          </div>
        )}
      </div>

      {/* 페이지네이션 버튼 */}
      {/* 페이지네이션 버튼 */}
      <div className="pagenation">
        {startPage > 1 && (
          <button onClick={() => paginate(startPage - 1)}>이전</button>
        )}{" "}
        {/* 이전 그룹 */}
        {pageNumbers.map((number) => (
          <button
            key={number}
            onClick={() => paginate(number)}
            style={{
              backgroundColor: number === currentPage ? "#4CAF50" : "",
              color: number === currentPage ? "white" : "",
            }}
          >
            {number}
          </button>
        ))}
        {endPage < totalPages && (
          <button onClick={() => paginate(endPage + 1)}>다음</button> // 다음 그룹
        )}
      </div>
    </>
  );
}
export default InquiryView;
