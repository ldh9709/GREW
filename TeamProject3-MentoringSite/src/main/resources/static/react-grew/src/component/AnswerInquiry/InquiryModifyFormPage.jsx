import React, { useEffect, useRef, useState } from "react";
import * as inquiryApi from "../../api/inquiryApi";
import { useNavigate, useParams } from "react-router-dom";
import { getCookie } from "../../util/cookieUtil";
export default function InqiuryModifyFormPage() {
  const memberCookie = getCookie("member");
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
      console.log(responseJsonObject.data);
      if(memberCookie.memberNo!=responseJsonObject.data.memberNo){
        navigate('/403')
    };
    }
    console.log(memberCookie.memberNo)
    a();
  }, [inquiryNo]);

  const onChangeInquiryForm = (e) => {
    setInquiry({
      ...inquiry,
      [e.target.name]: e.target.value,
    });
  };

  const inquiryModifyAction = async (e) => {
    if (!inquiry.inquiryTitle.trim()) {
      alert("제목을 입력해주세요."); // 사용자에게 입력을 요구하는 알림을 띄움
      return; // 폼 제출을 막음
    }
    if (!inquiry.inquiryContent.trim()) {
      alert("내용을 입력해주세요."); // 사용자에게 입력을 요구하는 알림을 띄움
      return; // 폼 제출을 막음
    }
    const responseJsonObject = await inquiryApi.updateInquiry(inquiry);
    console.log(responseJsonObject);
    navigate(`/inquiry/${inquiryNo}`);
  };
  return (
    <>
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
