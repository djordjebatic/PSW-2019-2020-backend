import React from 'react';
import "react-table/react-table.css";
import ReactTable from "react-table";
import axios from 'axios';
import Footer from '../../Footer/Footer';
import Header from '../../Header/Header';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import { Button} from 'react-bootstrap';

const AdminSuccess = withReactContent(Swal)

class Diagnosis extends React.Component{

      constructor (props) {
          super(props);
          this.handleChange = this.handleChange.bind(this);
          this.addNewDiagnosis = this.addNewDiagnosis.bind(this);


          const token = localStorage.getItem('token')
          axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

          this.state = {
              tableData: [
                {
                  id: '',
                  name: '',
                  description: ''
                }
              ],
              name: '',
              description: ''
          };
      }

      componentDidMount () {
          axios.get('http://localhost:8080/api/cc-admin/all-diagnosis', {
              responseType: 'json'
          }).then(response => {
              this.setState({ tableData: response.data });
          });
      }

      addNewDiagnosis =  event => {
        event.preventDefault();
        console.log(this.state);
        axios.post("http://localhost:8080/api/cc-admin/add-diagnosis/", {
          name: this.state.name,
          description: this.state.description
      }).then(response => {
          const {tableData} = this.state;
          tableData.push(response.data);
          this.setState({tableData});
        }).then((resp) => this.onSuccessHandler(resp))
      }

      onSuccessHandler(resp){
        AdminSuccess.fire({
            title: "Diagnosis succesfully added!",
            text: "",
            type: "success",
        })
      }

      deleteDiagnosis = (id) =>{
        axios.put("http://localhost:8080/api/cc-admin/delete-diagnosis/" + id).then(response => {
          const {tableData} = this.state;
          tableData.pop(response.data);
          this.setState({tableData});
        }).then((resp) => this.onSuccessDeleteHandler(resp))
        console.log('This is the id: ' + id);
      }

      onSuccessDeleteHandler(resp){
        AdminSuccess.fire({
            title: "Drug succesfully deleted!",
            text: "",
            type: "success",
        })
      }

      handleChange(e) {
        console.log(e.target.value)
        console.log([e.target.name])
        this.setState({...this.state, [e.target.name]: e.target.value});
        console.log(this.state)
      }

      editDiagnosis =  (id) => {
        axios.put("http://localhost:8080/api/cc-admin/update-drug/" + id, {
          name: this.state.name,
          description: this.state.description
      }).then(response => {
          const {tableData} = this.state;
          tableData.push(response.data);
          this.setState({tableData});
        }).then((resp) => this.onSuccessHandler(resp))
      }

      onSuccessHandler(resp){
        AdminSuccess.fire({
            title: "Diagnosis succesfully added!",
            text: "",
            type: "success",
        })
      }

      editPopup =  (id, name, ingredient, description) => {
      
      }

      render() {
        let { tableData } = this.state;
        return (
          <div className="AssignCCAdmin">
          <Header/>
          <div className='newDrug'>

          <button type="button" className="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Add new Diagnosis</button>
          
          <div class="modal fade" id="exampleModal" tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">New Diagnosis</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form onSubmit={this.addNewDiagnosis}>
                    <div class="form-group">
                      <label htmlFor="name" class="col-form-label">Name:</label>
                      <input type="text" 
                             className="form-control form-control-sm"
                             id="name"
                             name="name"
                             onChange={this.handleChange}
                             placeholder="Enter Name"
                             required/>
                    </div>
                    <div class="form-group">
                      <label htmlFor="description" class="col-form-label">Description:</label>
                      <input type="text" 
                             className="form-control form-control-sm"
                             id="description"
                             name="description"
                             onChange={this.handleChange}
                             placeholder="Enter Description"
                             required/>
                    </div>
                    <div class="modal-footer">
                      <Button type="submit">Save</Button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
          </div>
          <div className='nonccadmins rtable'>
          <ReactTable 
          data={tableData}
          columns={[{
                      Header: 'Id',
                      accessor: 'id',
                      width: 45
                    },{
                      Header: 'Name',
                      accessor: 'name',
                      width: 425
                    },{
                      Header: 'Description',
                      accessor: 'description',
                      width: 500
                    },
                    {
                      Header: '',
                      Cell: row => (                        
                        <div>
                          <button className="btn btn-primary" data-toggle="modal" data-target="#editModal" >Edit {row.original.id}</button>
                          <div></div>
                        </div>
                      ),
                      width: 100
                    },
                    {
                        Header: '',
                        Cell: row => (
                            <div>
                                <button className="btn btn-primary" onClick={() => this.deleteDiagnosis(row.original.id)}>Delete</button>
                            </div>
                        ),                      
                        width: 100

                      }
          ]}
          defaultPageSize = {10}
          />
          </div>
           <Footer/>
           </div>
        )
      }
    }

export default Diagnosis