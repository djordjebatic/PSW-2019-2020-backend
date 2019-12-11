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
    var token = localStorage.getItem('token');
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    axios.get("http://localhost:8080/auth/getMyUser")  
      .then(response => {
          console.log(response.data);
          this.setState({
              firstName: response.data.firstName,
              lastName: response.data.lastName,
              email: response.data.username,
              address: response.data.address,
              city: response.data.city,
              country: response.data.country,
              phoneNumber: response.data.phoneNumber,
              role: response.data.authorities[0].name,
              medicalNumber: response.data.medicalNumber,
              doctorStars: response.data.stars,
              doctorVotes: response.data.num_votes
          })
      })
    .catch((error) => console.log(error))
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