import React from 'react';
import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';
import Swal from 'sweetalert2';
import { Button} from 'react-bootstrap';
import withReactContent from 'sweetalert2-react-content';
import axios from 'axios';
import {NotificationManager} from 'react-notifications';

import './QuickReservation.css';

const QuickReservationAlert = withReactContent(Swal)

class QuickReservation extends React.Component {

  constructor(props){
    super(props);

    this.handleChange = this.handleChange.bind(this);
    this.SendQuickReservationRequest = this.SendQuickReservationRequest.bind(this);

    this.state = {
        date: '',
        time: '',
        type: '0',
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
    var token = localStorage.getItem('token');
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    axios.post("http://localhost:8080/api/clinic-admin/quick-reservation", {
      date: this.state.date,
      time: this.state.time,
      type: this.state.type,
      duration: this.state.duration,
      ordination: this.state.ordination,
      doctor: this.state.doctor,
      price: this.state.price
    })  
    .then((resp) => {
      NotificationManager.success('You have created appointment succesfully!', 'Success!', 4000)
    })
    .catch((error) => NotificationManager.success('Incorect values!', 'Error!', 4000))
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
    .catch((error) => console.log(error))

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
    .catch((error) => console.log(error))
  }

  render() {
    
  return (
    <div className="QuickReservation">
      <Header/>
          <div className="row">
                <div className="col-sm quick-res-header">
                  <h3>Quick Reservation</h3>
                </div>
          </div>
          <div className="row quick-res-form">
                <div className="col-sm">
                <form onSubmit={this.SendQuickReservationRequest}>
                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="doctor">Doctor</label>
                            <select required className="custom-select mr-sm-2" name="doctor" id="doctor" onChange={this.handleChange} >
                              <option defaultValue="0" >Choose...</option>
                              {this.state.doctors.map((doctor, index) => (
                                  <option key={doctor.id} value={doctor.id}>{doctor.firstName} {doctor.lastName}</option>
                                ))}
                            </select>
                        </div>
                        <div className="form-group col-md-6">
                            <label htmlFor="ordination">Ordination</label>
                            <select required className="custom-select mr-sm-2" name="ordination" id="ordination" onChange={this.handleChange} >
                              <option defaultValue="0" >Choose...</option>
                              {this.state.ordinations.map((ord, index) => (
                                  <option key={ord.id} value={ord.id}>{ord.number}</option>
                                ))}
                            </select>
                        </div>
                    </div>           
                    <div className="form-row">
                        <div className="form-group col-md-6">
                            <label htmlFor="date">Date</label>
                            <input required type="date" className="form-control" name="date" id="date" placeholder="Choose date"
                              onChange={this.handleChange}/>
                        </div>
                        <div className="form-group col-md-6">
                        <label htmlFor="time">Time</label>
                        <input required type="time" className="form-control" name="time" id="time" placeholder="Choose time"
                          onChange={this.handleChange}/>
                        </div>
                    </div>
                    <div className="form-group">
                        <label htmlFor="type">Type</label>
                        <div className="form-check form-check">
                            <input defaultChecked onChange={this.handleChange} className="form-check-input" type="radio" name="type" id="inlineRadio1" value="0"/>
                            <label className="form-check-label" htmlFor="examination">Medical Examination</label>
                        </div>
                        <div className="form-check form-check">
                            <input onChange={this.handleChange} className="form-check-input" type="radio" name="type" id="inlineRadio2" value="1"/>
                            <label className="form-check-label" htmlFor="operation">Surgery</label>
                        </div>
                    </div>
                    <div className="form-row">
                        <div className="form-group col-md-5">
                            <label htmlFor="duration">Duration</label>
                            <select required className="custom-select mr-sm-2" name="duration" id="duration" onChange={this.handleChange} >
                              <option defaultValue="0" >0</option>
                              <option defaultValue="10" >10</option>
                              <option defaultValue="15" >15</option>
                              <option defaultValue="20" >20</option>
                              <option defaultValue="30" >30</option>
                              <option defaultValue="45" >45</option>
                              <option defaultValue="60" >60</option>
                            </select>
                        </div>
                        <div className="form-group col-md-1">
                            <label htmlFor="minutes">Min / Hour</label>
                            <input required disabled type="text" className="form-control" name="minutes" id="minutes" placeholder="minutes"
                              value="minutes"/>
                        </div>
                        <div className="form-group col-md-5">
                            <label htmlFor="price">Price</label>
                            <input required type="number" className="form-control" name="price" id="price" placeholder="00.0"
                              onChange={this.handleChange}/>
                        </div>
                        <div className="form-group col-md-1">
                            <label htmlFor="currency">Currency</label>
                            <input required disabled type="text" className="form-control" name="currency" id="currency" placeholder="€"
                              value="€"/>
                        </div>
                    </div>
                    <Button type="submit" className="btn quick-res-btn">Create</Button>
                </form>
                </div>
          </div>
      <Footer/>

    </div>
  );
  }
}

export default QuickReservation;