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

    public ApplicationContext(Config config) {
        this.config = config;
        this.beans = new HashMap<>();
    }

    @Override
    public <T> T getBean(String beanName) throws Exception {
        Object bean = beans.get(beanName);

        if (bean == null) {
            BeanBuilder builder = new BeanBuilder(beanName);

            builder.createBean();
            builder.callPostCreateMethod();
            builder.callInitMethod();
            builder.createBeanProxy();

            bean = builder.build();
            beans.put(beanName, bean);
        }

        return (T) bean;
    }

    private class BeanBuilder<T> {
        private Class<T> beanType;
        private T bean;

        BeanBuilder(String beanName) {
            this.beanName = beanName;
        }

        void createBean() {
            try {
                bean = getBeanInstance(beanName);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        T build() {
            return bean;
        }

        void callInitMethod() throws Exception {
            try {
                Method initMethod = type.getMethod("init");
                if (!initMethod.isAnnotationPresent(PostCreate.class)) {
                    initMethod.invoke(bean);
                }
            } catch (NoSuchMethodException e)
            {
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        void callPostCreateMethod() {
            Method[] methods = type.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(PostCreate.class)) {
                    try {
                        method.invoke(instance);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        void createBeanProxy() {
            if (isAnyMethodAnnotated(Benchmark.class)) {
                ClassLoader classLoader = this.getClass().getClassLoader();
                Class<?>[] interfaces = type.getInterfaces();
                instance = (T) Proxy.newProxyInstance(classLoader, interfaces, this::processBenchmark);
            }
        }

        private Object processBenchmark(Object proxy,
                                        Method method,
                                        Object[] args) throws Throwable {

            Benchmark annotation = type.getMethod(method.getName(),
                    method.getParameterTypes()).getAnnotation(Benchmark.class);
            if (annotation.value()) {
                long start = System.nanoTime();
                Object result = method.invoke(original, args);
                long end = System.nanoTime();
                System.out.println(method.getName() + " took " + (end - start) + " nano to execute.");
                return result;
            }
            return method.invoke(original, args);
        }

        private <T> T getBeanInstance(String beanName) throws Exception {
            Class<?> type = config.getImplementation(beanName);
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
    }
}