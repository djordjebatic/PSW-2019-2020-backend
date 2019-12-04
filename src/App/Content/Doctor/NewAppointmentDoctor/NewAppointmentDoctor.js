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
        patients: [],
        patient: '',
        date: '',
        time: ''
    }
  }

  SendAppointmentRequest = event => {
    event.preventDefault();
      console.log(this.state);
    axios.post("http://localhost:8080/api/doctor/shedule-appointment", {
      patient: this.state.patient,
      date: this.state.date,
      time: this.state.time
    })  
    .then((resp) => this.onSuccessHandler(resp))
    .catch((error) => this.onFailureHandler(error))
  }

  onSuccessHandler(resp){
    SheduleAlert.fire({
        title: "Scheduled successfully",
        text: ""
    })
  }

  onFailureHandler(error){
    SheduleAlert.fire({
          title: "Scheduling failed",
          text: error
      })
  }

  handleChange(e) {
    this.setState({...this.state, [e.target.name]: String(e.target.value)});
  }

  componentDidMount() {
    axios.get("http://localhost:8080/patients")  
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
                <label htmlFor="patient" className="col-sm-2 col-form-label">Patient:</label>
                <div className="col-sm-10">
                <select className="custom-select mr-sm-2" name="patient" id="patient" onChange={this.handleChange} >
                  <option defaultValue="0" >Choose...</option>
                    {this.state.patients.map((patient, index) => (
                       <option key={patient.id} value={patient.id}>{patient.firstName} {patient.lastName}</option>
                    ))}
                </select>
                </div>
              </div>
              <div className="form-group row">
                <label htmlFor="date" className="col-sm-2 col-form-label">Date:</label>
                <div className="col-sm-10">
                  <input required type="date" className="form-control" name="date" id="date" placeholder="Choose date"
                    onChange={this.handleChange}/>
                </div>
              </div>
              <div className="form-group row">
                <label htmlFor="time" className="col-sm-2 col-form-label">Time:</label>
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