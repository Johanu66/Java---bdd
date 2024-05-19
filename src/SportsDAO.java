import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SportsDAO {
    private MYSQLDatabase database;

    public SportsDAO(MYSQLDatabase database) {
        this.database = database;
    }

    public ArrayList<Sport> findAll(){
        try {
            Statement myStatement = this.database.createStatement();

            ResultSet results = myStatement.executeQuery("SELECT * FROM sport;");

            ArrayList<Sport> sports = new ArrayList<Sport>();

            while(results.next())
            {
                final int id = results.getInt("id");
                final String name = results.getString("name");
                final int required_participants = results.getInt("required_participants");

                Sport sport = new Sport(id, name, required_participants);
                sports.add(sport);
            }

            return sports;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Sport>();
        }
    }

    public Sport findById(int id){
        try {
            PreparedStatement myPreparedStatement = this.database.prepareStatement("SELECT * FROM sport WHERE id = ?;");

            myPreparedStatement.setInt(1, id);

            ResultSet results = myPreparedStatement.executeQuery();

            if(results.next())
            {
                final String name = results.getString("name");
                final int required_participants = results.getInt("required_participants");

                return new Sport(id, name, required_participants);
            }
            else
            {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Sport> findByName(String name){
        try {
            PreparedStatement myPreparedStatement = this.database.prepareStatement("SELECT * FROM sport WHERE name LIKE ?;");

            myPreparedStatement.setString(1, '%'+name+'%');

            ResultSet results = myPreparedStatement.executeQuery();

            ArrayList<Sport> sports = new ArrayList<Sport>();

            while(results.next())
            {
                final int id = results.getInt("id");
                final String full_name = results.getString("name");
                final int required_participants = results.getInt("required_participants");

                Sport sport = new Sport(id, full_name, required_participants);
                sports.add(sport);
            }

            return sports;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insert(Sport sport){
        try {
            PreparedStatement myPreparedStatement = this.database.prepareStatement("INSERT INTO sport (name, required_participants) VALUES (?, ?);");

            myPreparedStatement.setString(1, sport.getName());
            myPreparedStatement.setInt(2, sport.getRequiredParticipants());

            myPreparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(int id, Sport sport){
        try {
            PreparedStatement myPreparedStatement = this.database.prepareStatement("UPDATE sport SET name = ?, required_participants = ? WHERE id = ?;");

            myPreparedStatement.setString(1, sport.getName());
            myPreparedStatement.setInt(2, sport.getRequiredParticipants());
            myPreparedStatement.setInt(3, id);

            myPreparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id){
        try {
            PreparedStatement myPreparedStatement = this.database.prepareStatement("DELETE FROM sport WHERE id = ?;");

            myPreparedStatement.setInt(1, id);

            myPreparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
