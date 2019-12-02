import React from 'react';
import './ClinicAdmin.css'
import {Link} from 'react-router-dom'
import Header from '../Header/Header';
import Footer from '../Footer/Footer';


import logo from '../../../images/med128.png'

class ClinicAdmin extends React.Component {
  render() {
    
  return (
    <div className="ClinicAdmin">
      <Header/>
        <div className="">
            <div className="row welcome-admin">
                <div className="col-12">
                        <div className="logo-admin">
                            <img src={logo} alt="logo" />
                        </div>
                        <h3 className="welcome-and-logo">Welcome, Clinic Admin</h3>
                </div>
            </div>
            <div className="row links">
                <div className="col link">
                    <h4>All Registration Requests</h4>
                    <p>Look at the list of all patient registration requests.</p>
                    <Link to="/registration-requests" className="btn link-btn-admin">View List</Link>
                </div>
                <div className="col link">
                    <h4>Create a Quick Reservation</h4>
                    <p>Create a quick reservation for predefined medical appointment.</p>
                    <a href="#" className="btn link-btn-admin">Create</a>
                </div>
            </div>
        </div>
        <Footer/>

    </div>
  );
  }
}

export default ClinicAdmin;