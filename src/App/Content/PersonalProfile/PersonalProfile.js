import React from 'react';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import { Link } from 'react-router-dom'
import axios from 'axios';
import'./PersonalProfile.css'


class PersonalProfile extends React.Component {
 
 constructor(props){
   super(props); 

   this.state={
      firstName: '',
      lastName:'',
      email:'',
      medicalNumber:'',
      address:'',
      city:'',
      country:'',
      phoneNumber: '',
      role: '',
      doctorStars: '',
      doctorVotes: ''
   }
 }
 
 componentDidMount(){
   axios.get("http://localhost:8080/patients/" + '6') //za sve usere
   .then(response=>{
     
      this.setState({
        firstName: response.data.firstName,
        lastName: response.data.lastName,
        email: response.data.email,
        medicalNumber: response.data.medicalNumber,
        address: response.data.address,
        city: response.data.city,
        country: response.data.country, 
        phoneNumber: response.data.phoneNumber


      })
   }
    )
 }
 
  render() {
    
  return (
    <div className="PersonalProfile">
      <Header/>
      <br/>
      <h3 >Personal page</h3>
      <br/>
        <div className="info">
          <div className="form-group">
            <label>First name:{this.state.fistName} </label>


          </div>
          <div className="form-group">
            <label>Last name:{this.state.lastName} </label>


          </div>
          <div className="form-group">
            <label>Email:{this.state.email} </label>


          </div>
          <div className="form-group">
            <label>Medical number: {this.state.medicalNumber}</label>


          </div>
          <div className="form-group">
            <label>Address: {this.state.address} </label>


          </div>
          <div className="form-group">
            <label>City: {this.state.city} </label>


          </div>
          <div className="form-group">
            <label>Country:{this.state.country} </label>


          </div>
          <div className="form-group">
            <label>Phone number: {this.state.phoneNumber} </label>


          </div>
      </div>
      <Link to="/edit-personal-page" class="btn link-btn-patient edit-button">Edit personal info</Link>
      <br/>
      <Footer/>

    </div>
  );
  }
}

export default PersonalProfile;