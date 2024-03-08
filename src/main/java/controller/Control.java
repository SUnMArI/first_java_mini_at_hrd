package controller;

import model.dao.ProductImplement;
import model.dto.Product;

import java.time.LocalDate;
import java.util.Scanner;

public class Control {
    private static Scanner scanner= new Scanner(System.in);
    private static ProductImplement pro = new ProductImplement();
    public static void writeDate(){
        String name;
        String unitPrice;
        String qty;
        System.out.print("Enter Product name: ");
        name = scanner.nextLine();
        System.out.print("Enter Unit Price: ");
        unitPrice = scanner.nextLine();
        System.out.print("Enter Qty: ");
        qty = scanner.nextLine();
    }
    public static void updateData(){

    }
    public static void deleteDate(){
        String id;
        System.out.print("Select ID:");
        id = scanner.nextLine();
    }
    public static void firstPage(){}
    public static void previous(){}
    public static void next(){}
    public static void lastPage(){}
    public static void search(){

    }
    public static void Goto(){}
    public static void setRow(){}
    public static void save(){}
    public static void backUP(){}
    public static void restore(){}
    public static void help(){}

}
