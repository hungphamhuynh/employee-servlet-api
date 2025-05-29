package service;

import data.entity.Employee;
import data.entity.Payroll;
import data.mapper.PayrollMapper;
import data.repository.EmployeeRepository;
import data.repository.PayrollRepository;
import data.request.AddPayrollRequest;
import data.request.UpdatePayrollRequest;
import data.response.PayrollResponse;
import exception.EmployeeNotFoundException;
import exception.PayrollExistsException;
import exception.PayrollNotFoundException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class PayrollService {
    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;

    public PayrollService(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
        this.employeeRepository = new EmployeeRepository();
    }

    public PayrollResponse getPayrollById(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("id must be greater than 0");

        try {
            Payroll payroll = payrollRepository.getPayroll(id);
            return PayrollMapper.toPayrollResponse(payroll);
        } catch (EntityNotFoundException e){
            throw new PayrollNotFoundException("Payroll with id " + id + " not found");
        }
    }

    public List<PayrollResponse> getAllPayrolls() {
        List<Payroll> payrolls = payrollRepository.getAllPayrolls();
        List<PayrollResponse> payrollResponses = new ArrayList<>();
        for (Payroll payroll : payrolls) {
            payrollResponses.add(PayrollMapper.toPayrollResponse(payroll));
        }
        return payrollResponses;
    }

    public PayrollResponse addPayroll(AddPayrollRequest request) {
        if (payrollRepository.existsByEmployeeIdAndMonth(request.getEmployeeId(), request.getPayrollDate()))
            throw new PayrollExistsException("Payroll with employee id " + request.getEmployeeId() + " already exists");

        Employee employee = employeeRepository.getEmployee(request.getEmployeeId());
        Payroll payroll = PayrollMapper.toPayroll(request);
        payroll.setEmployee(employee);
        payrollRepository.add(payroll);
        return PayrollMapper.toPayrollResponse(payroll);
    }

    public PayrollResponse deletePayroll(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("id must be greater than 0");

        try {
            Payroll payroll = payrollRepository.getPayroll(id);
            payrollRepository.delete(payroll.getId());
            return PayrollMapper.toPayrollResponse(payroll);
        } catch (EntityNotFoundException e){
            throw new PayrollNotFoundException(e.getMessage());
        }
    }

    public PayrollResponse updatePayroll(UpdatePayrollRequest request) {
        if (request.getPayrollId() <= 0)
            throw new IllegalArgumentException("Payroll Id must be greater than 0");

        Employee employee = getEmployeeById(request.getEmployeeId());
        Payroll payroll = PayrollMapper.toPayroll(request);
        try {
            payrollRepository.getPayroll(request.getPayrollId());
        } catch (EntityNotFoundException e){
            throw new PayrollNotFoundException(e.getMessage());
        }
        payroll.setEmployee(employee);
        payroll = payrollRepository.update(payroll);
        return PayrollMapper.toPayrollResponse(payroll);
    }

    public Employee getEmployeeById(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("id employee must be greater than 0");

        try {
            return employeeRepository.getEmployee(id);
        } catch (EntityNotFoundException e) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }
    }
}
