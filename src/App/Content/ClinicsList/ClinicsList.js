import React from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import './ClinicsList.css'
import Select from "react-select";
import axios from 'axios'
import { NotificationManager } from 'react-notifications';
import { Link, withRouter } from 'react-router-dom'

class ClinicsList extends React.Component{
   
    constructor(props){
        super(props);

        this.state={

            clinics: [{
              id:'',
              name :'',
              description:'',
              address:'',
              city:'',
              stars:'',
              num_votes:''
            }],
            data:[],
            filtered:[],
            selected: undefined
        };
    }

    componentDidMount() {
      var token = localStorage.getItem('token');
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      axios.get("http://localhost:8080/api/clinics", {
        responseType: 'json'
    }).then(response => {
        this.setState({ clinics: response.data });
    });
}  
       

    onFilteredChangeCustom = (value, accessor) => {
        let filtered = this.state.filtered;
        let insertNewFilter = 1;
    
        if (filtered.length) {
          filtered.forEach((filter, i) => {
            if (filter["id"] === accessor) {
              if (value === "" || !value.length) filtered.splice(i, 1);
              else filter["value"] = value;
    
              insertNewFilter = 0;
            }
          });
        }
        if (insertNewFilter) {
            filtered.push({ id: accessor, value: value });
          }
      
          this.setState({ filtered: filtered });
     };

     visit = (id) =>{
      console.log(id)
      axios.post("http://localhost:8080/api/clinic/"+id).then(response => {
          console.log(response)
          this.props.history.push({
          pathname:'/clinic-page',
          state: {data:id}
        })

      }).then((resp)=>{       
      }).catch((error)=>console.log(error.response))
  }
      

    render(){
        
        let { clinics } = this.state;
        const columns=[
          {
            Header:'Id',
            id: 'id',
            accessor: d => d.id
        },{
          Header:'Name',
          accessor: 'name'
        },{
            Header:'Address',
            accessor: 'address'
        },{
            Header:'Stars',
            accessor: 'stars'
        },{
          Header: '',
          Cell: row => (
              <div>
                 <button className="primary btn" onClick={() => this.visit(row.original.id)}>Visit</button>
               </div>
          ),
          width: 100

        }]

        return (
            <div className="ClinicsList">
              <Header/>
              <div >
              <div className="clinics-title">Clinic list</div>
                <br/>
                <br/>
                <div className='clinics rtable'>
                <ReactTable 
                  data={clinics}
                  filterable
                  filtered={this.state.filtered}
                  onFilteredChange={(filtered, column, value) => {
                    this.onFilteredChangeCustom(value, column.id || column.accessor);
                  }}
                  defaultFilterMethod={(filter, row, column) => {
                    const id = filter.pivotId || filter.id;
                    if (typeof filter.value === "object") {
                      return row[id] !== undefined
                        ? filter.value.indexOf(row[id]) > -1
                        : true;
                    } else {
                      return row[id] !== undefined
                        ? String(row[id]).indexOf(filter.value) > -1
                        : true;
                    }
                  }}
                  columns={columns}
                  defaultPageSize = {10}
                  pageSizeOptions = {[5, 10, 15]}
                />
                </div>
                </div> 
              <Footer/>
        
            </div>
          );
    }

}

export default withRouter (ClinicsList);