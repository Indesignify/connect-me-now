package io.connectmenow.connect.model.dto;

import java.sql.*;
import java.util.logging.*;

public class UserSearchDTO {
    Connection connection = null;
    String url = "jdbc:postgresql://localhost:5432/connect_me_now";
    String name = "postgres";
    String password = "postgres";

    public void UserSearchByMeil(String SearchPar){
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(
                    "SELECT id, first_name, last_name FROM users where email = ? "); /*or  first_name = ? or last_name = ?*/
            preparedStatement.setString(1, SearchPar);
            ResultSet result1 = preparedStatement.executeQuery();

            System.out.println("Выводит результат поиска по мейлу: ");
            while (result1.next()) {
                System.out.print(("UserID: ") + result1.getString("id") + ", ");
                System.out.print("First name: " + result1.getString("first_name") + ", ");
                System.out.println("Last name: " + result1.getString("last_name") + " ");

            }
        } catch (
                Exception ex)
        {
            Logger.getLogger(UserInfoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserInfoDTO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void UserSearchByFirstName(String SearchPar){
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(
                    "SELECT id, first_name, last_name FROM users where first_name = ?");
            preparedStatement.setString(1, SearchPar);
            ResultSet result1 = preparedStatement.executeQuery();

            System.out.println("Выводит результат поиска по имени: ");
            while (result1.next()) {
                System.out.print(("UserID: ") + result1.getString("id") + ", ");
                System.out.print("First name: " + result1.getString("first_name") + ", ");
                System.out.println("Last name: " + result1.getString("last_name") + " ");

            }
        } catch (
                Exception ex)
        {
            Logger.getLogger(UserInfoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserInfoDTO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void UserSearchByLastName(String SearchPar){
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(
                    "SELECT id, first_name, last_name FROM users where last_name = ?");
            preparedStatement.setString(1, SearchPar);
            ResultSet result1 = preparedStatement.executeQuery();

            System.out.println("Выводит результат поиска по фамилии: ");
            while (result1.next()) {
                System.out.print(("UserID: ") + result1.getString("id") + ", ");
                System.out.print("First name: " + result1.getString("first_name") + ", ");
                System.out.println("Last name: " + result1.getString("last_name") + " ");

            }
        } catch (
                Exception ex)
        {
            Logger.getLogger(UserInfoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserInfoDTO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void UserSearchByNickname(String SearchPar){
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(
                    "SELECT id, first_name, last_name FROM users where nickname = ? ");
            preparedStatement.setString(1, SearchPar);
            ResultSet result1 = preparedStatement.executeQuery();

            System.out.println("Выводит результат поиска по никнейму: ");
            while (result1.next()) {
                System.out.print(("UserID: ") + result1.getString("id") + ", ");
                System.out.print("First name: " + result1.getString("first_name") + ", ");
                System.out.println("Last name: " + result1.getString("last_name") + " ");

            }
        } catch (
                Exception ex)
        {
            Logger.getLogger(UserInfoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserInfoDTO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
