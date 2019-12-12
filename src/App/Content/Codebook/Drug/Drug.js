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

class Drug extends React.Component{

      constructor (props) {
          super(props);
          this.handleChange = this.handleChange.bind(this);
          this.addNewDrug = this.addNewDrug.bind(this);


          const token = localStorage.getItem('token')
          axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

          this.state = {
              tableData: [
                {
                  id: '',
                  name: '',
                  ingredient: '',
                  description: ''
                }
              ],
              name: '',
              ingredient: '',
              description: ''
          };
      }

      componentDidMount () {
          axios.get('http://localhost:8080/api/cc-admin/get-all-drugs', {
              responseType: 'json'
          }).then(response => {
              this.setState({ tableData: response.data });
          });
      }

      addNewDrug =  event => {
        event.preventDefault();
        console.log(this.state);
        axios.post("http://localhost:8080/api/cc-admin/add-drug/", {
          name: this.state.name,
          ingredient: this.state.ingredient,
          description: this.state.description
      }).then(response => {
          const {tableData} = this.state;
          tableData.push(response.data);
          this.setState({tableData});
        }).then((resp) => this.onSuccessHandler(resp))
      }

      onSuccessHandler(resp){
        AdminSuccess.fire({
            title: "Drug succesfully added!",
            text: "",
            type: "success",
        })
      }

      deleteDrug = (id) =>{
        axios.put("http://localhost:8080/api/cc-admin/delete-drug/" + id).then(response => {
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

      editDrug =  (id) => {
        axios.put("http://localhost:8080/api/cc-admin/edit-drug/" + id, {
          name: this.state.name,
          ingredient: this.state.ingredient,
          description: this.state.description
      }).then(response => {
          const {tableData} = this.state;
          tableData.push(response.data);
          this.setState({tableData});
        }).then((resp) => this.onSuccessHandler(resp))
      }

      onSuccessHandler(resp){
        AdminSuccess.fire({
            title: "Drug succesfully added!",
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

          <button type="button" className="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Add new Drug</button>
          
          <div class="modal fade" id="exampleModal" tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">New Drug</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form onSubmit={this.addNewDrug}>
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
                      <label htmlFor="ingredient" class="col-form-label">Ingredient:</label>
                      <input type="text" 
                             className="form-control form-control-sm"
                             id="ingredient"
                             name="ingredient"
                             onChange={this.handleChange}
                             placeholder="Enter Ingredient"
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
                      width: 225
                    },{
                      Header: 'Ingredients',
                      accessor: 'ingredient',
                      width: 200
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
                          <div>
          <div class="modal fade" id="editModal" tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel2" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel2">New Drug</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close2">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form onSubmit={this.editDrug(row.original.id)}>
                  <div class="form-group">
                    <label htmlFor="name" class="col-form-label">Name:</label>
                    <input type="text" 
                          className="form-control form-control-sm"
                          id="name"
                          name="name"
                          defaultValue={row.original.name}
                          onChange={this.handleChange}
                          placeholder="Enter Name"
                          required/>
                  </div>
                  <div class="form-group">
                    <label htmlFor="ingredient" class="col-form-label">Ingredient:</label>
                    <input type="text" 
                          className="form-control form-control-sm"
                          id="ingredient"
                          name="ingredient"
                          defaultValue={row.original.ingredient}
                          onChange={this.handleChange}
                          placeholder="Enter Ingredient"
                          required/>
                  </div>
                  <div class="form-group">
                    <label htmlFor="description" class="col-form-label">Description:</label>
                    <input type="text" 
                          className="form-control form-control-sm"
                          id="description"
                          name="description"
                          defaultValue={row.original.description}
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
                        </div>
                      )
                    },
                    {
                        Header: '',
                        Cell: row => (
                            <div>
                                <button className="btn btn-primary" onClick={() => this.deleteDrug(row.original.id)}>Delete</button>
                            </div>
                        )
                      }
          ]}
          defaultPageSize = {10}
          pageSizeOptions = {[5, 10, 15]}
          />
          </div>
           <Footer/>
           </div>
        )
      }
    }

export default Drug