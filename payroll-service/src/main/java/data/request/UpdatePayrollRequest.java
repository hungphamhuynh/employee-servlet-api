package data.request;

import java.math.BigDecimal;
import java.time.Instant;

public class UpdatePayrollRequest {
    private int payrollId;
    private int employeeId;
    private Long workingDays;
    private BigDecimal advancePayment;
    private Instant borrowedDate;
    private Instant payrollDate;
    private BigDecimal netSalary;

    public int getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Long getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Long workingDays) {
        this.workingDays = workingDays;
    }

    public BigDecimal getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(BigDecimal advancePayment) {
        this.advancePayment = advancePayment;
    }

    public Instant getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Instant borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Instant getPayrollDate() {
        return payrollDate;
    }

    public void setPayrollDate(Instant payrollDate) {
        this.payrollDate = payrollDate;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(BigDecimal netSalary) {
        this.netSalary = netSalary;
    }
}
