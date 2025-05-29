package servlet;

import data.repository.EmployeeRepository;
import data.repository.PayrollRepository;
import data.request.AddPayrollRequest;
import data.response.PayrollResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PayrollService;
import util.HttpResponseUtil;
import util.JsonUtil;

import java.io.IOException;

@WebServlet("/payroll/add")
public class AddPayrollServlet extends HttpServlet {
    private PayrollService payrollService;

    @Override
    public void init() throws ServletException {
        payrollService = new PayrollService(new PayrollRepository());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddPayrollRequest addPayrollRequest = JsonUtil.fromJson(req.getInputStream(), AddPayrollRequest.class);
        PayrollResponse payrollResponse = payrollService.addPayroll(addPayrollRequest);
        HttpResponseUtil.sendSuccess(
                resp,
                HttpServletResponse.SC_CREATED,
                "Payroll added successfully",
                payrollResponse
        );
    }
}
