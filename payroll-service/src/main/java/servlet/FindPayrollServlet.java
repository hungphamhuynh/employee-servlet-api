package servlet;

import data.entity.Payroll;
import data.repository.EmployeeRepository;
import data.repository.PayrollRepository;
import data.response.ErrorType;
import data.response.PayrollResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PayrollService;
import util.HttpResponseUtil;

import java.io.IOException;

@WebServlet("/payroll/find")
public class FindPayrollServlet extends HttpServlet {
    private PayrollService payrollService;

    @Override
    public void init() throws ServletException {
        payrollService = new PayrollService(new PayrollRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            HttpResponseUtil.sendError(
                    resp,
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Payroll id is required",
                    ErrorType.INVALID_INPUT
            );
            return;
        }

        try {
            int payrollId = Integer.parseInt(id);
            PayrollResponse response = payrollService.getPayrollById(payrollId);
            HttpResponseUtil.sendSuccess(
                    resp,
                    HttpServletResponse.SC_OK,
                    "Payroll retrieved successfully",
                    response
            );
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Payroll id is not a number");
        }
    }
}
