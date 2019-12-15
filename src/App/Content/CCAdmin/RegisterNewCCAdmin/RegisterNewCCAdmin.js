import React from 'react';
import { Button} from 'react-bootstrap';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import axios from 'axios';
import './RegisterNewCCAdmin.css'
import Header from '../../Header/Header';
import './RegisterNewCCAdmin.css'
import {NotificationManager} from 'react-notifications';

class RegisterNewCCAdmin extends React.Component {

  constructor(props){
      super(props);

      this.handleChange = this.handleChange.bind(this);
      this.SendRegisterRequest = this.SendRegisterRequest.bind(this);

      this.state = {
            email: '',
            password: '$2y$12$4zrqOojpixOe/ogFw1xyyuQuIvFqrzbj0IohYtshqqy1P5rS6kdbq',
            firstName: '',
            lastName: '',
            address: '',
            city: '',
            country: '',
            phoneNumber: ''
      }
   }

  SendRegisterRequest = event => {
      event.preventDefault();
        console.log(this.state);  
        axios.post("http://localhost:8080/api/cc-admin/register-cc-admin", {
            email: this.state.email,
            password: this.state.password,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            medicalNumber: this.state.medicalNumber,
            address: this.state.address,
            city: this.state.city,
            country: this.state.country,
            phoneNumber: this.state.phoneNumber

        }).then((resp) => {NotificationManager.success('Clinic Center Admin registration request has been sucessfull. \n Confirmation email has been sent', '', 2000);}) 
        .catch((error)=> {NotificationManager.error('Wrong input.', 'Error', 2000);}) 
  }

  handleChange(e) {
      this.setState({...this.state, [e.target.name]: e.target.value});
  }

  render() {
      return (
        <div className="RegisterNewCCAdmin">
        <Header/>
            <div className="row register-form">
                <div className="col-md-6">
                <form onSubmit={this.SendRegisterRequest}>
                            <div className="form-group">
                                <label htmlFor="email">Email</label>
                                <input type="email"
                                    className="form-control"
                                    id="email"
                                    name="email"
                                    onChange={this.handleChange}
                                    placeholder="Enter Email"
                                    required
                                />
                                <br/>
                                <label htmlFor="firstName">First Name</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="firstName"
                                    name="firstName"
                                    onChange={this.handleChange}
                                    placeholder="Enter Name"
                                    required
                                />
                                <br/>
                                <label htmlFor="lastName">Last Name</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="lastName"
                                    name="lastName"
                                    onChange={this.handleChange}
                                    placeholder="Enter Surname"
                                    required
                                />
                                <br/>
                                <label htmlFor="address">Address</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="address"
                                    name="address"
                                    onChange={this.handleChange}
                                    placeholder="Enter Address"
                                    required
                                />
                                <br/>
                                <label htmlFor="city">City</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="city"
                                    name="city"
                                    onChange={this.handleChange}
                                    placeholder="Enter City"
                                    required
                                />
                                <br/>
                                <label htmlFor="country">Country</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="country"
                                    name="country"
                                    onChange={this.handleChange}
                                    placeholder="Enter Country"
                                    required
                                />
                                <br/>
                                <label htmlFor="phoneNumber">Phone Number</label>
                                <input type="number"
                                    className="form-control form-control-sm"
                                    id="phoneNumber"
                                    name="phoneNumber"
                                    onChange={this.handleChange}
                                    placeholder="Enter Phone Number"
                                    required
                                />
                            </div>
                            <hr/>
                            <Button type="submit">Create</Button>
                        </form>
                </div>
            </div>
      </div>
  );
}
}

export default RegisterNewCCAdmin;