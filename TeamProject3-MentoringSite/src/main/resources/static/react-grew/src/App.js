import { Route, Routes } from "react-router-dom";
import "./css/styles.css";
import Header from "./layout/Header";
import { MainPage } from "./component/MainPage";
import Navigate from "./layout/navigate";
import InquiryWriteFormpage from "./component/AnswerInquiry/InqiuryWriteFormPage";
import InquiryModifyFormpage from "./component/AnswerInquiry/InquiryModifyFormPage";
import InqiuryList from "./component/AnswerInquiry/InqiuryList";
import InqiuryView from "./component/AnswerInquiry/InquiryView";
import ChatAlarim from "./component/Chatting/ChatAlarim";
import Footer from "./layout/Footer"
import AdminReport2 from "./component/admin/adminReport2"
import Admin from "./component/admin/admin"
import AdminReport4 from "./component/admin/adminReport4"

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
            <Route path="/inquiry" element={<InqiuryList/>}/>
            <Route path="/inquiry/inquiryWrite" element={<InquiryWriteFormpage/>}/>
            <Route path="/inquiry/:inquiryNo" element={<InqiuryView/>}/>
            <Route path="/inquiry/modify/:inquiryNo" element={<InquiryModifyFormpage/>}/>
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