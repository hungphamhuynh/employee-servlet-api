package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.response.ErrorResponse;
import data.response.ErrorType;
import data.response.SuccessResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpResponse;

public final class HttpResponseUtil {
    private HttpResponseUtil() {}

    public static <T> void sendSuccess(HttpServletResponse resp, int status, String message, T data) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(status);
        SuccessResponse<T> response = new SuccessResponse<>(message, data);
        resp.getWriter().write(JsonUtil.toJson(response));
    }

    public static <T> void sendError(HttpServletResponse resp, int status, String message, String type) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(status);
        ErrorResponse response = new ErrorResponse(message, type);
        resp.getWriter().write(JsonUtil.toJson(response));
    }
}
