import React, { useEffect, useRef, useState } from "react";
import * as inquiryApi from "../../api/inquiryApi";
import { useNavigate } from "react-router-dom";
import { getCookie } from "../../util/cookieUtil";
import * as categoryApi from "../../api/categoryApi";
export default function InqiuryWriteFormPage() {
  const writeFormRef = useRef();
  const navigate = useNavigate();
  const memberCookie = getCookie("member");
  const token = memberCookie.accessToken;

  const initInquiry = {
    inquiryNo: 0,
    inquiryTitle: "",
    inquiryContent: "",
    inquiryDate: "",
    inquiryState: 1,
    inquiryViews: 0,
    categoryNo: 0,
    memberNo: 0,
  };

  const [inquiry, setInquiry] = useState(initInquiry);
  const [categories, setCategories] = useState([]); // 카테고리 리스트
  const [selectedCategory, setSelectedCategory] = useState(null); // 선택된 카테고리
  const [childCategories, setChildCategories] = useState([]); // 하위 카테고리 상태

  // 카테고리 목록을 가져오는 함수
  const fetchCategories = async () => {
    try {
      const response = await categoryApi.ListCategory();
      setCategories(response.data); // 카테고리 목록을 상태에 저장
    } catch (error) {
      console.error("카테고리 가져오기 실패:", error);
    }
  };

  // 컴포넌트 로드 시 카테고리 목록 가져오기
  useEffect(() => {
    fetchCategories();
  }, []);

  const handleCategoryClick = async (categoryNo) => {
    setSelectedCategory(categoryNo); // 새 카테고리 선택
    const selectedCategory = categories.find(
      (cat) => cat.categoryNo === categoryNo
    );
    if (selectedCategory && selectedCategory.categoryDepth === 1) {
      try {
        // 하위 카테고리 API 호출
        const response = await categoryApi.childCategory(categoryNo); // categoryNo를 API 요청에 포함
        setChildCategories(response.data.childCategories); // 하위 카테고리 상태에 저장
      } catch (error) {
        console.error("하위 카테고리 로드 오류:", error);
      }
    }
    // 선택된 카테고리 번호를 inquiry 객체에 반영
    setInquiry({
      ...inquiry,
      categoryNo: categoryNo, // 선택된 categoryNo 업데이트
    });
  };

  const onChangeInquiryForm = (e) => {
    setInquiry({
      ...inquiry,
      [e.target.name]: e.target.value,
    });
    console.log(inquiry);
  };

  const inquiryWriteAction = async (e) => {
    // 상위 카테고리 선택 여부 확인
    if (!inquiry.categoryNo) {
      alert("카테고리를 선택해야 합니다.");
      return; // 상위 카테고리가 선택되지 않으면 폼 제출을 막음
    }
    if (
      inquiry.categoryNo &&
      !childCategories.some((child) => child.categoryNo === inquiry.categoryNo)
    ) {
      alert("하위 카테고리를 선택해야 합니다.");
      return; // 하위 카테고리가 선택되지 않으면 폼 제출을 막음
    }
    if (!inquiry.inquiryTitle.trim()) {
      alert("제목을 입력해주세요."); // 사용자에게 입력을 요구하는 알림을 띄움
      return; // 폼 제출을 막음
    }
    if (!inquiry.inquiryContent.trim()) {
      alert("내용을 입력해주세요."); // 사용자에게 입력을 요구하는 알림을 띄움
      return; // 폼 제출을 막음
    }
    const responseJsonObject = await inquiryApi.writeInquiry(inquiry, token);
    console.log(token);
    console.log(inquiry.inquiryNo)
    navigate(`/inquiry/${responseJsonObject.data.inquiryNo}`);
  };
  return (
    <>
      <link
        href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
        rel="stylesheet"
      ></link>
      <div>
        <form ref={writeFormRef} method="POST" className="inquiry-form">
          <div>
            <div>질문등록</div>
            <div>
              {categories
                .filter((category) => category.categoryDepth === 1) // categoryDepth가 1인 카테고리만 필터링
                .map((category) => (
                  <button
                    key={category.categoryNo}
                    className="category-button"
                    onClick={(e) => {
                      e.preventDefault();
                      handleCategoryClick(category.categoryNo);
                    }} // 클릭 시 카테고리 선택
                    style={{
                      margin: "5px",
                      backgroundColor:
                        selectedCategory === category.categoryNo
                          ? "#4CAF50"
                          : "", // 선택된 카테고리는 색상 변경
                      color:
                        selectedCategory === category.categoryNo ? "white" : "", // 선택된 카테고리 글자 색상 변경
                    }}
                  >
                    {category.categoryName}
                  </button>
                ))}
            </div>

            {/* 하위 카테고리 버튼 렌더링 */}
            {childCategories.length > 0 && (
              <div>
                {childCategories.map((child) => (
                  <button
                    key={child.categoryNo}
                    className="category-button"
                    onClick={(e) => {
                      e.preventDefault();
                      handleCategoryClick(child.categoryNo);
                    }} // 하위 카테고리 선택
                    style={{
                      margin: "5px",
                      backgroundColor:
                        selectedCategory === child.categoryNo ? "#4CAF50" : "", // 선택된 카테고리는 색상 변경
                      color:
                        selectedCategory === child.categoryNo ? "white" : "", // 선택된 카테고리 글자 색상 변경
                    }}
                  >
                    {child.categoryName}
                  </button>
                ))}
              </div>
            )}
            <input
              type="text"
              name="inquiryTitle"
              onChange={onChangeInquiryForm}
              value={inquiry.inquiryTitle}
              placeholder="제목을 입력하세요"
              required
            />
          </div>
          <div>
            <textarea
              name="inquiryContent"
              onChange={onChangeInquiryForm}
              value={inquiry.inquiryContent}
              placeholder="내용을 입력하세요"
              required
            />
          </div>
          <div className="inquiry-write-btn">
            <input
              type="button"
              value="질문등록"
              onClick={inquiryWriteAction}
              id="btn_inquiry_write_action"
            />
          </div>
        </form>
      </div>
    </>
  );
}
