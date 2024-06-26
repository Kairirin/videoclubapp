package videoclub.videoclubapp.management;

import videoclub.videoclubapp.materials.Material;
import videoclub.videoclubapp.users.Member;

import java.time.LocalDate;
import java.util.*;

/**
 * Class for the renting process
 * @author irenevinaderantón
 * @version 1
 */
public class Rent {
    private Member member;
    private List<Material> products;
    private LocalDate returnData;
    public Rent(Member member) {
        this.member = member;
        products = new ArrayList<>();
        returnData = LocalDate.now();
    }
    public Member getMember(){
        return member;
    }
    public List<Material> getProducts() {
        return products;
    }
    public void setProduct(List<Material> products) {
        this.products = products;
    }
    public LocalDate getReturnData(){
        return returnData;
    }
    public void setReturnData(LocalDate returnData){
        this.returnData = returnData;
    }
    public void setMember(Member m){
        member = m;
    }

    /**
     * Method to add a product to the list
     * @param prod The product to be added
     */
    public void addProduct(Material prod){
        products.add(prod);
    }

    /**
     * Method to remove a product from the list
     * @param prod The product to be removed
     */
    public void removeProduct(Material prod){
        if(products.contains(prod)){
            products.remove(prod);
        }
    }

    /**
     * Two rent objects will be the same if the Member is the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return Objects.equals(member, rent.member);
    }
    @Override
    public int hashCode() {
        return Objects.hash(member);
    }
    @Override
    public String toString(){
        return member.toString();
    }
}
