import React from 'react';
import {Link} from 'react-router-dom'
import '../Login/Login.css'
import Swal from 'sweetalert2';
import { Button} from 'react-bootstrap';
import withReactContent from 'sweetalert2-react-content';
import axios from 'axios'
import { withRouter } from 'react-router-dom';


import logo from '../../../images/med128.png'

const LoginAlert = withReactContent(Swal)

class ChangePassword extends React.Component {

    constructor(props){
        super(props);
  
        this.handleChange = this.handleChange.bind(this);
        this.SendLoginRequest = this.SendLoginRequest.bind(this);
  
        this.state = {
            email: 'admin@gmail.com',
            newPassword: ''
        }
    }

    SendLoginRequest = event => {
        event.preventDefault();
          console.log(this.state);
        axios.put("http://localhost:8080/api/cc-admin/change-ccadmin-password", this.state)
        .then((resp) => {this.onSuccessHandler(resp);
                this.props.history.push('/ccadmin/');
            }
        )
        .catch((error) => this.onFailureHandler(error))
    }
  
    onSuccessHandler(resp){
        LoginAlert.fire({
            title: "Password changed successfully",
            text: ""
        })
    }

    onFailureHandler(error){
        LoginAlert.fire({
            title: "Password change failed",
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
                                <label>You have logged in as the Clinic Center Administrator for the first time. <br></br>Please change your password.</label>
                                <input 
                                    required
                                    type="text" 
                                    className="form-control" 
                                    id="newPassword" 
                                    name="newPassword"
                                    onChange={this.handleChange}
                                    placeholder="Enter New Password"/>
                                <input 
                                    required
                                    type="text" 
                                    className="form-control" 
                                    id="confirmPassword" 
                                    name="confirmPassword"
                                    onChange={this.handleChange}
                                    placeholder="Confirm New Password"/>
                            </div>
                            <Button type="submit" className="btn">Change Password</Button>
                        </form>
                    </div>
                </div>
        </div>
        </div>
        );
    }
}

export default withRouter (ChangePassword);