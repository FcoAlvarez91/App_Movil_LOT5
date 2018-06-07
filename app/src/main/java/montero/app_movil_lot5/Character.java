package montero.app_movil_lot5;

public class Character {

    public String name;
    public String arch;
    public String race;
    public String role;
    public Ability[] abilities;

    public Character(String name, String arch, String race, String role, Ability[] abilities){
        this.name = name;
        this.arch = arch;
        this.race = race;
        this.role = role;
        this.abilities = abilities;
    }
}