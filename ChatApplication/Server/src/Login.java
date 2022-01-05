import java.sql.*;

public class Login {
    private final String jdbcURL = "jdbc:postgresql://localhost:5432/Users";
    private final String username = "postgres";
    private final String password = "123456";

    Login()
    {
        //
    }

    public int loginAttempt(String user, String pass)
    {
        try {
            Connection con = DriverManager.getConnection(jdbcURL, username, password);

            String select = "SELECT * FROM users WHERE users.username = '" + user +
                    "' AND users.password = '" + pass +"';";

            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(select);

            if (result.next()) {
                con.close();
               // System.out.println("Login succesfully");
                return 1;
            }
            else
            {
               // System.out.println("Wrong username/password");
                con.close();
                return 2;
            }
           // con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
