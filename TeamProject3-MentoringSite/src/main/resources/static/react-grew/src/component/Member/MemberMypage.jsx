import React from 'react'
import { useParams } from "react-router-dom";
import "../../css/member.css";
import MemberTabs from './MemberTabs'
import MemberSummary from './MemberSummary'

export default function MemberMypage() {
    const { memberNo } = useParams();

  return (
    <div>
        <MemberSummary memberNo={memberNo} />
        <MemberTabs memberNo={memberNo}/>
    </div>
  )
}
