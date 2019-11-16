import React from 'react';
import { Link } from 'react-router-dom'
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import './MedicalRecord.css'


class MedicalRecord extends React.Component{
    render(){
        return(

            <div className="MedicalRecord">
                <Header/>
                <div>

                <div className="medical-record-title">Medical record</div>

                </div>
                <Footer/>
            </div>

        );
    }
}

export default MedicalRecord;