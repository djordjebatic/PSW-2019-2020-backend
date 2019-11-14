import React from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css";
import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';


class AbsenceRequest extends React.Component {
  render() {
    
  return (
    <div className="AbsenceRequest">
      <Header/>
      <br/>
      <h3>Absence Request Page</h3>
      <br/>
      <Footer/>

    </div>
  );
  }
}

export default AbsenceRequest;