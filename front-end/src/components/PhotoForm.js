import { useState } from "react";
import axios from "axios"; // npm install axios > import
import { useHistory } from "react-router-dom";
import { Modal, Button, Form } from "react-bootstrap";

const PhotoForm = () => {
  const [showModal, setShowModal] = useState(false);

  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const handleShow = () => setShowModal(true);
  const handleClose = () => setShowModal(false);

  const onSubmit = () => {
    axios
      .post(
        "http://localhost:8080/api/photos",
        { title: title, content: content },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
      .then(() => {})
      .catch((error) => {
        // 에러 처리
        console.error("Error:", error);
      });
  };
  return (
    <div>
      <button className="btn btn-dark" onClick={handleShow}>
        Post
      </button>

      <Modal
        show={showModal}
        backdrop="static"
        onHide={handleClose}
        scrollable
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title>
            <h3>사진을 등록해 보세요.</h3>
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>제목:</p>
          <Form.Control
            type="text"
            onChange={(e) => {
              setTitle(e.target.value);
            }}
          />
        </Modal.Body>
        <Modal.Body>
          <p>내용:</p>
          <Form.Control
            as="textarea"
            rows={3}
            onChange={(e) => {
              setContent(e.target.value);
            }}
          />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleShow}>
            이미지 올리기
          </Button>
          <Button variant="primary" onClick={onSubmit}>
            등록
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default PhotoForm;
