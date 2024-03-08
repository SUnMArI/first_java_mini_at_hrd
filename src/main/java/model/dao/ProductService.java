package model.dao;

import model.dto.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductService {
    boolean insert(Product products) throws SQLException;
    boolean update(int id,Product products) throws SQLException;
    boolean delete(String del_id) throws SQLException;
    ResultSet selectAll(int limite,int ind) throws SQLException;
    ResultSet search(String txt) throws SQLException;
    ResultSet view (String views) throws SQLException;
    int count() throws SQLException;
    boolean duplicate(String txt) throws SQLException;
}
