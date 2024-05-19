public class PolySportsDatabase extends MYSQLDatabase {
    static private PolySportsDatabase instance = null;

    PolySportsDatabase() throws Exception{
        super("localhost", 3306, "poly_sports", "root", "");
    }

    static public PolySportsDatabase getInstance() throws Exception{
        if(PolySportsDatabase.instance==null){
            PolySportsDatabase.instance = new PolySportsDatabase();
        }

        return PolySportsDatabase.instance;
    }
}
