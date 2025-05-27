package servlet;

import data.response.ErrorType;
import exception.DegreeNotFoundException;
import exception.DepartmentNotFoundException;
import exception.EmployeeNotFoundException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.HttpResponseUtil;

import java.io.IOException;

@WebFilter("/*")
public class ExceptionHandlingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e){
            HttpResponseUtil.sendError(
                    response,
                    HttpServletResponse.SC_BAD_REQUEST,
                    e.getMessage(),
                    ErrorType.INVALID_INPUT
            );
        } catch (EmployeeNotFoundException | DepartmentNotFoundException | DegreeNotFoundException e){
            HttpResponseUtil.sendError(
                    response,
                    HttpServletResponse.SC_NOT_FOUND,
                    e.getMessage(),
                    ErrorType.NOT_FOUND
            );
        } catch (Exception e) {
            HttpResponseUtil.sendError(
                    response,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    ErrorType.SERVER_ERROR
            );
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
