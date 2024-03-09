import controller.Control;
import model.dao.ProductImplement;
import model.dto.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import util.Validate;
import view.View;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_VIOLETS = "\u001b[36m";
    static CellStyle center = new CellStyle(CellStyle.HorizontalAlign.CENTER);
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ProductImplement model = new ProductImplement();
        int start = 0;
        int limite;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\HRD\\Java_HRD\\Mini\\mini_project\\src\\main\\java\\util\\setRow.txt"));
            int data = Integer.parseInt(reader.readLine());
            limite = data;
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int cur_page = 1;
        int totalItems = model.count();
        back : do {
            int totalPages = (int) Math.ceil((double) totalItems / limite);
            View view = new View();
            int count = model.count();
            ResultSet rs = model.selectAll(limite,start);
            view.display(rs,limite,count,cur_page);
            System.out.println("F) First\t\t P) Previous\t\t N) Next\t\t L) Last\t\t G) Go to");
            System.out.println();
            System.out.println("================================================== Display ==================================================");
            System.out.println("W) Write\t\t R) Read\t\t U) update\t\t D) Delete\t\t S) Search\t\t Se) Set Row");
            System.out.println("Sa) Save\t\t Un) UnSave\t\t Ba) BackUp\t\t Re) Restore\t\t E)exit");
            System.out.println("=============================================================================================================");
            System.out.print("Choose Option : ");
            String option = scanner.next();
            switch (option.toUpperCase()){
                case "F" ->{
                    cur_page=1;
                    start = 0;
                    continue back;
                }
                case "P" ->{
                    if(cur_page > 1){
                        cur_page--;
                    }
                    if(start != 0){
                        start = start-limite;
                        continue back;
                    }else{
                        start = 0;
                        continue back;
                    }
                }
                case "N" ->{
                    if(cur_page != totalPages){
                        cur_page++;
                    }
                    if(start < totalItems-limite){
                        start = start+limite;
                        continue back;
                    }else{
                        start = (totalPages - 1) * limite;
                        continue back;
                    }
                }
                case "L" ->{
                    cur_page = totalPages;
                    if(limite==1){
                        start = totalItems-1;
                        System.out.println(start);
                    }else
                    if(totalItems%limite ==0){
                        start = totalItems-(totalItems/limite);
                        System.out.println("S"+start);
                    }else {
                        start = totalItems-(totalItems%limite);
                    }

                }
                case "G" ->{
                    String page_re;
                    int i=0;
                    do {
                        System.out.print("Enter page :");
                        page_re = scanner.next();
                        scanner.nextLine();
                        if(i==1){
                            System.out.println(ANSI_RED+"Allow only Number!!!"+ANSI_RESET);
                        }
                        i++;
                    }while (!Validate.validate_only_number(page_re));
                    if(Integer.parseInt(page_re) <= totalPages){
                        cur_page = Integer.parseInt(page_re);
                        start = (Integer.parseInt(page_re) - 1) * limite;
                    }else{
                        System.out.println("Out off limite page");
                    }
                }
                case "W" ->{
                    Control.writeDate();
                }
                case "R" ->{
                    Control.read();
                    System.out.print("Press enter for continues....");
                    scanner.nextLine();
                    scanner.nextLine();
                    break ;
                }
                case "U" ->{
                    Control.updateData();
                }
                case "D" ->{
                    Control.deleteDate();
                }
                case "S" ->{
                    Control.search();
                }
                case "SE" ->{
                    String num_row;
                    int i=0;
                    do{
                        System.out.print("Enter number of Row : ");
                        num_row = scanner.next();
                        scanner.nextLine();
                        if(i==1){
                            System.out.println(ANSI_RED+"Allow only number except 0!!!"+ANSI_RESET);
                        }
                        i++;
                    }while (!Validate.validate_set_row(num_row));
                    try{
                        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\HRD\\Java_HRD\\Mini\\mini_project\\src\\main\\java\\util\\setRow.txt"));
                        writer.write(num_row);
                        writer.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("D:\\HRD\\Java_HRD\\Mini\\mini_project\\src\\main\\java\\util\\setRow.txt"));
                        int data = Integer.parseInt(reader.readLine());
                        System.out.println(data);
                        limite = data;
                        cur_page =1;
                        start=0;
                        reader.close();
                        continue back;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "SA" ->{
                    Control.save();
                }
                case "UN" ->{
                    Control.unsave();
                    System.out.print("Press enter for continues....");
                    scanner.nextLine();
                    scanner.nextLine();
                }
                case "BA" ->{
                    break;
                }
                case "RE" ->{
                    break;
                }
                case "E" ->{
                    Table tl = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
                    tl.setColumnWidth(0,51,51);
                    String exit = ANSI_GREEN+"================= "+ANSI_RESET+ANSI_VIOLETS+" Good Bye!"+ANSI_RESET+ANSI_GREEN+" =================";
                    tl.addCell(exit, center);
                    System.out.println(tl.render());
                    return;
                }
                default -> {
                    System.out.println(ANSI_YELLOW+"Input not match!!!"+ANSI_RESET);
                    System.out.print("Press enter for continues....");
                    scanner.nextLine();
                    scanner.nextLine();
                }
            }
        }while (true);
    }
}
