package org.inspien.app;

import org.inspien.annotation.Component;

@Component
public class MyFirstComponent {

    public MyFirstComponent() {
        System.out.println(this);
    }

    public void print(String message) {
        System.out.println(message);
    }
}
