package com.projects.pizzaservice.web.infrastructure;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mariia Lapovska
 */
public interface MyController {
    void handleRequest(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException;
}
