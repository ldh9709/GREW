import React, { useEffect, useRef, useState } from "react";
import * as inquiryApi from "../../api/inquiryApi";
import { useNavigate, useParams } from "react-router-dom";
import { useMemberAuth } from "../../util/AuthContext";
export default function InqiuryModifyFormPage() {
  const {token, member} = useMemberAuth();
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
      if(member.memberNo!=responseJsonObject.data.memberNo){
        navigate('/403')
    };
    }
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
    const responseJsonObject = await inquiryApi.updateInquiry(inquiry,token);
    console.log(responseJsonObject);
    navigate(`/inquiry/${inquiryNo}`);
  };

  const inquiryRemoveAction = async () => {
    if (!window.confirm('질문을 삭제하시겠습니까?')) return;
    const responseJsonObject = await inquiryApi.deleteInquiry(inquiryNo,token);
    if (responseJsonObject.status === 5200) {
      navigate("/inquiry");
    } else {
      alert("실패");
    }
  };
  
  return (
    <>
      <div>
        <form ref={modifyFormRef} method="POST" className="inquiry-form">
        <h1>질문등록</h1>
          <div>
            <div>
              <div>카테고리</div>
              <div className="inquiry-modify-category">
                {inquiry.categoryName}
              </div>
              </div>
            <input
              className="inquiry-form-title"
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
          <div className="inquiry-write-btn-container">
            <input
              className="inquiry-write-btn"
              type="button"
              value="수정"
              onClick={inquiryModifyAction}
              id="btn_inquiry_write_action"
            />
            <input
              className="inquiry-write-btn"
              type="button"
              value="삭제"
              onClick={inquiryRemoveAction}
              id="btn_inquiry_write_action"
            />
          </div>
        </form>
      </div>
    </>
  );
}
