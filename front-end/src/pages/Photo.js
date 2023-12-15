import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import PhotoForm from "../components/PhotoForm";
import axios from "axios";

const Photo = () => {
  const [photos, setPhotos] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/photos")
      .then((response) => {
        setPhotos(response.data.data);
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }, []);
  return (
    <div>
      <PhotoForm />
      <div>
        <div className="row mt-3">
          {photos.map((photo) => (
            <div key={photo.photoId} className="col-md-4 mb-3">
              <div className="card">
                <img
                  src={`http://localhost:8080/api/photos/${photo.newFilename}`}
                  className="card-img-top"
                  alt="..."
                />
                <div className="card-body">
                  <h5 className="card-title">
                    <strong>{photo.title}</strong>
                  </h5>
                  <p className="card-text">{photo.content}</p>
                  <div
                    style={{ display: "flex", justifyContent: "space-between" }}
                  >
                    <span>{photo.nickname}</span>
                    <span>
                      <span style={{ marginRight: "10px" }}>
                        <i className="bi bi-chat-dots ">5</i>
                      </span>
                      <span>
                        <i className="bi bi-heart-fill text-danger">7</i>
                      </span>
                    </span>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Photo;
