package view;

import model.dao.ProductImplement;
import model.dto.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import util.Validate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class View {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_VIOLETS = "\u001b[36m";
    static Scanner scanner = new Scanner(System.in);
    static Validate validate = new Validate();
    static CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
    static ProductImplement model = new ProductImplement();
    public  void display(ResultSet resultSet, int limit,int count,int cur_page){
        int current_page = cur_page;
        int item_per_page = limit;
        int totalItems = model.count();
        int totalPages = (int) Math.ceil((double) totalItems / item_per_page);
        Table t = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        t.setColumnWidth(0, 15, 30);
        t.setColumnWidth(1, 25, 30);
        t.setColumnWidth(2, 25, 30);
        t.setColumnWidth(3, 25, 30);
        t.setColumnWidth(4, 25, 30);
        t.addCell(ANSI_BLUE + "Product List" + ANSI_RESET, numberStyle,5);
        t.addCell(ANSI_BLUE + "ID" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_GREEN + "Product Name" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_YELLOW + "Unit Price" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_RED + "QTY" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_RED + "Imported Date" + ANSI_RESET, numberStyle);
        try {
            while (resultSet.next()) {
                t.addCell(ANSI_BLUE + resultSet.getInt("id") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_GREEN + resultSet.getString("name") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_YELLOW + resultSet.getString("unit_price") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_RED + resultSet.getString("qty") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_RED + resultSet.getString("imported_date") + ANSI_RESET, numberStyle);
            }
            t.addCell(ANSI_RED + "Page "+current_page+" : "+totalPages + ANSI_RESET, numberStyle,2);
            t.addCell(ANSI_RED + "Total Record "+count+" " + ANSI_RESET, numberStyle,3);
            System.out.println(t.render());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public Product write(){
        System.out.print("Enter name : ");
        String name = scanner.nextLine();
        try {
            if(model.duplicate(name)){
                do {
                    System.out.println("Duplicate Product Name");
                    System.out.print("Enter name : ");
                    name = scanner.nextLine();
                }while(model.duplicate(name));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.print("Enter Unit Price : ");
        String unit_price = scanner.nextLine();
        System.out.print("Enter Qty : ");
        String qty = scanner.nextLine();
        Product product = new Product(name,Double.parseDouble(unit_price),Integer.parseInt(qty));
        return product;
    }
    public void read(ResultSet rs){
        try {
            rs.next();
            Table t = new Table(1, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            t.setColumnWidth(0, 40, 40);
            t.addCell(ANSI_BLUE + "Product" + ANSI_RESET,numberStyle);
            t.addCell(ANSI_GREEN + "ID : "+rs.getInt("id")+" " + ANSI_RESET);
            t.addCell(ANSI_GREEN + "Name : "+rs.getString("name")+" " + ANSI_RESET);
            t.addCell(ANSI_YELLOW + "Unit Price : "+rs.getInt("unit_price")+" "+ ANSI_RESET);
            t.addCell(ANSI_RED + "QTY : "+rs.getString("qty")+" " + ANSI_RESET);
            t.addCell(ANSI_RED + "Imported Date : "+rs.getString("imported_date")+" " + ANSI_RESET);
            System.out.println(t.render());
        }catch (SQLException e){
            System.out.println("Product Not Found !!!");
            return;
        }
    }
    public Product update(){
        System.out.print("Updat product name to : ");
        String name = scanner.nextLine();
        System.out.print("Updat product unit price to : ");
        String unit_price = scanner.nextLine();
        System.out.print("Updat product qty to : ");
        String qty = scanner.nextLine();
        Product product = new Product(name,Double.parseDouble(unit_price),Integer.parseInt(qty));
        return product;
    }
    public void search(ResultSet rs){
        try {
            Table t = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            t.setColumnWidth(0, 15, 30);
            t.setColumnWidth(1, 25, 30);
            t.setColumnWidth(2, 25, 30);
            t.setColumnWidth(3, 25, 30);
            t.setColumnWidth(4, 25, 30);
            t.addCell(ANSI_BLUE + "Product" + ANSI_RESET,numberStyle,5);
            t.addCell(ANSI_GREEN + "ID" + ANSI_RESET,numberStyle);
            t.addCell(ANSI_GREEN + "Name" + ANSI_RESET,numberStyle);
            t.addCell(ANSI_YELLOW + "Unit Price"+ ANSI_RESET,numberStyle);
            t.addCell(ANSI_RED + "QTY" + ANSI_RESET,numberStyle);
            t.addCell(ANSI_RED + "Imported Date" + ANSI_RESET,numberStyle);
            while (rs.next()){
                t.addCell(ANSI_GREEN + rs.getInt("id")  + ANSI_RESET,numberStyle);
                t.addCell(ANSI_GREEN +rs.getString("name")+ ANSI_RESET,numberStyle);
                t.addCell(ANSI_YELLOW +rs.getInt("unit_price")+ ANSI_RESET,numberStyle);
                t.addCell(ANSI_RED +rs.getString("qty") + ANSI_RESET,numberStyle);
                t.addCell(ANSI_RED +rs.getString("imported_date")+ ANSI_RESET,numberStyle);
            }
            System.out.println(t.render());
            System.out.print("Press enter for continues....");
            scanner.nextLine();
            scanner.nextLine();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void delete(ResultSet rs){
        try {
            rs.next();
            Table t = new Table(1, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            t.setColumnWidth(0, 40, 40);
            t.addCell(ANSI_BLUE + "Product" + ANSI_RESET,numberStyle);
            t.addCell(ANSI_GREEN + "ID : "+rs.getInt("id")+" " + ANSI_RESET);
            t.addCell(ANSI_GREEN + "Name : "+rs.getString("name")+" " + ANSI_RESET);
            t.addCell(ANSI_YELLOW + "Unit Price : "+rs.getInt("unit_price")+" "+ ANSI_RESET);
            t.addCell(ANSI_RED + "QTY : "+rs.getString("qty")+" " + ANSI_RESET);
            t.addCell(ANSI_RED + "Imported Date : "+rs.getString("imported_date")+" " + ANSI_RESET);
            System.out.println(t.render());
            System.out.print("Press enter for continues....");
            scanner.nextLine();
            scanner.nextLine();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

