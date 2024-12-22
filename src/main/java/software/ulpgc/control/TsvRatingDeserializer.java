package software.ulpgc;

public class TsvRatingDeserializer implements RatingDeserializer{
    @Override
    public Rating deserializer(String data) {
        String[] fields = data.split("\t");
        return new Rating(
                fields[0], //id
                Double.parseDouble(fields[1]), //rating
                Integer.parseInt(fields[2])//numero de votos
        );
    }
}
