import com.sun.org.apache.xpath.internal.operations.Number;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;
import java.sql.*;
import java.util.ArrayList;

@WebServlet( "/")
public class Users extends HttpServlet {

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
            PreparedStatement preparedStatement = conn.prepareStatement("select NAME from usersandrolls.users");
            ResultSet resultSet = preparedStatement.executeQuery();
            String name = "";
            while(resultSet.next()){
                if (name == "")
                    name = resultSet.getString("name");
                else
                    name =name +","+ resultSet.getString("name");
            }
            name.trim();
            request.setAttribute("data",name);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("users.jsp").forward(request, response);

    }




    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            method(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void method(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {




    }




    public void destroy() {
    }




}