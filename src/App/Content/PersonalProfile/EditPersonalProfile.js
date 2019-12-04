import React from 'react';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import { Button} from 'react-bootstrap';
import axios from 'axios';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import'./EditPersonalProfile.css'

const UpdateInfoAlert = withReactContent(Swal)
class EditPersonalProfile extends React.Component {
 
    constructor(props){
        super(props);

    
        this.handleChange = this.handleChange.bind(this);
        this.UpdateInfoRequest = this.UpdateInfoRequest.bind(this);
        
        this.state={
                password: '',
                firstName: '',
                lastName: '',
                address: '',
                city: '',
                country: '',
                phoneNumber: null,
                passwordConfirm:''

        }
    }

    componentDidMount(){
        axios.get("http://localhost:8080/patients/" + '6') //za sve usere
        .then(response=>{
          
           this.setState({
             password: response.data.password,
             firstName: response.data.firstName,
             lastName: response.data.lastName,
             address: response.data.address,
             city: response.data.city,
             country: response.data.country, 
             phoneNumber: response.data.phoneNumber,
             passwordConfirm: response.data.password
     
     
           })
        }
         )
      }

    UpdateInfoRequest = event => {
        event.preventDefault();
          console.log(this.state);  
          const { password, passwordConfirm } = this.state;
          if (password !== passwordConfirm) {
              alert("Passwords don't match");
          } else {
          axios.put("http://localhost:8080/patients/6", {

            password: this.state.password,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            address: this.state.address,
            city: this.state.city,
            country: this.state.country,
            phoneNumber: this.state.phoneNumber

          }).then(
              (resp) => this.onSuccessHandler(resp)
        )
        }
    }

    onSuccessHandler(resp){
        UpdateInfoAlert.fire({
            title: "Personal information updated successfully",
            text: "",
            type: "success",
        })
    }

    handleChange(e) {
        this.setState({...this.state, [e.target.name]: e.target.value});
    }


    render() {
    
        return (
          <div className="EditPersonalProfile">
            <Header/>
            <br/>
             <h3 >Edit personal page</h3>

            <form onSubmit={this.UpdateInfoRequest}>
                <div className="form-group">
                    <label htmlFor="firstName">First Name</label>
                    <input type="text"
                        className="form-control form-control-sm"
                        id="firstName"
                        name="firstName"
                        onChange={this.handleChange}
                        placeholder="Enter first name"
                        defaultValue={this.state.firstName}
                    />
                    <br/>      
                    <label htmlFor="lastName">Last Name</label>
                    <input type="text"
                        className="form-control form-control-sm"
                        id="lastName"
                        name="lastName"
                        onChange={this.handleChange}
                        placeholder="Enter last name"
                        defaultValue={this.state.lastName}
                    />
                    <br/>
                    <label htmlFor="password">Password</label>
                    <input type="password"
                        className="form-control form-control-sm"
                        id="password"
                        name="password"
                        onChange={this.handleChange}
                        placeholder="Enter password"
                        defaultValue={this.state.password}
                    />
                    <br/>
                    <label htmlFor="passwordConfirm">Confirm Password</label>
                    <input type="password"
                        className="form-control form-control-sm"
                        id="passwordConfirm"
                        name="passwordConfirm"
                        onChange={this.handleChange}
                        placeholder="Enter password"
                        defaultValue={this.state.passwordConfirm}
                    />
                    <br/>
                    <label htmlFor="address">Address</label>
                    <input type="text"
                        className="form-control form-control-sm"
                        id="address"
                        name="address"
                        onChange={this.handleChange}
                        placeholder="Enter address"
                        defaultValue={this.state.address}
                    />
                    <br/>
                    <label htmlFor="city">City</label>
                    <input type="text"
                        className="form-control form-control-sm"
                        id="city"
                        name="city"
                        onChange={this.handleChange}
                        placeholder="Enter city"
                        defaultValue={this.state.city}
                    />
                    <br/>
                    <label htmlFor="country">Country</label>
                    <input type="text"
                        className="form-control form-control-sm"
                        id="country"
                        name="country"
                        onChange={this.handleChange}
                        placeholder="Enter country"
                        defaultValue={this.state.country}
                    />
                    <br/>
                    <label htmlFor="phoneNumber">Phone Number</label>
                    <input type="number"
                        className="form-control form-control-sm"
                        id="phoneNumber"
                        name="phoneNumber"
                        onChange={this.handleChange}
                        placeholder="Enter phone number"
                        defaultValue={this.state.phoneNumber}
                    />
                </div>
                <hr/>
                <Button type="submit">Update</Button>
            </form>
            <br/>
        <Footer/>
    </div>
  );
  }
}

export default EditPersonalProfile;