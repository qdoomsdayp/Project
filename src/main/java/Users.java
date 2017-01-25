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

              try {
            Connection conn = new Connect().getConnect(URL,USERNAME,PASSWORD);
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("select NAME from usersandrolls.users ");
            String name = "";
            while(resultSet.next()){
                if (name == "")
                    name = resultSet.getString("name");
                else
                    name =name +","+ resultSet.getString("name");
            }
            name.trim();
            request.setAttribute("data",name);
            //System.out.println(name);



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("users.jsp").forward(request, response);

    }




    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        method(request, response);

    }

    private void method(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String command = request.getParameter( "command" ) ;
        switch (command){

        }



      //  String expression = request.getParameter("expression");

        System.out.println(command);
       // doGet(request,response);


    }
    public void Con(){
        try {
            Connect con = new Connect();
            Connection conn = con.getConnect(URL,USERNAME,PASSWORD);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void destroy() {
    }




}