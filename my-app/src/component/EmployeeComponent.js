import React, { Component } from 'react'
import EmployeeService from '../service/EmployeeService'

export default class EmployeeComponent extends Component {

   constructor(props) {
    super(props)
  
    this.state = {
       employee: {},
       department: {},
       organization: {}
    }
   }

   // componentDidMount() is invoked immediately after a component is mounted (inserted into the tree).
   componentDidMount() {
     EmployeeService.getEmployees().then((res) => {
         this.setState({ employee: res.data.data.employeeDto})
         this.setState({ department: res.data.data.departmentDto})
         this.setState({ organization: res.data.data.organizationDto})

         console.log(this.state.employee)
         console.log(this.state.department)
         console.log(this.state.organization)
      })
   }

  render() {
    return (
      <div>
        <div className="card col-md-6 offset-md-3">
          <h3 className='text-center card-header'>View Employee Details</h3>
          <div className='card-body'>
            <div className='row'>
              <p><strong>Employee First Name:</strong>{this.state.employee.firstName}</p>
            </div>
            <div className='row'>
              <p><strong>Employee Last Name:</strong>{this.state.employee.lastName}</p>
            </div>  
             <div className='row'>
              <p><strong>Employee Email:</strong>{this.state.employee.email}</p>
            </div> 
          </div>

          <h3 classNmae='text-center card-header'>View Department Details</h3> 
          <div className='card-body'>
            <div className='row'>
              <p><strong>Department Name:</strong>{this.state.department.departmentName}</p>
            </div>
            <div className='row'>
              <p><strong>Department Description:</strong>{this.state.department.departmentDescription}</p>
            </div>  
            <div className='row'>
              <p><strong>Department Code:</strong>{this.state.department.departmentCode}</p>
            </div>  
         </div>
         <h3 classNmae='text-center card-header'>View Organization Details</h3>
         <div className='card-body'>
            <div className='row'>
              <p><strong>Organization Name:</strong>{this.state.organization.organizationName}</p>
            </div>
            <div className='row'>
              <p><strong>Organization Description:</strong>{this.state.organization.organizationDescription}</p>
            </div>  
            <div className='row'>
              <p><strong>Organization Code:</strong>{this.state.organization.organizationCode}</p>
            </div> 
          </div>       
  
        </div>
      </div>
    )
  }
}
