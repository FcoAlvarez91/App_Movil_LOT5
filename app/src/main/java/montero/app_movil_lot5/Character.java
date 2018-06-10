package montero.app_movil_lot5;

public class Character {

    public String name;
    public int level;
    public int exp;
    public int arch;
    public int race;
    public String role;
    public String[] skills;
    public Ability[] abilities;
    public Character(String name, int arch, int race, String role, Ability[] abilities){
        this.name = name;
        this.arch = arch;
        this.race = race;
        this.role = role;
        this.abilities = abilities;
        buildCharacter();
    }

    public void buildCharacter(){
        if(arch==0){
            skills[0] = "Sense Magic";
            if(level>=2){
                skills[1] = "Cantrip";
            }
            if(level>=3){
                skills[2] = "Empowered Spell";
            }
            if(level>=4){
                skills[3] = "Dispel";
            }
            if(level>=5){
                skills[4] = "Arcane Block";
            }
            if(level>=6){
                skills[5] = "Potent Spell";
            }
            if(level>=7){
                skills[6] = "Blink";
            }
            if(level>=8){
                skills[7] = "Fly";
            }
            if(level>=9){
                skills[8] = "Powerful Spell";
            }
            if(level>=10){
                skills[9] = "Haste";
            }
        }
        else if(arch==1){
            skills[0] = "In and Out";
            if(level>=2){
                skills[1] = "Cheap Shot";
            }
            if(level>=3){
                skills[2] = "Big Crits";
            }
            if(level>=4){
                skills[3] = "Quick Steps";
            }
            if(level>=5){
                skills[4] = "Exploit";
            }
            if(level>=6){
                skills[5] = "Many Hits";
            }
            if(level>=7){
                skills[6] = "Quick Dodge";
            }
            if(level>=8){
                skills[7] = "Silent Strikes";
            }
            if(level>=9){
                skills[8] = "All in Bits";
            }
            if(level>=10){
                skills[9] = "Haste";
            }
        }
        else if(arch==2){
            skills[0] = "Adrenaline Rush";
            if(level>=2){
                skills[1] = "Battle Haste";
            }
            if(level>=3){
                skills[2] = "Double Attack";
            }
            if(level>=4){
                skills[3] = "Combat Focus";
            }
            if(level>=5){
                skills[4] = "Battle Cry";
            }
            if(level>=6){
                skills[5] = "Vengeance";
            }
            if(level>=7){
                skills[6] = "Triple Attack";
            }
            if(level>=8){
                skills[7] = "Hardened Will";
            }
            if(level>=9){
                skills[8] = "Raging Focus";
            }
            if(level>=10){
                skills[9] = "Haste";
            }
        }
    }
}