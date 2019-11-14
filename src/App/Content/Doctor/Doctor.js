import React from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css";
import Header from '../Header';
import Footer from '../Footer';
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
                    <a class="btn link-btn-doctor">View List</a>
                </div>
                <div className="col link">
                    <h4>Create New Appointment</h4>
                    <p>Choose date, ordination and type of the appointment.</p>
                    <a class="btn link-btn-doctor">Create</a>
                </div>
                <div className="col link">
                Create Absence/Vacation Request
                </div>
            </div>
        </div>
        <Footer/>
        </div>
        );
    }
}

export default Doctor;