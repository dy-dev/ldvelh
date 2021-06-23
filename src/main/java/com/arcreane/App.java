package com.arcreane;

import com.arcreane.ldvelh.controller.ConsoleController;
import com.arcreane.ldvelh.controller.IController;
import com.arcreane.ldvelh.controller.OCRController;
import com.arcreane.ldvelh.repository.DBRepository;
import com.arcreane.ldvelh.repository.IRepository;
import com.arcreane.ldvelh.repository.JSonRepository;
import com.arcreane.ldvelh.service.EditorService;
import com.arcreane.ldvelh.service.IService;
import com.arcreane.ldvelh.service.OCREditorService;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Select your option : ");
        int i = 0;
        System.out.println((++i) + " =>  Console");
        System.out.println((++i) + " =>  Scanner");

        //System.out.println("1: console / 2: scanner");
        Scanner scan = new Scanner(System.in);
        int choice = Integer.parseInt(scan.nextLine());
        IController myController = null;
        if (choice == 1) {
            myController = new ConsoleController();
        } else if (choice == 2) {
            myController = new OCRController();
        } else {
            System.out.println("Bye bye");
            return;
        }


        System.out.println("quel service souhaitez vous utiliser?");
        i = 0;
        System.out.println((++i) + " =>  Edition");
        System.out.println((++i) + " =>  OCR");
        choice = Integer.parseInt(scan.nextLine());
        IService myService = null;
        if (choice == 1) {
            myService = new EditorService();
        } else if (choice == 2) {
            myService = new OCREditorService();
        } else {
            System.out.println("Bye bye");
            return;
        }
        if (myService != null && myController != null) {
            myController.setService(myService);
        }


        System.out.println("quel repository souhaitez vous utiliser?");
        i = 0;
        System.out.println((++i) + " =>  JSonRepository");
        System.out.println((++i) + " =>  DBRepository");
        choice = Integer.parseInt(scan.nextLine());
        IRepository myRepository = null;
        if (choice == 1) {
            myRepository = new JSonRepository("library");
        } else if (choice == 2) {
            myRepository = new DBRepository();
        } else {
            System.out.println("Bye bye");
            return;
        }
        if (myRepository != null && myController != null && myService != null) {
            myService.setRepository(myRepository);
            myController.startApp();
        }

    }
}
