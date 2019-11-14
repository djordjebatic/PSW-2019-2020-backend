import React from 'react';
import './App.css';
import {BrowserRouter } from 'react-router-dom'
import {Switch, Route} from 'react-router'
import Register from './LoginAndRegistration/Register/Register'
import Login from './LoginAndRegistration/Login/Login'
import Nurse from './Content/Nurse/Nurse'
import Doctor from './Content/Doctor/Doctor'

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/nurse">
            <Nurse />
          </Route>
          <Route path="/doctor">
            <Doctor />
          </Route>
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
