package videoclub.videoclubapp.users;
/**
 * Class with all the data of people stored
 * @author irenevinaderantón
 * @version 1
 */
public class Member {
    private String id;
    private String name;
    private String email;
    private int phoneNumber;
    /**
     * Constructor with parameters
     * @param id A String with the ID associated to a member
     * @param name A String with the name of that person
     * @param email A String with the email
     * @param phoneNumber A int with the phone number
     */
    public Member(String id, String name, String email,int phoneNumber){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString(){
        return id + " - " + name + " - Email: " + email;
    }
}
