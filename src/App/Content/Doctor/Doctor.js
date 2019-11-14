import React from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css";
import Header from '../Header';
import Footer from '../Footer';
import './Doctor.css'

class Doctor extends React.Component {
  render() {
      return (
        <div className="Doctor">
        <Header/>
        <div className="">
            <div className="row welcome">
                <div className="col">
                Welcome
                </div>
            </div>
            <div className="row links">
                <div className="col link">
                One of three columns
                </div>
                <div className="col link">
                One of three columns
                </div>
                <div className="col link">
                One of three columns
                </div>
            </div>
        </div>
        <Footer/>
        </div>
        );
    }
}

export default Doctor;