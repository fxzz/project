import { useHistory } from "react-router-dom";

const MyPage = () => {
  const history = useHistory();
  const accessToken = localStorage.getItem("accessToken");

  if (!accessToken) {
    // 엑세스 토큰이 없으면 로그인 페이지로 이동
    history.push("/login");
  }

  return (
    <div>
      <div>my page</div>
    </div>
  );
};

export default MyPage;
