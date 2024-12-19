import React from "react";
import "../css/MainFormPage.css";
import { Link } from "react-router-dom";

function MainFormPage() {
  return (
    <div className="main-container">
      <header className="main-header">
        <h1 className="main-title">환영합니다!</h1>
        <p className="main-subtitle">저희 서비스를 시작해보세요.</p>
        <Link to={'/login'}>
        <input type="button" className="cta-button"value="로그인"/>
        </Link>
        &nbsp;&nbsp;
        <Link to={'/join'}>
        <input type="button" className="cta-button"value="회원가입"/>
        </Link>

        
      </header>

      <section className="features-section">
        <h2 className="section-title">주요 기능</h2>
        <div className="features">
          <div className="feature-item">
            <h3>기능 1</h3>
            <p>사용하기 쉽고 직관적인 인터페이스.</p>
          </div>
          <div className="feature-item">
            <h3>기능 2</h3>
            <p>안정적이고 빠른 성능 제공.</p>
          </div>
          <div className="feature-item">
            <h3>기능 3</h3>
            <p>다양한 사용자 맞춤형 옵션 제공.</p>
          </div>
        </div>
      </section>

      <footer className="main-footer">
        <p>© 2024 Your Company. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default MainFormPage;
