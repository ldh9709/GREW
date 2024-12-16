import React, { useEffect, useState } from "react";
import "../css/styles.css";
import * as answerApi from "../api/answerApi";
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
    }, []);

    const handleUpvote = async () => {
      try {
        const response = await answerApi.upVote(answer.answerNo, 6);  // API 호출
        if (response.status === 6000) {  // 추천 성공 상태 확인
          console.log("성공");
        } else {
          console.error('추천 실패:', response.message);  // 실패 시 에러 로그
        }
      } catch (error) {
        console.error('API 호출 중 오류 발생:', error);  // 오류 처리
      }
    };
    return (
      <>
        <div className="answer-member">{answer.memberName}</div>
        <div className="answer-content">{answer.answerContent}</div>
        <div className="answer-date">{answer.answerDate.substring(0, 10)}</div>
        <button onClick={handleUpvote}>추천
        </button>
           {voteCount}   
        <button>비추천
        </button>
      </>
    );
  }
