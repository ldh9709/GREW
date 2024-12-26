import React, { useEffect, useRef, useState } from "react";
import * as answerApi from "../../api/answerApi";
import { useNavigate, useParams } from "react-router-dom";
import { useMemberAuth } from "../../util/AuthContext";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faEye } from "@fortawesome/free-regular-svg-icons";
import * as inquiryApi from "../../api/inquiryApi";

export default function AnswerModifyFormPage() {

  const { answerNo } = useParams();
  const {token, member} = useMemberAuth();
  const modifyFormRef = useRef();
  const navigate = useNavigate();
  const initAnswer = {
    answerNo: 0,
    answerContent: "",
    answerDate: "",
    answerStatus: 1,
    answerAccept: 1,
    memberNo: member.memberNo,
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
      if (member.memberNo != responseJsonObject.data.memberNo) {
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

  const answerModifyAction = async () => {
    console.log('answer',answer)
    const responseJsonObject = await answerApi.updateAnswer(answer,token);
    console.log(responseJsonObject);
    navigate(`/inquiry/${answer.inquiryNo}`);
  };

  const answerRemoveAction = async () => {
    try {
      const confirmation = window.confirm('답변을 삭제하시겠습니까?')
      if (!confirmation) return;
    
      const responseJsonObject = await answerApi.deleteAnswer(answerNo, token);
      if (responseJsonObject.status === 6300) {
        navigate("/inquiry");
      }
    } catch (error) {
      console.log(error)
    }
  };
  
  return (
    <>
      <div>
        <div style={{ paddingLeft: 10 }}>
          <input type="hidden" name="inquiryNo" value={inquiry.inquiryNo} />

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
        </div>
      </div>
      <div className="inquiry-answer-form">
        <form ref={modifyFormRef} method="POST" className="inquiry-form">
          <div>
            <textarea
              className="answer-textarea"
              name="answerContent"
              onChange={onChangeAnswerForm}
              value={answer.answerContent}
              placeholder="내용을 입력하세요"
              required
            />
          </div>
          <div className="answer-write-btn-container">
            <input
              className="answer-write-btn"
              type="button"
              value="수정"
              onClick={answerModifyAction}
              id="btn_answer_modify_action"
            />
            <input
              className="answer-write-btn"
              type="button"
              value="삭제"
              onClick={answerRemoveAction}
              id="btn_answer_modify_action"
            />
          </div>
        </form>
      </div>
    </>
  );
}
