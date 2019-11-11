import React from 'react';
import './Register.css'
import logo from '../../../images/med128.png'

function Register() {
  return (
    <div className="Register">
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
                            <input type="text" className="form-control" aria-describedby="emailHelp" placeholder="Username"/>
                        </div>
                        <div className="form-group">
                            <label>E-mail address</label>
                            <input type="email" className="form-control" aria-describedby="emailHelp" placeholder="E-mail address"/>
                        </div>
                        <div className="form-group">
                            <label>Password</label>
                            <input type="password" className="form-control" placeholder="Password"/>
                        </div>
                        <div className="form-group">
                            <label>Confirm Password</label>
                            <input type="password" className="form-control" placeholder="Confirm Password"/>
                        </div>
                        <a className="btn">Sign Up</a>
                    </form>
                </div>
            </div>
      </div>
    </div>
  );
}

export default Register;