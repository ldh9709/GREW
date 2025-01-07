import { Route, Routes, useLocation } from "react-router-dom";
import "./css/styles.css";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import Navigate from "./layout/Navigate";
import { MainPage } from "./component/MainPage";
import { AuthProvider } from "./util/AuthContext";
/* 토스트 메시지 임포트 */
import "./css/toast.css";
import { ToastContainer } from "react-toastify";

/* 멤버 페이지 임포트 */
import MemberMainJoinFormPage from "./component/Member/MemberMainJoinFormPage";
import MemberJoinFormPage from "./component/Member/MemberJoinFormPage";
import MemberLoginFormPage from "./component/Member/MemberLoginFormPage";
import MemberProfileFormPage from "./component/Member/MemberProfileFormPage";
import MemberFindPasswordFormPage from "./component/Member/MemberFindPasswordFormPage";
import MemberFindIdFormPage from "./component/Member/MemberFindIdFormPage";
import MemberProfileLayoutFormPage from "./component/Member/MemberProfileLayoutFormPage";

/* 멘토 페이지 임포트 */
import MemberMypage from "./component/Member/mypage/MemberMypage";
import MemberSummary from "./component/Member/mypage/MemberSummary";
/* 멘토 프로필 페이지 임포트 */
import MentorJoinFormPage from "./component/Member/MentorJoinFormPage";
import MentorModifyFormPage from "./component/Member/MentorModifyFormPage";
import MentorProfileAdd from "./component/MentorProfile/MentorProfileAdd";
import MentorProfileList from "./component/MentorProfile/MentorProfileList";
import MentorProfileDetail from "./component/MentorProfile/MentorProfileDetail";
import MentorSearchList from "./component/MentorProfile/MentorSearchList"; // 🔥 정확한 경로로 추가
/* 멘토 보드 페이지 임포트트 */
import MentorBoardFind from "./component/MentorBoard/MentorBoardFind";
import MentorBoardList from "./component/MentorBoard/MentorBoardList";
import MentorBoardDetail from "./component/MentorBoard/MentorBoardDetail";
import MentorBoardCreate from "./component/MentorBoard/MentorBoardCreate";
import MentorBoardUpdate from "./component/MentorBoard/MentorBoardUpdate";

import InquiryWriteFormpage from "./component/AnswerInquiry/InqiuryWriteFormPage";
import InquiryModifyFormpage from "./component/AnswerInquiry/InquiryModifyFormPage";
import AnswerWriteFormpage from "./component/AnswerInquiry/AnswerWriteFormPage";
import AnswerModifyFormpage from "./component/AnswerInquiry/AnswerModifyFormPage";
import InqiuryList from "./component/AnswerInquiry/InqiuryList";
import InqiuryView from "./component/AnswerInquiry/InquiryView";
import ChatAlarim from "./component/Chatting/ChatAlarim";
import SearchList from "./component/SearchList";
import InquirySearchList from "./component/AnswerInquiry/InquirySearchList";
import MentorBoardSearchList from "./component/MentorBoard/MentorBoardSearchList";
import ReviewWriteFormPage from "./component/Review/ReviewWriteFormPage";
import ReviewView from "./component/Review/ReviewView";
import ForbiddenPage from "./component/ForbiddenPage";
import MentorProfileItem from "./component/MentorProfile/MentorProfileItem";

import AdminRoutes from "./routes/AdminRoutes";

/* 신고 모달 */
import ReportModal from "./component/Report/ReportModal";
import MemberMaskedFindIdFormPage from "./component/Member/MemberMaskedFindIdFormPage";

