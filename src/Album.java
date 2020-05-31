public class Album {

    private String type;
    private String title;
    private String artist;
    private int publication;
    private int rating;

    public Album(String type, String title, String artist, int publication, int rating) {
        this.type = type;
        this.title = title;
        this.artist = artist;
        this.publication = publication;
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getPublication() {
        return publication;
    }

    public int getRating() {
        return rating;
    }
}
