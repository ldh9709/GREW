import React, { useEffect, useRef, useState } from "react";
import * as answerApi from "../../api/answerApi";
import { useNavigate, useParams } from "react-router-dom";
import { useMemberAuth } from "../../util/AuthContext";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faEye } from "@fortawesome/free-regular-svg-icons";

export default function AnswerWriteFormPage() {
  const {token, member} = useMemberAuth();
  const writeFormRef = useRef();
  const navigate = useNavigate();
  const { inquiryNo } = useParams();
  const initAnswer = {
    answerNo: 0,
    answerContent: "",
    answerDate: "",
    answerStatus: 1,
    answerAccept: 1,
    memberNo: 1,
  };
  const initInquiry = {
    inquiryNo: 0,
    inquiryTitle: "",
    inquiryContent: "",
    inquiryDate: "",
    inquiryState: 1,
    inquiryViews: 0,
    categoryNo: 0,
    memberNo: 1,
    memberName: "",
  };
  const [answer, setAnswer] = useState(initAnswer);
  const [inquiry, setInquiry] = useState(initInquiry); // inquiry 데이터를 저장할 상태

  // 질문 데이터 가져오는 함수
  useEffect(() => {
    const fetchInquiryData = async () => {
      try {
        const response = await answerApi.findInquiry(inquiryNo);
        console.log(response.data);
        setInquiry(response.data); // 받아온 데이터를 상태로 설정
      } catch (error) {
        console.error("Error fetching inquiry data", error);
      }
    };

    fetchInquiryData();
  }, [inquiryNo]); // inquiryNo가 변경될 때마다 실행

  const onChangeAnswerForm = (e) => {
    setAnswer({
      ...answer,
      [e.target.name]: e.target.value,
    });
    console.log(answer);
  };

  const answerWriteAction = async (e) => {
    // answerContent가 비어있는지 확인
    if (!answer.answerContent.trim()) {
      alert("답변 내용을 입력해주세요."); // 사용자에게 입력을 요구하는 알림을 띄움
      return; // 폼 제출을 막음
    }
    const responseJsonObject = await answerApi.writeAnswer(
      answer,
      inquiryNo,
      token
    );
    console.log(responseJsonObject);
    navigate(`/inquiry/${inquiryNo}`);
  };
  return (
    <>
      <div className="inquiry-container-inview">
        <div className="inquiry-view-category">{inquiry.categoryName}</div>
        <div className="inquiry-view-title">
          <span>Q.</span>
          <span>{inquiry.inquiryTitle}</span>
        </div>
        <div className="inquiry-view-desc">
          <div>
            {inquiry.memberName} 멘티ㆍ
            {inquiry.inquiryDate.substring(0, 10)}ㆍ
            <FontAwesomeIcon icon={faEye}/> {inquiry.inquiryViews}
          </div>
        </div>
        <div className="inquiry-view-content">
          {inquiry.inquiryContent}
        </div>
      </div>

      <div>
        <form ref={writeFormRef}  className="inquiry-form">
          <div>
            <textarea
              name="answerContent"
              onChange={onChangeAnswerForm}
              value={answer.answerContent}
              placeholder="답변을 입력해주세요."
              required
            />
          </div>
          <div className="answer-write-btn-container">
            <input
              className="answer-write-btn"
              type="button"
              value="답변등록"
              onClick={answerWriteAction}
              id="btn_inquiry_write_action"
            />
          </div>
        </form>
      </div>
    </>
  );
}
