package servlet;

import data.repository.EmployeeRepository;
import data.request.UpdateEmployeeRequest;
import data.response.EmployeeResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EmployeeService;
import util.HttpResponseUtil;
import util.JsonUtil;

import java.io.IOException;

@WebServlet("/employee/update")
public class UpdateEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService(new EmployeeRepository());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UpdateEmployeeRequest requestBody = JsonUtil.fromJson(req.getInputStream(), UpdateEmployeeRequest.class);
        EmployeeResponse employeeResponse = employeeService.updateEmployee(requestBody);
        HttpResponseUtil.sendSuccess(
                resp,
                HttpServletResponse.SC_OK,
                "Employee updated successfully",
                employeeResponse
        );
    }
}
