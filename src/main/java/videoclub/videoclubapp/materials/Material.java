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
