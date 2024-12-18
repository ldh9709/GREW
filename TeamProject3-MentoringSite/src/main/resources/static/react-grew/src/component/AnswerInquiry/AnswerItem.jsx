import React, { useEffect, useState } from "react";
import "../../css/styles.css";
import * as answerApi from "../../api/answerApi";
import { Link } from "react-router-dom";
export default function AnswerItem({ answer }) {
  const [voteCount, setVoteCount] = useState(null);
  async function fetchData() {
    try {
      // 비동기 호출
      const responseJsonObject = await answerApi.countVote(answer.answerNo);
      setVoteCount(responseJsonObject.data);
    } catch (error) {
      console.error("오류 발생:", error);
    }
  }
  useEffect(() => {
    fetchData();
  }, [voteCount]);

  const handleUpvote = async () => {
    try {
      const response = await answerApi.upVote(answer.answerNo, 1); // API 호출
      if (response.status === 6000) {
        fetchData(); // 추천 성공 상태 확인
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
      const response = await answerApi.downVote(answer.answerNo, 1); // API 호출
      if (response.status === 6000) {
        fetchData(); // 추천 성공 상태 확인
      } else {
        alert("이미 추천 혹은 비추천을 누르셨습니다"); // 실패 시 에러 로그
      }
    } catch (error) {
      console.error("API 호출 중 오류 발생:", error); // 오류 처리
    }
  };
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <div className="answer-container">
        {/* 질문작성자만 보이는조건 */}
        <div className="answer-accept">
          <button>채택하기</button>
        </div>
        {/* 질문작성자만 보이는조건 */}

        <div className="answer-member">{answer.memberName}</div>
        <div className="answer-content">{answer.answerContent}</div>
        <div className="answer-date">{answer.answerDate.substring(0, 10)}</div>
        <div className="answer-vote">
          <button onClick={handleUpvote}>추천</button>
          {voteCount}
          <button onClick={handleDownvote}>비추천</button>
        </div>
        {/* 답변작성자에게만 보이는조건 */}
        <div>
          <Link to={`/answer/modify/${answer.answerNo}`}>
            <button>수정</button>
          </Link>

          <button
            onClick={(e) => {
              e.preventDefault(); // 폼 제출 방지
            }}
            >
            삭제
          </button>
        </div>
            {/* 답변작성자에게만 보이는조건 */}
      </div>
    </>
  );
}
