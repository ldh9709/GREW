import { Route, Routes } from "react-router-dom";
import "./css/styles.css";
import HeaderMenu from "./layout/Header";
import { MainPage } from "./component/MainPage";
import Navigate from "./layout/navigate";
import InquiryWriteFormpage from "./component/AnswerInquiry/InqiuryWriteFormPage";
import InquiryModifyFormpage from "./component/AnswerInquiry/InquiryModifyFormPage";
import InqiuryList from "./component/AnswerInquiry/InqiuryList";
import InqiuryView from "./component/AnswerInquiry/InquiryView";
import ChatAlarim from "./component/Chatting/ChatAlarim";
import Admin from "./component/admin/admin";
import AdminReport from "./component/admin/adminReport";
import AdminReport2 from "./component/admin/adminReport2";


function App() {
  return (
    <>
      <HeaderMenu />
      <Navigate/>
      <ChatAlarim/>
      <div id="wrapper">
        <div id="content">
          <Routes>
            <Route path="/" exact element={<MainPage />} />
            <Route path="/main" element={<MainPage />} />
            <Route path="/inquiry" element={<InqiuryList/>}/>
            <Route path="/inquiry/inquiryWrite" element={<InquiryWriteFormpage/>}/>
            <Route path="/inquiry/:inquiryNo" element={<InqiuryView/>}/>
            <Route path="/inquiry/modify/:inquiryNo" element={<InquiryModifyFormpage/>}/>
            <Route path="/adim" element={<Admin/>}/>
            <Route path="/adminReport" element={<AdminReport/>}/>
            <Route path="/adminReport2" element={<AdminReport2/>}/>
            
          </Routes>
        </div>
      </div>
    </>
  );
}
export default App;
