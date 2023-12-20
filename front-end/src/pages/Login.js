import { useState } from "react";
import axios from "axios";
import { Card, Form } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useHistory } from "react-router-dom";

const Login = () => {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();

  const onSubmit = () => {
    const formData = new FormData();
    formData.append("username", id);
    formData.append("password", password);

    // const accessToken = localStorage.getItem("accessToken");
    //    headers: {
    //     Authorization: `Bearer ${accessToken}`,
    // },
    axios
      .post("http://localhost:8080/api/login", formData)
      .then((response) => {
        const accessToken = response.data.data;
        localStorage.setItem("accessToken", accessToken);
        history.push("/MyPage");
      })
      .catch((error) => {});
  };
  return (
    <div>
      <Card style={{ width: "27rem" }} className="mx-auto mt-5">
        <Card.Body>
          <Card.Body>
            <Card.Title className="mb-4">
              <strong>Login</strong>
            </Card.Title>
            <Form>
              <Form.Group controlId="formBasicId" className="mb-4">
                <Form.Control
                  type="text"
                  placeholder="ID"
                  onChange={(e) => {
                    setId(e.target.value);
                  }}
                />
              </Form.Group>

              <Form.Group controlId="formBasicPassword" className="mb-4">
                <Form.Control
                  type="password"
                  placeholder="Password"
                  onChange={(e) => {
                    setPassword(e.target.value);
                  }}
                />
              </Form.Group>

              <div className="d-flex justify-content-center mb-3">
                <button
                  onClick={(e) => {
                    e.preventDefault();
                    onSubmit();
                  }}
                  className="btn btn-primary"
                >
                  로그인
                </button>
              </div>
            </Form>

            <div>
              <Link
                id="link"
                to="/SignUp"
                className=" d-flex justify-content-center"
              >
                새 계정 만들기
              </Link>
            </div>
          </Card.Body>
        </Card.Body>
      </Card>
    </div>
  );
};
export default Login;
