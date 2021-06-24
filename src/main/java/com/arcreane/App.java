package com.arcreane;

import com.arcreane.ldvelh.controller.IController;
import com.arcreane.ldvelh.repository.IRepository;
import com.arcreane.ldvelh.service.IService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        System.out.println("Select your classes for  : ");
//        int i = 0;
//        IController controller = null;
//        IService service = null;
//        IRepository repository = null;
//        Scanner scan = new Scanner(System.in);
//        try {
//            String className = null;
//
//            //Retrieve controller class name and use reflexion to instatiate the corresponding class
//            System.out.println("Constroller");
//            className = scan.nextLine();
//            controller = (IController) Class.forName(className).getDeclaredConstructor().newInstance();
//
//            //Retrieve service class name and use reflexion to instatiate the corresponding class
//            System.out.println("Service");
//            className = scan.nextLine();
//            service = (IService) Class.forName(className).getDeclaredConstructor().newInstance();
//            controller.setService(service);
//            System.out.println("Repository");
//            className = scan.nextLine();
//            repository = (IRepository) Class.forName(className).getDeclaredConstructor().newInstance();
//            service.setRepository(repository);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IController controller = context.getBean(IController.class);
        controller.startApp();
    }
}
