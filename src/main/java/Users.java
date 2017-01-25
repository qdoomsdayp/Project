import com.sun.org.apache.xpath.internal.operations.Number;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.*;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/")
public class Users extends HttpServlet {
private static final String URL = "jdbc:mysql://localhost:3306/mysql";
private static final String USERNAME = "root";
private static final String PASSWORD = "1234";

    @Override
    public void init() throws ServletException {


Con();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //request.getRequestDispatcher("users.jsp").forward(request, response);


    }




    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        method(request, response);

    }

    private void method(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


      //  String expression = request.getParameter("expression");


       // doGet(request,response);


    }
    public void Con(){
        try {
            Connect con = new Connect();
            Connection conn = con.getConnect(URL,USERNAME,PASSWORD);
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("select*from usersandrolls.users");

            while(resultSet.next()){
                int id = resultSet.getInt("id_users");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int idRoll = resultSet.getInt("id_roll");
                System.out.println(id+" "+name+" "+email+" "+idRoll);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }




}