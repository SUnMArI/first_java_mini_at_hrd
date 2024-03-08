import model.dao.ProductImplement;
import model.dto.Product;
import util.Validate;
import view.View;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
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
        int totalPages = (int) Math.ceil((double) totalItems / limite);
        back : do {
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
            switch (option){
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
                    if(start <totalItems-limite){
                        start = start+limite;
                        continue back;
                    }else{
                        start = (totalPages - 1) * limite;
                        continue back;
                    }
                }
                case "L" ->{
                    cur_page = totalPages;
                    int last = totalItems%limite;
                    if(limite == 2){
                        start = totalItems-(totalItems%limite) - 2 ;
                        continue back;
                    }else{
                        start = totalItems-(totalItems%limite);
                        continue back;
                    }
                }
                case "G" ->{
                    System.out.print("Enter page :");
                    int page_re = scanner.nextInt();
                    scanner.nextLine();
                    if(page_re <= totalPages){
                        cur_page = page_re;
                        start = (page_re - 1) * limite;
                        continue back;
                    }else{
                        System.out.println("Out off limite page");
                        break;
                    }
                }
                case "W" ->{
                    Product insert = view.write();
                    model.insert(insert);
                    System.out.println("Product created successfully");
                    break;
                }
                case "R" ->{
                    System.out.print("Enter ID to show product : ");
                    String id = scanner.next();
                    ResultSet rs_read = model.view(id);
                    view.read(rs_read);
                    System.out.print("Press enter for continues....");
                    scanner.nextLine();
                    scanner.nextLine();
                    break ;
                }
                case "U" ->{
                    System.out.print("Enter ID to update product : ");
                    String id = scanner.next();
                    Product rs_update = view.update();
                    model.update(Integer.parseInt(id),rs_update);
                    System.out.println("Product update successfully !!!");
                    break;
                }
                case "D" ->{
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
                            continue back;
                        }
                    }
                    break;
                }
                case "S" ->{
                    System.out.print("Enter product name to search : ");
                    String search = scanner.next();
                    ResultSet rs_search = model.search(search);
                    view.search(rs_search);
                    break;
                }
                case "Se" ->{
                    System.out.print("Enter number of Row :");
                    String num_row = scanner.next();
                    scanner.nextLine();
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
                        limite = data;
                        reader.close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    continue back;
                }
                case "Sa" ->{
                    break;
                }
                case "Un" ->{
                    break;
                }
                case "Ba" ->{
                    break;
                }
                case "Re" ->{
                    break;
                }
                case "E" ->{
                    break;
                }
            }
        }while (true);
    }
}
