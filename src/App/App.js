import React from 'react';
import './App.css';
// import Router !!!
// switch ...
import Register from './LoginAndRegistration/Register/Register'
import Login from './LoginAndRegistration/Login/Login'
import Nurse from './Content/Nurse/Nurse'

function App() {
  return (
    <div className="App">
      <Nurse />
    </div>
  );
}

export default App;
