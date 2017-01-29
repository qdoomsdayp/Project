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

@WebServlet("/us")
public class Users extends HttpServlet {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    Connection conn;

    @Override
    public void init() throws ServletException {
        try {
            this.conn = new Connect().getConnect(USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            PreparedStatement preparedStatement1 = conn.prepareStatement("select NAME from usersandrolls.rolls");

            ResultSet resultSet = preparedStatement1.executeQuery();
            String name = "";
            while (resultSet.next()) {
                if (name == "")
                    name = resultSet.getString("name");
                else
                    name = name + "," + resultSet.getString("name");
            }
            name.trim();
            request.setAttribute("nameR", name);

            ResultSet resultSet1;
            PreparedStatement preparedStatement = conn.prepareStatement("select * from usersandrolls.users");
            resultSet = preparedStatement.executeQuery();
            String str = "";
            while (resultSet.next()) {
                preparedStatement1 = conn.prepareStatement("select NAME from usersandrolls.rolls where id_roll = "
                        + resultSet.getInt("id_roll"));
                resultSet1 = preparedStatement1.executeQuery();
                resultSet1.next();
                if (str == "") {
                    str = resultSet.getString("id_users") +
                            "," + resultSet.getString("name") +
                            "," + resultSet.getString("email") +
                            "," + resultSet1.getString("name");
                } else {
                    str = str + "," + resultSet.getString("id_users") +
                            "," + resultSet.getString("name") +
                            "," + resultSet.getString("email") +
                            "," + resultSet1.getString("name");
                }

            }
            str.trim();
            request.setAttribute("table",str);
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