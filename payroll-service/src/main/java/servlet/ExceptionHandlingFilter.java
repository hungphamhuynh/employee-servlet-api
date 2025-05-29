package servlet;

import data.response.ErrorType;
import exception.EmployeeNotFoundException;
import exception.PayrollExistsException;
import exception.PayrollNotFoundException;
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
        } catch (EmployeeNotFoundException | PayrollNotFoundException e){
            HttpResponseUtil.sendError(
                    response,
                    HttpServletResponse.SC_NOT_FOUND,
                    e.getMessage(),
                    ErrorType.NOT_FOUND
            );
        } catch (PayrollExistsException e){
            HttpResponseUtil.sendError(
                    response,
                    HttpServletResponse.SC_CONFLICT,
                    e.getMessage(),
                    ErrorType.SC_CONFLICT
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
