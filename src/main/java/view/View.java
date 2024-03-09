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
        t.addCell(ANSI_BLUE + "Product Name" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_BLUE + "Unit Price" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_BLUE + "QTY" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_BLUE + "Imported Date" + ANSI_RESET, numberStyle);
        try {
            while (resultSet.next()) {
                t.addCell(ANSI_GREEN + resultSet.getInt("id") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_GREEN + resultSet.getString("name") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_GREEN + resultSet.getString("unit_price") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_GREEN + resultSet.getString("qty") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_GREEN + resultSet.getString("imported_date") + ANSI_RESET, numberStyle);
            }
            t.addCell(ANSI_BLUE + "Page "+current_page+" : "+totalPages + ANSI_RESET, numberStyle,2);
            t.addCell(ANSI_BLUE + "Total Record "+count+" " + ANSI_RESET, numberStyle,3);
            System.out.println(t.render());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public Product write(){
        System.out.print("Enter name : ");
        String name = scanner.nextLine();
        if(!Validate.validate_pro_name(name)){
            do{
                System.out.println(ANSI_RED+"Don't allow digit"+ANSI_RESET);
                System.out.print("Enter name : ");
                name = scanner.nextLine();
            }while (!Validate.validate_pro_name(name));
        }
        try {
            if(model.duplicate(name)){
                do {
                    System.out.println("Duplicate Product Name");
                    System.out.print("Enter name : ");
                    name = scanner.nextLine();
                    if(!Validate.validate_pro_name(name)){
                        do{
                            System.out.println(ANSI_RED+"Don't allow digit"+ANSI_RESET);
                            System.out.print("Enter name : ");
                            name = scanner.nextLine();
                        }while (!Validate.validate_pro_name(name));
                    }
                }while(model.duplicate(name));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.print("Enter Unit Price : ");
        String unit_price = scanner.nextLine();
        if(!Validate.validate_pro_unitePrice(unit_price)){
            do{
                System.out.println(ANSI_RED+"Don't allow to input character"+ANSI_RESET);
                System.out.print("Enter Unit Price : ");
                unit_price = scanner.nextLine();
            }while (!Validate.validate_pro_unitePrice(unit_price));
        }

        System.out.print("Enter Qty : ");
        String qty = scanner.nextLine();;
        if(!Validate.validate_pro_qty(qty)){
            do{
                System.out.println(ANSI_RED+"Don't allow to input character"+ANSI_RESET);
                System.out.print("Enter Qty : ");
                qty = scanner.nextLine();
            }while (!Validate.validate_pro_qty(qty));
        }

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
            t.addCell(ANSI_GREEN + "Unit Price : "+rs.getInt("unit_price")+" "+ ANSI_RESET);
            t.addCell(ANSI_GREEN + "QTY : "+rs.getString("qty")+" " + ANSI_RESET);
            t.addCell(ANSI_GREEN + "Imported Date : "+rs.getString("imported_date")+" " + ANSI_RESET);
            System.out.println(t.render());
        }catch (SQLException e){
            System.out.println(ANSI_RED+"Product Not Found !!!"+ANSI_RESET);
        }
    }
    public void display_unsave(ResultSet rs){
        Table t = new Table(6, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        t.setColumnWidth(0, 15, 30);
        t.setColumnWidth(1, 25, 30);
        t.setColumnWidth(2, 25, 30);
        t.setColumnWidth(3, 25, 30);
        t.setColumnWidth(4, 25, 30);
        t.setColumnWidth(5, 25, 30);
        t.addCell(ANSI_BLUE + "Unsave List" + ANSI_RESET, numberStyle,6);
        t.addCell(ANSI_GREEN + "ID" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_GREEN + "Product Name" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_GREEN + "Unit Price" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_GREEN + "QTY" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_GREEN + "Imported Date" + ANSI_RESET, numberStyle);
        t.addCell(ANSI_GREEN + "Status" + ANSI_RESET, numberStyle);
        try {
            while (rs.next()) {
                t.addCell(ANSI_GREEN + rs.getInt("id") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_GREEN + rs.getString("name") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_GREEN + rs.getString("unit_price") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_GREEN + rs.getString("qty") + ANSI_RESET, numberStyle);
                t.addCell(ANSI_GREEN + rs.getString("imported_date") + ANSI_RESET, numberStyle);
                int pro_id = rs.getInt("product_id");
                if( pro_id != 0){
                    t.addCell(ANSI_RED + "Update" + ANSI_RESET, numberStyle);
                }else{
                    t.addCell(ANSI_YELLOW + "Insert" + ANSI_RESET, numberStyle);
                }
            }
            System.out.println(t.render());

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public Product update(int id){
        System.out.print("Update product name to : ");
        String name = scanner.nextLine();
        if(!Validate.validate_pro_name(name)){
            do{
                System.out.println(ANSI_RED+"Don't allow digit"+ANSI_RESET);
                System.out.print("Update product name to : ");
                name = scanner.nextLine();
            }while (!Validate.validate_pro_name(name));
        }
        try {
            if(model.duplicate_update(name,id)){
                do {
                    System.out.println(ANSI_RED+"Duplicate Product Name"+ANSI_RESET);
                    System.out.print("Update product name to : ");
                    name = scanner.nextLine();
                    if(!Validate.validate_pro_name(name)){
                        do{
                            System.out.println(ANSI_RED+"Don't allow digit"+ANSI_RESET);
                            System.out.print("Update product name to : ");
                            name = scanner.nextLine();
                        }while (!Validate.validate_pro_name(name));
                    }
                }while(model.duplicate_update(name,id));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.print("Updat product unit price to : ");
        String unit_price = scanner.nextLine();
        if(!Validate.validate_pro_unitePrice(unit_price)){
            do{
                System.out.println(ANSI_RED+"Don't allow to input character"+ANSI_RESET);
                System.out.print("Updat product unit price to : ");
                unit_price = scanner.nextLine();
            }while (!Validate.validate_pro_unitePrice(unit_price));
        }

        System.out.print("Updat product qty to : ");
        String qty = scanner.nextLine();
        if(!Validate.validate_pro_qty(qty)){
            do{
                System.out.println(ANSI_RED+"Don't allow to input character"+ANSI_RESET);
                System.out.print("Updat product qty to : ");
                qty = scanner.nextLine();
            }while (!Validate.validate_pro_qty(qty));
        }

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
            t.addCell(ANSI_GREEN + "Unit Price"+ ANSI_RESET,numberStyle);
            t.addCell(ANSI_GREEN + "QTY" + ANSI_RESET,numberStyle);
            t.addCell(ANSI_GREEN + "Imported Date" + ANSI_RESET,numberStyle);
            while (rs.next()){
                t.addCell(ANSI_GREEN + rs.getInt("id")  + ANSI_RESET,numberStyle);
                t.addCell(ANSI_GREEN +rs.getString("name")+ ANSI_RESET,numberStyle);
                t.addCell(ANSI_GREEN +rs.getInt("unit_price")+ ANSI_RESET,numberStyle);
                t.addCell(ANSI_GREEN +rs.getString("qty") + ANSI_RESET,numberStyle);
                t.addCell(ANSI_GREEN +rs.getString("imported_date")+ ANSI_RESET,numberStyle);
            }
            System.out.println(t.render());
            System.out.print("Press enter for continues....");
            scanner.nextLine();
//            scanner.nextLine();
        }catch (SQLException e){
            System.out.println(ANSI_RED+"Product not found"+ANSI_RESET);
        }
    }
}

