import React, { useEffect, useRef, useState } from 'react'
import * as inquiryApi from "../api/inquiryApi";
import { useNavigate, useParams } from 'react-router-dom';
export default function InqiuryModifyFormPage() {
  const modifyFormRef = useRef();
  const navigate = useNavigate();
  const initInquiry ={
    inquiryNo:0,
    inquiryTitle:"",
    inquiryContent:"",
    inquiryDate:"",
    inquiryState:"",
    inquiryViews:"",
    categoryNo:"",
    memberNo:"",
  }
  const [inquiry,setInquiry] = useState(initInquiry);
  const {inquiryNo} = useParams();
  useEffect(()=>{

    const a = async()=>{
      const responseJsonObject = await inquiryApi.viewInquiry(inquiryNo);
      setInquiry(responseJsonObject.data);
    };
    a();
  },[inquiryNo]);

  const onChangeInquiryForm=(e)=>{
    setInquiry({
      ...inquiry,
      [e.target.name]:e.target.value
    })
  }

  const inquiryModifyAction=async (e)=>{
    const responseJsonObject = await inquiryApi.updateInquiry(inquiry);
    console.log(responseJsonObject);
    navigate(`/inquiry/${inquiryNo}`);
  }
  return (
    <div>
      <form ref={modifyFormRef} method="POST">
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
      value='수정'
      onClick={inquiryModifyAction}
      id='btn_inquiry_write_action'
      />
    </div>
    </form>
    </div>
  );
}