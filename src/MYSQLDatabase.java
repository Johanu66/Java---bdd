import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MYSQLDatabase {
    private final String host;
    private final int port;
    private final String databaseName;
    private final String user;
    private final String password;
    private Connection connection;
    static private Boolean driverLoaded;

    MYSQLDatabase(String host, int port, String databaseName, String user, String password) throws Exception{
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;
        this.connection = null;
        MYSQLDatabase.driverLoaded = false;

        MYSQLDatabase.loadDriver();
        this.connect();
    }

    public void connect() throws SQLException{
        this.connection = DriverManager.getConnection(
            "jdbc:mysql://"+this.host+":"+this.port+"/"+this.databaseName+"?allowMultiQueries=true",
            this.user,
            this.password
        );
    }

    public Statement createStatement() throws SQLException{
        return this.connection.createStatement();
    }

    public PreparedStatement prepareStatement(String request) throws SQLException{
        return this.connection.prepareStatement(request);
    }

    static private void loadDriver() throws ClassNotFoundException{
        if(!driverLoaded){
            Class.forName("com.mysql.cj.jdbc.Driver");
            MYSQLDatabase.driverLoaded = true;
        }
    }
}
