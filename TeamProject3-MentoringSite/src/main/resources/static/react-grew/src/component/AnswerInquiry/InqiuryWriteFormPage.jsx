import React, { useRef, useState } from 'react'
import * as inquiryApi from "../../api/inquiryApi";
import { useNavigate } from 'react-router-dom';
export default function InqiuryWriteFormPage() {
  const writeFormRef = useRef();
  const navigate = useNavigate();
  const initInquiry ={
    inquiryNo:0,
    inquiryTitle:"",
    inquiryContent:"",
    inquiryDate:"",
    inquiryState:1,
    inquiryViews:0,
    categoryNo:2,
    memberNo:1,
  }
  
  const [inquiry,setInquiry] = useState(initInquiry);

  const onChangeInquiryForm=(e)=>{
    setInquiry({
      ...inquiry,
      [e.target.name]:e.target.value
    });
    console.log(inquiry);
  }

  const inquiryWriteAction=async(e)=>{
    const responseJsonObject = await inquiryApi.writeInquiry(inquiry);
    console.log(responseJsonObject.data);
    navigate(`/inquiry/${responseJsonObject.data.inquiryNo}`)
  }
  return (
    <div>
      <form ref={writeFormRef} method="POST" className='inquiry-form'>
    <div>
      <div>
        질문등록
      </div>
      <input
        type="text"
        name="inquiryTitle" 
        onChange={onChangeInquiryForm}
        value={inquiry.inquiryTitle}
        placeholder="제목을 입력하세요"
        required/>
    </div>
    <div>
      <textarea 
        name="inquiryContent" 
        onChange={onChangeInquiryForm}
        value={inquiry.inquiryContent}
        placeholder="내용을 입력하세요"
        required/>
    </div>
    <div className='inquiry-write-btn'>
      <input
      type='button'
      value='질문등록'
      onClick={inquiryWriteAction}
      id='btn_inquiry_write_action'
      />
    </div>
    </form>
    </div>
  );
}