import React, { useEffect, useState } from 'react'
import { useParams } from "react-router-dom";
import "../../../css/styles.css"
import "../../../css/member.css";
import MemberTabs from './MemberTabs'
import MemberSummary from './MemberSummary'
import * as memberApi from "../../../api/memberApi"

export default function MemberMypage() {
  const { memberNo } = useParams();
  const [isDataReady, setIsDataReady] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        await memberApi.memberCountSummary(memberNo);
        setIsDataReady(true);
      } catch (error) {
        console.log("데이터 로드 실패", error);
      }
    };
    fetchData();
  },[memberNo])

  // 데이터가 준비되기 전까지 아무것도 렌더링하지 않음
  if (!isDataReady) {
    return null; // 아무것도 렌더링하지 않음
  }

  return (
    <div>
        <MemberSummary memberNo={memberNo} />
        <MemberTabs memberNo={memberNo}/>
    </div>
  )
}
