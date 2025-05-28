package service;

import data.entity.Payroll;
import data.mapper.PayrollMapper;
import data.repository.PayrollRepository;
import data.request.AddPayrollRequest;
import data.response.PayrollResponse;
import exception.PayrollNotFoundException;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class PayrollService {
    private final PayrollRepository payrollRepository;

    public PayrollService(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
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
        if (payrollRepository.existsByEmployeeIdAndMonth(request.getEmployeeId(), request.getPayrollDate()));
    }
}
