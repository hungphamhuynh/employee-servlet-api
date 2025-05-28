package data.mapper;

import data.entity.Employee;
import data.request.AddEmployeeRequest;
import data.request.UpdateEmployeeRequest;
import data.response.EmployeeResponse;

public class EmployeeMapper {

    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(employee.getId());
        employeeResponse.setEmployeeName(employee.getEmployeeName());
        employeeResponse.setDateOfBirth(employee.getDateOfBirth());
        employeeResponse.setBasicSalary(employee.getBasicSalary());
        employeeResponse.setPhoneNumber(employee.getPhoneNumber());
        if (employee.getDepartment() != null) {
            employeeResponse.setDepartmentName(employee.getDepartment().getDepartmentName());
        }
        if (employee.getDegree() != null) {
            employeeResponse.setDegreeName(employee.getDegree().getDegreeName());
        }
        return employeeResponse;
    }

    public static Employee toEmployee(AddEmployeeRequest request) {
        Employee employee = new Employee();
        employee.setEmployeeName(request.getEmployeeName());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setBasicSalary(request.getBasicSalary());
        employee.setPhoneNumber(request.getPhoneNumber());
        return employee;
    }

    public static Employee toEmployee(UpdateEmployeeRequest request) {
        Employee employee = new Employee();
        employee.setId(request.getEmployeeId());
        employee.setEmployeeName(request.getEmployeeName());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setBasicSalary(request.getBasicSalary());
        employee.setPhoneNumber(request.getPhoneNumber());
        return employee;
    }
}
