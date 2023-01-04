package org.inspien.app;

import org.inspien.annotation.Component;

@Component
public class MyFirstComponent {

    public MyFirstComponent() {
        System.out.println(this);
    }

    public void print() {
        System.out.println("This is my first component.");
    }
}
