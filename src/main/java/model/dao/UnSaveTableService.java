package model.dao;

import model.dto.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UnSaveTableService {
    boolean insert(Product products) throws SQLException;
    ResultSet selectAll() throws SQLException;
    boolean transferProduct() throws SQLException;
    boolean save_unsave_update() throws SQLException;
}
