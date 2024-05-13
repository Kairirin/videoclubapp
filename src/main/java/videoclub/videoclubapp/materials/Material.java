package videoclub.videoclubapp.materials;

import java.util.Objects;

/**
 * Abstract class to define stored materials information
 * @author irenevinaderantón
 * @version 1.1
 */
public abstract class Material {
    protected String code;
    protected String title;
    protected int year;
    protected String genre;
    protected String extra;
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
    @Override
    public String toString(){
        return code+';'+title+';'+year+';'+genre+';'+extra;
    }

}
