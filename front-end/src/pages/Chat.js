import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";

const Chat = () => {
  const location = useLocation();
  const { nickname = "", title = "" } = location.state || {};
  const accessToken = localStorage.getItem("accessToken");
  const [messages, setMessages] = useState([]);
  const [messageInput, setMessageInput] = useState("");
  const [stompClient, setStompClient] = useState(null);

  const connectStomp = () => {
    const socket = new SockJS("http://localhost:8080/ws");
    const stompClient = Stomp.over(socket);

    stompClient.connect(
      {},
      () => {
        console.log("STOMP 연결 성공");
        stompClient.subscribe(`/queue/chat/1`, (chatDto) => {
          const chat = JSON.parse(chatDto.body);
          const message = chat.message;
          console.log(chat);
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
    };
  }, []);

  const sendMessage = () => {
    if (messageInput === "") {
      return;
    }
    const chatDto = {
      sender: "dd",
      message: messageInput,
    };

    stompClient.send(`/app/chat/message/1`, {}, JSON.stringify(chatDto));
    setMessageInput("");
  };

  const onChangeMessage = (e) => {
    setMessageInput(e.target.value);
  };

  return (
    <div>
      <div class="row mt-5">
        <div class="col-3">col</div>
        <div class="col-3">col</div>
        <div class="col-3">
          <div>사용자 이름 {nickname && <div>Nickname: {nickname}</div>}</div>
          <div className="mt-1"> 제목 {title && <div>Title: {title}</div>}</div>
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
                  sendMessage();
                }
              }}
            />
          </div>
        </div>
        <div class="col-3">col</div>
      </div>
    </div>
  );
};
export default Chat;
