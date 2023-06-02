package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection conn;

    public DatabaseManager(Connection conn) {
        this.conn = conn;
    }

    public ResultSet read(String tableName) throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }

    public int delete(String tableName, String idColumnName, String id) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        return stmt.executeUpdate();
    }

    public int update(String tableName, String idColumnName, String id, String columnName, String value) throws SQLException {
        String sql = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE " + idColumnName + " = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, value);
        stmt.setString(2, id);
        return stmt.executeUpdate();
    }
}