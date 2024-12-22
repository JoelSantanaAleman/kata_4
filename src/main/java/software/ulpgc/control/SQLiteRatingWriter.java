package software.ulpgc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLiteRatingWriter implements AutoCloseable{
   private final Connection connection;
   private static final String createTable = """
           CREATE TABLE IF NOT EXISTS ratings(
                id TEXT PRIMARY KEY,
                average_ratings REAL,
                num_votes INTEGER
           );""";

   private static final String insertStatement = "INSERT OR IGNORE INTO ratings (id, average_rating, num_votes) VALUES(?, ? , ?)";
   private PreparedStatement insertStm;

   public SQLiteRatingWriter(File dbFile) throws IOException{
       try{
           connection = openConnection(dbFile);
           prepareDatabase();
       }catch (SQLException e){
           throw new IOException(e);
       }
   }

   public void write(List<Rating> ratings){
       try {
           for(Rating rating : ratings){
               insertStm.setString(1, rating.getId());
               insertStm.setDouble(2, rating.getAverageRating());
               insertStm.setInt(3, rating.getNumVotes());
               insertStm.execute();
           }
           connection.commit();
       }catch (SQLException e){
           throw new RuntimeException(e);
       }
   }

   private static Connection openConnection(File dbFile) throws SQLException{
       return DriverManager.getConnection("jdbc:sqlite" + dbFile.getAbsolutePath());
   }

   private void prepareDatabase() throws SQLException{
       connection.createStatement().execute(createTable);
       this.insertStm = connection.prepareStatement(insertStatement);
       connection.setAutoCommit(false);
   }

    @Override
    public void close() throws Exception {
      connection.close();
    }
}
