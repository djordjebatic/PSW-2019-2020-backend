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
          console.log(this.state);
        axios.post("http://localhost:8080/auth/login", this.state)
        .then((resp) => {this.onSuccessHandler(resp);
            localStorage.setItem('token', resp.data.accessToken)
            axios.defaults.headers.common['Authorization'] = `Bearer ${resp.data.accessToken}`;
            axios.get("http://localhost:8080/auth/getMyUser").then((resp) => {
                if (resp.data.username == "admin@gmail.com" && resp.data.userStatus == "NEVER_LOGGED_IN"){
                    this.props.history.push('/change-password');
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
            text: error
        })
    }

    handleChange(e) {
        this.setState({...this.state, [e.target.name]: e.target.value});
    }

    render(){
      return (
        <div className="Login">
        <div className="">
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
                                <small id="newAccount" className="form-text text-muted"><Link to="/register">Doesn't have an account?</Link></small>
                            </div>
                            <Button type="submit" className="btn">Log In</Button>
                        </form>
                    </div>
                </div>
        </div>
        </div>
        );
    }
}

export default withRouter (Login);