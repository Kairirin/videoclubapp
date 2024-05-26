package videoclub.videoclubapp.users;
/**
 * Type of user who manage users creation
 * @author irenevinaderantón
 * @version 1
 * @see User
 */
public class Admin extends User{
    public Admin(String name, String password){
        super(name, password);
    }
    public Admin(User a){super(a);}
}
