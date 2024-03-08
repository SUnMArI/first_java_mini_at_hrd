package controller;

import model.dao.ProductImplement;
import model.dao.UnSaveImplement;
import model.dto.Product;
import view.View;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

public class Control {
    private static Scanner scanner= new Scanner(System.in);
    private static ProductImplement pro = new ProductImplement();
    static View view = new View() ;
    static ProductImplement model = new ProductImplement();
    static UnSaveImplement unsave_model = new UnSaveImplement();
    public static void writeDate(){
        Product insert = view.write();
        unsave_model.insert(insert);
        System.out.println("Create successfully please check in Unsave!!!");
    }
    public static void read(){
        System.out.print("Enter ID to show product : ");
        String id = scanner.next();
        ResultSet rs_read = model.view(id);
        view.read(rs_read);
    }
    public static void updateData(){
        System.out.print("Enter ID to update product : ");
        String id = scanner.next();
        Product rs_update = view.update();
        model.update(Integer.parseInt(id),rs_update);
        System.out.println("Update successfully please check in Unsave!!!");
    }
    public static void deleteDate(){
        System.out.print("Enter ID to delete product : ");
        String id = scanner.next();
        ResultSet rs_delete = model.view(id);
        view.read(rs_delete);
        System.out.print("Enter Y for confirm or B for back to display :");
        String ans = scanner.nextLine();
        scanner.nextLine();
        switch (ans){
            case "y" ->{
                model.delete(id);
            }
            case "b" ->{
                break;
            }
        }
    }
    public static void search(){
        System.out.print("Enter product name to search : ");
        String search = scanner.next();
        ResultSet rs_search = model.search(search);
        view.search(rs_search);
    }
    public static void save(){
        UnSaveImplement unsave = new UnSaveImplement();
        System.out.println("Do you want to save unsave insertion or unsave update?Please choose one of them!");
        System.out.print("'Ui' for save unsave insertion , 'Uu' for save unsave update or 'B' for back main menu :");
        String ans = scanner.nextLine();
        scanner.nextLine();
        switch (ans.toUpperCase()){
            case "UI" ->{
                unsave.transferProduct();
            }
            case "UU" ->{
                unsave.save_unsave_update();
            }
        }
    }
    public static void unsave(){
        ResultSet rs_read = unsave_model.selectAll();
        view.display_unsave(rs_read);
    }
    public static void backUP(){}
    public static void restore(){}
    public static void help(){}

}
