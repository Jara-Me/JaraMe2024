package siliconDream.jaraMe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.dto.UserDto;

@Repository
public class UserDao {
    @Autowired
    private Connection jdbcConnection; // Inject a JDBC connection or manage it as needed

    // Receive emailCheck from Controller
    public String emailCheck(String email) {
        String check = null;
        try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement("SELECT EMAIL FROM USER WHERE EMAIL = ?")) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    check = resultSet.getString("EMAIL");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(check);
        return check;
    }

    // This will also be processed in the DB to prevent duplicate values when signing up.
    public String signUpCheck(UserDto userDto) {
        String check = null;
        try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement("SELECT NICKNAME FROM USER WHERE NICKNAME = ?")) {
            preparedStatement.setString(1, userDto.getNickname());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    check = resultSet.getString("NICKNAME");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    // Insert user into the database
    public void insert(UserDto userDto) {
        try (PreparedStatement preparedStatement = jdbcConnection.prepareStatement("INSERT INTO USER (EMAIL, PASSWORD, NICKNAME, PHONE_NUM) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, userDto.getEmail());
            preparedStatement.setString(2, userDto.getPassword());
            preparedStatement.setString(3, userDto.getNickname());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }