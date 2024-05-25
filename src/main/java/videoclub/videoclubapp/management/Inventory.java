package videoclub.videoclubapp.management;

import videoclub.videoclubapp.materials.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Material> inventory;
    public Inventory(){
        inventory = readFile();
    }
    public List<Material> getInventory(){
        return inventory;
    }
    public void setInventory(List<Material> inv){
        inventory = inv;
    }
    public void addMaterial(Material m){
        inventory.add(m);
        saveInventory();
    }
    public void removeMaterial(Material m){
        inventory.remove(m);
        saveInventory();
    }
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
    private void saveInventory(){
        try(PrintWriter file = new PrintWriter("materials.txt")){
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
