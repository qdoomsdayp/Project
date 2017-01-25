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

@WebServlet("/rolls")
public class Rolls extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    @Override
    public void init() throws ServletException {
        Con();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //request.getRequestDispatcher("rolls.jsp").forward(request, response);



        try {
            Connection conn = new Connect().getConnect(URL,USERNAME,PASSWORD);
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("select id_roll from usersandrolls.rolls ");
            String id_roll = "";
            while(resultSet.next()){
                if (id_roll == "")
                    id_roll = resultSet.getString("id_roll");
                else
                    id_roll =id_roll +","+ resultSet.getString("id_roll");
            }
            id_roll.trim();
            request.setAttribute("data",id_roll);
           // System.out.println(id_roll);



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("rolls.jsp").forward(request, response);

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