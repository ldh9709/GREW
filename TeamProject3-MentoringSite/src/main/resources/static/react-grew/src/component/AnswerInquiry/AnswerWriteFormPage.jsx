import React, { useEffect, useRef, useState } from "react";
import * as answerApi from "../../api/answerApi";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios"; // axios를 사용하여 API 호출
export default function AnswerWriteFormPage() {
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
    memberName:""
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
    const responseJsonObject = await answerApi.writeAnswer(answer, inquiryNo);
    console.log(responseJsonObject);
    navigate(`/inquiry/${inquiryNo}`);
  };
  return (
<>
   <div className="inquiry-container">
                <div>
                  <div className="inquiry-title">{inquiry.inquiryTitle}</div>
                </div>
                <div className="inquiry-desc">
                  <div>
                    {inquiry.memberName.slice(0, 1) +
                      "*" +
                      inquiry.memberName.slice(2)}{" "}
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
      <form ref={writeFormRef} method="POST" className="inquiry-form">
        <div>
          <div>답변등록</div>
        </div>
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
