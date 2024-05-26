package videoclub.videoclubapp.materials;

import java.util.Objects;

/**
 * Abstract class to define stored materials information
 * @author irenevinaderantón
 * @version 1.5
 */
public abstract class Material {
    protected String code;
    protected String title;
    protected int year;
    protected String genre;
    protected String extra;
    protected boolean available;
    /**
     * Constructor with parameters
     * @param c A String with the code of the material
     * @param t A String with the title of the movie or videogame
     * @param y A int with the release year
     * @param g A String with the genre
     * @param e a String with extra information that will be different for each subclass
     */
    public Material(String c, String t, int y, String g, String e){
        code = c;
        title = t;
        year = y;
        genre = g;
        extra = e;
        available = true;
    }
    public Material(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year = year;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getExtra(){
        return extra;
    }
    public void setExtra(String extra){
        this.extra = extra;
    }
    public boolean isAvailable(){
        return available;
    }
    public void setAvailable(boolean available){
        this.available = available;
    }
    @Override
    public String toString(){
        return code+ " - " + title+ "(" + year +")";
    }

    /**
     * Two materials are the same depending on their codes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(code, material.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
