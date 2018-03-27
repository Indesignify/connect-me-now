package io.connectmenow.connect.model.dto;

import java.sql.*;
import java.util.logging.*;

/*@Getter
@Setter
*/


public class UserInfoDTO {
    Connection connection = null;
    String url = "jdbc:postgresql://localhost:5432/connect_me_now";
    String name = "postgres";
    String password = "postgres";

    public long userID;

    public UserInfoDTO(long userID) {
        this.userID = userID;
    }

    public long GetUserID() {
        System.out.println("Наш юзер - это " + userID);
        return userID;
    }


    public void GetFriendsID(long userID) {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);

            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(
                    "SELECT friend_id FROM users_friends WHERE person_id = ?");
            preparedStatement.setLong(1, userID);
            ResultSet result2 = preparedStatement.executeQuery();

            System.out.println("Выводит друзей: ");
            while (result2.next()) {
                System.out.println(result2.getLong("friend_id"));
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