package firstTry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class Bullet extends GameObject{
	Vector2f v;
	private static final float VELOCITY = 0.2f;
	
	public Bullet(){
		super();
	}
	
	public void update(GameContainer gc, StateBasedGame gs, int d) throws SlickException {
		changeX(v.getX());
		changeY(v.getY());
		
		if(atWorldEdge()) {
			getWorld().removeObject(this);
		}
	}

}
