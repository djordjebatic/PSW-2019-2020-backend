import React from 'react';
import { Link } from 'react-router-dom'
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import './Doctor.css'

import logo from '../../../images/med128.png'

class Doctor extends React.Component {
  render() {
      return (
        <div className="Doctor">
        <Header/>
        <div className="">
            <div className="row welcome-doctor">
                <div className="col-12">
                        <div className="logo-doctor">
                            <img src={logo} alt="logo" />
                        </div>
                        <h3 className="welcome-and-logo">Welcome, Doctor</h3>
                </div>
            </div>
            <div className="row links">
                <div className="col link">
                    <h4>All Patients</h4>
                    <p>Look at the list of all patients in your clinic.</p>
                    <Link to="/patients" className="btn link-btn-doctor">View List</Link>
                </div>
                <div className="col link">
                    <h4>Create New Appointment</h4>
                    <p>Choose date, ordination and type of the appointment.</p>
                    <Link to="/new-appointment-doctor" className="btn link-btn-doctor">Create</Link>
                </div>
                <div className="col link">
                    <h4>Create Absence/Vacation Request</h4>
                    <p>Choose start and end date.</p>
                    <Link to="/absence-request" className="btn link-btn-doctor">Create</Link>
                </div>
            </div>
        </div>
        <Footer/>
        </div>
        );
    }
}

export default Doctor;