import React, { useEffect, useRef, useState } from "react";
import * as inquiryApi from "../../api/inquiryApi";
import { useNavigate, useParams } from "react-router-dom";
export default function InqiuryModifyFormPage() {
  const modifyFormRef = useRef();
  const navigate = useNavigate();
  const initInquiry = {
    inquiryNo: 0,
    inquiryTitle: "",
    inquiryContent: "",
    inquiryDate: "",
    inquiryState: "",
    inquiryViews: "",
    categoryNo: "",
    memberNo: "",
  };
  const [inquiry, setInquiry] = useState(initInquiry);
  const { inquiryNo } = useParams();
  useEffect(() => {
    const a = async () => {
      const responseJsonObject = await inquiryApi.viewInquiry(inquiryNo);
      setInquiry(responseJsonObject.data);
    };
    a();
  }, [inquiryNo]);

  const onChangeInquiryForm = (e) => {
    setInquiry({
      ...inquiry,
      [e.target.name]: e.target.value,
    });
  };

  const inquiryModifyAction = async (e) => {
    const responseJsonObject = await inquiryApi.updateInquiry(inquiry);
    console.log(responseJsonObject);
    navigate(`/inquiry/${inquiryNo}`);
  };
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <div>
        <form ref={modifyFormRef} method="POST" className="inquiry-form">
          <div>
            <input
              type="text"
              name="inquiryTitle"
              onChange={onChangeInquiryForm}
              value={inquiry.inquiryTitle}
              placeholder="내용을 입력하세요"
              required
            />
          </div>
          <div>
            <textarea
              name="inquiryContent"
              onChange={onChangeInquiryForm}
              value={inquiry.inquiryContent}
              placeholder="내용을 입력하세요"
              required
            />
          </div>
          <div className="inquiry-write-btn">
            <input
              type="button"
              value="수정"
              onClick={inquiryModifyAction}
              id="btn_inquiry_write_action"
            />
          </div>
        </form>
      </div>
    </>
  );
}
