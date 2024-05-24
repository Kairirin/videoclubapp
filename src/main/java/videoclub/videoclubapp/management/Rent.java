package videoclub.videoclubapp.management;

import videoclub.videoclubapp.materials.Material;
import videoclub.videoclubapp.users.Member;

import java.time.LocalDate;
import java.util.*;

public class Rent {
    private Member member;
    private List<Material> products;
    private LocalDate returnData;
    public Rent(Member member) {
        this.member = member;
        products = new ArrayList<>();
    }
    /*public Rent(Member member, Material product, LocalDate returnData){
        this.member = member;
        this.product = product;
        this.returnData = returnData;
    }*/
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

    public void addProduct(Material prod){
        if(products.size() < 5){
            products.add(prod);
        }
    }
    public void removeProduct(Material prod){
        if(products.contains(prod)){
            products.remove(prod);
        }
    }
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
}
