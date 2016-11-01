package com.projects.pizzaservice.web;

import com.projects.pizzaservice.services.PizzaService;
import com.projects.pizzaservice.web.infrastructure.MyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Mariia Lapovska
 */
@Controller("/hello")
public class HelloController implements MyController {

    @Autowired
    private PizzaService pizzaService;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("Hello from HelloServlet!");
            out.println(pizzaService.findPizzaById(1));
        }
    }
}