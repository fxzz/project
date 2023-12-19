import { useState } from "react";
import axios from "axios";
import { Card, Form } from "react-bootstrap";
import { Link } from "react-router-dom";

const SignUp = () => {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");

  const onSubmit = () => {
    const formData = new FormData();
    formData.append("username", id);
    formData.append("password", password);
    formData.append("email", email);

    axios
      .post("http://localhost:8080/api/users", formData)
      .then(() => {})
      .catch((error) => {});
  };
  return (
    <div>
      <Card style={{ width: "27rem" }} className="mx-auto mt-5">
        <Card.Body>
          <Card.Title className="text-center">
            <strong>새 계정 만들기</strong>
            <div style={{ fontSize: "15px" }}>쉽게 가입할 수 있습니다.</div>
          </Card.Title>
          <hr />
          <Card.Body>
            <Form>
              <Form.Group controlId="formBasicId" className="mb-4">
                <Form.Label>아이디</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="ID"
                  onChange={(e) => {
                    setId(e.target.value);
                  }}
                />
                {/* <Form.Text className="text-muted">
                  에러 있을때 에러메세지 보여주기
                </Form.Text> */}
              </Form.Group>

              <Form.Group controlId="formBasicPassword" className="mb-4">
                <Form.Label>비밀번호</Form.Label>
                <Form.Control
                  type="password"
                  placeholder="Password"
                  onChange={(e) => {
                    setPassword(e.target.value);
                  }}
                />
                {/* <Form.Text className="text-muted">
                  에러 있을때 에러메세지 보여주기
                </Form.Text> */}
              </Form.Group>

              <Form.Group controlId="formBasicEmail" className="mb-4">
                <Form.Label>이메일</Form.Label>
                <Form.Control
                  type="Email"
                  placeholder="Email"
                  onChange={(e) => {
                    setEmail(e.target.value);
                  }}
                />
                {/* <Form.Text className="text-muted">
                  에러 있을때 에러메세지 보여주기
                </Form.Text> */}
              </Form.Group>

              <div className="d-flex justify-content-center mb-3">
                <button
                  onClick={onSubmit}
                  type="submit"
                  className="btn btn-primary"
                >
                  Submit
                </button>
              </div>
            </Form>
            <div>
              <Link to="" className="d-flex justify-content-center">
                이미 계정이 있으신가요?
              </Link>
            </div>
          </Card.Body>
        </Card.Body>
      </Card>
    </div>
  );
};

export default SignUp;
