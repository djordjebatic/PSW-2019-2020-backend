import React from 'react';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';


class PersonalProfile extends React.Component {
  render() {
    
  return (
    <div className="PersonalProfile">
      <Header/>
      <br/>
      <h3>Personal Profile Page</h3>
      <br/>
      <Footer/>

    </div>
  );
  }
}

export default PersonalProfile;