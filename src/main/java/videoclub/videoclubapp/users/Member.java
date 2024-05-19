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
    /**
     * Returns member's Id
     * @return Member's Id
     */
    public String getId(){
        return id;
    }
    /**
     * Establishes member's Id
     * @param id Member's Id
     */
    public void setId(String id){
        this.id = id;
    }
    /**
     * Returns member's name
     * @return Member's name
     */
    public String getName(){
        return name;
    }
    /**
     * Establishes member's name
     * @param name Member's name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Returns member's email
     * @return Member's email
     */
    public String getEmail(){
        return email;
    }
    /**
     * Establishes member's email
     * @param email Member's email
     */
    public void setEmail(String email){
        this.email = email;
    }
    /**
     * Returns member's phone
     * @return Member's phone
     */
    public int getPhoneNumber(){
        return phoneNumber;
    }
    /**
     * Establishes member's phone
     * @param phoneNumber Member's phone
     */
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
