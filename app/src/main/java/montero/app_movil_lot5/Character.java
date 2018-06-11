package montero.app_movil_lot5;

public class Character {

    public String name;
    public int level;
    public int exp;
    public String arch;
    public String race;
    public String role;
    public String[] skills = new String[10];
    public Ability[] abilities = new Ability[4];
    public int hp;
    public int copper;
    public int silver;
    public int gold;
    public int plat;
    public int locationX;
    public int locationY;
    public Character(String name, String arch, String race, String role){
        this.name = name;
        this.arch = arch;
        this.race = race;
        this.role = role;
        this.level = 1;
        buildCharacter();
    }

    public void buildCharacter(){
        if(arch.equals("Mage")){
            skills[0] = "Sense Magic";
            hp = 6;
            if(level>=2){
                skills[1] = "Cantrip";
                hp = hp + 6;
            }
            if(level>=3){
                skills[2] = "Empowered Spell";
                hp = hp + 6;
            }
            if(level>=4){
                skills[3] = "Dispel";
                hp = hp + 6;
            }
            if(level>=5){
                skills[4] = "Arcane Block";
                hp = hp + 6;
            }
            if(level>=6){
                skills[5] = "Potent Spell";
                hp = hp + 6;
            }
            if(level>=7){
                skills[6] = "Blink";
                hp = hp + 6;
            }
            if(level>=8){
                skills[7] = "Fly";
                hp = hp + 6;
            }
            if(level>=9){
                skills[8] = "Powerful Spell";
                hp = hp + 6;
            }
            if(level>=10){
                skills[9] = "Haste";
                hp = hp + 6;
            }
        }
        else if(arch.equals("Rogue")){
            skills[0] = "In and Out";
            hp = 7;
            if(level>=2){
                skills[1] = "Cheap Shot";
                hp = hp + 7;
            }
            if(level>=3){
                skills[2] = "Big Crits";
                hp = hp + 7;
            }
            if(level>=4){
                skills[3] = "Quick Steps";
                hp = hp + 7;
            }
            if(level>=5){
                skills[4] = "Exploit";
                hp = hp + 7;
            }
            if(level>=6){
                skills[5] = "Many Hits";
                hp = hp + 7;
            }
            if(level>=7){
                skills[6] = "Quick Dodge";
                hp = hp + 7;
            }
            if(level>=8){
                skills[7] = "Silent Strikes";
                hp = hp + 7;
            }
            if(level>=9){
                skills[8] = "All in Bits";
                hp = hp + 7;
            }
            if(level>=10){
                skills[9] = "Haste";
                hp = hp + 7;
            }
        }
        else if(arch.equals("Warrior")){
            skills[0] = "Adrenaline Rush";
            hp = 8;
            if(level>=2){
                skills[1] = "Battle Haste";
                hp = hp + 8;
            }
            if(level>=3){
                skills[2] = "Double Attack";
                hp = hp + 8;
            }
            if(level>=4){
                skills[3] = "Combat Focus";
                hp = hp + 8;
            }
            if(level>=5){
                skills[4] = "Battle Cry";
                hp = hp + 8;
            }
            if(level>=6){
                skills[5] = "Vengeance";
                hp = hp + 8;
            }
            if(level>=7){
                skills[6] = "Triple Attack";
                hp = hp + 8;
            }
            if(level>=8){
                skills[7] = "Hardened Will";
                hp = hp + 8;
            }
            if(level>=9){
                skills[8] = "Raging Focus";
                hp = hp + 8;
            }
            if(level>=10){
                skills[9] = "Haste";
                hp = hp + 8;
            }
        }
    }
}