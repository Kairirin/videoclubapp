package videoclub.videoclubapp.materials;
/**
 * Movie format
 * @author irenevinaderantón
 * @version 1
 * @see Movie
 */
public class DVD extends Movie{
    public DVD(String code){super(code);}
    public DVD(String c, String t, int y, String g, String d){
        super(c, t, y, g, d);
    }
}
