import { useMemberAuth } from "../../../util/AuthContext";
import React, { useEffect, useState } from "react";
import * as inquiryApi from "../../../api/inquiryApi";
import * as answerApi from "../../../api/answerApi";
import { useNavigate } from "react-router-dom";
import PagenationItem from "../../PagenationItem";

export default function MemberInquiryAnswerList() {
  /* Context에 저장된 토큰, 멤버정보 */
  const { token, member } = useMemberAuth();

  const [dataList, setdataList] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const navigate = useNavigate();

  const fetchInquiryAnswerList = async (page) => {
    try {
      let response;
      if (member.memberRole === "ROLE_MENTEE") {
        response = await inquiryApi.listInquiryByMemberNo(token, page);
        console.log(response);
        setdataList(response.data.content);
        setTotalPages(response.data.totalPages);
      } else if (member.memberRole === "ROLE_MENTOR") {
        response = await answerApi.listAnswerByMemberNo(token, page);
        console.log(response);
        const updateAnswers = await Promise.all(
          response.data.content.map(async (answer) => {
            const inquiryResponse = await inquiryApi.viewInquiry(
              answer.inquiryNo
            );
            const voteResponse = await answerApi.countVote(answer.answerNo);
            return {
              ...answer,
              inquiryTitle: inquiryResponse.data.inquiryTitle,
              vote: voteResponse.data,
            };
          })
        );

        setdataList(updateAnswers);
        setTotalPages(response.data.totalPages);
      }
    } catch (error) {
      console.log("내가 쓴 질문 또는 답변 리스트 조회 실패", error);
    }
  };

  useEffect(() => {
    fetchInquiryAnswerList(currentPage - 1);
  }, [currentPage, token]);

  // 페이지 변경 시 데이터 갱신
  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  // 페이지 번호 버튼 표시
  const pageNumbers = [];
  for (let i = 1; i <= totalPages; i++) {
    pageNumbers.push(i);
  }

  return (
    <>
      <div className="tab-content tab-inquiry" id="inquiry">
        {dataList.length === 0 ? (
          <p> 작성 내용이 없습니다.</p>
        ) : member.memberRole === "ROLE_MENTEE" ? (
          <table className="list-table">
            <thead>
              <tr>
                <th className="col-no">번호</th>
                <th className="col-category">카테고리</th>
                <th className="col-title">제목</th>
                <th className="col-date">작성일자</th>
                <th className="col-views">조회수</th>
              </tr>
            </thead>
            <tbody>
              {/* 질문 리스트 map으로 반복 */}
              {dataList && dataList.length > 0 ? (
                dataList.map((inquiry, index) => (
                  <tr
                    key={index}
                    onClick={() => {
                      navigate(`/inquiry/${inquiry.inquiryNo}`);
                    }}
                  >
                    <td className="col-no">{index + 1}</td>
                    <td className="col-category">
                      {inquiry.parentsCategoryName}
                    </td>
                    <td className="col-title">{inquiry.inquiryTitle}</td>
                    {inquiry.inquiryDate ? (
                      <td className="col-date">
                        {inquiry.inquiryDate.substring(0, 10)}
                      </td>
                    ) : (
                      <td>-</td>
                    )}
                    <td className="col-views">{inquiry.inquiryViews}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="5" style={{ textAlign: "center" }}>
                    질문내용이 없습니다
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        ) : (
          <table className="list-table">
            <thead>
              <tr>
                <th className="col-no">번호</th>
                <th className="col-inquiry-title">질문 글</th>
                <th className="col-answer-content">댓글 작성 내용</th>
                <th className="col-answer-likes">추천 수</th>
              </tr>
            </thead>
            <tbody>
              {/* 질문 리스트 map으로 반복 */}
              {dataList && dataList.length > 0 ? (
                dataList.map((answer, index) => (
                  <tr
                    key={index}
                    onClick={() => {
                      navigate(`/inquiry/${answer.inquiryNo}`);
                    }}
                  >
                    <td className="col-no">{index + 1}</td>
                    <td className="col-inquiry-title">{answer.inquiryTitle}</td>
                    <td className="col-views">
                      {answer.answerContent
                        ? answer.answerContent.length > 50
                          ? `${answer.answerContent.substring(0, 50)}...`
                          : answer.answerContent
                        : "내용 없음"}
                    </td>
                    <td className="col-likes">{answer.vote}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="4" style={{ textAlign: "center" }}>
                    질문내용이 없습니다
                  </td>
                </tr>
              )}
            </tbody>
            <PagenationItem
              currentPage={currentPage}
              totalPages={totalPages}
              paginate={paginate}
            />
          </table>
        )}
      </div>
    </>
  );
}
