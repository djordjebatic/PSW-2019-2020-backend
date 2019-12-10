import React from 'react';
import { Link } from 'react-router-dom'
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import './ClinicPage.css'
import { Button} from 'react-bootstrap';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import axios from 'axios';

class PredefinedExaminations extends React.Component {
    render() {
        return(
            
            <div className="Predefined-Examinations">
                <Header/>

                <div className="Predefined-Examinations-title">Predefined examinations</div>

                <div className="">
                    
                   
                </div>

                <Footer/>
            </div>

            );
        }
    }
    
export default ClinicPage;