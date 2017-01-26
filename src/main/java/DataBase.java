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
            this.conn = new Connect().getConnect(USERNAME,PASSWORD);
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





        String command1 = request.getParameter( "type" ) ;
        switch (command1){
            case "Input": {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO usersandrolls.users " +
                        "(name,email,id_roll) VALUES (?,?,?)");
                String nameUser = request.getParameter("nameUser");
                String email = request.getParameter("email");
                int idRoll = Integer.parseInt(request.getParameter("idRoll"));
                preparedStatement.setString(1, nameUser);
                preparedStatement.setString(2, email);
                preparedStatement.setInt(3, idRoll);
                preparedStatement.executeUpdate();
                doGet(request, response);
                break;
            }
            case "selectList": {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                String edit = request.getParameter("name");
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM usersandrolls.users WHERE name ="+"\""+edit+"\"");
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int idroll = resultSet.getInt("id_roll");
                try (PrintWriter out = response.getWriter()) {
                    JSONObject jsonEnt = new JSONObject();

                    jsonEnt.put("nam",name);
                    jsonEnt.put("emai", email);
                    jsonEnt.put("idrol",""+idroll);

                    out.print(jsonEnt.toString());
                    System.out.println("ssfsdgkjwukfhjwjesdfsghoui");
                }




                break;
            }


        }






    }




    public void destroy() {
    }




}