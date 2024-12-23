import { getCookie } from "../../util/cookieUtil";
import React, { useEffect, useState } from 'react'
import * as adminApi from '../../api/adminApi'

export default function AdminInquiry() {//질문게시글
  
  const boardCookie = getCookie("board");
  const token = boardCookie.accessToken;

  const [boards,setBoard] = useState([]);
  const []=useState("ALL");

  const getInquiry = async ()=>{// -전체조회

  }

  
  // -삭제
  // -겅색어조회
  // -카테고리(번호2번 뭔지 찾아야함)*/
  

  


  
}