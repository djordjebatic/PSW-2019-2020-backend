import React from 'react';

import {Link} from 'react-router-dom'

import './Header.css'

import logo from '../../../images/med.png'

function Header() {
    return (
        <div className="Header">
          <nav className="navbar navbar-dark clinic-center-nav">
            <div className="navbar-brand">
              <img src={logo} width="30" height="30" className="d-inline-block align-top logo-header" alt="logo"/>
              Clinic Center
            </div>
            <ul class="nav">
              <li class="nav-item">
                <Link to="/my-profile" class="nav-link link-header" href="#">Profile</Link>
              </li>
              <li class="nav-item">
                <Link to="work-calendar" class="nav-link link-header" href="#">Work Calendar</Link>
              </li>
              <li class="nav-item">
                <a class="nav-link link-header" href="#">Log Out</a>
              </li>
            </ul>
          </nav>
        </div>
    );
}

export default Header;