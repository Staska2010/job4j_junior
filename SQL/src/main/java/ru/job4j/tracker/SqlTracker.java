package ru.job4j.tracker;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;
    private static final Logger LOG = LoggerFactory.getLogger(SqlTracker.class.getName());

    public SqlTracker() {
        init();
    }

    @Override
    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error("Error in init block", e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        String sqlString = "INSERT INTO item (name, descn) VALUES(?, ?);";
        try (PreparedStatement pst = cn.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, item.getName());
            pst.setString(2, item.getDesc());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            int id = -1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            item.setId(Integer.toString(id));
        } catch (SQLException e) {
            LOG.error("Insert row error", e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        int rowsQuantity = 0;
        String sqlString = "UPDATE item SET name = ?, descn = ? WHERE id = ?;";
        try (PreparedStatement pst = cn.prepareStatement(sqlString)) {
            pst.setString(1, item.getName());
            pst.setString(2, item.getDesc());
            pst.setInt(3, Integer.parseInt(id));
            rowsQuantity = pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Update row error", e);
        }
        return (rowsQuantity > 0);
    }

    @Override
    public boolean delete(String id) {
        int rowsQuantity = 0;
        String sqlString = "DELETE FROM item WHERE id = ?;";
        try (PreparedStatement pst = cn.prepareStatement(sqlString)) {
            pst.setInt(1, Integer.parseInt(id));
            rowsQuantity = pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Update row error", e);
        }
        return (rowsQuantity > 0);
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        String sqlString = "SELECT * FROM item;";
        try (PreparedStatement pst = cn.prepareStatement(sqlString)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Item newItem = new Item(rs.getString(2), rs.getString(3));
                newItem.setId(String.valueOf(rs.getInt(1)));
                result.add(newItem);
            }
        } catch (SQLException e) {
            LOG.error("Error in find all block", e);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        String sqlString = "SELECT * FROM item WHERE name = ?;";
        try (PreparedStatement pst = cn.prepareStatement(sqlString)) {
            pst.setString(1, key);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Item newItem = new Item(rs.getString(2), rs.getString(3));
                newItem.setId(String.valueOf(rs.getInt(1)));
                result.add(newItem);
            }
        } catch (SQLException e) {
            LOG.error("Error in find by name block", e);
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        String sqlString = "SELECT * FROM item WHERE id = ?;";
        try (PreparedStatement pst = cn.prepareStatement(sqlString)) {
            pst.setInt(1, Integer.parseInt(id));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = new Item(rs.getString(2), rs.getString(3));
                result.setId(String.valueOf(rs.getInt(1)));
            }
        } catch (SQLException e) {
            LOG.error("Error in find by ID block", e);
        }
        return result;
    }

}
