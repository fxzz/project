import { useState } from "react";
import axios from "axios";
import { Modal, Button, Form } from "react-bootstrap";
import ReCAPTCHA from "react-google-recaptcha";

const PhotoForm = () => {
  const [showModal, setShowModal] = useState(false);
  const [captchaValue, setCaptchaValue] = useState("");
  const [image, setImage] = useState(null);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const [validationTitle, setValidationTitle] = useState(null);
  const [validationContent, setValidationContent] = useState(null);
  const [validationImage, setValidationImage] = useState(null);

  const handleShow = () => setShowModal(true);
  const handleClose = () => window.location.replace("/photo");
  const handleImage = (e) => {
    setImage(e.target.files[0]);
  };

  const handleCaptchaChange = (value) => {
    setCaptchaValue(value || "");
  };

  const onSubmit = () => {
    // 구글캡차
    if (captchaValue) {
      const formData = new FormData();
      formData.append("title", title);
      formData.append("content", content);
      if (image !== null) {
        formData.append("image", image);
      }
      axios
        .post("http://localhost:8080/api/photos", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          window.location.replace("/photo");
        })
        .catch((error) => {
          if (error.response) {
            const errors = error.response.data.errors;

            errors.forEach((err) => {
              switch (err.field) {
                case "title":
                  setValidationTitle(err.defaultMessage);
                  break;
                case "content":
                  setValidationContent(err.defaultMessage);
                  break;
                case "image":
                  setValidationImage(err.defaultMessage);
                  break;

                default:
                  break;
              }
            });
          }
        });
    } else {
      alert("reCAPTCHA를 통과해야 합니다.");
    }
  };
  return (
    <div>
      <button className="btn btn-dark" onClick={handleShow}>
        공유하기
      </button>

      <Modal show={showModal} onHide={handleClose} scrollable centered>
        <Modal.Header closeButton>
          <Modal.Title>
            <h3>사진을 등록해 보세요.</h3>
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>제목:</p>
          <Form.Control
            type="text"
            placeholder={validationTitle}
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
            placeholder={validationContent}
            onChange={(e) => {
              setContent(e.target.value);
            }}
          />
        </Modal.Body>
        <Modal.Body>
          <p>이미지 올리기:</p>
          <input type="file" onChange={handleImage} />
          {validationImage && <p style={{ color: "red" }}>{validationImage}</p>}
        </Modal.Body>
        <Modal.Body>
          <ReCAPTCHA
            sitekey="6LdVZkIoAAAAAHqqBx2hHpwkBIKn_SIVrdrMH25n"
            onChange={handleCaptchaChange}
          />
        </Modal.Body>

        <Modal.Footer
          style={{ display: "flex", justifyContent: "space-between" }}
        >
          <strong>일부 특수 문자는 사용 불가능합니다.</strong>
          <Button variant="primary" onClick={onSubmit}>
            등록
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default PhotoForm;
