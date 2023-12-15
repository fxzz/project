import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import NavBar from "./components/NavBar";
import Index from "./pages/Index";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import Signup from "./pages/Signup";
import Footer from "./components/Footer";

import PhotoForm from "./components/PhotoForm";
import CusPhoto from "./pages/CusPhoto";

function App() {
  return (
    <Router>
      <NavBar />
      <div className="container">
        <Switch>
          <Route path="/" exact>
            <Index />
          </Route>
          <Route path="/signup" exact>
            <Signup />
          </Route>
          <Route path="/photo" exact>
            <CusPhoto />
          </Route>
          <Route path="/PhotoForm" exact>
            <PhotoForm />
          </Route>
        </Switch>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
