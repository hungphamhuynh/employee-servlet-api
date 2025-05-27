package servlet;

import data.repository.EmployeeRepository;
import data.request.AddEmployeeRequest;
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

@WebServlet("/employee/add")
public class AddEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService(new EmployeeRepository());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddEmployeeRequest addEmployeeRequest = JsonUtil.fromJson(req.getInputStream(), AddEmployeeRequest.class);
        EmployeeResponse employeeResponse = employeeService.addEmployee(addEmployeeRequest);
        HttpResponseUtil.sendSuccess(
                resp,
                HttpServletResponse.SC_CREATED,
                "Employee added successfully",
                employeeResponse
        );
    }
}
