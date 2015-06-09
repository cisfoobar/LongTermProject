package firstTry;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World extends BasicGame {

	private Ball ball;
	private ArrayList<GameObject> gameObjects = new ArrayList();
	private ArrayList<GameObject> removeList = new ArrayList();
	
    public World() {
        super("SimpleTest");
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
    	ball = new Ball();
    	gameObjects.add(ball);
    	container.setShowFPS(false);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	for(GameObject gameObject : gameObjects) {
        	gameObject.update(container, delta);
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	for(GameObject gameObject : gameObjects) {
        	gameObject.render();
        }
    }
    
    public void removeObject(GameObject e) {
		removeList.add(e);
	}

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new World());
            app.setDisplayMode(1024, 768, false);
            app.start();
        } catch (SlickException e	) {
            e.printStackTrace();
        }
    }
}