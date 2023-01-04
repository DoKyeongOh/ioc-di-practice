package org.inspien.container;

import org.inspien.annotation.Autowired;
import org.inspien.annotation.Component;
import org.inspien.annotation.Run;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationBeanContainerTemplate extends BeanContainerTemplate {
    @Override
    protected void init(String... basePackages) {
        for (String packageName : basePackages) {
            Reflections reflections = new Reflections(
                    packageName,
                    new SubTypesScanner(false)
            );

            if (nameClassMap == null) {
                nameClassMap = new HashMap<>();
            }

            Set<Class<?>> classSet = reflections.getSubTypesOf(Object.class);
            nameClassMap.putAll(getClassMapWithComponent(classSet));
        }
    }

    @Override
    protected void createInstances() {
        if (beanContainer == null) {
            beanContainer = new HashMap<>();
        }
        for (Class aClass : nameClassMap.values()) {
            try {
                beanContainer.put(aClass.getName(), aClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void injectBean() {
        for (Object bean : beanContainer.values()) {
            for (Field field : bean.getClass().getDeclaredFields()) {
                if (field.getAnnotation(Autowired.class) == null) {
                    continue;
                }
                String fieldName = field.getType().getName();
                if (!beanContainer.containsKey(fieldName)) {
                    throw new RuntimeException("Injection fail, object "
                            + field + " in " + bean.getClass() + " is not in bean container!");
                }
                try {
                    field.setAccessible(true);
                    field.set(bean, beanContainer.get(fieldName));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void executeMethods() {
        for (Object bean : beanContainer.values()) {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                if (!method.isAnnotationPresent(Run.class)) {
                    continue;
                }
                try {
                    method.invoke(bean);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Map<String, Class> getClassMapWithComponent(Set<Class<?>> classSet) {
        Map<String, Class> classMap = new HashMap<>();
        for (Class aClass : classSet) {
            for (Annotation annotation : aClass.getAnnotations()) {
                if (annotation.annotationType().equals(Component.class)) {
                    classMap.put(aClass.getName(), aClass);
                    break;
                }
            }
        }
        return classMap;
    }
}
