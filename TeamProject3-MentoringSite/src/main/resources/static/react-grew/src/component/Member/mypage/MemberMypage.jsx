import React, { useEffect, useState } from 'react'
import MemberSummary from './MemberSummary'
import { useNavigate, useParams } from "react-router-dom";
import "../../../css/styles.css"
import "../../../css/member.css";
import MemberTabs from './MemberTabs'


export default function MemberMypage() {
  const [triggerUpdate, setTriggerUpdate] = useState(0);

  const handleUpdate = () => {
    setTriggerUpdate((prev) => prev + 1);
  };

  return (
    <div>
      <MemberSummary triggerUpdate={triggerUpdate}/>
      <MemberTabs handleUpdate={handleUpdate} />
    </div>
  )
}
