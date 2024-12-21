import { Route, Routes } from "react-router-dom";
import "./css/styles.css";
import Header from "./layout/Header";
import { MainPage } from "./component/MainPage";
import Navigate from "./layout/Navigate";
import MemberJoinFormPage from "./component/Member/MemberJoinFormPage";
import MemberLoginFormPage from "./component/Member/MemberLoginFormPage";
import MemberProfileFormPage  from "./component/Member/MemberProfileFormPage";



import MentorProfileAdd  from "./component/MentorProfile/MentorProfileAdd";
import MentorProfileList  from "./component/MentorProfile/MentorProfileList";
import MentorProfileDetail from "./component/MentorProfile/MentorProfileDetail";
import MentorSearchList from "./component/MentorProfile/MentorSearchList"; // 🔥 정확한 경로로 추가

import MentorBoardCreat from "./component/MentorBoard/MentorBoardCreat"; 
import MentorBoardFind from "./component/MentorBoard/MentorBoardFind"; 
import MentorBoardList from "./component/MentorBoard/MentorBoardList"; 
import MentorBoardUpdate from "./component/MentorBoard/MentorBoardUpdate"; 



import InquiryWriteFormpage from "./component/AnswerInquiry/InqiuryWriteFormPage";
import InquiryModifyFormpage from "./component/AnswerInquiry/InquiryModifyFormPage";
import AnswerWriteFormpage from "./component/AnswerInquiry/AnswerWriteFormPage";
import AnswerModifyFormpage from "./component/AnswerInquiry/AnswerModifyFormPage";
import InqiuryList from "./component/AnswerInquiry/InqiuryList";
import InqiuryView from "./component/AnswerInquiry/InquiryView";
import ChatAlarim from "./component/Chatting/ChatAlarim";
import Footer from "./layout/Footer"
import MemberMypage from "./component/Member/mypage/MemberMypage";
import SearchList from "./component/SearchList";
import InquirySearchList from "./component/AnswerInquiry/InquirySearchList";
import AdminReport2 from "./component/admin/adminReport2"
import Admin from "./component/admin/admin"
import AdminReport4 from "./component/admin/adminReport4"
import ForbiddenPage from "./component/ForbiddenPage";

function App() {
  return (
    <>
      <Header />
      <Navigate/>
      <ChatAlarim/>
      <div id="wrapper">
        <div id="content">
          <Routes>
            <Route path="/" exact element={<MainPage />} />
            <Route path="/main" element={<MainPage />} />
            <Route path="/403" element={<ForbiddenPage />} />
            {/* Member페이지 */}
            <Route path="/member/profile" element={<MemberMypage />} />
            <Route path="/member/join" element={<MemberJoinFormPage/>} />
            <Route path="/member/login" element={<MemberLoginFormPage/>} />
            <Route path="/member/profile/edit" element={<MemberProfileFormPage/>}/>
            {/*멘토프로필페이지*/}
            <Route path="/mentorprofile/list" element={<MentorProfileList/>}/>
            <Route path="/mentorprofile/add" element={<MentorProfileAdd/>}/>
            <Route path="/mentorprofile/detail/:mentorProfileNo" element={<MentorProfileDetail/>}/>
            <Route path="/mentorprofile/detail" element={<MentorProfileDetail/>}/>
            <Route path="/mentorprofile/search" element={<MentorSearchList/>} />
            <Route path="/mentor-profile/:mentorProfileNo" element={<MentorProfileDetail/>} />
            {/*멘토보드드페이지*/}
            <Route path="/mentorboard/creat" element={<MentorBoardCreat/>} />
            <Route path="/mentorboard/find" element={<MentorBoardFind/>} />
            <Route path="/mentorboard/list" element={<MentorBoardList/>} />
            <Route path="/mentorboard/update" element={<MentorBoardUpdate/>} />



            <Route path="/inquiry" element={<InqiuryList/>}/>
            <Route path="/inquiry/:inquiryNo" element={<InqiuryView/>}/>
            <Route path="/inquiry/inquiryWrite" element={<InquiryWriteFormpage/>}/>
            <Route path="/inquiry/modify/:inquiryNo" element={<InquiryModifyFormpage/>}/>
            <Route path="/answer/answerWrite/:inquiryNo" element={<AnswerWriteFormpage/>}/>
            <Route path="/answer/modify/:answerNo" element={<AnswerModifyFormpage/>}/>
            <Route path="/searchList" element={<SearchList/>}/>
            <Route path="/inquirySearchList" element={<InquirySearchList/>}/>
            <Route path="/admin/reports" element={<AdminReport2/>}/>
            <Route path="/admin" element={<Admin/>}/>
            <Route path="/adminReport4" element={<AdminReport4/>}/>
          </Routes>
          <Footer/>
        </div>
      </div>
    </>
  );
}
export default App;