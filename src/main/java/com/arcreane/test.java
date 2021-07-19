package com.arcreane;


import com.arcreane.ldvelh.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("What's the file location");
        String name = scan.nextLine();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.readValue(new File(name), Book.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
