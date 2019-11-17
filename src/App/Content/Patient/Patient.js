import React from 'react';
import { Link } from 'react-router-dom'
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import './Patient.css'

import logo from '../../../images/med128.png'

class Patient extends React.Component {
    render() {
        return(
            <div className="Patient">
                <Header/>
                <div className="">
                    <div className="row welcome-patient">
                        <div className="col-12">
                                <div className="logo-patient">
                                    <img src={logo} alt="logo" />
                                </div>
                                <h3 className="welcome-and-logo-patient">Welcome, (patient)!</h3>
                        </div>
                    </div> 

                    <div className="row links">
                        <div className="col link">
                            <h4>All clinics</h4>
                            <p>See all clinics.</p>
                            <Link to="/clinics" class="btn link-btn-patient">View clinics</Link>
                        </div>
                        <div className="col link">
                            <h4>History of appointments and surgeries</h4>
                            <p>See all previous appointments and surgeries.</p>
                            <Link to="/appointment-history" class="btn link-btn-patient">View </Link>
                        </div>
                        <div className="col link">
                            <h4>Medical record</h4>
                            <p>See personal medical record.</p>
                            <Link to="/medical-record" class="btn link-btn-patient">View</Link>
                        </div>
                    </div>



                </div>
                <Footer/>
            </div>

        );
    }
}

export default Patient;