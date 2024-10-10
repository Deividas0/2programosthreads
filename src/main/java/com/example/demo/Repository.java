package com.example.demo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.demo.Constants.*;

@org.springframework.stereotype.Repository
public class Repository {
    private static Connection _connection;

    public static void sqlConnection() throws SQLException {
        _connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void laiskasYDuombaze(String gavejas, String turinys) throws SQLException {
        final String sql = "INSERT INTO laiskai (gavejas, turinys) VALUES (?,?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, gavejas);
            preparedStatement.setString(2, turinys);

            preparedStatement.executeUpdate();
        }
    }

    public void paveiksliukasYDuombaze(byte[] base64Image) throws SQLException {
        final String sql = "INSERT INTO paveiksleliai (paveikslelis) VALUES (?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBytes(1, base64Image);

            preparedStatement.executeUpdate();
        }
    }

}
