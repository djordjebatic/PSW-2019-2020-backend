import React from 'react';
import { Link } from 'react-router-dom'
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import './ClinicPage.css'

class ClinicPage extends React.Component {
    render() {
        return(
            
            <div className="Clinic-page">
                <Header/>

                <div className="clinic-page-title">Clinic page</div>

                <div className="btn-predefined-exam">
                    <Link to="/predefined-examinations" className="btn link-btn-patient predefined-btn">Predefined examinations</Link>
                </div>

                <Footer/>
            </div>

            );
        }
    }
    
export default ClinicPage;