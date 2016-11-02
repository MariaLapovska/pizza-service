package com.projects.pizzaservice.web.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mariia Lapovska
 */
public class SimpleURLHandlerMapping implements HandlerMapping,
        ApplicationContextAware {

    private ApplicationContext webContext;

    @Override
    public MyController getController(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String controllerName = getControllerName(uri);

        return webContext.getBean(controllerName, MyController.class);
    }

    private String getControllerName(String uri) {
        return uri.substring(uri.lastIndexOf('/'));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.webContext = applicationContext;
    }
}
