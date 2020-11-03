package Login;

import Users.Users;

import java.sql.*;

public class Database {
    Connection connection = null;
    public Database(String userid, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=USERS",userid,password);
    }
    public void testConnect(){
        System.out.println(this.connection);
    }




    public void close() throws SQLException{
        connection.close();
    }


    public Users getUser(String userid) throws SQLException{
        PreparedStatement preparedStatement=connection.prepareStatement("select * from users where userid=?");
        preparedStatement.setString(1,userid);
        preparedStatement.executeQuery();


        ResultSet resultSet=preparedStatement.getResultSet();
        if (resultSet.next()){
            String name=resultSet.getString("userid");
            String pswd=resultSet.getString("password");
            return new Users(name,pswd);
        }
        else {
            return null;
        }
    }

    public Users check(String userid, String password) throws SQLException {
        PreparedStatement preparedStatement=connection.prepareStatement("select * from users where userid=?");
        preparedStatement.setString(1,userid);
        preparedStatement.executeQuery();

        ResultSet resultSet=preparedStatement.getResultSet();
        if (resultSet.next()){
            String pswd=resultSet.getString("password");
            if (pswd.equals(password)){
                return getUser(userid);
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }
}
