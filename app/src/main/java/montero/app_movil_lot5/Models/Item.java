package montero.app_movil_lot5.Models;

public class Item {

    public String name;
    public int size;
    public int date;
    public int dateMax;
    public String comment;

    public Item(String name, int size, int date, int dateMax, String comment){
        this.name = name;
        this.size = size;
        this.date = date;
        this.dateMax = dateMax;
        this.comment = comment;
    }
}