package org.inspien.container;

import junit.framework.TestCase;
import org.inspien.annotation.Run;

public class AnnotationBeanContainerTemplateTest extends TestCase {
    private BeanContainerTemplate beanContainerTemplate = new AnnotationBeanContainerTemplate();

    public void testInit() {
        beanContainerTemplate.init("org/inspien");
    }

    public void testCreateInstances() {
        beanContainerTemplate.init("org/inspien");
        beanContainerTemplate.createInstances();
    }

    public void testInjectBean() {
        beanContainerTemplate.init("org/inspien");
        beanContainerTemplate.createInstances();
        beanContainerTemplate.injectBean();
    }

    public void testExecuteMethods() {
        beanContainerTemplate.init("org/inspien");
        beanContainerTemplate.createInstances();
        beanContainerTemplate.injectBean();
        beanContainerTemplate.executeMethods();
    }

    public void testExecute() {
        beanContainerTemplate.execute("org/inspien");
        beanContainerTemplate.beanContainer.values().forEach(System.out::println);
        beanContainerTemplate.nameClassMap.values().forEach(System.out::println);
    }
}