package servlet;

import data.repository.EmployeeRepository;
import data.repository.PayrollRepository;
import data.request.UpdatePayrollRequest;
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

@WebServlet("/payroll/update")
public class UpdatePayrollServlet extends HttpServlet {
    private PayrollService payrollService;

    @Override
    public void init() throws ServletException {
        payrollService = new PayrollService(new PayrollRepository());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UpdatePayrollRequest requestBody = JsonUtil.fromJson(req.getInputStream(), UpdatePayrollRequest.class);
        PayrollResponse payrollResponse = payrollService.updatePayroll(requestBody);
        HttpResponseUtil.sendSuccess(
                resp,
                HttpServletResponse.SC_OK,
                "payroll updated successfully",
                payrollResponse
        );
    }
}
