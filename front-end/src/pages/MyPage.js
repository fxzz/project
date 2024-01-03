import { useHistory } from "react-router-dom";
import Avvvatars from "avvvatars-react"; // npm install avvvatars-react
import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { Form, Button } from "react-bootstrap";

const MyPage = () => {
  const [nickname, setNickname] = useState("");
  const history = useHistory();
  const accessToken = localStorage.getItem("accessToken");
  const accountId = localStorage.getItem("accountId");
  const [newNickname, setNewNickname] = useState("");
  const [msg, setMsg] = useState("");

  const handleLogout = () => {
    localStorage.removeItem("accessToken");
    localStorage.removeItem("accountId");

    history.push("/login");
  };

  const requestData = {
    nickname: newNickname,
  };
  const handleChangeNickname = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.patch(
        `http://localhost:8080/api/users/${accountId}`,
        requestData,
        {
          headers: {
            Authorization: `${accessToken}`,
          },
        }
      );

      setMsg("닉네임을 성공적으로 변경했습니다.");
      setNickname("재랜더링");
    } catch (error) {
      if (error.response && error.response.data && error.response.data.errors) {
        const errorMessage = error.response.data.errors[0].defaultMessage;
        setMsg(errorMessage);
      }
    }
  };

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
  }, [nickname, accessToken, history, accountId]);

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
          <div className="mt-4">
            <Link className="myPageLink" to="#">
              설정
            </Link>
            <br />
            <br />
            <Link className="myPageLink" to="#" onClick={handleLogout}>
              로그아웃
            </Link>
          </div>
        </div>
      </div>

      <div className="col-8">
        <div style={{ marginBottom: "120px" }}></div>
        <h4 className="mb-5">설정</h4>
        <Form>
          <Form.Group controlId="formBasicEmail">
            <Form.Label>닉네임 변경</Form.Label>
            <Form.Control
              type="text"
              placeholder="변경할 닉네임을 입력해 주세요"
              value={newNickname}
              onChange={(e) => setNewNickname(e.target.value)}
            />
            <Form.Text className="text-muted">{msg}</Form.Text>
          </Form.Group>

          <br />
          <Button
            type="submit"
            onClick={handleChangeNickname}
            variant="primary"
          >
            저장
          </Button>
        </Form>
      </div>
    </div>
  );
};

export default MyPage;
