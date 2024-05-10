package videoclub.videoclubapp;
import videoclub.videoclubapp.materials.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of our program that stores all the materials that integrated the videoclub
 * @author irenevinaderantón
 * @version 1
 */
public class Inventory {
    private List<Material> inventory;
    public Inventory(){
        inventory = new ArrayList<>();
        //Método que carga toda la info de fichero del arrayList
    }
    public List<Material> getInventory(){
        return inventory;
    }
    public void setInventory(List<Material> list){
        inventory = list;
    }
    public void addMaterial(Material m){
        //Método que añade nuevo material
    }
    public Material searchMaterial(){
        //Método de búsqueda
        return null;
    }
    public void viewAll(){
        //Mostrar todo
        //Implementar filtros
    }
    public void modifyMaterial(){
        //Método que modifica un material
    }
    public void removeMaterial(){
        //Método que borra el material
    }

}
