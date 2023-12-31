import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

const NavBar = () => {
  return (
    <div>
      <ul className="nav justify-content-center">
        <li className="nav-item">
          <Link
            className="btn btn-outline-info border border-white bg-white"
            to="/"
          >
            Main
          </Link>
        </li>
        <li className="nav-item">
          <Link
            className="btn btn-outline-info border border-white bg-white"
            to="/photos"
          >
            Photo
          </Link>
        </li>
        <li className="nav-item">
          <Link
            className="btn btn-outline-info border border-white bg-white"
            to="/Chat"
          >
            Talk
          </Link>
        </li>
        <li className="nav-item">
          <Link
            className="btn btn-outline-info border border-white bg-white"
            to="/MyPage"
          >
            MyPage
          </Link>
        </li>
      </ul>
    </div>
  );
};

export default NavBar;
