package org.inspien.app;

import org.inspien.annotation.Autowired;
import org.inspien.annotation.Component;
import org.inspien.annotation.Run;

@Component
public class MyApp {

    @Autowired
    private MyFirstComponent firstComponent;

    @Autowired
    private MySecondComponent secondComponent;

    @Run
    public void run() {
        System.out.println("first - " + firstComponent);
        firstComponent.print();
        System.out.println();
        System.out.println("second - " + secondComponent);
        secondComponent.print();
    }


}
