package model.dao;

import model.dto.Product;

import java.sql.SQLException;

public interface UnSaveTableService {
    boolean insert(Product products) throws SQLException;
    boolean update(Product products) throws SQLException;
    boolean search(Product products) throws SQLException;
    boolean view (String views) throws SQLException;
    int count() throws SQLException;
    boolean transferProduct(Product products) throws SQLException;
}
