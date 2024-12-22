package software.ulpgc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLiteRatingReader {
    private final Connection connection;
    private final PreparedStatement selecStm;
    private final ResultSet ratings;

    public SQLiteRatingReader(Connection connection, PreparedStatement selecStm, ResultSet ratings) {
        this.connection = connection;
        this.selecStm = selecStm;
        this.ratings = ratings;
    }

    public SQLiteRatingReader(File dbFile)
}
