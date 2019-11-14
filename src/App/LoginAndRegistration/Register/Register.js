import React from 'react';
import './Register.css'
import logo from '../../../images/med128.png'

class Register extends React.Component {
  render() {
      return (
    <div className="Register">
      <div className="">
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
                        <div className="form-group">
                            <label>First name</label>
                            <input type="text" className="form-control" placeholder="First name"/>
                        </div>
                        <div className="form-group">
                            <label>Last name</label>
                            <input type="text" className="form-control" placeholder="Last name"/>
                        </div>
                        <div className="form-group">
                            <label>Medical number</label>
                            <input type="password" className="form-control" placeholder="Medical number"/>
                        </div>
                        <div className="form-group">
                            <label>Address</label>
                            <input type="text" className="form-control" placeholder="Address"/>
                        </div>
                        <div className="form-group">
                            <label>City</label>
                            <input type="text" className="form-control" placeholder="City"/>
                        </div>
                        <div className="form-group">
                            <label>Country</label>
                            <input type="text" className="form-control" placeholder="Country"/>
                        </div>
                        <div className="form-group">
                            <label>Phone number</label>
                            <input type="text" className="form-control" placeholder="Phone number"/>
                        </div>
                        <a className="btn">Sign Up</a>
                    </form>
                </div>
            </div>
      </div>
    </div>
  );}
}

export default Register;