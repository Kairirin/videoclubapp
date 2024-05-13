package videoclub.videoclubapp.users;

import java.util.Objects;

/**
 * Abstract class to manage users information
 * @author irenevinaderantón
 * @version 1
 */
public abstract class User {
    protected String name;
    protected String password;
    /**
     * Constructor with parameters
     * @param name A String with the name of user
     * @param password A String with the password for the user
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    //Implementar métodos de crear miembro
}
