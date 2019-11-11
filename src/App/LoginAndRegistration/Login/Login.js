import React from 'react';
import './Login.css'

import logo from '../../../images/med128.png'

function Login() {
  return (
    <div className="Login">
      <div className="container">
            <div className="row">
                <div className="col-4 welcome">
                    <div className="logo">
                        <img alt="logo" src={logo} />
                        <h1 className="title">Clinic Center</h1>
                    </div>
                </div>
                <div className="col-8 login">
                    <form>
                        <div className="form-group">
                            <label>Username</label>
                            <input type="text" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Username"/>
                        </div>
                        <div className="form-group">
                            <label>Password</label>
                            <input type="password" className="form-control" id="exampleInputPassword1" placeholder="Password"/>
                            <small id="emailHelp" className="form-text text-muted"><a href="#">Forgot password?</a></small>
                        </div>
                        <a href="#" className="btn">Log In</a>
                    </form>
                </div>
            </div>
      </div>
    </div>
  );
}

export default Login;