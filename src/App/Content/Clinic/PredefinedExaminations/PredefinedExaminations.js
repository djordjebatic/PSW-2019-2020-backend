import React from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css";
import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';
import './PredefinedExaminations.css'
import { Button} from 'react-bootstrap';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import axios from 'axios';

class PredefinedExaminations extends React.Component {

    constructor(){
        super();

        this.state={
            examinations: [{
                id:'',
                type:'',
                date:'',
                time:'',
                price:'',
                duration:'',
                ordination:'',
                doctor:'',
                discount:''
            }],
            filtered:[],
            selected: undefined
        };
    }

    
    componentDidMount() {
        var token = localStorage.getItem('token');
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        axios.get("http://localhost:8080/api/all-predefined-appointments", {
          responseType: 'json'
      }).then(response => {
          this.setState({ examinations: response.data });
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

    render() {

        let{examinations}=this.state;
        const columns=[{
            Header: 'Id',
            id: 'id',
            accessor: d=>d.id
        },{
            Header:'Type',
            accessor:'type'
        },{
            Header:'Date',
            accessor:'date'
        },{
            Header:'Time',
            accessor:'time'
        },{
            Header:'Price',
            accessor:'price'
        },{
            Header:'Duration',
            accessor:'duration'
        },{
            Header:'Doctor',
            accessor:'doctor'
        },{
            Header:'Ordination',
            accessor:'ordination'
        },{
            Header:'Discount',
            accessor:'discount'
        },{
            Header:'Schedule',
            Cell: <Button>Schedule</Button>
        }
    ]

        return(
            
            <div className="Predefined-Examinations">
                <Header/>

                <div className="Predefined-Examinations-title">Predefined examinations</div>

                <div className='clinics rtable'>
                    <ReactTable 
                    data={examinations}
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

                <Footer/>
            </div>

            );
        }
    }
    
export default PredefinedExaminations;