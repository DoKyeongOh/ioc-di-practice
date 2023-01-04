package org.inspien;

import org.inspien.container.AnnotationBeanContainerTemplate;
import org.inspien.container.BeanContainerTemplate;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        BeanContainerTemplate container = new AnnotationBeanContainerTemplate();
        container.execute("org/inspien");
    }
}
