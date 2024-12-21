import React, { useEffect, useState } from 'react'
import { getCookie } from "../../../util/cookieUtil"
import * as mentorBoardApi from '../../../api/mentorBoardApi'


export default function MemberMentorContentList() {
    const memberCookie = getCookie("member");
    const token = memberCookie.accessToken;

    const [contentsList, setContentsList] = useState([]);

    const fetchContentsList = async(page,size) => {
        const response = await mentorBoardApi.listBoardContentsByMemberNo(token,page,size);
        setContentsList(response.data.content);
    }


useEffect(()=>{
    fetchContentsList(0,3)
},[])

  return (
    <div>

    </div>
  )
}
