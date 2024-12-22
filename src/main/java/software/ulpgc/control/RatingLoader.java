package software.ulpgc;

import java.io.File;
import java.util.List;

public class RatingLoader {
    private final File source;
    private final File dbFile;

    public RatingLoader(File source, File dbFile) {
        this.source = source;
        this.dbFile = dbFile;
    }

    public void execute(){
        TsvRatingReader tsvReader = new TsvRatingReader(source);
        try(SQLiteRatingWriter writer = new SQLiteRatingWriter(dbFile)){
            List<Rating> ratings = tsvReader.read();
            writer.write(ratings);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
