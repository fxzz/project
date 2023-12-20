import { useState } from "react";
import axios from "axios";
import { Card, Form } from "react-bootstrap";
import { Link } from "react-router-dom";

const SignUp = () => {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [validationId, setValidationId] = useState(null);
  const [validationPassword, setValidationPassword] = useState(null);
  const [validationEmail, setValidationEmail] = useState(null);
  //TODO 나중에 validation 상태값 유지하는 문제 수정
  const onSubmit = () => {
    const formData = new FormData();
    formData.append("username", id);
    formData.append("password", password);
    formData.append("email", email);

    axios
      .post("http://localhost:8080/api/users", formData)
      .then(() => {})
      .catch((error) => {
        if (error.response) {
          const errorMessages = error.response.data.errors;

          errorMessages.forEach((error) => {
            const fieldName = error.field;
            const errorMessage = error.defaultMessage;

            switch (fieldName) {
              case "username":
                setValidationId(errorMessage);
                break;
              case "password":
                setValidationPassword(errorMessage);
                break;
              case "email":
                setValidationEmail(errorMessage);
                break;
              default:
                break;
            }
          });
        }
      });
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
                <Form.Text className="text-muted">
                  {validationId != null && validationId}
                </Form.Text>
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
                <Form.Text className="text-muted">
                  {validationPassword != null && validationPassword}
                </Form.Text>
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
                <Form.Text className="text-muted">
                  {validationEmail != null && validationEmail}
                </Form.Text>
              </Form.Group>

              <div className="d-flex justify-content-center mb-3">
                <button
                  onClick={(e) => {
                    e.preventDefault();
                    onSubmit();
                  }}
                  className="btn btn-primary"
                >
                  가입하기
                </button>
              </div>
            </Form>
            <div>
              <Link
                id="link"
                to="/Login"
                className="d-flex justify-content-center"
              >
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
