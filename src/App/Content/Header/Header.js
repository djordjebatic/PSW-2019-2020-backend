import React from 'react';

import {Link} from 'react-router-dom';
import axios from 'axios';

import './Header.css'

import logo from '../../../images/med.png'

class Header extends React.Component{

    constructor(props){
      super(props); 

      this.state = {
          role: ''
      }
    }

    componentDidMount(){
    var token = localStorage.getItem('token');
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    axios.get("http://localhost:8080/auth/getMyUser")  
        .then(response => {
            console.log(response.data);
            this.setState({
                role: response.data.authorities[0].name
            })
        })
    .catch((error) => console.log(error))
    }

    render() {

      var homePageLink;
      if (this.state.role === "ROLE_CC_ADMIN"){
        homePageLink = (
          <Link to="/cc-admin" className="nav-link link-header" href="#">Clinic Center</Link>
        )
      } else if (this.state.role === "ROLE_CLINIC_ADMIN"){
        homePageLink = (
          <Link to="/clinic-admin" className="nav-link link-header" href="#">Clinic Center</Link>
        )
      } else if (this.state.role === "ROLE_DOCTOR"){
        homePageLink = (
          <Link to="/doctor" className="nav-link link-header" href="#">Clinic Center</Link>
        )
      } else if (this.state.role === "ROLE_NURSE"){
        homePageLink = (
          <Link to="/nurse" className="nav-link link-header" href="#">Clinic Center</Link>
        )
      } else if (this.state.role === "ROLE_PATIENT"){
        homePageLink = (
          <Link to="/patient" className="nav-link link-header" href="#">Clinic Center</Link>
        )
      }

      return (
        <div className="Header">
          <nav className="navbar navbar-dark clinic-center-nav">
            <div className="navbar-brand">
              {homePageLink}
            </div>
            <ul className="nav">
              <li className="nav-item">
                <Link to="/my-profile" className="nav-link link-header" href="#">Profile</Link>
              </li>
              <li className="nav-item">
                <Link to="work-calendar" className="nav-link link-header" href="#">Work Calendar</Link>
              </li>
              <li className="nav-item">
                <a className="nav-link link-header" href="#">Log Out</a>
              </li>
            </ul>
          </nav>
        </div>
      );
    }
}

export default Header;