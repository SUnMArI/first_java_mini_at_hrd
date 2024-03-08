package util;

import java.io.IOException;

public class DuplicationData {
    public static void main(String[] args) {
        DuplicationData.backup();
    }
    public static boolean backup(){
        String username = GetProperties.read().getProperty("user");
        String[] cmd = new String[]{"pg_dump", "-U",username, "-d", "stock_management","-f","/home/sunmario/Documents/HRD/Mini_Project/java_mini_project/java_mini_project/src/main/java/util/backup.sql"};
        ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        try {
            Process process = processBuilder.start();
            if(process.waitFor()!=0){
                System.out.println("Backup successfully");
            }
        }catch (IOException | InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        return true;
    }
    public static boolean recovery(){
        return true;
    }
}
