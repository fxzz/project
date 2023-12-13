import { Carousel } from "react-bootstrap";

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
              <button className="btn btn-dark">시작하기</button>
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
              <button className="btn btn-dark">시작하기</button>
            </div>
          </div>
        </Carousel.Item>
      </Carousel>
      <div>
        <div class="row mt-3">
          <div class="col-md-4">
            <div class="card">
              <img src="/test3.jpg" class="card-img-top" alt="..." />
              <div class="card-body">
                <h5 class="card-title">
                  <strong>휴식</strong>
                </h5>
                <p class="card-text">달콤한 휴식중!!!</p>
                <a href="#" class="btn btn-primary">
                  Go somewhere
                </a>
              </div>
            </div>
          </div>

          <div class="col-md-4">
            <div class="card">
              <img src="/test4.jpg" class="card-img-top" alt="..." />
              <div class="card-body">
                <h5 class="card-title">
                  <strong>한강 왔다</strong>
                </h5>
                <p class="card-text">날이 좋아서 좋다~</p>
                <a href="#" class="btn btn-primary">
                  Go somewhere
                </a>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card">
              <img src="/test5.jpg" class="card-img-top" alt="..." />
              <div class="card-body">
                <h5 class="card-title">
                  <strong>🌹화려한 장미</strong>
                </h5>
                <p class="card-text">
                  우리집 정원 💖 #장미의나라 #꿈나라 #소소한행복
                </p>
                <a href="#" class="btn btn-primary">
                  Go somewhere
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Index;
