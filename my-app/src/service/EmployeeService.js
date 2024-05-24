import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:9091/api/v1/employees";

const EMPLOYEE_ID = 3;

class EmployeeService {

    getEmployees(){
       return axios.get(`${EMPLOYEE_API_BASE_URL}/${EMPLOYEE_ID}`);
    }

}

export default new EmployeeService()