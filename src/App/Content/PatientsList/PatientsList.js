import React from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import axios from 'axios'

import './PatientsList.css'

class PatientsList extends React.Component {

  constructor(props){
    super(props);

    this.state = {
        patients: [],
        searchQuery: ''
    }

    this.cancel = '';
  }

  componentDidMount() {
    var token = localStorage.getItem('token');
    axios.get("http://localhost:8080/api/patients")  
      .then(response => {
          let tmpArray = []
          for (var i = 0; i < response.data.length; i++) {
              tmpArray.push(response.data[i])
          }

          this.setState({
              patients: tmpArray
          })
      })
    .catch((error) => console.log(error))
  }

  fetchSearchResults(updatedPageNumber = '', query) {
      var pageNumber = updatedPageNumber ? `&page=${updatedPageNumber}` : ''; //za sad
      var searchUrl = `blabla${query}blabla`;

      if (this.cancel) {
        this.cancel.cancel();
      }

      this.cancel = axios.CancelToken.source();

      axios.get("http://localhost:8080/api/patients", { //inace searchUrl
        cancelToken: this.cancel.token
      })
      .then(res => {
        console.log(res.data[0]);
        //this.setState({patients: [res.data[0], res.data[1]] })
      })
      .catch(error => {
        console.warn(error);
      })
  }

  handleOnFilterInputChange = (event) => {
      if (event[0] != undefined){
        console.log(event[0].value);
        var query = event[0].value;
        this.setState({searchQuery: query}, () => {
          //this.fetchSearchResults(1, query);
        })
      } else {
        this.setState({searchQuery: ""})
      }
  }

  render() {

    const columns=[
      {
        Header:'Id',
        id: 'id',
        accessor: d => d.id
    },{
      Header:'First Name',
      accessor: 'firstName'
    },{
      Header:'Last Name',
      accessor: 'lastName'
    },{
        Header:'Email Address',
        accessor: 'username'
    },{
      Header:'Address',
      accessor: 'address'
  },{
        Header:'City',
        accessor: 'city'
    },{
      Header:'Country',
      accessor: 'country'
  }]

  return (
    <div className="PatientsList">
      <Header/>
      <div className="row">
        <div className="col-10">
          <br/>
        <h3>Patients List</h3>
          <div className='patients rtable'>
            <ReactTable 
              data={this.state.patients}
              columns={columns}
              filterable
              onFilteredChange = {this.handleOnFilterInputChange}
              defaultPageSize = {6}
              pageSizeOptions = {[6, 10, 15]}
            />
            </div>
        </div>
        <div className="col-2 patient-list-image">

        </div>
      </div>
       
      <Footer/>

    </div>
  );
  }
}

export default PatientsList;