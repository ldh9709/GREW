import React, { useRef, useState } from 'react'
import * as inquiryApi from "../api/inquiryApi";
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
      <form ref={writeFormRef} method="POST">
    <div>
      제목:
      <input
        type="text"
        name="inquiryTitle" 
        onChange={onChangeInquiryForm}
        value={inquiry.inquiryTitle}
        required/>
    </div>
    <div>
      내용:
      <input 
        type="text" 
        name="inquiryContent" 
        onChange={onChangeInquiryForm}
        value={inquiry.inquiryContent}
        required/>
    </div>
    <div>
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