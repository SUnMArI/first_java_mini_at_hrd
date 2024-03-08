package model.dao;

import connectDB.PostgresConnection;
import model.dto.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UnSaveImplement implements UnSaveTableService {
    @Override
    public boolean insert(Product products)  {
        String query= "INSERT INTO product_tb(Name,Unit_Price,QTY)\n" +
                "VALUES ('" +products.getName()+
                "'," +products.getUnitPrice()+
                "," +products.getQty()+
                ")";
        try(Connection cn = PostgresConnection.connection()){
            Statement st = cn.createStatement();
            st.execute(query);
            return true;
        }catch (SQLException ex){
            return false;
        }
    }

    @Override
    public boolean update(Product products) throws SQLException {
        String update= "UPDATE product_tb SET name = Sting WHERE id = 1";
        return false;
    }

    @Override
    public boolean search(Product products) throws SQLException {
        String search= "SELECT * FROM product_tb WHERE name LIKE '%s%'";

        return false;
    }

    @Override
    public boolean view(String views) throws SQLException {
        String view= "SELECT * FROM product_tb WHERE id = 2";

        return false;
    }

    @Override
    public int count() throws SQLException {
        String view= "SELECT COUNT(*) FROM product_tb";
        return 0;
    }

    @Override
    public boolean transferProduct(Product products) throws SQLException {
        String transfer= "INSERT INTO product_tb ( name, unit_price,qty)\n" +
                "SELECT name, unit_price,qty\n" +
                "FROM Unsave_tb\n" +
                "WHERE id = 1;";
        return false;
    }
}
