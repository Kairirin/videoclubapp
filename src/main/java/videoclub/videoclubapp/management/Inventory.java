package videoclub.videoclubapp.management;

import videoclub.videoclubapp.materials.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class from which all materials inventory is managed
 * @author irenevinaderantón
 * @version 1
 */
public class Inventory {
    private List<Material> inventory;

    /**
     * Constructor of the class, automatically reads the data from a file
     */
    public Inventory(){
        inventory = readFile();
    }
    public List<Material> getInventory(){
        return inventory;
    }
    public void setInventory(List<Material> inv){
        inventory = inv;
    }

    /**
     * Method for add a material to the list
     * @param m The material object to be added
     */
    public void addMaterial(Material m){
        inventory.add(m);
        saveInventory();
    }

    /**
     * Method for remove a material from the list
     * @param m The material object to be removed
     */
    public void removeMaterial(Material m){
        inventory.remove(m);
        saveInventory();
    }

    /**
     * Changes the availability of an item once we include it in a rent object
     * @param m A certain material
     */
    public void setAvailability(Material m){
        int index = inventory.indexOf(m);

        if(inventory.get(index).isAvailable()){
            inventory.get(index).setAvailable(false);
        }
        else {
            inventory.get(index).setAvailable(true);
        }
        saveInventory();
    }

    /**
     * Method to modify the attributes of certain material
     * @param position The position in the list, serves to claim the right material
     * @param title The new title
     * @param genre The new genre
     * @param year The new year
     * @param extra The new extra information
     */
    public void modifyMaterial(int position, String title, String genre, int year, String extra){
        inventory.get(position).setTitle(title);
        inventory.get(position).setGenre(genre);
        inventory.get(position).setYear(year);
        inventory.get(position).setExtra(extra);

        saveInventory();
    }

    /**
     * Method for the data load
     * @return a List of Material objects
     */
    private List<Material> readFile(){
        List<Material> materials = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/sample/materials.txt"));
            for(String l: lines){
                if (l.split(";")[0].contains("BR"))
                    materials.add(new BluRay(l.split(";")[0],l.split(";")[1],Integer.parseInt(l.split(";")[2]), l.split(";")[3], l.split(";")[4]));
                else if (l.split(";")[0].contains("DVD"))
                    materials.add(new DVD(l.split(";")[0],l.split(";")[1],Integer.parseInt(l.split(";")[2]), l.split(";")[3], l.split(";")[4]));
                else if (l.split(";")[0].contains("VHS"))
                    materials.add(new VHS(l.split(";")[0],l.split(";")[1],Integer.parseInt(l.split(";")[2]), l.split(";")[3], l.split(";")[4]));
                else if (l.split(";")[0].contains("NIN"))
                    materials.add(new Nintendo(l.split(";")[0],l.split(";")[1],Integer.parseInt(l.split(";")[2]), l.split(";")[3], l.split(";")[4]));
                else if (l.split(";")[0].contains("PS"))
                    materials.add(new Playstation(l.split(";")[0],l.split(";")[1],Integer.parseInt(l.split(";")[2]), l.split(";")[3], l.split(";")[4]));
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return materials;
    }

    /**
     * Method to save all the information in the right file
     */
    private void saveInventory(){
        try(PrintWriter file = new PrintWriter("src/main/resources/sample/materials.txt")){
            inventory.forEach(material -> {
                        file.println(material.getCode() + ';' +
                                material.getTitle() + ';' +
                                material.getYear() + ';' +
                                material.getGenre() + ';' +
                                material.getExtra());
                    });
        } catch (Exception e) {}
    }
}
