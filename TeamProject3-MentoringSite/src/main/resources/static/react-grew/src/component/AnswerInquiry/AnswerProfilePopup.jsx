import React, { useEffect, useState } from 'react'
import * as answerApi from "../../api/answerApi"
import * as memberApi from "../../api/memberApi"
export default function AnswerProfilePopup({memberNo}) {

const [mentorProfile, setMentorProFile] = useState([]);
const [member,setMember] = useState([]);
useEffect(()=>{
    fetchMentorProfile();
    fetchMember();
},[]);

const fetchMember = async()=>{
    const response = await memberApi.getMemberByMemberNo(memberNo);
    console.log(response);
    setMember(response.data);
}
const fetchMentorProfile = async()=>{
    const response = await answerApi.getMentorProfileByMemberNo(memberNo);
    console.log(response);
    setMentorProFile(response.data);
}
  return (
    <div className="popup">
    <div>{member.memberName} 멘토</div>
    <br/>
    <div>한줄소개 : {mentorProfile.mentorIntroduce}</div>
  </div>
  )
}
