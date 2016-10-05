package com.javalab.pizzaservice.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mariia Lapovska
 */
public class ApplicationContext implements Context {
    private final Config config;
    private final Map<String, Object> beans;

    private class BeanBuilder {
        private String beanName;
        private Object bean;

        BeanBuilder(String beanName) {
            this.beanName = beanName;
        }

        private void createBean() {
            try {
                if (beans.containsKey(beanName)) {
                    bean = beans.get(beanName);
                } else {
                    bean = getBeanInstance(beanName);
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

        private Object[] getConstructorArgs(Constructor<?> constructor) throws Exception  {
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

        private <T> T build() {
            return (T) bean;
        }

        public void callInitMethod() throws Exception {
            Class<?> clazz = bean.getClass();
            Method method;

            try {
                method = clazz.getMethod("init");
            } catch (NoSuchMethodException ex) {
                return;
            }

            method.invoke(bean);
        }

        private void callPostCreateMethod() {
            Class<?> type = config.getImpl(beanName);

            for (Method method : type.getMethods()) {
                if (method.isAnnotationPresent(PostCreate.class)) {
                    try {
                        method.invoke(bean);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        public void createBeanProxy() {
            // implement
            // Proxy.newProxyInstance
        }
    }

    public ApplicationContext(Config config) {
        this.config = config;
        this.beans = new HashMap<>();
    }

    @Override
    public <T> T getBean(String beanName) throws Exception  {
        Object bean = beans.get(beanName);

        if (bean != null) {
            return (T) bean;
        }

        BeanBuilder builder = new BeanBuilder(beanName);

        builder.createBean();
        builder.callPostCreateMethod();
        builder.callInitMethod();
        builder.createBeanProxy();

        bean = builder.build();
        beans.put(beanName, bean);

        return (T) bean;
    }


}