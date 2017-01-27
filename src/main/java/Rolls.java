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

    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    Connection conn;
    @Override
    public void init() throws ServletException {
        try {
            this.conn = new Connect().getConnect(USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select name from usersandrolls.rolls ");
            ResultSet resultSet = preparedStatement.executeQuery();
            String id_roll = "";
            while(resultSet.next()){
                if (id_roll == "")
                    id_roll = resultSet.getString("name");
                else
                    id_roll =id_roll +","+ resultSet.getString("name");
            }
            id_roll.trim();
            request.setAttribute("data",id_roll);




        } catch (SQLException e) {
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





    }




    public void destroy() {
    }




}