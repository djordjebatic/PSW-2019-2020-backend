import React from 'react';
import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';
import Swal from 'sweetalert2';
import { Button} from 'react-bootstrap';
import withReactContent from 'sweetalert2-react-content';
import axios from 'axios'

import './QuickReservation.css'

const QuickReservationAlert = withReactContent(Swal)

class QuickReservation extends React.Component {

  constructor(props){
    super(props);

    this.handleChange = this.handleChange.bind(this);
    this.SendQuickReservationRequest = this.SendQuickReservationRequest.bind(this);

    this.state = {
        date: '',
        time: '',
        type: '',
        duration: '',
        ordination: '',
        doctor: '',
        price: 0.0,
        doctors: [],
        ordinations: []
    }
  }

  SendQuickReservationRequest = event => {
    event.preventDefault();
      console.log(this.state);
    axios.post("http://localhost:8080/api/clinic-admin/quick-reservation", {
        date: this.state.date,
        time: this.state.time,
        type: this.state.type,
        duration: this.state.duration,
        ordination: this.state.ordination,
        doctor: this.state.doctor,
        price: this.state.price
    })  
    .then((resp) => this.onSuccessHandler(resp))
    .catch((error) => this.onFailureHandler(error))
  }

  onSuccessHandler(resp){
    QuickReservationAlert.fire({
        title: "Created successfully",
        text: ""
    })
  }

  onFailureHandler(error){
    QuickReservationAlert.fire({
          title: "Creation failed",
          text: error
      })
  }

  handleChange(e) {
    this.setState({...this.state, [e.target.name]: String(e.target.value)});
  }

  componentDidMount() {
    axios.get("http://localhost:8080/api/doctors")  
      .then(response => {
          let tmpArray = []
          for (var i = 0; i < response.data.length; i++) {
              tmpArray.push(response.data[i])
          }

          this.setState({
              doctors: tmpArray
          })
      })
    .catch((error) => this.onFailureHandler(error))

    axios.get("http://localhost:8080/api/ordinations")  
      .then(response => {
          let tmpArray = []
          for (var i = 0; i < response.data.length; i++) {
              tmpArray.push(response.data[i])
          }

          this.setState({
              ordinations: tmpArray
          })
      })
    .catch((error) => this.onFailureHandler(error))
  }

  render() {
    
  return (
    <div className="QuickReservation">
      <Header/>
        <div className="">
        <div className="row">
            <div className="col-sm new-appointment-header">
              <h3>Quick Reservation</h3>
            </div>
          </div>
          <div className="row new-appointment-form">
            <div className="col-sm">
            <form onSubmit={this.SendQuickReservationRequest}>
              <div className="form-group row">
                <label htmlFor="colFormLabel" className="col-sm-2 col-form-label">Doctor:</label>
                <div className="col-sm-10">
                <select required className="custom-select mr-sm-2" name="doctor" id="inlineFormCustomSelect" onChange={this.handleChange} >
                  <option defaultValue="0" >Choose...</option>
                  {this.state.doctors.map((doctor, index) => (
                       <option key={doctor.id} value={doctor.id}>{doctor.firstName} {doctor.lastName}</option>
                    ))}
                </select>
                </div>
              </div>
              <div className="form-group row">
                <label htmlFor="colFormLabel" className="col-sm-2 col-form-label">Ordination:</label>
                <div className="col-sm-10">
                <select className="custom-select mr-sm-2" name="ordination" id="inlineFormCustomSelect2" onChange={this.handleChange} >
                  <option defaultValue="0" >Choose...</option>
                  {this.state.ordinations.map((ord, index) => (
                       <option key={ord.id} value={ord.id}>{ord.number}</option>
                    ))}
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
              <div className="form-group row">
                <label htmlFor="colFormLabel" className="col-sm-2 col-form-label">Type:</label>
                <div className="form-check form-check-inline">
                    <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"/>
                    <label className="form-check-label" htmlFor="inlineRadio1">Medical Examination</label>
                </div>
                <div className="form-check form-check-inline">
                    <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"/>
                    <label className="form-check-label" htmlFor="inlineRadio2">Surgery</label>
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

export default QuickReservation;