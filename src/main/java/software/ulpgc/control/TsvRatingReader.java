package software.ulpgc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TsvRatingReader implements RatingReader {
    private final File file;

    public TsvRatingReader(File file) {
        this.file = file;
    }


    @Override
    public List<Rating> read() {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            return readWith(reader);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private List<Rating> readWith(BufferedReader reader) throws IOException{
        TsvRatingDeserializer deserializer = new TsvRatingDeserializer();
        List<Rating> result = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null){
            Rating rating = deserializer.deserializer(line);
            result.add(rating);
        }
        return result;
    }
}
