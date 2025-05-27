package service;

import data.entity.Degree;
import data.entity.Department;
import data.entity.Employee;
import data.mapper.EmployeeMapper;
import data.repository.DegreeRepository;
import data.repository.DepartmentRepository;
import data.repository.EmployeeRepository;
import data.request.AddEmployeeRequest;
import data.response.EmployeeResponse;
import exception.DegreeNotFoundException;
import exception.DepartmentNotFoundException;
import exception.EmployeeNotFoundException;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DegreeRepository degreeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.degreeRepository = new DegreeRepository();
        this.departmentRepository = new DepartmentRepository();
    }

    public EmployeeResponse getEmployeeById(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("ID must be positive");

        try {
            Employee employee = employeeRepository.getEmployee(id);
            return EmployeeMapper.toEmployeeResponse(employee);
        } catch (EntityNotFoundException e){
            throw new EmployeeNotFoundException(e.getMessage());
        }
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        if (employees.isEmpty())
            throw new EmployeeNotFoundException("There are no employees");

        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (Employee employee : employees) {
            employeeResponses.add(EmployeeMapper.toEmployeeResponse(employee));
        }
        return employeeResponses;
    }

    public EmployeeResponse addEmployee(AddEmployeeRequest request) {
        Department department = null;
        Degree degree = null;
        if (request.getDepartmentId() > 0) {
            try {
                department = departmentRepository.getDepartment(request.getDepartmentId());
            } catch (EntityNotFoundException e){
                throw new DepartmentNotFoundException(e.getMessage());
            }
        }
        if (request.getDegreeId() > 0) {
            try {
                degree = degreeRepository.getDegree(request.getDegreeId());
            } catch (EntityNotFoundException e){
                throw new DegreeNotFoundException(e.getMessage());
            }
        }

        Employee newEmployee = EmployeeMapper.toEmployee(request);
        newEmployee.setDepartment(department);
        newEmployee.setDegree(degree);
        employeeRepository.add(newEmployee);
        return EmployeeMapper.toEmployeeResponse(newEmployee);
    }
}
