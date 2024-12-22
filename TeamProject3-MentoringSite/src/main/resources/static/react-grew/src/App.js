import { Route, Routes, useLocation } from "react-router-dom";
import "./css/styles.css";
import Header from "./layout/Header";
import { MainPage } from "./component/MainPage";
import Navigate from "./layout/Navigate";
/* 멤버 페이지 임포트 */
import MemberMainJoinFormPage from "./component/Member/MemberMainJoinFormPage";
import MemberJoinFormPage from "./component/Member/MemberJoinFormPage";
import MemberLoginFormPage from "./component/Member/MemberLoginFormPage";
import MemberProfileFormPage  from "./component/Member/MemberProfileFormPage";
/* 멘토 페이지 임포트 */
import MentorJoinFormPage from "./component/Member/MentorJoinFormPage";



import MentorProfileAdd  from "./component/MentorProfile/MentorProfileAdd";
import MentorProfileList  from "./component/MentorProfile/MentorProfileList";
import MentorProfileDetail from "./component/MentorProfile/MentorProfileDetail";
import MentorSearchList from "./component/MentorProfile/MentorSearchList"; // 🔥 정확한 경로로 추가



import InquiryWriteFormpage from "./component/AnswerInquiry/InqiuryWriteFormPage";
import InquiryModifyFormpage from "./component/AnswerInquiry/InquiryModifyFormPage";
import AnswerWriteFormpage from "./component/AnswerInquiry/AnswerWriteFormPage";
import AnswerModifyFormpage from "./component/AnswerInquiry/AnswerModifyFormPage";
import InqiuryList from "./component/AnswerInquiry/InqiuryList";
import InqiuryView from "./component/AnswerInquiry/InquiryView";
import ChatAlarim from "./component/Chatting/ChatAlarim";
import Footer from "./layout/Footer";
import MemberMypage from "./component/Member/mypage/MemberMypage";
import SearchList from "./component/SearchList";
import InquirySearchList from "./component/AnswerInquiry/InquirySearchList";

import ReviewWriteFormPage from "./component/Review/ReviewWrithFormPage";
import ReviewView from "./component/Review/ReviewView";
import ReviewListPage from "./component/Review/ReviewList";
import ForbiddenPage from "./component/ForbiddenPage";
import MentorBoardCreate from "./component/MentorBoard/MentorBoardCreate";
import MentorProfileItem from "./component/MentorProfile/MentorProfileItem";
import AdminRoutes from "./routes/AdminRoutes";

function App() {
  const location = useLocation(); // 현재 URL 경로를 가져옴

  // 어드민 경로 여부를 확인
  const isAdminRoute = location.pathname.startsWith("/admin");

  return (
    <>
      {/* 어드민 경로가 아닌 경우에만 공통 레이아웃 표시 */}
      {!isAdminRoute && <Header />}
      {!isAdminRoute && <Navigate />}
      {!isAdminRoute && <ChatAlarim />}

      <div id="wrapper">
        <div id="content">
          <Routes>
            <Route path="/" exact element={<MainPage />} />
            <Route path="/main" element={<MainPage />} />
            <Route path="/login" element={<MainPage />} />
            <Route path="/403" element={<ForbiddenPage />} />

            {/* Member페이지 */}
            <Route path="/member/profile" element={<MemberMypage />} />
            <Route path="/member/join" element={<MemberMainJoinFormPage/>} />
            <Route path="/member/join/form" element={<MemberJoinFormPage/>} />
            <Route path="/member/login" element={<MemberLoginFormPage/>} />
            <Route path="/member/profile/edit" element={<MemberProfileFormPage/>}/>

            {/*멘토페이지*/}
            <Route path="/mentorprofile/list" element={<MentorProfileList/>}/>
            <Route path="/mentorprofile/add" element={<MentorProfileAdd/>}/>
            <Route path="/mentorprofile/view" element={<MentorProfileItem/>}/>
            <Route path="/mentorprofile/search" element={<MentorSearchList />} />
            <Route path="/mentorboardcreate/:mentorProfileNo" element={<MentorBoardCreate />} />
            <Route path="/mentorprofile/detail/:mentorProfileNo" element={<MentorProfileDetail/>}/>
            <Route path="/mentorprofile/detail" element={<MentorProfileDetail/>}/>
            <Route path="/mentorprofile/search" element={<MentorSearchList/>} />
            <Route path="/mentor-profile/:mentorProfileNo" element={<MentorProfileDetail/>} />
			
            {/* Mentor페이지 */}
            <Route path="/mentor/join" element={<MentorJoinFormPage />} />
            <Route path="/member/join" element={<MemberJoinFormPage />} />
            <Route path="/member/login" element={<MemberLoginFormPage />} />
			
            <Route path="/member/profile/edit" element={<MemberProfileFormPage />} />
            <Route path="/review/reviewWrite" element={<ReviewWriteFormPage />} />
            <Route path="/review/reviewView" element={<ReviewView />} />
            <Route path="/review/reviewList" element={<ReviewListPage />} />
            <Route path="/inquiry" element={<InqiuryList/>}/>
            <Route path="/inquiry/:inquiryNo" element={<InqiuryView/>}/>
            <Route path="/inquiry/inquiryWrite" element={<InquiryWriteFormpage/>}/>
            <Route path="/inquiry/modify/:inquiryNo" element={<InquiryModifyFormpage/>}/>
            <Route path="/answer/answerWrite/:inquiryNo" element={<AnswerWriteFormpage/>}/>
            <Route path="/answer/modify/:answerNo" element={<AnswerModifyFormpage/>}/>
            <Route path="/searchList" element={<SearchList/>}/>
            <Route path="/inquirySearchList" element={<InquirySearchList/>}/>

            {/* Admin페이지 */}
            <Route path="/admin/*" element={<AdminRoutes />} />

            
          </Routes>
          {/* 어드민 경로가 아닌 경우에만 Footer 표시 */}
          {!isAdminRoute && <Footer />}
        </div>
      </div>
    </>
  );
}
export default App;
