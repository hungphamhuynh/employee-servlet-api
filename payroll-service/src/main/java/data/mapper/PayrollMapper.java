package data.mapper;

import data.entity.Degree;
import data.entity.Employee;
import data.entity.Payroll;
import data.request.AddPayrollRequest;
import data.request.UpdatePayrollRequest;
import data.response.PayrollResponse;

public class PayrollMapper {
    public static PayrollResponse toPayrollResponse(Payroll payroll) {
        PayrollResponse response = new PayrollResponse();
        response.setPayrollId(payroll.getId());
        response.setWorkingDays(payroll.getWorkingDays());
        response.setAdvancePayment(payroll.getAdvancePayment());
        response.setBorrowedDate(payroll.getBorrowedDate());
        response.setPayrollDate(payroll.getPayrollDate());
        response.setNetSalary(payroll.getNetSalary());

        if (payroll.getEmployee() != null) {
            Employee employee = payroll.getEmployee();
            response.setEmployeeId(employee.getId());
            response.setEmployeeName(employee.getEmployeeName());
            response.setBasicSalary(employee.getBasicSalary());
            Degree degree = employee.getDegree();
            if (degree != null) {
                response.setBaseSalaryFactor(degree.getBaseSalaryFactor());
            }
        }
        return response;
    }

    public static Payroll toPayroll(AddPayrollRequest request) {
        Payroll payroll = new Payroll();
        payroll.setWorkingDays(request.getWorkingDays());
        payroll.setAdvancePayment(request.getAdvancePayment());
        payroll.setBorrowedDate(request.getBorrowedDate());
        payroll.setPayrollDate(request.getPayrollDate());
        payroll.setNetSalary(request.getNetSalary());
        return payroll;
    }

    public static Payroll toPayroll(UpdatePayrollRequest request) {
        Payroll payroll = new Payroll();
        payroll.setId(request.getPayrollId());
        payroll.setWorkingDays(request.getWorkingDays());
        payroll.setAdvancePayment(request.getAdvancePayment());
        payroll.setBorrowedDate(request.getBorrowedDate());
        payroll.setPayrollDate(request.getPayrollDate());
        payroll.setNetSalary(request.getNetSalary());
        return payroll;
    }
}
