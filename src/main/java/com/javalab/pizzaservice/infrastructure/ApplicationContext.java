package com.javalab.pizzaservice.infrastructure;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mariia Lapovska
 */
public class ApplicationContext implements Context {
    private final Config config;
    private final Map<String, Object> beans;

    public ApplicationContext(Config config) {
        this.config = config;
        this.beans = new HashMap<>();
    }

    @Override
    public <T> T getBean(String beanName) {
        try {
            if (beans.containsKey(beanName)) {
                return (T) beans.get(beanName);
            } else {
                T bean = getBeanInstance(beanName);
                beans.put(beanName, bean);
                return bean;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private <T> T getBeanInstance(String beanName) throws Exception {
        Class<?> type = config.getImpl(beanName);
        Constructor<?> constructor = getConstructor(type);

        if (hasParameters(constructor)) {
            Object[] constructorArgs = getConstructorArgs(constructor);
            return (T) constructor.newInstance(constructorArgs);
        } else {
            return (T) type.newInstance();
        }
    }

    private Object[] getConstructorArgs(Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object params[] = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            String paramBean = convertTypeToBeanName(parameterTypes[i]);
            params[i] = getBean(paramBean);
        }

        return params;
    }

    private Constructor<?> getConstructor(Class<?> type) {
        Constructor<?>[] constructors = type.getConstructors();

        if (constructors.length > 1) {
            throw new IllegalStateException("Class has multiple constructors!");
        }

        return constructors[0];
    }

    private boolean hasParameters(Constructor<?> constructor) {
        return constructor.getParameterCount() > 0;
    }

    private String convertTypeToBeanName(Class<?> type) {
        String typeName = type.getSimpleName();
        return typeName.substring(0, 1).toLowerCase() + typeName.substring(1);
    }
}