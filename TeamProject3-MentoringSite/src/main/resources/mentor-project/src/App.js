import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import React, { useState } from "react";
import LoginFormPage from './page/LoginFormPage';
import MainFormPage from './page/MainFormPage';
import JoinFormPage from './page/JoinFormPage';

export const MemberContext = React.createContext({});

function App() {
  const [loginStatus, setLoginStatus] = useState({
    isLogin:false,
    loginUser: {}
  })


  return ( 
    <MemberContext.Provider value={{loginStatus, setLoginStatus}}>
    <BrowserRouter>
      <div className="App">
        <div className="black-nav">
          <div>멘토멘티 프로그램</div>
        </div>
        <header className="App-header">
          <Routes>
            <Route path="/main" element={<MainFormPage/>} />
            <Route path="/login" element={<LoginFormPage/>} />
            <Route path="/Join" element={<JoinFormPage/>} />
          </Routes>
        </header>
      </div>
    </BrowserRouter>
    </MemberContext.Provider>
  );
}

export default App;
