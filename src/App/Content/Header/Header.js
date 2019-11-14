import React from 'react';
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
                <a class="nav-link link-header" href="#">Profile</a>
              </li>
              <li class="nav-item">
                <a class="nav-link link-header" href="#">Work Calendar</a>
              </li>
              <li class="nav-item">
                <a class="nav-link link-header" href="#">Ask To Leave</a>
              </li>
            </ul>
          </nav>
        </div>
    );
}

export default Header;