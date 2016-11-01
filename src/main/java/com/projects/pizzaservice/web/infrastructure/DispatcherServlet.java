package com.projects.pizzaservice.web.infrastructure;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.Arrays;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @author Mariia Lapovska
 */
public class DispatcherServlet extends HttpServlet {

    private ConfigurableApplicationContext webContext;
    private ConfigurableApplicationContext[] applicationContexts;

    @Override
    public void init() throws ServletException {
        String[] contexts = getServletContext()
                .getInitParameter("contextConfigLocation").split(" ");
        int length = contexts.length;
        this.applicationContexts = new ConfigurableApplicationContext[length];

        for (int i = 0; i < length; i++) {
            ConfigurableApplicationContext context;

            if (i == 0) {
                context = new ClassPathXmlApplicationContext(contexts[i]);
            } else {
                context = new ClassPathXmlApplicationContext(
                        new String[]{contexts[i]},
                       applicationContexts[i]);
            }
        }

        String webContextConfigLocation = getInitParameter
                ("contextConfigLocation");
        webContext = new ClassPathXmlApplicationContext(webContextConfigLocation);
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        String controllerName = getControllerName(uri);

        MyController controller = webContext.getBean(controllerName,
                MyController.class);

        if (controller != null) {
            controller.handleRequest(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    private String getControllerName(String uri) {
        return uri.substring(uri.lastIndexOf('/'));
    }

    @Override
    public void destroy() {

        for (int i = applicationContexts.length - 1; i >= 0; i++) {
            applicationContexts[i].close();
        }

        webContext.close();
    }
}