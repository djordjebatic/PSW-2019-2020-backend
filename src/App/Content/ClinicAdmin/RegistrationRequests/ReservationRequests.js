import React from 'react';
import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';
import Swal from 'sweetalert2';
import { Button} from 'react-bootstrap';
import withReactContent from 'sweetalert2-react-content';
import axios from 'axios'

import './ReservationRequests.css'

const SheduleAlert = withReactContent(Swal)

class RegistrationRequests extends React.Component {

  constructor(props){
    super(props);

    this.handleChange = this.handleChange.bind(this);
    this.SendAppointmentRequest = this.SendAppointmentRequest.bind(this);

    this.state = {
        appointmentRequests: []
    }
  }

  SendAppointmentRequest = event => {
    event.preventDefault();
      
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
    var token = localStorage.getItem('token');
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    axios.get("http://localhost:8080/auth/getMyUser")  
      .then(response => {
          console.log(response);
          this.setState({
              appointmentRequests: response.data.appointmentRequests
          })
          console.log(this.state.appointmentRequests[0]);
      })
    .catch((error) => console.log(error))
  }

  render() {

    var content;
    if (this.state.appointmentRequests.length == 0){
      content = <h4>You don't have any appointment reservation requests.</h4>;
    } else {
      content = this.state.appointmentRequests.map((req) => (
        <div>
        <div className="col">
            <div className="card">
            <div className="card-body">
                <h5 className="card-title">Appointment Request</h5>
                <hr/>
                <p className="card-text"><strong>Date:</strong> {req.date} | <strong>Time:</strong> {req.time} | <strong>Type:</strong> </p>
                <hr/>
                <a href="#" className="btn btn-primary btn-app-req">Create appointment</a>
            </div>
            </div>
        </div>
        <br/>
        </div>
    ))
    }

  return (
    <div className="RegistrationRequests">
      <Header/>
        <br/>
      <h3>Appointment Requests</h3>
        <div className="cards">
            <br/>
            {content}
        </div>
      <Footer/>

    </div>
  );
  }
}

export default RegistrationRequests;