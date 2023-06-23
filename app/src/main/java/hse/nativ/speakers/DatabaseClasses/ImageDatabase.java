package hse.nativ.speakers.DatabaseClasses;

public class ImageDatabase {
    private long height;
    private long width;
    private String id;
    private String url;

    public ImageDatabase() {}
    public ImageDatabase(long height, long width, String id, String url) {
        this.height = height;
        this.id = id;
        this.width = width;
        this.url = url;
    }

    public void setHeight(long height) {this.height = height;}
    public long getHeight() {return height;}

    public void setWidth(long width) {this.width = width;}
    public long getWidth() {return width;}

    public void setId(String id) {this.id = id;}
    public String getId() {return id;}

    public void setUrl(String url) {this.url = url;}
    public String getUrl() {return url;}
}
