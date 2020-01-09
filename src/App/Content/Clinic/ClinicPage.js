import React from 'react';
import { Link } from 'react-router-dom'
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import './ClinicPage.css'
import axios from 'axios'

class ClinicPage extends React.Component {

    constructor(props){
        super(props); 
    
        this.state = {
            id: '',
            name: ''
        }
    }

    componentDidMount(){
        var token = localStorage.getItem('token');
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;  
         axios.get("http://localhost:8080/api/clinic/"+this.state.id)  
            .then(response => {
                console.log(response.data);
                this.setState({
                    id: response.data.id,
                    name: response.data.name
                })
            })
        .catch((error) => console.log(error))
        }

    render() {
        return(
            
            <div className="Clinic-page">
                <Header/>

                <div className="clinic-page-title">Clinic page</div>
                <h3>{this.state.name}</h3>

                <div className="btn-predefined-exam">
                    <Link to="/predefined-examinations" className="btn link-btn-patient predefined-btn">Predefined examinations</Link>
                </div>

                <div className="btn-predefined-exam">
                    <Link to="/doctors-list" className="btn link-btn-patient predefined-btn">Doctors of the clinic</Link>
                </div>

                <Footer/>
            </div>

            );
        }
    }
    
export default ClinicPage;