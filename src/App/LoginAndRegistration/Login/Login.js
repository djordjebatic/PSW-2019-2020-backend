import React from 'react';
import {Link} from 'react-router-dom'
import './Login.css'
import Swal from 'sweetalert2';
import { Button} from 'react-bootstrap';
import withReactContent from 'sweetalert2-react-content';
import axios from 'axios'
import { withRouter } from 'react-router-dom';


import logo from '../../../images/med128.png'

const LoginAlert = withReactContent(Swal)

class Login extends React.Component {
    
    constructor(props){
        super(props);
  
        this.handleChange = this.handleChange.bind(this);
        this.SendLoginRequest = this.SendLoginRequest.bind(this);
  
        this.state = {
            email: '',
            password: ''
        }
    }

    SendLoginRequest = event => {
        event.preventDefault();
        axios.post("http://localhost:8080/auth/login", this.state)
        .then((resp) => {this.onSuccessHandler(resp);
            localStorage.setItem('token', resp.data.accessToken)
            axios.defaults.headers.common['Authorization'] = `Bearer ${resp.data.accessToken}`;
            axios.get("http://localhost:8080/auth/getMyUser")
                .then((resp) => {
                    console.log(resp.data)
                    if ((resp.data.authorities[0].name == "ROLE_DOCTOR" 
                        || resp.data.authorities[0].name == "ROLE_NURSE" 
                        || resp.data.authorities[0].name == "ROLE_CLINIC_ADMIN"
                        || resp.data.authorities[0].name == "ROLE_CC_ADMIN")
                         && resp.data.userStatus == "NEVER_LOGGED_IN"){
                        this.props.history.push('/change-password');
                    } else {
                        if (resp.data.authorities[0].name == "ROLE_DOCTOR"){
                            this.props.history.push('/doctor')
                        }
                        if (resp.data.authorities[0].name == "ROLE_NURSE"){
                            this.props.history.push('/nurse')
                        }
                        if (resp.data.authorities[0].name == "ROLE_CLINIC_ADMIN"){
                            this.props.history.push('/clinic-admin')
                        }
                        if (resp.data.authorities[0].name == "ROLE_PATIENT"){
                            this.props.history.push('/patient')
                        }
                    }

                })
        }
        )
        .catch((error) => this.onFailureHandler(error))
    }
  
    onSuccessHandler(resp){
        LoginAlert.fire({
            title: "Logged in successfully",
            text: ""
        })
    }

    onFailureHandler(error){
        LoginAlert.fire({
            title: "Log In failed",
            text: "Email and password combination is not acceptable."
        })
    }

    handleChange(e) {
        this.setState({...this.state, [e.target.name]: e.target.value});
    }

    render(){
      return (
        <div className="Login">
            <div className="row">
                <div className="col-4 welcome">
                    <div className="logo">
                        <img alt="logo" src={logo} />
                        <h1 className="title">Clinic Center</h1>
                    </div>
                </div>
                <div className="col-8 login">
                    <form onSubmit={this.SendLoginRequest}>
                        <div className="form-group">
                            <label>E-mail address</label>
                            <input 
                                required
                                type="text" 
                                className="form-control" 
                                id="email" 
                                name="email"
                                aria-describedby="emailHelp"
                                onChange={this.handleChange} 
                                placeholder="E-mail address"/>
                        </div>
                        <div className="form-group">
                            <label>Password</label>
                            <input 
                                required
                                type="password" 
                                className="form-control" 
                                id="password" 
                                name="password"
                                onChange={this.handleChange}
                                placeholder="Password"/>
                            </div>
                        <small id="newAccount" className="form-text text-muted"><Link to="/register">Doesn't have an account?</Link></small>
                        <br/>
                        <Button type="submit" className="btn">Log In</Button>
                    </form>
                </div>
            </div>
        </div>
        );
    }
}

export default withRouter (Login);