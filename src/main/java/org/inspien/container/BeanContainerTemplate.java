package org.inspien.container;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public abstract class BeanContainerTemplate {

    protected List<Method> toDoMethods;
    protected Map<String, Class> nameClassMap;
    protected Map<String, Object> beanContainer;

    protected abstract void init(String...basePackages);
    protected abstract void createInstances();
    protected abstract void injectBean();
    protected abstract void executeMethods();

    public void execute(String...basePackages) {
        init(basePackages);
        createInstances();
        injectBean();
        executeMethods();
    };

}
