import React, { useEffect, useRef, useState } from "react";
import * as ChattingApi from "../../../api/chattingApi.js";
import { useMemberAuth } from "../../../util/AuthContext.js";
import ReportModal from "../../Report/ReportModal.jsx";
import SockJS from "sockjs-client";
import { Client as StompClient } from "@stomp/stompjs";
const ChatRoomList = ({ onRoomClick }) => {
  const [isModalOpen1, setIsModalOpen1] = useState(false); //tate
  const [isModalOpen2, setIsModalOpen2] = useState(false);
  const [newRoomName, setNewRoomName] = useState(""); // 수정할 채팅방 이름을 저장 모달창의 열림/닫힘 상태를 관리하기 위한 state
  const [rooms, setRooms] = useState([]);
  const [currentRoom, setCurrentRoom] = useState(null); // 수정 중인 채팅방 정보를 담는 s하는 state
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
  const [totalPages, setTotalPages] = useState(0); // 총 페이지 수
  const [isReportHovered, setIsReportHovered] = useState(false);
  const [report, setreport] = useState({});
  const [messages, setMessages] = useState([]);
  const { token, member } = useMemberAuth();
  let stompClient = useRef(null);

  useEffect(() => {
    if (member?.memberName) {
      // memberName이 있을 때만 WebSocket 연결을 시작
      let socket;

      try {
        socket = new SockJS("http://localhost:8080/chat");
      } catch (error) {
        console.error("Failed to connect to localhost, trying ngrok...");
        socket = new SockJS("https://f8eb-175-123-27-55.ngrok-free.app/chat");
      }

      // StompClient 생성
      stompClient.current = new StompClient({
        webSocketFactory: () => socket,
        onConnect: () => {
          // 서버와의 연결이 성공하면 구독 시작
          stompClient.current.subscribe(
            `/topic/messages/member/${member.memberNo}`,
            (response) => {
              const message = JSON.parse(response.body);

              setRooms((prevRooms) =>
                prevRooms.map((room) =>
                  room.chatRoomNo === message.chatRoomNo
                    ? {
                        ...room,
                        countIsRead: room.countIsRead + 1, // 읽지 않은 메시지 수 증가
                        lastedMessage: message.chatMessageContent, // 최신 메시지 업데이트
                      }
                    : room
                )
              );
            }
          );
        },
      });

      stompClient.current.activate(); // 소켓 활성화

      // cleanup 함수: 컴포넌트가 unmount 될 때 연결 종료
      return () => {
        if (stompClient.current) {
          stompClient.current.deactivate(); // 소켓 연결 종료
        }
      };
    }
  }, [member]); // member가 변경될 때마다 useEffect 실행

  const chatRoomList = async (page) => {
    const responseJsonObject = await ChattingApi.activeListChatRoom(
      token,
      page,
      7
    );
    if (
      responseJsonObject.status === 7010 &&
      Array.isArray(responseJsonObject.data.content)
    ) {
      // 각 채팅방 상태를 개별적으로 비교하여 필터링
      const activeRooms = responseJsonObject.data.content.filter((room) => {
        // filter()는 배열의 각 항목을 하나씩 검사하며, 주어진 콜백 함수에서 true를 반환하는 항목만 새로운 배열에 포함

        return (
          (room.chatRoomStatus === 7100 &&
            (room.chatRoomLeaveStatus === 7500 ||
              room.chatRoomLeaveStatus === 7600)) ||
          (room.chatRoomStatus === 7200 &&
            (room.chatRoomLeaveStatus === 7500 ||
              room.chatRoomLeaveStatus === 7600))
        );
      });
      setRooms(activeRooms); // 필터링된 채팅방만 setRooms에 설정
      setTotalPages(responseJsonObject.data.totalPages);
    }
  };

  useEffect(() => {
    chatRoomList(currentPage - 1); // 페이지 번호는 0부터 시작
  }, [currentPage]);

  const openEditModal = (room) => {
    // 특정 채팅방을 수정하기 위해 모달을 열고, 해당 채팅방의 정보를 저장하는 함수
    setCurrentRoom(room); // 현재 수정 중인 채팅방 정보를 저장
    setNewRoomName(room.chatRoomName); // 수정할 채팅방 이름을 입력창에 보여줌
    setIsModalOpen1(true); // 모달창 열기
  };
  const saveRoomName = async () => {
    // 채팅방 이름을 저장하는 함수 (모달창에서 이름 수정 후 저장 버튼 클릭 시 호출됨)
    if (currentRoom && newRoomName.trim()) {
      await ChattingApi.changeChatRoomName(
        currentRoom.chatRoomNo,
        token,
        newRoomName.trim()
      );
      await chatRoomList(currentPage - 1); // 현재 페이지 갱신
      setIsModalOpen1(false); // 닫기
    }
  };

  const leaveRoom = async (roomNo) => {
    await ChattingApi.leaveChatRoom(roomNo, token);
    await chatRoomList(currentPage - 1); // 현재 페이지 갱신
  };

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber); // 페이지 변경
  };

  // 페이지 번호 버튼 생성
  const pageNumbers = [];
  for (let i = 1; i <= totalPages; i++) {
    pageNumbers.push(i);
  }

  //신고 하기 창 열고 닫기
  const handleOpenModal = (room) => {
    let memberNo;
    setIsModalOpen2(true);
    if (member.memberNo == room.menteeNo) {
      memberNo = room.mentorNo;
    } else {
      memberNo = room.menteeNo;
    }
    setreport({
      type: "MEMBER",
      target: memberNo,
    });
  };

  const handleCloseModal = () => {
    setIsModalOpen2(false);
  };
  // 메뉴 토글 함수
  const toggleMenu = (chatRoomNo) => {
    setRooms((prevRooms) =>
      prevRooms.map((room) =>
        room.chatRoomNo === chatRoomNo
          ? { ...room, isMenuOpen: !room.isMenuOpen }
          : room
      )
    );
  };
  return (
    <div>
      <ul className="chat-room-list">
        {rooms.length > 0 ? ( // 채팅방 리스트의 갯수로 화면 출력 판단 여부
          rooms.map((room) => (
            <li
              key={room.chatRoomNo} // 고유 키 설정 (React에서 반복문에 필수)
              className="chat-room-item"
              onClick={() =>
                onRoomClick(
                  room.chatRoomNo,
                  room.chatRoomName,
                  room.chatRoomStatus,
                  room.mentorNo
                )
              }
              // 채팅방 클릭 시 부모 컴포넌트에 해당 채팅방 id 전달
            >
              <div className="chat-room-first">
                <span className="room-name">{room.chatRoomName}</span>{" "}
                {room.countIsRead === 0 ? null : (
                  <div className="chat-is-read">{room.countIsRead}</div>
                )}
                <div className="button-container">
                  {/* 우측 상단 메뉴 버튼 */}
                  <span className="chat-button-container">
                    <button
                      className="chat-menu-button"
                      onClick={(e) => {
                        e.stopPropagation(); // 부모 이벤트 전파 방지
                        toggleMenu(room.chatRoomNo); // 메뉴 열기/닫기
                      }}
                    >
                      &#8942; {/* 메뉴 아이콘 (일반적으로 세로로 3개의 점) */}
                    </button>
                    {room.isMenuOpen && (
                      <div className="chat-menu-dropdown">
                        <button
                          className="edit-button"
                          onClick={(e) => {
                            e.stopPropagation();
                            openEditModal(room); // 채팅방 이름 수정 모달 열기
                          }}
                        >
                          이름 수정
                        </button>
                        <button
                          className="leave-button"
                          onClick={(e) => {
                            e.stopPropagation();
                            leaveRoom(room.chatRoomNo); // 방 나가기 기능 호출
                          }}
                        >
                          방 나가기
                        </button>
                        <div className="inquiry-report-btn">
                          {isModalOpen2 && (
                            <div
                              onClick={(e) => e.stopPropagation()} // 클릭 이벤트 전파 방지
                            >
                              <ReportModal
                                onClose={handleCloseModal}
                                report={report}
                              />
                            </div>
                          )}
                          <button
                            onClick={(e) => {
                              e.stopPropagation();
                              handleOpenModal(room);
                            }}
                            onMouseEnter={() => setIsReportHovered(true)}
                            onMouseLeave={() => setIsReportHovered(false)}
                            className={`hover-button ${
                              isReportHovered ? "hovered" : ""
                            }`}
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
                      </div>
                    )}
                  </span>
                </div>
              </div>
              <div className="chat-last-message">
                {room.lastedMessage
                  ? room.lastedMessage.substring(0, 14)
                  : null}
              </div>
            </li>
          ))
        ) : (
          <div className="no-rooms-message">
            현재 생성된 채팅방은 존재하지 않습니다.
          </div>
        )}
      </ul>
      {/* 페이지네이션 */}
      <div className="pagination">
        {pageNumbers.map((number) => (
          <button
            key={number}
            onClick={() => paginate(number)}
            className={number === currentPage ? "active" : ""}
          >
            {number}
          </button>
        ))}
      </div>

      {/* 채팅방 이름 수정 panel */}
      {isModalOpen1 && (
        <div className={`modal ${isModalOpen1 ? "active" : ""}`}>
          <div
            className="modal-content"
            onClick={(e) => e.stopPropagation()} // 클릭 이벤트 전파 방지
          >
            <div className="modal-header">채팅방 이름 변경</div>
            <input
              type="text"
              value={newRoomName}
              onChange={(e) => setNewRoomName(e.target.value)} // 입력된 값을 newRoomName에 저장
              placeholder="새 채팅방 이름"
            />
            <button onClick={saveRoomName}>저장</button>
            <button
              className="close-button"
              onClick={() => setIsModalOpen1(false)} // 닫기 버튼
            >
              닫기
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default ChatRoomList;
