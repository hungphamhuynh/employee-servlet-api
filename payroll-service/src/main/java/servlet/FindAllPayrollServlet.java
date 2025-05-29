package servlet;

import data.repository.EmployeeRepository;
import data.repository.PayrollRepository;
import data.response.PayrollResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PayrollService;
import util.HttpResponseUtil;

import java.io.IOException;
import java.util.List;

@WebServlet("/payroll/find-all")
public class FindAllPayrollServlet extends HttpServlet {
    private PayrollService payrollService;

    @Override
    public void init() throws ServletException {
        payrollService = new PayrollService(new PayrollRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PayrollResponse> responses = payrollService.getAllPayrolls();
        String message;
        if (responses.isEmpty()) {
            message = "There are no payrolls in the database";
        } else message = "There are " + responses.size() + " payrolls in the database";
        HttpResponseUtil.sendSuccess(
                resp,
                HttpServletResponse.SC_OK,
                message,
                responses
        );
    }
}
