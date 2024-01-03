import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";
import axios from "axios";
import ChatRoom from "../components/ChatRoom";

const Chat = () => {
  const location = useLocation();
  const { accountId = "", title = "", newFilename = "" } = location.state || {};
  const [messages, setMessages] = useState([]);
  const [messageInput, setMessageInput] = useState("");
  const [stompClient, setStompClient] = useState(null);

  const [chatRoomRes, setChatRoomRes] = useState("");

  const [chatRoomName, setChatRoomName] = useState(
    `${newFilename}_${accountId}`
  );

  const senderAccountId = localStorage.getItem("accountId");
  const accessToken = localStorage.getItem("accessToken");

  const getChatRoomName = (name) => {
    setChatRoomName(name);
    console.log(chatRoomName);
  };

  const submitButton = () => {
    sendMessage();

    if (chatRoomRes === "OK") {
      console.log("ok에 걸림");
      return;
    }

    console.log("ok에 안걸림");
    createChatRoom();
  };

  const createChatRoom = async () => {
    const chatRoomDto = {
      roomName: chatRoomName,
      senderAccountId: senderAccountId,
      accountId: accountId,
      title: title,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/api/chat-rooms",
        chatRoomDto,
        {
          headers: {
            Authorization: `${accessToken}`,
          },
        }
      );
      setChatRoomRes(response.data.data);
    } catch (error) {}
  };

  const connectStomp = () => {
    const socket = new SockJS("http://localhost:8080/ws");
    const stompClient = Stomp.over(socket);

    stompClient.connect(
      {},
      () => {
        console.log("STOMP 연결 성공");
        stompClient.subscribe(`/queue/chat/${chatRoomName}`, (chatDto) => {
          const chat = JSON.parse(chatDto.body);
          const message = `${chat.sender}: ${chat.message}`; //나중에 닉네임으로 통신해서 가져와야함
          console.log(chat.sender);
          setMessages((prevMessages) => [...prevMessages, message]);
        });
      },
      (error) => {}
    );

    setStompClient(stompClient);
  };

  useEffect(() => {
    const stompClient = connectStomp();

    return () => {
      if (stompClient && stompClient.connected) {
        stompClient.disconnect();
      }
      setMessages([]);
    };
  }, [chatRoomName]);

  const sendMessage = () => {
    if (messageInput === "") {
      return;
    }
    const chatDto = {
      sender: senderAccountId,
      message: messageInput,
    };

    stompClient.send(
      `/app/chat/message/${chatRoomName}`,
      {},
      JSON.stringify(chatDto)
    );
    setMessageInput("");
  };

  const onChangeMessage = (e) => {
    setMessageInput(e.target.value);
  };

  return (
    <div>
      <div className="row mt-5">
        <div className="col-3"></div>
        <div className="col-3">
          <ChatRoom
            senderAccountId={senderAccountId}
            accessToken={accessToken}
            getChatRoomName={getChatRoomName}
          />
        </div>
        <div className="col-3">
          <hr />
          <div>
            {messages.map((msg, index) => (
              <div key={index}>{msg}</div>
            ))}
          </div>

          <div>
            <input
              type="text"
              value={messageInput}
              onChange={onChangeMessage}
              onKeyDown={(e) => {
                if (e.key === "Enter") {
                  submitButton();
                }
              }}
            />
            <button onClick={submitButton} className="btn btn-primary">
              버튼
            </button>
          </div>
        </div>
        <div className="col-3"></div>
      </div>
    </div>
  );
};
export default Chat;
