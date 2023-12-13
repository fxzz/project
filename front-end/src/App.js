import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import NavBar from "./components/NavBar";
import Index from "./pages/Index";
import "bootstrap/dist/css/bootstrap.min.css";
import Signup from "./pages/Signup";
import Footer from "./components/Footer";

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
            <Signup />
          </Route>
        </Switch>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
