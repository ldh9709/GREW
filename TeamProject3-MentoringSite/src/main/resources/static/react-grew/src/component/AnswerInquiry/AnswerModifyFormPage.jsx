import React, { useEffect, useRef, useState } from "react";
import * as answerApi from "../../api/answerApi";
import { useNavigate, useParams } from "react-router-dom";
import { getCookie } from "../../util/cookieUtil";
export default function AnswerModifyFormPage() {
  const memberCookie = getCookie("member");
  const modifyFormRef = useRef();
  const navigate = useNavigate();
  const initAnswer = {
    answerNo: 1,
    answerContent: "",
    answerDate: "",
    answerStatus: 1,
    answerAccept: 1,
    memberNo: 1,
    inquiryNo: 1,
  };
  const initInquiry = {
    inquiryNo: 0,
    inquiryTitle: "",
    inquiryContent: "",
    inquiryDate: "",
    inquiryState: 1,
    inquiryViews: 0,
    categoryName: "",
    categoryNo: 0,
    memberNo: 1,
    memberName: "",
  };
  const [answer, setAnswer] = useState(initAnswer);
  const [inquiry, setInquiry] = useState(initInquiry);
  const { answerNo } = useParams();

  // 질문 데이터 가져오는 함수
  useEffect(() => {
    const fetchInquiryData = async () => {
      try {
        const response = await answerApi.findInquiry(answer.inquiryNo);
        console.log(response.data);

        setInquiry(response.data); // 받아온 데이터를 상태로 설정
      } catch (error) {
        console.error("Error fetching inquiry data", error);
      }
    };

    fetchInquiryData();
  }, [answer.inquiryNo]); // inquiryNo가 변경될 때마다 실행
  useEffect(() => {
    const a = async () => {
      const responseJsonObject = await answerApi.viewAnswer(answerNo);
      console.log(responseJsonObject.data);
      if (memberCookie.memberNo != responseJsonObject.data.memberNo) {
        navigate("/403");
      }
      setAnswer(responseJsonObject.data);
    };
    a();
  }, [answerNo]);

  const onChangeAnswerForm = (e) => {
    setAnswer({
      ...answer,
      [e.target.name]: e.target.value,
    });
  };

  const answerModifyAction = async (e) => {
    const responseJsonObject = await answerApi.updateAnswer(answer);
    console.log(responseJsonObject);
    navigate(`/inquiry/${answer.inquiryNo}`);
  };
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <div className="inquiry-container">
        <div>
          <div className="inquiry-title">{inquiry.inquiryTitle}</div>
        </div>
        <div className="inquiry-desc">
          <div>
            {inquiry.memberName.slice(0, 1) + "*" + inquiry.memberName.slice(2)}{" "}
            | 조회수 {inquiry.inquiryViews} |{" "}
            {inquiry.inquiryDate.substring(0, 10)}
          </div>
          <br />
          <div>{inquiry.categoryName}</div>
        </div>
        <br />
        <br />
        <div className="inquiry-content">
          <div>{inquiry.inquiryContent}</div>
        </div>

        <br />
      </div>
      <div>
        <form ref={modifyFormRef} method="POST" className="inquiry-form">
          <div>
            <textarea
              name="answerContent"
              onChange={onChangeAnswerForm}
              value={answer.answerContent}
              placeholder="내용을 입력하세요"
              required
            />
          </div>
          <div className="inquiry-write-btn">
            <input
              type="button"
              value="수정"
              onClick={answerModifyAction}
              id="btn_answer_modify_action"
            />
          </div>
        </form>
      </div>
    </>
  );
}
