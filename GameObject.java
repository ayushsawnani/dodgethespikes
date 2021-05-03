import java.awt.*;

public abstract class GameObject{
    protected int width, height, speedX, speedY, posx, posy;
    protected Color c;
    private ID id;
    public GameObject(int width, int height, int speedX, int speedY, int posx, int posy, Color c, ID id){
        
        this.posx = posx;
        this.posy = posy;

        this.id = id;

        this.width = width;
        this.height = height;

        this.speedX = speedX;
        this.speedY = speedY;

        this.c = c;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public int getX(){
        return posx;
    }
    public int getY(){
        return posy;
    }
    public int getSpeedX(){
        return speedX;
    }
    public int getSpeedY(){
        return speedY;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Color getColor(){
        return c;
    }

    public void setX(int newX){
        posx = newX;
    }
    public void setY(int newY){
        posy = newY;
    }
    public void setSpeedX(int newSpeedX){
        speedX = newSpeedX;
    }
    public void setSpeedY(int newSpeedY){
        speedY = newSpeedY;
    }
    public void setColor(Color newc){
        c = newc;
    }

    public ID getID(){
        return id;
    }

}
