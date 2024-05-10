package videoclub.videoclubapp.materials;
/**
 * Branch of Material dedicated to movies
 * @author irenevinaderantón
 * @version 1
 */
public class Movie extends Material{
    private String director;
    public Movie(String director, String c, String t, int y, String g){
        super(c,t,y,g);
        this.director = director;
    }
    public String getDirector(){
        return director;
    }
    public void setDirector(String director){
        this.director = director;
    }
}
