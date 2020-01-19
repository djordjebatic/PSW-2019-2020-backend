import React from 'react';
import Header from '../../Header/Header';
import axios from 'axios';
import { withRouter } from 'react-router';
import Footer from '../../Footer/Footer';
import Button from '@material-ui/core/Button';
import Tooltip from '@material-ui/core/Tooltip';


class MedicalCard extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            disabled: false,
            medicalCard: {}
        }
    }

    componentDidMount () {    
        const patientId = window.location.pathname.split("/")[2];
    
        const dto = {
            patientId: patientId,
            doctorId: 3
        }
        console.log(dto);
        axios.get('http://localhost:8080/api/medicalRecords/' + patientId, {
          responseType: 'json'
        })
              .then(response => {
                this.setState({medicalCard: response.data});
    
                console.log(this.state);
        })
        .catch((error) => console.log(error))
    
      }

    render() {

        return(
            <div className="CalendarEventClickWindow">
            <Header/>

            <div className="appointmentInfo">
                </div>
            <div className="buttons">
                <Button 
                    ariant="contained" color="primary" className="medicalCard">
                    Edit Medical Card
                </Button>

                <Tooltip title="You can only start the appointment after intended start time">
                <span>
                    <Button
                    ariant="contained" color="primary" className="examinationReport"
                    disabled={this.state.disabled} 
                    style={this.state.disabled ? { pointerEvents: "none" } : {}}>
                    {'Start Examination'}
                    </Button>
                </span>
                </Tooltip>
            </div>
            <Footer/>
            </div>
        );

    }
}
export default withRouter(MedicalCard);

