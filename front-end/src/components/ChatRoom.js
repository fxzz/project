import axios from "axios";
import React, { useState, useEffect } from "react";

const ChatRoom = ({ senderAccountId, accessToken, getChatRoomName }) => {
  const [chatRoomData, setChatRoomData] = useState([]);

  const handleChatRoomClick = (selectedChatRoom) => {
    getChatRoomName(selectedChatRoom);
  };

  const fetchChatRooms = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/chat-rooms/${senderAccountId}`,

        {
          headers: {
            Authorization: `${accessToken}`,
          },
        }
      );
      setChatRoomData(response.data.data);
      console.log(chatRoomData);
    } catch (error) {}
  };

  useEffect(() => {
    fetchChatRooms();
  }, []);

  return (
    <div>
      <p>대화중인 채팅</p>
      <ul>
        {chatRoomData.map((chatRoom) => (
          <li
            key={chatRoom.chatRoomId}
            onClick={() => handleChatRoomClick(chatRoom.roomName)}
          >
            <p>{chatRoom.title}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ChatRoom;
