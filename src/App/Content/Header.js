import React from 'react';

function Header() {
    return (
        <div className="Nurse">
          <nav className="navbar navbar-default navbar-fixed-top">
                  <div className="container">
                      <div className="navbar-header">
                          <a className="nav-link heder" href="#">HomePage</a>
                      </div>
              <div className="navbar-header">
              <ul className="nav justify-content-end">
              <li className="nav-item">
                <a className="nav-link" href="#">Profile</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">Calendar</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">Ask for Leave</a>
              </li>
            </ul>
                      </div>
                  </div>
              </nav>
        </div>
    );
}

export default Header;