function App() {
  const location = useLocation(); // 현재 URL 경로를 가져옴

  // 어드민 경로 여부를 확인
  const isAdminRoute = location.pathname.startsWith("/admin");

  return (
    <>
      <AuthProvider>
        {/* 토스트 메시지 컨테이너 */}
        <ToastContainer
          autoClose={3000}
          position="top-center"
          toastClassName="custom-toast" // 토스트 스타일
          progressClassName="custom-progress" // 프로그레스 바 스타일
          hideProgressBar={true} // 프로그레스 바 숨기기
          closeOnClick={true} // 클릭하면 닫히도록 설정
        />
        {/* 어드민 경로가 아닌 경우에만 공통 레이아웃 표시 */}
        {!isAdminRoute && <Header />}
        {!isAdminRoute && <Navigate />}
        {!isAdminRoute && <ChatAlarim />}

        <div id="wrapper">
          <link
            href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
            rel="stylesheet"
          ></link>
          <div id="content">
            <Routes>
              <Route path="/" exact element={<MainPage />} />
              <Route path="/main" element={<MainPage />} />
              <Route path="/403" element={<ForbiddenPage />} />
              <Route path="/summary" element={<MemberSummary />} />
              {/* Member페이지 */}
              <Route path="/member/join" element={<MemberMainJoinFormPage />} />
              <Route
                path="/member/join/form"
                element={<MemberJoinFormPage />}
              />
              <Route path="/member/login" element={<MemberLoginFormPage />} />
              <Route path="/member/profile" element={<MemberMypage />} />
              <Route
                path="//profile/:tab"
                element={<MemberProfileLayoutFormPage />}
              />
              <Route
                path="/member/profile/edit"
                element={<MemberProfileFormPage />}
              />
              <Route
                path="/member/find-password"
                element={<MemberFindPasswordFormPage />}
              />
              <Route
                path="/member/find-id"
                element={<MemberFindIdFormPage />}
              />
              <Route
                path="/member/find-id-check"
                element={<MemberMaskedFindIdFormPage />}
              />
              {/*검색 페이지지*/}
              <Route path="/searchList" element={<SearchList />} />
              <Route
                path="/mentor-board/search"
                element={<MentorBoardSearchList />}
              />
              <Route
                path="/mentor-profile/search"
                element={<MentorSearchList />}
              />
              <Route path="/inquiry/search" element={<InquirySearchList />} />
              {/* Mentor페이지 */}
              <Route path="/mentor/join" element={<MentorJoinFormPage />} />
              <Route
                path="/member/profile/edit"
                element={<MemberProfileFormPage />}
              />
              <Route path="/member/join" element={<MemberMainJoinFormPage />} />
              <Route
                path="/member/join/form"
                element={<MemberJoinFormPage />}
              />
              <Route path="/member/login" element={<MemberLoginFormPage />} />
              <Route
                path="/member/profile/edit"
                element={<MemberProfileFormPage />}
              />
              {/* MentorProfile 페이지 */}
              <Route
                path="/mentor/join"
                element={<MentorJoinFormPage />}
              />{" "}
              {/* 멘토 회원가입 */}
              <Route
                path="/mentor/modify"
                element={<MentorModifyFormPage />}
              />{" "}
              {/* 멘토 회원가입 */}
              <Route
                path="/mentor-profile/list"
                element={<MentorProfileList />}
              />
              <Route
                path="/mentor-profile/add"
                element={<MentorProfileAdd />}
              />
              <Route
                path="/mentor-profile/view"
                element={<MentorProfileItem />}
              />
              <Route
                path="/mentor-profile/detail/:mentorProfileNo"
                element={<MentorProfileDetail />}
              />
              <Route
                path="/mentor-profile/detail"
                element={<MentorProfileDetail />}
              />
              <Route
                path="/mentor-profile/:mentorProfileNo"
                element={<MentorProfileDetail />}
              />
              {/* MentorBoard 페이지 */}
              <Route path="/mentor-board/find" element={<MentorBoardFind />} />
              <Route path="/mentor-board/list" element={<MentorBoardList />} />
              <Route
                path="/mentor-board/create"
                element={<MentorBoardCreate />}
              />
              <Route
                path="/mentor-board/update/:mentorBoardNo"
                element={<MentorBoardUpdate />}
              />
              <Route
                path="/mentor-board/detail"
                element={<MentorBoardDetail />}
              />
              <Route
                path="/mentor-board/detail/:mentorBoardNo"
                element={<MentorBoardDetail />}
              />
              {/* inquiry 페이지 */}
              <Route path="/inquiry" element={<InqiuryList />} />
              <Route path="/inquiry/:inquiryNo" element={<InqiuryView />} />
              <Route path="/inquiry/write" element={<InquiryWriteFormpage />} />
              <Route
                path="/inquiry/modify"
                element={<InquiryModifyFormpage />}
              />
              <Route
                path="/review/reviewWrite"
                element={<ReviewWriteFormPage />}
              />
              {/* answer 페이지 */}
              <Route path="/answer/write" element={<AnswerWriteFormpage />} />
              <Route path="/answer/modify" element={<AnswerModifyFormpage />} />
              {/* review 페이지 */}
              <Route
                path="/review/reviewWrite"
                element={<ReviewWriteFormPage />}
              />
              <Route path="/review/:reviewNo" element={<ReviewView />} />{" "}
              {/* 상세 페이지 라우팅 */}
              <Route path="/inquiry" element={<InqiuryList />} />
              <Route path="/inquiry/:inquiryNo" element={<InqiuryView />} />
              <Route path="/inquiry/write" element={<InquiryWriteFormpage />} />
              {/* Admin페이지 */}
              <Route path="/admin/*" element={<AdminRoutes />} />
              {/* 신고 모달 */}
              <Route path="/report-modal" element={<ReportModal />}></Route>
            </Routes>
            {/* 어드민 경로가 아닌 경우에만 Footer 표시 */}
            {!isAdminRoute && <Footer />}
          </div>
        </div>
      </AuthProvider>
    </>
  );
}
export default App;
