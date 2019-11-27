import React from 'react';
import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';
import Swal from 'sweetalert2';
import { Button} from 'react-bootstrap';
import withReactContent from 'sweetalert2-react-content';
import axios from 'axios'

import './NewAppointmentDoctor.css'

const SheduleAlert = withReactContent(Swal)

class NewAppointmentDoctor extends React.Component {

  constructor(props){
    super(props);

    this.handleChange = this.handleChange.bind(this);
    this.SendAppointmentRequest = this.SendAppointmentRequest.bind(this);

    this.state = {
        patient: '',
        date: '',
        time: ''
    }
  }

  SendAppointmentRequest = event => {
    event.preventDefault();
      console.log(this.state);
    axios.post("http://localhost:8080/api/doctor/shedule-appointment", this.state)  
    .then((resp) => this.onSuccessHandler(resp))
    .catch((error) => this.onFailureHandler(error))
  }

  onSuccessHandler(resp){
    SheduleAlert.fire({
        title: "Logged in successfully",
        text: ""
    })
  }

  onFailureHandler(error){
    SheduleAlert.fire({
          title: "Log In failed",
          text: error
      })
  }

  handleChange(e) {
    this.setState({...this.state, [e.target.name]: String(e.target.value)});
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
            <form onSubmit={this.SendAppointmentRequest}>
              <div className="form-group row">
                <label htmlFor="colFormLabel" className="col-sm-2 col-form-label">Patient:</label>
                <div className="col-sm-10">
                <select className="custom-select mr-sm-2" name="patient" id="inlineFormCustomSelect" onChange={this.handleChange} >
                  <option defaultValue="0" >Choose...</option>
                  <option value="1">Patient 1</option>
                  <option value="2">Patient 2</option>
                  <option value="3">Patient 3</option>
                </select>
                </div>
              </div>
              <div className="form-group row">
                <label htmlFor="colFormLabel" className="col-sm-2 col-form-label">Date:</label>
                <div className="col-sm-10">
                  <input required type="date" className="form-control" name="date" id="date" placeholder="Choose date"
                    onChange={this.handleChange}/>
                </div>
              </div>
              <div className="form-group row">
                <label htmlFor="colFormLabel" className="col-sm-2 col-form-label">Time:</label>
                <div className="col-sm-10">
                  <input required type="time" className="form-control" name="time" id="time" placeholder="Choose time"
                    onChange={this.handleChange}/>
                </div>
              </div>
              <div className="col-sm">
              <Button type="submit" className="btn create-appointment-btn">Shedule</Button>
              </div>
            </form>
            </div>
            
          </div>
        </div>
      <Footer/>

    </div>
  );
  }
}

export default NewAppointmentDoctor;