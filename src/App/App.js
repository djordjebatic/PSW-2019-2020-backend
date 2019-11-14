import React from 'react';
import './App.css';
import {BrowserRouter } from 'react-router-dom'
import {Switch, Route} from 'react-router'
import Register from './LoginAndRegistration/Register/Register'
import Login from './LoginAndRegistration/Login/Login'
import Nurse from './Content/Nurse/Nurse'
import Doctor from './Content/Doctor/Doctor'
import PatientsList from './Content/PatientsList/PatientsList'
import AbsenceRequest from './Content/Doctor/AbsenceRequest/AbsenceRequest';
import NewAppointmentDoctor from './Content/Doctor/NewAppointmentDoctor/NewAppointmentDoctor';
import PersonalProfile from './Content/PersonalProfile/PersonalProfile';
import WorkCalendar from './Content/WorkCalendar/WorkCalendar';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/my-profile">
            <PersonalProfile />
          </Route>
          <Route path="/nurse">
            <Nurse />
          </Route>
          <Route path="/doctor">
            <Doctor />
          </Route>
          <Route path="/patients">
            <PatientsList />
          </Route>
          <Route path="/absence-request">
            <AbsenceRequest />
          </Route>
          <Route path="/new-appointment-doctor">
            <NewAppointmentDoctor />
          </Route>
          <Route path="/work-calendar">
            <WorkCalendar />
          </Route>
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
