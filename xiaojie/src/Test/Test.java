package Test;

import Login.Database;
import Users.Users;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException,ClassNotFoundException{
        Database database=new Database("sa","zxj010611");

        database.testConnect();

      Users users1=database.getUser("xiaojie");
      System.out.println(users1);
    }
}
