package servlet;

import data.repository.EmployeeRepository;
import data.response.EmployeeResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EmployeeService;
import util.HttpResponseUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee/find-all")
public class FindAllEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService(new EmployeeRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EmployeeResponse> responses = employeeService.getAllEmployees();
        HttpResponseUtil.sendSuccess(
                resp,
                HttpServletResponse.SC_OK,
                "All employees found",
                responses
        );
    }
}
