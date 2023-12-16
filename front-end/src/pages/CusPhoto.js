import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import PhotoForm from "../components/PhotoForm";
import axios from "axios";

const CusPhoto = () => {
  const [photoData, setPhotoData] = useState([]);
  const [error, setError] = useState(null);
  const [nextCursorRequest, setNextCursorRequest] = useState(null);

  const truncateText = (text, maxLength) => {
    if (text.length > maxLength) {
      return text.substring(0, maxLength) + "...";
    }
    return text;
  };

  const fetchData = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/photos", {
        params: {
          size: 9,
          // nextCursorRequest가 존재할 경우 photoId를 포함시킵니다.
          //const array1 = [1, 2, 3];   const array2 = [...array1, 4, 5]; // [1, 2, 3, 4, 5]
          ...(nextCursorRequest ? { photoId: nextCursorRequest.photoId } : {}),
        },
      });

      // API 응답이 유효하고 content가 배열인 경우에만 처리합니다.
      if (response.data && Array.isArray(response.data.data.content)) {
        // setPhotoData 함수를 사용하여 현재 데이터를 업데이트합니다.
        setPhotoData((prevData) => {
          // 중복되지 않은 데이터를 필터링합니다.
          const uniqueData = response.data.data.content.filter(
            (newPhoto) =>
              // 현재 데이터에 존재하지 않는 경우만 필터링합니다.
              !prevData.some(
                (prevPhoto) => prevPhoto.photoId === newPhoto.photoId
              )
          );
          // 현재 데이터와 새로운 데이터를 합쳐서 반환합니다.
          return [...prevData, ...uniqueData];
        });

        setNextCursorRequest(response.data.data.nextCursorRequest);
      } else {
        setError("Invalid response structure");
      }
    } catch (error) {
      setError(error.message);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  if (error) {
    return <div>Error: {error}</div>;
  }
  return (
    <div>
      <PhotoForm />
      <div>
        <div className="row mt-3">
          {photoData.map((photo) => (
            <div key={photo.photoId} className="col-md-4 mb-3">
              <div className="card">
                <img
                  src={`http://localhost:8080/api/photos/${photo.newFilename}`}
                  className="card-img-top"
                  alt="..."
                />
                <div className="card-body">
                  <h5 className="card-title">
                    <strong>{truncateText(photo.title, 15)}</strong>
                  </h5>
                  <p className="card-text">{truncateText(photo.content, 19)}</p>
                  <div
                    style={{ display: "flex", justifyContent: "space-between" }}
                  >
                    <span>{photo.nickname}</span>
                    <span>
                      <span style={{ marginRight: "10px" }}>
                        <i className="bi bi-chat-dots ">0</i>
                      </span>
                      <span>
                        <i className="bi bi-heart-fill text-danger">0</i>
                      </span>
                    </span>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
      {nextCursorRequest && nextCursorRequest.photoId !== -1 ? (
        <div className="text-center mt-3">
          <button className="btn btn-dark" onClick={fetchData}>
            더 보기
          </button>
        </div>
      ) : (
        <div className="text-center mt-3">
          <p>사용자님들이 사진을 올리고 있어요.</p>
        </div>
      )}
    </div>
  );
};

export default CusPhoto;
