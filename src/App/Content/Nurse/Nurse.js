import React from 'react';
import './Nurse.css'
import "react-table/react-table.css";
import {Link} from 'react-router-dom'
import Header from '../Header/Header';
import Footer from '../Footer/Footer';


import logo from '../../../images/med128.png'

class Nurse extends React.Component {
  render() {
    
  return (
    <div className="Nurse">
      <Header/>
        <div className="">
            <div className="row welcome-nurse">
                <div className="col-12">
                        <div className="logo-nurse">
                            <img src={logo} alt="logo" />
                        </div>
                        <h3 className="welcome-and-logo">Welcome, Nurse</h3>
                </div>
            </div>
            <div className="row links">
                <div className="col link">
                    <h4>All Patients</h4>
                    <p>Look at the list of all patients in your clinic.</p>
                    <Link to="/patients" class="btn link-btn-doctor">View List</Link>
                </div>
                <div className="col link">
                    <h4>Prescriptions to Authenticate</h4>
                    <p>Look at the list of all prescriptions.</p>
                    <Link to="/absence-request" class="btn link-btn-doctor">View List</Link>
                </div>
            </div>
        </div>
        <Footer/>

    </div>
  );
  }
}

export default Nurse;