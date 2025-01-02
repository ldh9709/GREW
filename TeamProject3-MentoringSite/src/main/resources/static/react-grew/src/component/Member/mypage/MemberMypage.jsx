import React, { useEffect, useState } from 'react'
import MemberSummary from './MemberSummary'
import { useNavigate, useParams } from "react-router-dom";
import "../../../css/styles.css"
import "../../../css/member.css";
import MemberTabs from './MemberTabs'


export default function MemberMypage() {
  const [summary, setSummary] = useState(0);

  const handleUpdate = () => {
    setSummary((prev) => prev + 1);
  };

  return (
    <div>
      <MemberSummary key={summary}/>
      <MemberTabs handleUpdate={handleUpdate} />
    </div>
  )
}
