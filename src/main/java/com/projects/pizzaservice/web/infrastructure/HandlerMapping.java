package com.projects.pizzaservice.web.infrastructure;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mariia Lapovska
 */
public interface HandlerMapping {
    MyController getController(HttpServletRequest request);
}