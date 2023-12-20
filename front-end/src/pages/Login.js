import { useState } from "react";
import axios from "axios";
import { Card, Form } from "react-bootstrap";
import { Link } from "react-router-dom";

const Login = () => {
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
                  onChange={(e) => {}}
                />
              </Form.Group>

              <Form.Group controlId="formBasicPassword" className="mb-4">
                <Form.Control
                  type="password"
                  placeholder="Password"
                  onChange={(e) => {}}
                />
              </Form.Group>

              <div className="d-flex justify-content-center mb-3">
                <button
                  onClick={(e) => {
                    e.preventDefault();
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
