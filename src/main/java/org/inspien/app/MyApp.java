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
        firstComponent.print();
        secondComponent.print();
    }


}
