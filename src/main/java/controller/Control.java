package controller;

import model.dao.ProductImplement;
import model.dao.UnSaveImplement;
import model.dto.Product;
import util.Validate;
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
        if(!model.check_id(Integer.parseInt(id))){
            do {
                System.out.println("ID not fount");
                System.out.print("Enter ID to show product : ");
                id = scanner.next();
            }while (!model.check_id(Integer.parseInt(id)));
        }
        ResultSet rs_read = model.view(id);
        view.read(rs_read);
    }
    public static void updateData(){
        System.out.print("Enter ID to update product : ");
        String id = scanner.next();
        if (!model.check_id(Integer.parseInt(id))){
            do{
                System.out.println("ID not found!!!");
                System.out.print("Enter ID to update product : ");
                id = scanner.next();
            }while (!model.check_id(Integer.parseInt(id)));
        }
        Product rs_update = view.update(Integer.parseInt(id));
        model.update(Integer.parseInt(id),rs_update);
        System.out.println("Update successfully please check in Unsave!!!");
    }
    public static void deleteDate(){
        System.out.print("Enter ID to delete product : ");
        String id = scanner.next();
        if(!model.check_id(Integer.parseInt(id))){
            do {
                System.out.println("ID not found!!!");
                System.out.print("Enter ID to delete product : ");
                id = scanner.next();
            }while (!model.check_id(Integer.parseInt(id)));
        }
        ResultSet rs_delete = model.view(id);
        view.read(rs_delete);
        String ans;
        int i=0;
        do {
            System.out.print("Enter Y for confirm or B for back to display :");
            ans = scanner.next();
            scanner.nextLine();
            if (i == 1) {
                System.out.println("Allow only y or n");
            }
            i++;
        }while (!Validate.validate_answer(ans));
        switch (ans){
            case "y" ->{
                model.delete(id);
                System.out.println("Product Delete Successfully!!!");
            }
            case "b" ->{
                break;
            }
        }
    }
    public static void search(){
        String search;
        int i=0;
        do {
            System.out.print("Enter product name to search : ");
            search = scanner.next();
            if(i==1){
                System.out.println("Allow only String !!!");
            }
        }while (!Validate.validate_string(search));

        ResultSet rs_search = model.search(search);
        view.search(rs_search);
    }
    public static void save(){
        do {
            UnSaveImplement unsave = new UnSaveImplement();
            System.out.println("Do you want to save unsave insertion or unsave update?Please choose one of them!");
            System.out.print("'Ui' for save unsave insertion , 'Uu' for save unsave update or 'B' for back main menu :");
            String ans = scanner.next();
            scanner.nextLine();
            if(!ans.toLowerCase().equals("ui") && !ans.toLowerCase().equals("uu") && !ans.toLowerCase().equals("b")){
                System.out.println("Allow only 'ui' or 'uu' and 'b' ");
                continue;
            }
            switch (ans.toUpperCase()){
                case "UI" ->{
                    unsave.transferProduct();
                    System.out.println("** Product Save Successfully **");
                }
                case "UU" ->{
                    unsave.save_unsave_update();
                    System.out.println("** Product Update Save Successfully **");
                }
                case "B" ->{
                    return;
                }
            }
        }while (true);
    }
    public static void unsave(){
        ResultSet rs_read = unsave_model.selectAll();
        view.display_unsave(rs_read);
    }
    public static void backUP(){}
    public static void restore(){}
    public static void help(){}
}
