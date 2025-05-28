package data.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payrolls")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_id", nullable = false)
    private Integer id;

    @ColumnDefault("0")
    @Column(name = "working_days", nullable = false)
    private Long workingDays;

    @Column(name = "advance_payment", precision = 19, scale = 4)
    private BigDecimal advancePayment;

    @Column(name = "borrowed_date")
    private Instant borrowedDate;

    @Column(name = "payroll_date")
    private Instant payrollDate;

    @ColumnDefault("0.0000")
    @Column(name = "net_salary", nullable = false, precision = 19, scale = 4)
    private BigDecimal netSalary;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}