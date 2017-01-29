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

@WebServlet("/DataBase")
public class DataBase extends HttpServlet {

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

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            method(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void method(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String command1 = request.getParameter("type");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        switch (command1) {
            case "add": {
                add(request, response);
                break;
            }

            case "edit": {
                edit(request, response);
                break;
            }
            case "delete": {
                delete(request, response);
                break;
            }
            case "addr": {
                addr(request, response);
                break;
            }
            case "selectListR": {
                selectListR(request, response);
                break;
            }
            case "editr": {
                editr(request, response);
                break;
            }
            case "deleter": {
                deleter(request, response);
                break;
            }

        }


    }


    public void add(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String idroll = request.getParameter("idroll");
        PreparedStatement preparedStatement1 = conn.prepareStatement("SELECT id_roll FROM usersandrolls.rolls " +
                "WHERE name = " + "\"" + idroll + "\"");
        ResultSet resultSet = preparedStatement1.executeQuery();
        resultSet.next();
        int tmp = resultSet.getInt("id_roll");
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO usersandrolls.users " +
                "(name,email,id_roll) VALUES (?,?,?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, tmp);
        preparedStatement.executeUpdate();

        preparedStatement1 = conn.prepareStatement("SELECT id_users FROM usersandrolls.users " +
                "WHERE name = " + "\"" + name + "\"" + "and email = " + "\"" + email + "\"" + "and id_roll = " + tmp);
        resultSet = preparedStatement1.executeQuery();
        resultSet.next();
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            jsonEnt.put("infoU", resultSet.getInt("id_users") + "," + name + "," + email + "," + idroll);
            out.print(jsonEnt.toString());
        }
    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int edit = Integer.parseInt(request.getParameter("nameS"));
        String name = request.getParameter("nameI");
        String email = request.getParameter("emailI");
        String idroll = request.getParameter("idrollI");
        PreparedStatement preparedStatement1 = conn.prepareStatement("SELECT id_roll FROM usersandrolls.rolls where name = " + "\"" + idroll + "\"");
        ResultSet resultSet = preparedStatement1.executeQuery();
        resultSet.next();
        int tmp = resultSet.getInt("id_roll");
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE usersandrolls.users SET name =?,email = ?,id_roll = ? WHERE id_users = " + edit);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, tmp);
        preparedStatement.executeUpdate();
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            jsonEnt.put("dataU", name + "," + email + "," + idroll);
            out.print(jsonEnt.toString());
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int delet = Integer.parseInt(request.getParameter("nameS"));
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE from usersandrolls.users  WHERE id_users = ?");
        preparedStatement.setInt(1, delet);
        preparedStatement.executeUpdate();
    }

    public void addr(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO usersandrolls.rolls " +
                "(name) VALUES (?)");
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        String listName = checkRolls();
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();

            jsonEnt.put("listName", listName);

            out.print(jsonEnt.toString());
        }
    }

    public void selectListR(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String edit = request.getParameter("name");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            jsonEnt.put("nam", edit);
            out.print(jsonEnt.toString());
        }
    }

    public void editr(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String edit = request.getParameter("nameS");
        String name = request.getParameter("nameI");
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE usersandrolls.rolls SET name =? WHERE name = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, edit);
        preparedStatement.executeUpdate();
        String listName = checkRolls();
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();

            jsonEnt.put("listName", listName);

            out.print(jsonEnt.toString());
        }


    }

    public void deleter(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String delet = request.getParameter("nameS");
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE from usersandrolls.rolls  WHERE name = ?");
        preparedStatement.setString(1, delet);
        preparedStatement.executeUpdate();
        String listName = checkRolls();
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            jsonEnt.put("listName", listName);
            out.print(jsonEnt.toString());
        }
    }

    public String checkRolls() throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("select name from usersandrolls.rolls ");
        ResultSet resultSet = preparedStatement.executeQuery();
        String id_roll = "";
        while (resultSet.next()) {
            if (id_roll == "")
                id_roll = resultSet.getString("name");
            else
                id_roll = id_roll + "," + resultSet.getString("name");
        }
        id_roll.trim();
        return id_roll;
    }


    public void destroy() {
    }


}