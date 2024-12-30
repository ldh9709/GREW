import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import * as inquiryApi from "../../api/inquiryApi";
import * as answerApi from "../../api/answerApi";
import AnswerItem from "./AnswerItem";
import "../../css/styles.css";
import { useMemberAuth } from "../../util/AuthContext";
import ReportModal from "../Report/ReportModal";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye } from "@fortawesome/free-regular-svg-icons";
import { faPen } from "@fortawesome/free-solid-svg-icons";
import PagenationItem from "../PagenationItem";

function InquiryView() {
  const { token, member } = useMemberAuth();
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
  const [isReportHovered, setIsReportHovered] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [report, setreport] = useState({});
  const [isAnswer, setisAnswer] = useState([false]);
  useEffect(() => {
    (async () => {
      const responseJsonObject = await inquiryApi.viewInquiry(inquiryNo);

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
  useEffect(() => {
    if(inquiryNo){
      increaseView(inquiryNo);

    }
  }, []);
  const increaseView = async (inquiryNo) => {
    if (inquiryNo) {
      await inquiryApi.increaseView(inquiryNo);
    }
  };
  const fetchAnswers = async (inquiryNo, page, size, sortButton) => {
    try {
      let responseJsonObject;
      if (sortButton === "latest") {
        responseJsonObject = await answerApi.AnswerByDate(
          inquiryNo,
          page,
          size
        );
      } else if (sortButton === "vote") {
        responseJsonObject = await answerApi.AnswerByVote(
          inquiryNo,
          page,
          size
        );
      }
      setAnswer(responseJsonObject.data.content); // 답변 목록 상태에 저장
      setTotalPages(responseJsonObject.data.totalPages);
    } catch (error) {
      // 새로운 API로 답변 목록 가져오기
      console.error("답변 목록 가져오기 실패:", error);
    }
  };
  //질문에 본인 답변 유무
  const isAnswerByInquiryNo = async () => {
    const responseJsonObject = await answerApi.isAnswerByInquiryNo(
      inquiryNo,
      member.memberNo
    );
    if (member.memberNo && inquiryNo) {
      setisAnswer(responseJsonObject.data);
    }
  };
  useEffect(() => {
    if (member.memberNo && inquiryNo) {
      isAnswerByInquiryNo();
    }
  }, [member.memberNo && inquiryNo]);
  //질문삭제
  const inquiryRemoveAction = async () => {
    if (!window.confirm("질문을 삭제하시겠습니까?")) return;
    const responseJsonObject = await inquiryApi.deleteInquiry(inquiryNo, token);
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
    fetchAnswers(inquiryNo, currentPage - 1, itemsPerPage, sortType);
  }, [currentPage, sortType]);
  // 버튼 클릭시 호출 함수
  const handleRadioChange = (e) => {
    setSortType(e.target.value); //버튼 밸류로 sorttype변경
  };
  // 페이지네이션 버튼 표시 (10개씩 끊어서 표시)
  const pageNumbers = [];
  const pagesToShow = 5; // 한 번에 보여줄 페이지 수
  const startPage =
    Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;
  const endPage = Math.min(startPage + pagesToShow - 1, totalPages);

  for (let i = startPage; i <= endPage; i++) {
    pageNumbers.push(i);
  }
  const handleWriteButton = () => {
    if (!token) {
      alert("로그인이 필요한 서비스입니다.");
      return;
    } else if (member.memberRole == "ROLE_MENTEE") {
      alert("멘토만 답변 작성이 가능합니다");
      return;
    } else if (inquiry.memberNo == member.memberNo) {
      alert("본인의 질문엔 답변을 남길 수 없습니다.");
      return;
    } else if (isAnswer == true) {
      alert("한 질문에 하나의 답변만 작성할 수 있습니다.");
      return;
    } else if (member.memberRole == "ROLE_MENTOR") {
      navigate('/answer/write', {
        state: { inquiryNo: inquiryNo }
      });
    }
  };
  const handleModify = () => {
    navigate('/inquiry/modify', {
      state: { inquiryNo: inquiryNo }
    });
  };

  // 이름 마스킹
  const maskName = (name) => {
    if (name.length <= 2) {
      return name[0] + "*".repeat(name.length);
    }
    return name[0] + "*".repeat(name.length - 2) + name[name.length - 1];
  };

  //신고 하기 창 열고 닫기
  const handleOpenModal = () => {
    setIsModalOpen(true);
    setreport({
      type: "INQUIRY",
      target: inquiry.inquiryNo,
    });
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  return (
    <>
      <div style={{ paddingLeft: 10 }}>
        <input type="hidden" name="inquiryNo" value={inquiry.inquiryNo} />

        <div className="inquiry-container-inview">
          <div className="inquiry-view-category">{inquiry.categoryName}</div>
          <div className="inquiry-view-title">
            <span>Q.</span>
            <span>{inquiry.inquiryTitle}</span>
          </div>
          <div className="inquiry-view-desc">
            <div>
              {maskName(inquiry.memberName)} 멘티ㆍ
              {inquiry.inquiryDate.substring(0, 10)}ㆍ
              <FontAwesomeIcon icon={faEye} /> {inquiry.inquiryViews}
            </div>
          </div>
          <div
            className="inquiry-view-content"
            dangerouslySetInnerHTML={{
              __html: inquiry.inquiryContent.replace(/\n/g, "<br/>"),
            }}
          ></div>

          {member != null && member.memberNo == inquiry.memberNo ? (
            <div className="modify-delete-btn2">
              <button onClick={handleModify}>수정</button>

              <button
                onClick={(e) => {
                  e.preventDefault(); // 폼 제출 방지
                  inquiryRemoveAction(); // 삭제 액션 실행
                }}
              >
                삭제
              </button>
            </div>
          ) : (
            <div></div>
          )}
          {/* 신고하기 버튼 */}
          {token ? (
            <div className="inquiry-report-btn">
              {isModalOpen && (
                <ReportModal onClose={handleCloseModal} report={report} />
              )}
              <button
                onClick={handleOpenModal}
                onMouseEnter={() => setIsReportHovered(true)}
                onMouseLeave={() => setIsReportHovered(false)}
                className={`hover-button ${isReportHovered ? "hovered" : ""}`}
              >
                <img
                  src={
                    isReportHovered
                      ? "https://img.icons8.com/?size=100&id=jy7dy2jsJ5UR&format=png&color=ed1515"
                      : "https://img.icons8.com/?size=100&id=t5aOnHwCycmN&format=png&color=000000"
                  }
                  alt="Button Image"
                  className="button-image"
                />
              </button>
            </div>
          ) : (
            <div></div>
          )}
          <button className="answer-notify-btn" onClick={handleWriteButton}>
            <FontAwesomeIcon icon={faPen} />
            <span>답변하기</span>
          </button>
        </div>
      </div>
      <div className="inquiry-view-answer-container">
        <div className="radio-container">
          <div>{answer.length}개 답변</div>
          {/* 라디오 버튼 */}
          <div>
            <label style={{ marginRight: "10px" }}>
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
        </div>
        {answer && answer.length > 0 ? (
          <>
            {answer.map((answer) => (
              <AnswerItem key={answer.answerNo} answer={answer} />
            ))}
            <PagenationItem
              currentPage={currentPage}
              totalPages={totalPages}
              paginate={paginate}
            />
          </>
        ) : (
          <div className="inquiry-write-btn">
            <div>아직 등록된 답변이 없습니다.</div>
          </div>
        )}
      </div>
    </>
  );
}
export default InquiryView;
