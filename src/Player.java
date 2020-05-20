import com.structures.*;

// TODO: comment Class

public class Player {
    private int coins;
    private int stars;
    private Square position;
    private int placement;
    private String name;
    private List path;

    //TODO : comment constructor
    public Player(String name){
        this.coins = 0;
        this.stars = 0;
        // TODO: add first Square as position
        this.placement = 1;
        this.name = name;
        // TODO: add Main Path as path
    }

    // TODO: JAVADoc comment addCoins
    public void addCoins(int coins){
        this.coins += coins;
    }

    // TODO: JAVADoc comment removeCoins
    public void removeCoins(int coins){
        this.coins -= coins;
    }

    // TODO: move(){}

    // TODO: roll(){}

    // TODO: buyStar(){}

    //TODO: Comment Getters and Setters???
    public int getCoins(){
        return this.coins;
    }

    public void setCoins(int coins){
        this.coins = coins;
    }

    public int getStars(){
        return this.stars;
    }

    public void setStars(int stars){
        this.stars = stars;
    }

    public Square getPosition(){
        return this.position;
    }

    public void setPosition(Square position){
        this.position = position;
    }

    public int getPlacement(){
        return this.placement;
    }

    public void setPlacement(int placement){
        this.placement = placement;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List getPath(){
        return this.path;
    }

    public void setPath(List path){
        this.path = path;
    }

}
