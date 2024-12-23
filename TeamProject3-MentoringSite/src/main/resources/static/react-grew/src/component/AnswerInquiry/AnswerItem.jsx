import React, { useEffect, useRef, useState } from "react";
import "../../css/styles.css";
import * as answerApi from "../../api/answerApi";
import { useNavigate } from "react-router-dom";
import { getCookie } from "../../util/cookieUtil";
import AnswerProfilePopup from "./AnswerProfilePopup";
export default function AnswerItem({ answer }) {
  const [inquiry, setInquiry] = useState(0);
  const [voteCount, setVoteCount] = useState(0);
  const [isPopupVisible, setIsPopupVisible] = useState(false);

  const memberCookie = getCookie("member");
  const navigate = useNavigate();
  const token =
    memberCookie && memberCookie.accessToken ? memberCookie.accessToken : null;
  // 버튼 클릭 시 팝업 창을 토글하는 함수
  const togglePopup = () => {
    setIsPopupVisible((prevState) => !prevState);
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
  return (
    <>
      
      <div className="answer-container">
        {answer.answerAccept == 2 ? (
          <div className="answer-accept-status">
            <img
              src="https://img.icons8.com/?size=100&id=Ri1uVwXhVKOJ&format=png&color=000000"
              className="answer-accept-img"
            />
            채택된 답변
          </div>
        ) : (
          <div></div>
        )}

        {memberCookie && memberCookie.memberNo == inquiry.memberNo ? (
          <div className="answer-accept">
            <button onClick={handleAccept}>채택하기</button>
          </div>
        ) : (
          <div></div>
        )}
        {/* 신고하기버튼 */}
        {/* 신고하기버튼 */}
        {/* 신고하기버튼 */}
        {/* 신고하기버튼 */}
        {/* 신고하기버튼 */}
        <div className="answer-report-btn">
          <button>신고하기</button>
        </div>
        {/* 신고하기버튼 */}
        {/* 신고하기버튼 */}
        {/* 신고하기버튼 */}
        {/* 신고하기버튼 */}
        {/* 신고하기버튼 */}
        {/* 신고하기버튼 */}
        <button className="answer-member" onClick={togglePopup}>
          {answer.memberName}
        </button>
        {/* 팝업 창 */}
        {isPopupVisible && (
          <AnswerProfilePopup key = {answer.answerNo} memberNo= {answer.memberNo}className="popup"/>
        )}
        <div className="answer-content">{answer.answerContent}</div>
        <div className="answer-date">{answer.answerDate.substring(0, 10)}</div>
        <div className="answer-vote">
          <button onClick={handleUpvote}>추천</button>
          {voteCount}
          <button onClick={handleDownvote}>비추천</button>
        </div>
        {memberCookie && memberCookie.memberNo == answer.memberNo ? (
          <div className="modify-delete-btn">
            <button onClick={handleModify}>수정</button>

            <button
              onClick={(e) => {
                e.preventDefault(); // 폼 제출 방지
              }}
            >
              삭제
            </button>
          </div>
        ) : (
          <div></div>
        )}
      </div>
    </>
  );
}
