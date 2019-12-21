import React from 'react';
import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';
import Swal from 'sweetalert2';
import { Button } from 'react-bootstrap';
import withReactContent from 'sweetalert2-react-content';
import axios from 'axios';
import {NotificationManager} from 'react-notifications';
import { withRouter } from 'react-router-dom';

import './NewAppointmentDoctor.css';

const SheduleAlert = withReactContent(Swal)

class NewAppointmentDoctor extends React.Component {

  constructor(props) {
    super(props);

    this.handleChange = this.handleChange.bind(this);
    this.SendAppointmentRequest = this.SendAppointmentRequest.bind(this);

    this.state = {
      patients: [],
      patient: '',
      date: '',
      time: '',
      doctorId: '',
      type: '0'
    }
  }

  SendAppointmentRequest = event => {
    event.preventDefault();
    var token = localStorage.getItem('token');
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    axios.post("http://localhost:8080/api/doctor/schedule-appointment", {
      patient: this.state.patient,
      date: this.state.date,
      time: this.state.time,
      doctor: this.state.doctorId,
      type: this.state.type
    })
      .then((resp) => {
        console.log(this.state);
        NotificationManager.success('You have scheduled appointment succesfully!', 'Success!', 4000);
        this.props.history.push('/reservation-requests');
      })
      .catch((error) => this.onFailureHandler(error))
  }

  onSuccessHandler(resp) {
    SheduleAlert.fire({
      title: "Scheduled successfully",
      text: ""
    })
  }

  onFailureHandler(error) {
    SheduleAlert.fire({
      title: "Scheduling failed",
      text: error
    })
  }

  handleChange(e) {
    this.setState({ ...this.state, [e.target.name]: String(e.target.value) });
  }

  componentDidMount() {
    var token = localStorage.getItem('token');
    axios.get("http://localhost:8080/api/patients")
      .then(response => {
        let tmpArray = []
        for (var i = 0; i < response.data.length; i++) {
          tmpArray.push(response.data[i])
        }

        this.setState({
          patients: tmpArray
        })
      })
      .catch((error) => console.log(error))

    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    axios.get("http://localhost:8080/auth/getMyUser")
      .then(response => {
        this.setState({
          doctorId: response.data.id
        })
      })
      .catch((error) => console.log(error))
  }

  render() {
    return (
      <div className="NewAppointmentDoctor">
        <Header />
        <div className="row">
          <div className="col-10">
            <div className="row">
              <div className="col-sm new-appointment-header">
                <h3>Scheduling Appointment</h3>
              </div>
            </div>
            <div className="row new-appointment-form">
              <div className="col-sm">
                <form onSubmit={this.SendAppointmentRequest}>
                  <div className="row">
                    <div className="col-6">
                      <div className="form-group">
                        <div className="col">
                          <label htmlFor="patient">Patient:</label>
                          <select required className="custom-select mr-sm-2" name="patient" id="patient" onChange={this.handleChange} >
                            <option defaultValue="0" >Choose...</option>
                            {this.state.patients.map((patient, index) => (
                              <option key={patient.id} value={patient.id}>{patient.firstName} {patient.lastName}</option>
                            ))}
                          </select>
                        </div>
                      </div>
                    </div>
                    <div className="col-3">
                      <div className="form-group">
                          <label htmlFor="date">Date:</label>
                          <input required type="date" className="form-control" name="date" id="date" placeholder="Choose date"
                            onChange={this.handleChange} />
                      </div>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-6">
                      <div className="form-group">
                        <div className="row">
                          <div className="col-6">
                            <label htmlFor="time">Start Time:</label>
                            <input required type="time" className="form-control" name="time" id="time" placeholder="Choose start time"
                              onChange={this.handleChange} />
                          </div>
                          <div className="col-6">
                            <label htmlFor="time">End Time:</label>
                            <input required type="time" className="form-control" name="time" id="time" placeholder="Choose end time"
                              onChange={this.handleChange} />
                          </div>
                        </div>
                      </div>
                    </div>
                    <div className="col-6">
                      <div className="form-group">
                        <label htmlFor="type">Type:</label>
                        <div className="form-check form-check">
                          <input defaultChecked onChange={this.handleChange} className="form-check-input" type="radio" name="type" id="inlineRadio1" value="0" />
                          <label className="form-check-label" htmlFor="examination">Medical Examination</label>
                        </div>
                        <div className="form-check form-check">
                          <input onChange={this.handleChange} className="form-check-input" type="radio" name="type" id="inlineRadio2" value="1" />
                          <label className="form-check-label" htmlFor="operation">Surgery</label>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="col-sm">
                    <br />
                    <Button type="submit" className="btn create-appointment-btn">Schedule</Button>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <div className="col-2 new-app-doctor-image">

          </div>
        </div>
        <Footer />
      </div>
    );
  }
}

export default withRouter (NewAppointmentDoctor);