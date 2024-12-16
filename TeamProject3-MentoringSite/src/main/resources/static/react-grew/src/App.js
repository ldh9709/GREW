import { Route, Routes } from "react-router-dom";
import "./css/styles.css";
import HeaderMenu from "./layout/Header";
import { MainPage } from "./component/MainPage";
import SlideTab from "./layout/SlideTab";
import Navigate from "./layout/navigate";
import InquiryWriteFormpage from "./component/InqiuryWriteFormPage";
import InquiryModifyFormpage from "./component/InquiryModifyFormPage";
import InqiuryList from "./component/InqiuryList";
import InqiuryView from "./component/InquiryView";


function App() {
  return (
    <>
      <HeaderMenu />
      <Navigate/>
      <SlideTab />
      <div id="wrapper">
        <div id="content">
          <Routes>
            <Route path="/" exact element={<MainPage />} />
            <Route path="/main" element={<MainPage />} />
            <Route path="/inquiry" element={<InqiuryList/>}/>
            <Route path="/inquiry/inquiryWrite" element={<InquiryWriteFormpage/>}/>
            <Route path="/inquiry/:inquiryNo" element={<InqiuryView/>}/>
            <Route path="/inquiry/modify/:inquiryNo" element={<InquiryModifyFormpage/>}/>

          </Routes>
        </div>
      </div>
    </>
  );
}
export default App;
