package data.mapper;

import data.entity.Degree;
import data.entity.Employee;
import data.entity.Payroll;
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
            response.setEmployeeName(employee.getEmployeeName());
            response.setBasicSalary(employee.getBasicSalary());
            Degree degree = employee.getDegree();
            if (degree != null) {
                response.setBaseSalaryFactor(degree.getBaseSalaryFactor());
            }
        }
        return response;
    }
}
