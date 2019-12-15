import React from 'react';
import "react-table/react-table.css";
import ReactTable from "react-table";
import axios from 'axios';
import Footer from '../../Footer/Footer';
import Header from '../../Header/Header';
import {NotificationManager} from 'react-notifications';
import { Button} from 'react-bootstrap';
import './Diagnosis.css'
import Modal from 'react-modal';
Modal.setAppElement('#root')


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
              id: '',
              name: '',
              description: '',
              modalIsOpen: false,
              editModalIsOpen: false
          };
          this.openModal = this.openModal.bind(this);
          this.closeModal = this.closeModal.bind(this);
          this.closeEditModal = this.closeModal.bind(this);

      }

      openModal() {
        this.setState({modalIsOpen: true});
      }

      openEditModal(p_id, p_name, p_description) {
        this.setState({id: p_id, name: p_name, description: p_description})
        this.setState({editModalIsOpen: true});
      }
     
      closeModal() {
        this.setState({modalIsOpen: false});
      }

      closeEditModal() {
        this.setState({editModalIsOpen: false});
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
        this.setState({modalIsOpen: false});
        console.log(this.state);
        axios.post("http://localhost:8080/api/cc-admin/add-diagnosis/", {
          name: this.state.name,
          description: this.state.description
      }).then(response => {
          NotificationManager.success('Diagnosis successfuly added!', '', 3000);
          const {tableData} = this.state;
          tableData.push(response.data);
          this.setState({tableData});
        })
        .catch((error)=> {NotificationManager.error('Wrong input.', 'Error', 3000);}) 
      }

      deleteDiagnosis = (id) =>{
        axios.put("http://localhost:8080/api/cc-admin/delete-diagnosis/" + id).then(response => {
          const {tableData} = this.state;
          tableData.pop(response.data);
          this.setState({tableData});
        }).then(response => {
          NotificationManager.success('Diagnosis successfuly deleted', '', 3000);
          ;})
      }

      handleChange(e) {
        console.log(e.target.value)
        console.log([e.target.name])
        this.setState({...this.state, [e.target.name]: e.target.value});
        console.log(this.state)
      }

      editDiagnosis =  (id) => {
        //this.setState({editModalIsOpen: false});
        axios.put("http://localhost:8080/api/cc-admin/update-diagnosis/" + id, {
          name: this.state.name,
          description: this.state.description
      }).then(response => {
          NotificationManager.success('Diagnosis successfuly updated', '', 3000);
        })
      }

      render() {
        let { tableData } = this.state;
        return (
          <div className="AssignCCAdmin">
          <Header/>
          <div>
        <Modal
          isOpen={this.state.editModalIsOpen}
          onRequestClose={this.closeEditModal}
          contentLabel="Example Modal"
        >
          <button onClick={this.closeEditModal} type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
          </button>
          <form>
          <div class="form-group">
                      <label htmlFor="name" class="col-form-label">Name:</label>
                      <input type="text" 
                             className="form-control form-control-sm"
                             id="name"
                             name="name"
                             defaultValue={this.state.name}
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
                             defaultValue={this.state.name}
                             onChange={this.handleChange}
                             placeholder="Enter Description"
                             required/>
                    </div>
                    <div class="modal-footer">
                      <Button onClick={() => this.editDiagnosis(this.state.id)}>Save</Button>
            </div>
          </form>
        </Modal>

        <button className="btn primary jej" onClick={this.openModal}>Add new Diagnosis</button>
        <Modal
          isOpen={this.state.modalIsOpen}
          onRequestClose={this.closeModal}
          contentLabel="Example Modal"
        >
          <button onClick={this.closeModal} type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
          </button>
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
        </Modal>
      </div>
          <div className='newDrug'>
          </div>
          <div className='nonccadmins rtable'>
          <ReactTable 
          data={tableData}
          columns={[{
                      Header: 'Name',
                      accessor: 'name',
                      width: 300
                    },{
                      Header: 'Description',
                      accessor: 'description',
                      width: 475
                    },
                    {
                      Header: '',
                      Cell: row => (                        
                        <div>
                          <button className="btn primary" onClick={() => this.openEditModal(row.original.id, row.original.name, row.original.description)}>Edit</button>
                        </div>
                      ),
                      width: 150
                    },
                    {
                        Header: '',
                        Cell: row => (
                            <div>
                                <button className="primary btn" onClick={() => this.deleteDiagnosis(row.original.id)}>Delete</button>
                            </div>
                        ),                      
                        width: 125

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