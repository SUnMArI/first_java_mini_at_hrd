package model.dao;

import connectDB.PostgresConnection;
import model.dto.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UnSaveImplement implements UnSaveTableService {
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
    public ResultSet selectAll(){
        String select= "SELECT * FROM unsave_tb";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(select);
            return rs;
        }catch (SQLException ex){
            return null;
        }
    }
    @Override
    public boolean transferProduct(){
        String transfer= "INSERT INTO product_tb ( name, unit_price,qty)\n" +
                "SELECT name, unit_price,qty\n" +
                "FROM unsave_tb\n" +
                "WHERE product_id is null";
        String delete_transfer ="DELETE FROM unsave_tb WHERE product_id IS NULL";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            st.execute(transfer);
            st.execute(delete_transfer);
            return true;
        }catch (SQLException ex){
            return false;
        }
    }
    @Override
    public boolean save_unsave_update(){
        String save_unsaveUpdate = "UPDATE product_tb\n" +
                "SET name = unsave_tb.name,\n" +
                "unit_price = unsave_tb.unit_price,\n" +
                "qty = unsave_tb.QTY\n" +
                "FROM unsave_tb\n" +
                "WHERE product_tb.id = unsave_tb.product_id";
        String delete_unsaveUndate = "DELETE FROM unsave_tb USING product_tb WHERE unsave_tb.product_id = product_tb.ID";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            st.execute(save_unsaveUpdate);
            st.execute(delete_unsaveUndate);
            return true;
        }catch (SQLException ex){
            return false;
        }
    }
}
