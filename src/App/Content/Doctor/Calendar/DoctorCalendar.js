import React from 'react';
import axios from 'axios';
import Header from '../../Header/Header';
import {NotificationManager} from 'react-notifications';
import './DoctorCalendar.css'
import Footer from '../../Footer/Footer';
import {
  Calendar,
  DateLocalizer,
  momentLocalizer,
  globalizeLocalizer,
  move,
  Views,
  Navigate,
  components,
} from 'react-big-calendar'
import "react-big-calendar/lib/css/react-big-calendar.css";
import moment from 'moment'

const localizer = momentLocalizer(moment) // or globalizeLocalizer

const CustomEvent = (event) => { 
  console.log(event)
  return ( 

  <span><strong style={{ color: 'orange' }}>{event.event.title}</strong><em><br></br> Ordination: {event.event.ordination}
  <br></br> Patient: {event.event.patient} </em></span> 
  ) 
}

class DoctorCalendar extends React.Component {

  constructor(props){
    super(props);
    this.state = {
            appointments: [],
    }
  }

  componentDidMount () {
    axios.get('http://localhost:8080/api/appointment/get-doctor-appointments', {
      responseType: 'json'
    })
          .then(response => {
            this.setState({appointments: response.data});
            console.log(this.state.appointments)
    })
    .catch((error) => console.log(error))
  }

  render() {
        return (
          <div className="DoctorCalendar">
            <Header/>
            <div className="cal" style={{ height: '500pt'}}>
            <Calendar
              showMultiDayTimes={true}
              header={{
                left: "prev,next today",
                center: "title",
                right: ""
              }}
              popup
              selectable
              localizer={localizer}
              events={this.state.appointments}
              components={{event:CustomEvent}}
              /*components={{
                event: Event,
                agenda: {
                  event: EventAgenda,
                },
              }}*/
              timeslots={3}
              step={30}

              defaultView={Views.MONTH}
              startAccessor="start"
              endAccessor="end"
            />
            </div>
            <Footer/>
          </div>
        )
  }
}

export default DoctorCalendar;
