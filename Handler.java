import java.util.LinkedList;
import java.awt.*;
public class Handler {
    LinkedList<GameObject> objects = new LinkedList<GameObject>();
    public void tick(){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }
    public void render(Graphics g){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject o){
        if (o instanceof Player){
            boolean isDuplicate = false;
            for(int i = 0; i < objects.size(); i++){
                if (objects.get(i) instanceof Player){
                    isDuplicate = true;
                }
            }
            if (!(isDuplicate)){
                objects.add((GameObject) o);
            }
        } else {
            objects.add((GameObject) o);
        }
    }
    public void removeObject(GameObject o){
        objects.remove(o);
    }
    public void removeAllObjects(){
        while(objects.size() > 0)
            objects.remove(0);
    }

    public void removeAllSpikes(){
        for(int i = 0; i < objects.size(); i++){
            GameObject obj = (GameObject) objects.get(i);
            if (obj instanceof Spike){
                objects.remove((Spike) obj);
            }
        }
    }
}
