import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import * as inquiryApi from "../../api/inquiryApi";
import * as answerApi from "../../api/answerApi";
import AnswerItem from "./AnswerItem";
function InquiryView() {
  const navigate = useNavigate();
  const { inquiryNo } = useParams(); //path에서 받아오는거임! app.js의 경로와 관련있음
  const [inquiry, setInquiry] = useState({
    inquiryNo: 0,
    inquiryTitle: "",
    inquiryContent: "",
    inquiryDate: "",
    memberName: "",
  });
  const [answer, setAnswer] = useState([]);
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
  const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
  const [itemsPerPage] = useState(5); // 페이지당 항목 수 (예: 한 페이지에 5개 항목)
  const [sortType, setSortType] = useState("latest"); // 기본적으로 'latest'로 설정
  useEffect(() => {
    (async () => {
      const responseJsonObject = await inquiryApi.viewInquiry(inquiryNo);
      console.log(responseJsonObject);
      if (
        responseJsonObject.status === 5500 &&
        responseJsonObject.data.inquiryStatus === 1
      ) {
        setInquiry(responseJsonObject.data);
      } else {
        alert("게시물이 존재하지 않습니다");
        navigate("/inquiry");
      }
    })();
  }, [inquiryNo, navigate]);
  
    const fetchAnswers = async (inquiryNo,page, size, sortButton) => {
      try {
        let responseJsonObject;
        if(sortButton==="latest"){
        responseJsonObject = await answerApi.AnswerByDate(
          inquiryNo,
          page,
          size,
        )}else if(sortButton==="vote"){
          responseJsonObject =await answerApi.AnswerByVote(
            inquiryNo,
            page,
            size,
          )
        }
        console.log(responseJsonObject.data);
        setAnswer(responseJsonObject.data.content); // 답변 목록 상태에 저장
        setTotalPages(responseJsonObject.data.totalPages);
      } // 새로운 API로 답변 목록 가져오기
       catch (error) {
        console.error("답변 목록 가져오기 실패:", error);
      }
    };
  

  
  //질문삭제
  const inquiryRemoveAction = async () => {
    const responseJsonObject = await inquiryApi.deleteInquiry(inquiryNo);
    console.log(responseJsonObject);
    if (responseJsonObject.status === 5200) {
      navigate("/inquiry");
    } else {
      alert("실패");
    }
  };
  
  
  // 페이지 변경 시 데이터 갱신
  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  // 페이지 로드 시 데이터 가져오기
  useEffect(() => {
    fetchAnswers(inquiryNo,currentPage - 1, itemsPerPage, sortType);
  }, [currentPage, sortType]);
  // 버튼 클릭시 호출 함수
  const handleRadioChange = (e) => {
    setSortType(e.target.value); //버튼 밸류로 sorttype변경
  };
  // 페이지 번호 버튼 표시
  const pageNumbers = [];
  for (let i = 1; i <= totalPages; i++) {
    pageNumbers.push(i);
  }
  return (
    <>
      <table
        style={{ paddingLeft: 10 }}
        border="0"
        cellPadding="0"
        cellSpacing="0"
      >
        <tbody>
          <tr>
            <td bgcolor="f4f4f4" height="22">
              &nbsp;&nbsp;<b>질문</b>
            </td>
          </tr>
        </tbody>
      </table>
      <form name="f" method="post">
        <input type="hidden" name="inquiryNo" value={inquiry.inquiryNo} />
        <table
          border="0"
          cellPadding="0"
          cellSpacing="1"
          width="300"
          bgcolor="BBBBBB"
        >
          <tbody>
            <tr>
              <td width="100" align="center" bgcolor="white" height="22">
                번호
              </td>
              <td
                width="490"
                bgcolor="ffffff"
                align="left"
                style={{ paddingLeft: 10 }}
              >
                {inquiry.inquiryNo}
              </td>
            </tr>
            <tr>
              <td width="100" align="center" bgcolor="white" height="22">
                이름
              </td>
              <td
                width="490"
                bgcolor="ffffff"
                align="left"
                style={{ paddingLeft: 10 }}
              >
                {inquiry.memberName}
              </td>
            </tr>
            <tr>
              <td width="100" align="center" bgcolor="white" height="22">
                날짜
              </td>
              <td
                width="490"
                bgcolor="ffffff"
                align="left"
                style={{ paddingLeft: 10 }}
              >
                {inquiry.inquiryDate.substring(0, 10)}
              </td>
            </tr>
            <tr>
              <td width="100" align="center" bgcolor="white" height="22">
                제목
              </td>
              <td
                width="490"
                bgcolor="ffffff"
                align="left"
                style={{ paddingLeft: 10 }}
              >
                {inquiry.inquiryTitle}
              </td>
            </tr>
            <tr>
              <td width="100" align="center" bgcolor="ffffff" height="110">
                내용
              </td>
              <td
                width="490"
                bgcolor="ffffff"
                align="left"
                style={{ paddingLeft: 10 }}
              >
                {inquiry.inquiryContent}
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      
      <br />
      <table width="590" border="0" cellPadding="0" cellSpacing="0">
        <tbody>
          <tr>
            <td align="center">
              <Link to={`/inquiry/modify/${inquiryNo}`}>
                <input type="button" value="수정" />
              </Link>
              &nbsp;
              <input type="button" value="삭제" onClick={inquiryRemoveAction} />
              &nbsp;{" "}
              <Link to={"/inquiry"}>
                <input type="button" value="목록" />
              </Link>
            </td>
          </tr>
        </tbody>
      </table>
      <table
        border="0"
        cellPadding="0"
        cellSpacing="0"
        style={{ marginTop: "20px" }}
      >
        <tbody>
          <tr>
            <td>
              <br />
              <div className="radio-container">
        {/* 라디오 버튼 */}
        <label>
          <input
            type="radio"
            name="sortType"
            value="latest"
            checked={sortType === "latest"}
            onChange={handleRadioChange}
          />
          최신순
        </label>
        <label>
          <input
            type="radio"
            name="sortType"
            value="vote"
            checked={sortType === "vote"}
            onChange={handleRadioChange}
          />
          추천순
        </label>
      </div>
             
                    {answer.map((answer) => (
                      <AnswerItem
                        key={answer.answerNo}
                        answer={answer}
                      /> //한 질문에 대한 답변(조회수 정렬)
                    ))}
                  
             
            </td>
          </tr>
        </tbody>
      </table>
      {/* 페이지네이션 버튼 */}
      <div className="pagenation">
        {pageNumbers.map((number) => (
          <button key={number} onClick={() => paginate(number)}>
            {number}
          </button>
        ))}
      </div>
    </>
  );
}
export default InquiryView;
