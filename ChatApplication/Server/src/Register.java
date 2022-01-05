import java.sql.*;

public class Register {
    private final String jdbcURL = "jdbc:postgresql://localhost:5432/Users";
    private final String username = "postgres";
    private final String password = "123456";

    public Register(){
        //
    }

    /*public Register(String user, String pass) {
        try {
            Connection con = DriverManager.getConnection(jdbcURL, username, password);
          //  System.out.println("Connected to Database");

            String select = "SELECT * FROM users WHERE users.username = '" + user + "';";

            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(select);

            if (result.next())
                System.out.println("Username already in use.");
            else
            {
                String insert = "INSERT INTO users (username, password)" +
                    " VALUES('" + user + "', '" + pass + "')";

                Statement _statement = con.createStatement();
                int rows = _statement.executeUpdate(insert);
                if (rows > 0) {
                    System.out.println("New user registered.");
                }
            }

            con.close();

        } catch (SQLException e) {
            System.out.println("Error connecting to Database");
            e.printStackTrace();
        }
    }*/

    public int registerAttempt(String user, String pass)
    {
        try {
            Connection con = DriverManager.getConnection(jdbcURL, username, password);
            //  System.out.println("Connected to Database");

            String select = "SELECT * FROM users WHERE users.username = '" + user + "';";

            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(select);

            if (result.next())
            {
               // System.out.println("Username already in use.");
                return 1;
            }
            else
            {
                String insert = "INSERT INTO users (username, password)" +
                        " VALUES('" + user + "', '" + pass + "')";

                Statement _statement = con.createStatement();
                int rows = _statement.executeUpdate(insert);
              //  if (rows > 0) {
                //    System.out.println("New user registered.");
               // }
            }

            con.close();
            return 2;

        } catch (SQLException e) {
            System.out.println("Error connecting to Database");
            e.printStackTrace();
            return 0;
        }
    }

   /* public static void main(String[] args) {
       // Register register = new Register("guest", "guest");
        Login login = new Login();
        login.loginAttempt("guest", "guest1");
    }*/
}
