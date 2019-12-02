import React from 'react';
import './CCAdmin.css'
import "react-table/react-table.css";
import {Link} from 'react-router-dom'
import Header from '../Header/Header';
import Footer from '../Footer/Footer';


import logo from '../../../images/med128.png'

class CCAdmin extends React.Component {
  render() {
    
  return (
    <div className="CCAdmin">
      <Header/>
        <div className="">
            <div className="row welcome-nurse">
                <div className="col-12">
                        <div className="logo-nurse">
                            <img src={logo} alt="logo" />
                        </div>
                        <h3 className="welcome-and-logo">Welcome, Clinic Center Admin</h3>
                </div>
            </div>
            <div className="row links">
                <div className="col link">
                    <h4>All Registration Requests</h4>
                    <p>Look at the list of all patient registration requests.</p>
                    <Link to="/registration-requests" class="btn link-btn-doctor">View List</Link>
                </div>
                <div className="col link">
                    <h4>Create a new Clinic or Clinic Admin</h4>
                    <a href="#" class="btn link-btn-doctor">Create</a>
                </div>
            </div>
        </div>
        <Footer/>

    </div>
  );
  }
}

export default CCAdmin;