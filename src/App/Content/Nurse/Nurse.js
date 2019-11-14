import React from 'react';
import './Nurse.css'
import ReactTable from "react-table";
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';

class Nurse extends React.Component {
  render() {
    const auth = [{
    code: 'HSCV1235-1',
    name: 'Pentaksilin',
    patient: 'Katic Kata',
    doctor: 'Marko Stojic',
    authenticate: <button>Confirm</button>
  },{
    code: 'BLJV135-19',
    name: 'Amoksicilin',
    patient: 'Katic Kata',
    doctor: 'Marko Stojic',
    authenticate: <button>Confirm</button>
  }]
    const auth_columns = [{
      Header: 'Code',
      accessor: 'code'
    },{
      Header: 'Name',
      accessor: 'name'
    },{
      Header: 'Patient',
      accessor: 'patient'
    },{
      Header: 'Doctor',
      accessor: 'doctor'
    },{
      Header: 'Authenticate',
      accessor: 'authenticate'
    }]
    const columns = [{
        Header: 'Surname',
        accessor: 'surname'
      },{
        Header: 'Name',
        accessor: 'name'
      },{
        Header: 'Email',
        accessor: 'email'
      },{
        Header: 'CardNumber',
        accessor: 'cardNumber'
      }]
    const data = [{
        email: 'kata@gmail.com',
        surname: 'Katic',
        name: 'Kata',
        cardNumber: 'SA129-2012'
      },{
        email: 'sata@gmail.com',
        surname: 'Satic',
        name: 'Tata',
        cardNumber: 'SA19-2012'
      },{
        email: 'bata@gmail.com',
        surname: 'Batic',
        name: 'Mara',
        cardNumber: 'SA12-2016'
      },{
        email: 'tata@gmail.com',
        surname: 'Tatic',
        name: 'Sava',
        cardNumber: 'SA19-2019'

      },{
        email: 'pata@gmail.com',
        surname: 'Patic',
        name: 'Savo',
        cardNumber: 'SA1219-2016'

      },{
        email: 'zata@gmail.com',
        surname: 'Zatic',
        name: 'Marko',
        cardNumber: 'SA1291-2012'

      }]
  return (
    <div className="Nurse">
      <Header/>
      <div className='patients rtable'>
      <div className="naslov"> My Patients </div>
        <ReactTable 
          data={data}
          columns={columns}
          defaultPageSize = {5}
          pageSizeOptions = {[5, 10, 15]}
        />
        </div> 
      <div className='presc rtable'>
      <div className="naslov"> Prescriptions to Authenticate </div>
        <ReactTable
          data={auth}
          columns={auth_columns}
          pageSize={2}
          showPagination={false}
        />
        </div> 
      <Footer/>

    </div>
  );
  }
}

export default Nurse;