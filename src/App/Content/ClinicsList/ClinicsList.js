import React from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import './ClinicsList.css'

class ClinicsList extends React.Component{
    render(){

        const columns=[{
            Header:'Name',
            accessor: 'name'
        },{
            Header:'Address',
            accessor: 'address'
        },{
            Header:'Score',
            accessor: 'score'
        }]

        const data=[{
            name:'ST MEDICINA',
            address:'Bulevar oslobodjenja 79',
            score:'3,2'
        },{
            name:'Klinika Perinatal',
            address:'Ilije Ognjenovica 79',
            score:'4,5' 
        },{
            name:'Eliksir',
            address:'Marodiceva 12',
            score:'4,5' 
        }]

        return (
            <div className="ClinicsList">
              <Header/>
              <div className='clinics rtable'>
              <div className="clinics-title">Clinic list</div>
                <ReactTable 
                  data={data}
                  columns={columns}
                  defaultPageSize = {10}
                  pageSizeOptions = {[5, 10, 15]}
                />
                </div> 
              <Footer/>
        
            </div>
          );
    }

}

export default ClinicsList;