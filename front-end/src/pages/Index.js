import { Carousel } from "react-bootstrap";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

const Index = () => {
  return (
    <div>
      <Carousel>
        <Carousel.Item>
          <div id="carousel2">
            <div className="wrapText">
              <h1>여러분의 아름다운 순간을 공유하세요. </h1>
              <div className="d-none d-md-block">
                <p>
                  간단한 몇 가지 클릭만으로 여러분의 사진을 누구에게나 손쉽게
                  공유할 수 있습니다.
                </p>
              </div>
              <Link to="/photo" className="btn btn-dark">
                시작하기
              </Link>
            </div>
          </div>
        </Carousel.Item>
        <Carousel.Item>
          <div id="carousel1">
            <div className="wrapText">
              <h1>당신의 감동적인 사진을 기다립니다!</h1>
              <div className="d-none d-md-block">
                <p>
                  아름다운 순간을 공유하세요! 여기서 사진을 업로드하고 놀라운
                  순간을 나눠보세요.
                </p>
              </div>
              <Link to="/photo" className="btn btn-dark">
                시작하기
              </Link>
            </div>
          </div>
        </Carousel.Item>
      </Carousel>
      <div>
        <div className="row mt-3">
          <div className="col-md-4">
            <div className="card">
              <img src="/test3.jpg" className="card-img-top" alt="..." />
              <div className="card-body">
                <h5 className="card-title">
                  <strong>휴식</strong>
                </h5>
                <p className="card-text">달콤한 휴식중!!!</p>
                <div
                  style={{ display: "flex", justifyContent: "space-between" }}
                >
                  <span>하루</span>
                  <span>
                    <span style={{ marginRight: "10px" }}>
                      <i className="bi bi-chat-dots ">8</i>
                    </span>
                    <span>
                      <i className="bi bi-heart-fill text-danger">3</i>
                    </span>
                  </span>
                </div>
              </div>
            </div>
          </div>

          <div className="col-md-4">
            <div className="card">
              <img src="/test4.jpg" className="card-img-top" alt="..." />
              <div className="card-body">
                <h5 className="card-title">
                  <strong>한강 왔다</strong>
                </h5>
                <p className="card-text">날이 좋아서 좋다~</p>
                <div
                  style={{ display: "flex", justifyContent: "space-between" }}
                >
                  <span>나라짱</span>
                  <span>
                    <span style={{ marginRight: "10px" }}>
                      <i className="bi bi-chat-dots ">3</i>
                    </span>
                    <span>
                      <i className="bi bi-heart-fill text-danger">7</i>
                    </span>
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div className="col-md-4">
            <div className="card">
              <img src="/test5.jpg" className="card-img-top" alt="..." />
              <div className="card-body">
                <h5 className="card-title">
                  <strong>🌹화려한 장미</strong>
                </h5>
                <p className="card-text">
                  우리집 정원 💖 #장미의나라 #꿈나라 #소소한행복
                </p>
                <div
                  style={{ display: "flex", justifyContent: "space-between" }}
                >
                  <span>서김추</span>
                  <span>
                    <span style={{ marginRight: "10px" }}>
                      <i className="bi bi-chat-dots ">7</i>
                    </span>
                    <span>
                      <i className="bi bi-heart-fill text-danger">9</i>
                    </span>
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Index;
