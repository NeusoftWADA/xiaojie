package servlet;

import Login.Database;
import Users.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setHeader("content-type","text/html;charset=utf-8");

    String userid=request.getParameter("text");
    String password=request.getParameter("password");

        PrintWriter printWriter=response.getWriter();

        try {
            Database database=new Database("sa","123");
            Users users=database.check(userid,password);
            database.close();
            if (users==null){
                printWriter.write("用户不存在或密码错误");
            }else{
                printWriter.write("登录成功"+users.getUserid()+"!");
            }
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
            printWriter.write("登录失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
