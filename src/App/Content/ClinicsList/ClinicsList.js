import React from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import './ClinicsList.css'
import Select from "react-select";

class ClinicsList extends React.Component{
   
    constructor(){
        super();

        this.state={
            data: [],
            filtered:[],
            selected: undefined
        };
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
      

    render(){

        const columns=[{
            Header:'Name',
            id: 'name',
            accessor: d => d.name
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
              <div >
              <div className="clinics-title">Clinic list</div>
                <br/>
                <br/>
              <div className="clinic-filter">  
              <label >Filter clinics by address: </label> 
              <Select
                onChange={entry => {
                    this.setState({ selected: entry });
                    this.onFilteredChangeCustom(
                    entry.map(o => {
                        return o.value;
                    }),
                    "address"
                    );
                }}
                value={this.state.selected}
                multi={true}
                options={this.state.data.map((o, i) => {
                    return { id: i, value: o.address, label: o.address };
                })}
             />
             </div>
                <div className='clinics rtable'>
                <ReactTable 
                  data={data}
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

export default ClinicsList;