package montero.app_movil_lot5;

import java.util.ArrayList;

public class Profile {
    public boolean check = false;
    public String username;
    public String password;
    public ArrayList<Character> characters;

    public Profile(String username, String password, ArrayList<Character> characters){
        this.username = username;
        this.password = password;
        this.characters = characters;
    }

}
