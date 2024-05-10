package videoclub.videoclubapp.users;
/**
 * Abstract class to manage users information
 * @author irenevinaderantón
 * @version 1
 */
public abstract class User {
    protected String name;
    protected String password;
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    @Override
    public String toString(){
        return name;
    }

    //Implementar métodos de crear miembro
}
