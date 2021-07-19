package com.arcreane;

import com.arcreane.ldvelh.controller.IController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Application Spring boot
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        IController controller = context.getBean(IController.class);
        controller.startApp();
    }
}
