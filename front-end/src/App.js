import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import NavBar from "./components/NavBar";
import Index from "./pages/Index";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import SignUp from "./pages/SignUp";
import Footer from "./components/Footer";

import PhotoForm from "./components/PhotoForm";
import CusPhoto from "./pages/CusPhoto";
import Login from "./pages/Login";

function App() {
  return (
    <Router>
      <NavBar />
      <div className="container">
        <Switch>
          <Route path="/" exact>
            <Index />
          </Route>
          <Route path="/SignUp" exact>
            <SignUp />
          </Route>
          <Route path="/photo" exact>
            <CusPhoto />
          </Route>
          <Route path="/PhotoForm" exact>
            <PhotoForm />
          </Route>
          <Route path="/Login" exact>
            <Login />
          </Route>
        </Switch>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
