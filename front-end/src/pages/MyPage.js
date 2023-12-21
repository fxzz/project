import { useHistory } from "react-router-dom";
import Avvvatars from "avvvatars-react"; // npm install avvvatars-react
import { useEffect, useState } from "react";
import axios from "axios";

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
      <div className="col-3">
        <Avvvatars value={nickname} size={130} />
        <div>{nickname}</div>
      </div>
      <div className="col-9">col-4</div>
    </div>
  );
};

export default MyPage;
