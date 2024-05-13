package videoclub.videoclubapp;
import videoclub.videoclubapp.materials.*;
import videoclub.videoclubapp.users.*;
import java.time.LocalDate;
import java.util.HashMap;
/**
 * Class of our program that stores all the information about renting
 * @author irenevinaderantón
 * @version 1
 */
public class Rent {
    private Member member;
    private HashMap<Material, LocalDate> materials;
    public Rent(Member m){
        member = m;
        materials = new HashMap<>();
    }
    public void addMaterial(Material m){
        //Añadir material a prestar
    }
    public void removeMaterial(Material m){
        //Eliminar
    }
    public void extendDate(String code, LocalDate d){
        //Alargar fecha
    }
    public void viewAll(){
        //Mostrar lo prestado a esta persona
    }
}
