package model.dao;

import connectDB.PostgresConnection;
import model.dto.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductImplement implements ProductService {
    @Override
    public boolean insert(Product products)  {
        String insert= "INSERT INTO unsave_tb(Name,Unit_Price,QTY)\n" +
                "VALUES ('" +products.getName()+
                "'," +products.getUnitPrice()+
                "," +products.getQty()+
                ")";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            st.execute(insert);
            return true;
        }catch (SQLException ex){
            return false;
        }
    }
    @Override
    public boolean update(int id,Product products) {
        String update= "INSERT INTO unsave_tb(Name,Unit_Price,QTY,product_id)\n" +
                "VALUES ('" +products.getName()+
                "'," +products.getUnitPrice()+
                "," +products.getQty()+
                "," + id+
                ")";;
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            st.execute(update);
            return true;
        }catch (SQLException ex){
            return false;
        }
    }
    @Override
    public boolean delete(String del_id) {
        String delete= "DELETE FROM product_tb WHERE id = "+del_id+" ";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            st.executeQuery(delete);
            return true;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    @Override
    public ResultSet selectAll(int limite,int ind){
        String select= "SELECT * FROM product_tb ORDER BY id ASC LIMIT "+limite+" offset "+ind+" ";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(select);
            return rs;
        }catch (SQLException ex){
            return null;
        }
    }
    @Override
    public ResultSet search(String txt) {
        String search= "SELECT * FROM product_tb WHERE name ILIKE '%"+txt+"%' ORDER BY id ASC";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(search);
            return rs;
        }catch (SQLException ex){
            return null;
        }
    }
    @Override
    public ResultSet view(String views) {
        String view = "SELECT * FROM product_tb WHERE id = " + views + " ";
        try (Connection cn = PostgresConnection.connection()) {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(view);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    @Override
    public int count(){
        String count = "SELECT COUNT(*) FROM product_tb";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            ResultSet count_data = st.executeQuery(count);
            if (count_data.next()) {
                int rowCount = count_data.getInt(1);
                return rowCount;
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    @Override
    public boolean duplicate(String txt){
        String duplicate= "SELECT name FROM product_tb WHERE LOWER(name) = LOWER('"+txt+"')";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            ResultSet dupli_data = st.executeQuery(duplicate);
            boolean rs = dupli_data.next();
            if (rs){
                return true;
            }else {
                return false;
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public boolean duplicate_update(String txt,int id){
        String duplicate= "SELECT name FROM product_tb WHERE LOWER(name) = LOWER('"+txt+"') AND id != "+id+" ";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            ResultSet dupli_data = st.executeQuery(duplicate);
            boolean rs = dupli_data.next();
            if (rs){
                return true;
            }else {
                return false;
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean check_id(String id){
        String duplicate= "SELECT * FROM product_tb WHERE id = "+id+" ";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            ResultSet dupli_data = st.executeQuery(duplicate);
            boolean rs = dupli_data.next();
            if (rs){
                return true;
            }else {
                return false;
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
