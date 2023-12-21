import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

const DetailsPhoto = () => {
  const { photoId } = useParams();
  const [id, setPhotoId] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [nickname, setNickname] = useState("");
  const [newFilename, setNewFilename] = useState("");
  const [createdAt, setCreatedAt] = useState("");
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/photos/${photoId}`
        );

        setPhotoId(response.data.data.photoId);
        setTitle(response.data.data.title);
        setContent(response.data.data.content);
        setNickname(response.data.data.nickname);
        setNewFilename(response.data.data.newFilename);
        setCreatedAt(response.data.data.createdAt);
      } catch (error) {
        console.error("Error :", error);
      }
    };

    fetchData();
  }, [photoId]);

  return (
    <>
      <div className="mt-5">
        <div className="row">
          <div className="col-2"></div>
          <div className="col-4">
            <div>
              {newFilename && (
                <img
                  src={`http://localhost:8080/api/file/${newFilename}`}
                  alt="..."
                  style={{ width: "100%" }}
                />
              )}
            </div>
          </div>
          <div className="col-4">
            <div>{createdAt}</div>
            <hr />

            <div className="mt-5">
              <h5>
                <strong>{title}</strong>
              </h5>
            </div>
            <div className="mt-5">
              <div>{content}</div>
            </div>
            <div className="mt-5">
              <div className="d-flex">{nickname}</div>
            </div>
            <div className="mt-5"></div>
            <hr />
            <div className="d-flex">
              <button type="button" className="btn btn-dark rounded-0 ms-1">
                <i className="bi bi-chat-dots "></i> 채팅
              </button>

              <button type="button" className="btn btn-danger rounded-0 ms-1">
                <i className="bi bi-heart-fill"></i> 좋아요
              </button>
            </div>
          </div>

          <div className="col-2"></div>
        </div>
      </div>
    </>
  );
};

export default DetailsPhoto;
