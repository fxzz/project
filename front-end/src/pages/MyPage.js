import { useHistory } from "react-router-dom";
import Avvvatars from "avvvatars-react"; // npm install avvvatars-react
import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const MyPage = () => {
  const [nickname, setNickname] = useState("");
  const history = useHistory();
  const accessToken = localStorage.getItem("accessToken");
  const accountId = localStorage.getItem("accountId");
  useEffect(() => {
    // 엑세스 토큰이 없으면 로그인 페이지로 이동
    if (!accessToken) {
      history.push("/login");
      return;
    }

    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/users/${accountId}`,
          {
            headers: {
              Authorization: `${accessToken}`,
            },
          }
        );

        setNickname(response.data.data);
      } catch (error) {
        console.error("Error :", error);
      }
    };

    fetchData();
  }, [accessToken, accountId, history]);

  return (
    <div className="row">
      <div className="col-1"></div>
      <div className="col-3">
        <div style={{ marginBottom: "120px" }}></div>
        <div>
          <Avvvatars value={nickname} size={110} />
        </div>
        <div id="page">
          <div className="mt-4">
            <strong>{nickname}</strong>
          </div>
        </div>
        <div id="buttonPage">
          <div>
            <Link className="myPageLink">내가 쓴 글</Link>
          </div>
          <div className="mt-4">
            <Link className="myPageLink">채팅 리스트</Link>
          </div>
          <div className="mt-4">
            <Link className="myPageLink">설정</Link>
          </div>
        </div>
      </div>

      <div className="col-8">
        <div style={{ marginBottom: "120px" }}></div>
        <h4>설정</h4>
      </div>
    </div>
  );
};

export default MyPage;
