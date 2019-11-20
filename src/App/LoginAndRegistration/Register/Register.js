import React from 'react';
import './Register.css'
import logo from '../../../images/med128.png'
import { Button} from 'react-bootstrap';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import axios from 'axios';
import Header from '../../Content/Header/Header';

const PatientRegisterAlert = withReactContent(Swal)

class Register extends React.Component {

  constructor(props){
      super(props);

      this.handleChange = this.handleChange.bind(this);
      this.SendRegisterRequest = this.SendRegisterRequest.bind(this);

      this.state = {
          email: '',
          password: '',
          firstName: '',
          lastName: '',
          address: '',
          city: '',
          country: '',
          phoneNumber: null
      }
  }

  SendRegisterRequest = event => {
      event.preventDefault();
        console.log(this.state);
      axios.post("http://localhost:8080/patient/register", this.state).then(
          (resp) => this.onSuccessHandler(resp)
      )
  }

  onSuccessHandler(resp){
      PatientRegisterAlert.fire({
          title: "Patient registered successfully",
          text: "",
          type: "success",
      })
  }

  handleChange(e) {
      this.setState({...this.state, [e.target.name]: e.target.value});
  }

  render() {
      return (
    <div className="Register">
      <div className="">
            <div className="row">
                <Header></Header>
                <div className="col-12 login">
                <form onSubmit={this.SendRegisterRequest}>
                            <div className="form-group">
                                <label htmlFor="email">Email</label>
                                <input type="email"
                                    className="form-control form-control-sm"
                                    id="email"
                                    name="email"
                                    onChange={this.handleChange}
                                    placeholder="Enter Email"
                                    required
                                />
                                <br/>
                                <label htmlFor="password">Password</label>
                                <input type="password"
                                    className="form-control form-control-sm"
                                    id="password"
                                    name="password"
                                    onChange={this.handleChange}
                                    placeholder="Enter Password"
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
    </div>
  );}
}

export default Register;