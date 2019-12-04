import React from 'react';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import'./PersonalProfile.css'

class PersonalProfile extends React.Component {
 
 construct(props){
   
   this.state={

   }
 }
 
 
  render() {
    
  return (
    <div className="PersonalProfile">
      <Header/>
      <br/>
      <h3 >Personal page</h3>
      <br/>
        <div className="info">
          <div className="form-group">
            <label>First name: </label>


          </div>
          <div className="form-group">
            <label>Last name: </label>


          </div>
          <div className="form-group">
            <label>Medical number: </label>


          </div>
          <div className="form-group">
            <label>Address: </label>


          </div>
          <div className="form-group">
            <label>City: </label>


          </div>
          <div className="form-group">
            <label>Country: </label>


          </div>
          <div className="form-group">
            <label>Phone number: </label>


          </div>
      </div>
      <br/>
      <Footer/>

    </div>
  );
  }
}

export default PersonalProfile;