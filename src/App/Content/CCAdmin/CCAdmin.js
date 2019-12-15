import React from 'react';
import './CCAdmin.css'
import "react-table/react-table.css";
import {Link} from 'react-router-dom'
import Header from '../Header/Header';
import Footer from '../Footer/Footer';


import logo from '../../../images/med128.png'
import NonCCAdmins from './Tables/NonCCAdmins';

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
                    <h4>Registration Requests</h4>
                    <p>Look at the list of all patient registration requests.</p>
                    <Link to="/registration-requests" class="btn link-btn-doctor">View List</Link>
                </div>
                <div className="col link">
                    <h4>Register new Clinic Center Admin</h4>
                    <p>Fill out a registration form.</p>
                    <a href="/register-ccadmin" class="btn link-btn-doctor">Create</a>
                </div>
                <div className="col link">
                    <h4>Menage Diagnosis</h4>
                    <p>Create new, edit or delete diagnosis.</p>
                    <a href="/diagnosis" class="btn link-btn-doctor">View List</a>
                </div>
                <div className="col link">
                    <h4>Manage Drugs</h4>
                    <p>Create new, edit or delete drugs.</p>
                    <Link to="/drugs" class="btn link-btn-doctor">View List</Link>
                </div>
            </div>
        </div>
        <Footer/>

    </div>
  );
  }
}

export default CCAdmin;