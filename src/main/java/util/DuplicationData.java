package util;

import java.io.IOException;

public class DuplicationData {
    public static boolean backup(){
        String cmd = "pg_dump -U postgres -h Localhost -p 5432 stock_management > backup.sql";
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd","/c ",cmd);
        try {
            Process process = processBuilder.start();
            int p =process.waitFor();
            if(p!=0){
                System.out.println("Backup successfully");
                return true;
            }
        }catch (IOException | InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public static boolean recovery(){
        String cmd = "psql -U postgres -h Localhost -p 5432 stock_management < backup.sql";
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd","/c", cmd);
        try {
            Process process = processBuilder.start();
            int p =process.waitFor();
            if(p!=0){
                System.out.println("Recovery successfully");
                return true;
            }
        }catch (IOException | InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
