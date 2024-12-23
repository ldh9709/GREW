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
    const response = await memberApi.getMemberByMemberNo(mentorProfile.memberNo);
    console.log(response);
    setMember(response);
}
const fetchMentorProfile = async()=>{
    const response = await answerApi.getMentorProfileByMemberNo(memberNo);
    console.log(response);
    setMentorProFile(response.data);
}
  return (
    <div className="popup">
    <div>{member.memberName}</div>
    <div>{mentorProfile.memberNo}</div>
    <div>{mentorProfile.mentorIntroduce}</div>
  </div>
  )
}
