package servlet;

import data.entity.Employee;
import data.repository.EmployeeRepository;
import data.response.EmployeeResponse;
import data.response.ErrorType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EmployeeService;
import util.HttpResponseUtil;

import java.io.IOException;

@WebServlet("/employee/find")
public class FindEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService(new EmployeeRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            HttpResponseUtil.sendError(
                    resp,
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Employee id is required",
                    ErrorType.INVALID_INPUT
            );
            return;
        }

        try {
            int employeeId = Integer.parseInt(id);
            EmployeeResponse response = employeeService.getEmployeeById(employeeId);
            HttpResponseUtil.sendSuccess(
                    resp,
                    HttpServletResponse.SC_OK,
                    "Employee retrieved successfully",
                    response
            );
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Employee id is not a number");
        }
    }
}
