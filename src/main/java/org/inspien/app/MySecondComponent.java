package org.inspien.app;

import org.inspien.annotation.Component;

@Component
public class MySecondComponent {
    public MySecondComponent() {
        System.out.println(this);
    }

    public void print() {
        System.out.println("This is my second component.");
    };
}
