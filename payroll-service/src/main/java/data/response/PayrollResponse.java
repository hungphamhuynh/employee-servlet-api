package data.response;

import java.math.BigDecimal;
import java.time.Instant;

public class PayrollResponse {
    private int payrollId;
    private String employeeName;
    private double basicSalary;
    private double baseSalaryFactor;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getBaseSalaryFactor() {
        return baseSalaryFactor;
    }

    public void setBaseSalaryFactor(double baseSalaryFactor) {
        this.baseSalaryFactor = baseSalaryFactor;
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
