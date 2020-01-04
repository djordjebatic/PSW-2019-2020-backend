import React from 'react';
import { Button} from 'react-bootstrap';
import axios from 'axios';
import Header from '../../Header/Header';
import './RegisterNewCCAdmin.css'
import {NotificationManager} from 'react-notifications';

const emailRegex = RegExp(
  /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
);

const phoneRegex = RegExp(
    /06[0-9]{7,8}/
);

const capitalLetterRegex = RegExp(
    /^([A-Z][a-z]+)+$/
);

const formValid = ({ formErrors, ...rest }) => {
    let valid = true;
  
    // validate form errors being empty
    Object.values(formErrors).forEach(val => {
      val.length > 0 && (valid = false);
    });
  
    // validate the form was filled out
    Object.values(rest).forEach(val => {
      val === null && (valid = false);
    });
  
    return valid;
  };


class RegisterNewCCAdmin extends React.Component {

  constructor(props){
      super(props);

      this.handleChange = this.handleChange.bind(this);
      this.SendRegisterRequest = this.SendRegisterRequest.bind(this);

      this.state = {
            email: null,
            password: '$2y$12$4zrqOojpixOe/ogFw1xyyuQuIvFqrzbj0IohYtshqqy1P5rS6kdbq',
            firstName: null,
            lastName: null,
            address: null,
            city: null,
            country: null,
            phoneNumber: null,
            formErrors: {
                email: "",
                firstName: "",
                lastName: "",
                address: "",
                city: "",
                country: "",
                phoneNumber: "",
              }
      }
   }

  SendRegisterRequest = event => {

      event.preventDefault();
      console.log(this.state);
      if (formValid(this.state)) {
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

        }).then((resp) => {NotificationManager.success('Clinic Center Admin registration request has been sucessfull. \n Confirmation email has been sent', '', 3000);}) 
        .catch((error)=> {NotificationManager.error('System error. Please try again later.', 'Error', 3000);})
      }
      else {
        NotificationManager.error('Wrong form input. Please input the correct strings.', '', 3000);
      } 
  }

  handleChange = e => {
    e.preventDefault();
    const { name, value } = e.target;
    let formErrors = { ...this.state.formErrors };

    switch (name) {
      case "firstName":
        formErrors.firstName = capitalLetterRegex.test(value)
          ? ""
          : "first name must start with a capital letter";

        if (formErrors.firstName === ""){  
            formErrors.firstName =
            value.length < 3 ? "minimum 3 characaters required" : "";
        }
        break;
      case "lastName":
        formErrors.lastName = capitalLetterRegex.test(value)
          ? ""
          : "last name must start with a capital letter";
        
          if (formErrors.lastName === ""){  
            formErrors.lastName =
            value.length < 3 ? "minimum 3 characaters required" : "";
        }
        break;
      case "email":
        formErrors.email = emailRegex.test(value)
          ? ""
          : "invalid email address";
        break;
      case "city":
        formErrors.city =
          value.length < 3 ? "minimum 3 characaters required" : "";
        break;
      case "country":
        formErrors.country =
          value.length < 3 ? "minimum 3 characaters required" : "";
        break;
      case "address":
            formErrors.address =
              value.length < 3 ? "minimum 3 characaters required" : "";
            break;
      case "phoneNumber":
            formErrors.phoneNumber = phoneRegex.test(value)
            ? ""
            : "Input letters must be numbers, start with '06' and have between 9 and 10 numbers";
          break;
      default:
        break;
    }
    this.setState({ formErrors, [name]: value}, () => console.log(this.state));
  }

  render() {
      const { formErrors } = this.state;
      return (
        <div className="RegisterNewCCAdmin">
        <Header/>
            <div className="row register-form">
                <div className="col-md-6">
                <form onSubmit={this.SendRegisterRequest} noValidate>
                <div className="firstName">
              <label htmlFor="firstName">First Name: </label>
              <input
                className={formErrors.firstName.length > 0 ? "error" : null}
                placeholder="First Name"
                type="text"
                name="firstName"
                noValidate
                onChange={this.handleChange}
              />
              {formErrors.firstName.length > 0 && (
                <span className="errorMessage">{formErrors.firstName}</span>
              )}
            </div>
            <div className="lastName">
              <label htmlFor="lastName">Last Name: </label>
              <input
                className={formErrors.lastName.length > 0 ? "error" : null}
                placeholder="Last Name"
                type="text"
                name="lastName"
                noValidate
                onChange={this.handleChange}
              />
              {formErrors.lastName.length > 0 && (
                <span className="errorMessage">{formErrors.lastName}</span>
              )}
            </div>
            <div className="email">
              <label htmlFor="email">Email: </label>
              <input
                className={formErrors.email.length > 0 ? "error" : null}
                placeholder="Email"
                type="email"
                name="email"
                noValidate
                onChange={this.handleChange}
              />
              {formErrors.email.length > 0 && (
                <span className="errorMessage">{formErrors.email}</span>
              )}
            </div>
            <div className="address">
              <label htmlFor="address">Address: </label>
              <input
                className={formErrors.address.length > 0 ? "error" : null}
                placeholder="Address"
                type="address"
                name="address"
                noValidate
                onChange={this.handleChange}
              />
              {formErrors.address.length > 0 && (
                <span className="errorMessage">{formErrors.address}</span>
              )}
            </div>
            <div className="city">
              <label htmlFor="city">City: </label>
              <input
                className={formErrors.city.length > 0 ? "error" : null}
                placeholder="City"
                type="city"
                name="city"
                noValidate
                onChange={this.handleChange}
              />
              {formErrors.city.length > 0 && (
                <span className="errorMessage">{formErrors.city}</span>
              )}
            </div>
            <div className="country">
              <label htmlFor="country">Country: </label>
              <input
                className={formErrors.country.length > 0 ? "error" : null}
                placeholder="Country"
                type="country"
                name="country"
                noValidate
                onChange={this.handleChange}
              />
              {formErrors.country.length > 0 && (
                <span className="errorMessage">{formErrors.country}</span>
              )}
            </div>
            <div className="phoneNumber">
              <label htmlFor="phoneNumber">Phone Number: </label>
              <input
                className={formErrors.phoneNumber.length > 0 ? "error" : null}
                placeholder="Phone Number"
                type="phoneNumber"
                name="phoneNumber"
                noValidate
                onChange={this.handleChange}
              />
              {formErrors.phoneNumber.length > 0 && (
                <span className="errorMessage">{formErrors.phoneNumber}</span>
              )}
            </div>
                            <hr/>
                            <Button className="createAccount" type="submit">Create</Button>
                        </form>
                </div>
            </div>
      </div>
  );
}
}

export default RegisterNewCCAdmin;