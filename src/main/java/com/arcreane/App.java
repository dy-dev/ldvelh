package com.arcreane;

import com.arcreane.ldvelh.controller.ConsoleController;
import com.arcreane.ldvelh.controller.OCRController;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Do you want to write book (type 1) or scan one (type 2)");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        if(choice == 1) {
            ConsoleController controller = new ConsoleController();
            controller.startApp();
        }
        else if(choice == 2)
        {
            OCRController ocr = new OCRController();
            ocr.startScan();
        }
    }
}
