package videoclub.videoclubapp.materials;
/**
 * Branch of Material dedicated to videogames
 * @author irenevinaderantón
 * @version 1
 */
public class Videogame extends Material {
    private String company;
    public Videogame(String company, String c, String t, int y, String g) {
        super(c, t, y, g);
        this.company = company;
    }
    public String getCompany(){
        return company;
    }
    public void setCompany(String company){
        this.company = company;
    }
}
