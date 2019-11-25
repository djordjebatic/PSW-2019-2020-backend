import React from 'react';
import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';

import {Button} from 'react-bootstrap'

import './NewAppointmentDoctor.css'

class NewAppointmentDoctor extends React.Component {


  sendAppointmentRequest(){

  }

  render() {
    
  return (
    <div className="NewAppointmentDoctor">
      <Header/>
        <div className="">
        <div className="row">
            <div className="col-sm new-appointment-header">
              <h3>Sheduling Appointment</h3>
            </div>
          </div>
          <div className="row new-appointment-form">
            <div className="col-sm">
            <form onSubmit={this.sendAppointmentRequest}>
              <div className="form-group row">
                <label htmlFor="colFormLabel" className="col-sm-2 col-form-label">Patient:</label>
                <div className="col-sm-10">
                <select className="custom-select mr-sm-2" id="inlineFormCustomSelect">
                  <option selected>Choose...</option>
                  <option value="1">Patient 1</option>
                  <option value="2">Patient 2</option>
                  <option value="3">Patient 3</option>
                </select>
                </div>
              </div>
              <div className="form-group row">
                <label htmlFor="colFormLabel" className="col-sm-2 col-form-label">Date:</label>
                <div className="col-sm-10">
                  <input type="date" className="form-control" id="date" placeholder="Choose date"/>
                </div>
              </div>
              <div className="form-group row">
                <label htmlFor="colFormLabel" className="col-sm-2 col-form-label">Time:</label>
                <div className="col-sm-10">
                  <input type="time" className="form-control" id="time" placeholder="Choose time"/>
                </div>
              </div>
            </form>
            </div>
            <div className="col-sm">
              <Button type="submit" className="btn create-appointment-btn">Create request</Button>
            </div>
          </div>
        </div>
      <Footer/>

    </div>
  );
  }
}

export default NewAppointmentDoctor;