package entity;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import utility.Geometry;
import utility.Vector;
import entity.structure.LargeBlock;
import entity.structure.SmallBlock;

public class Player extends Entity {
	private Vector a, v;
	private float maxSpeed;
	
	public Player() throws SlickException {
		Image i = new Image("images/player.png").getScaledCopy(0.25f);
		a = new Vector(0.1f, 0.1f);
		v = new Vector(0, 0);
		
		setImage(i);
		maxSpeed = 7;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame gs, int d)
			throws SlickException {
		Input in = gc.getInput();
		
		float mx = in.getMouseX();
		float my = in.getMouseY();
		
		turnTowards(mx, my);
		shootProjectile(in);
		movePlayer(in);
	}
	
	public void movePlayer(Input in) {
		ArrayList<Entity> small = getIntersectingEntities(SmallBlock.class);
		ArrayList<Entity> large = getIntersectingEntities(LargeBlock.class);
		ArrayList<Entity> blocks = new ArrayList<Entity>();
		
		for(Entity e : small)
			blocks.add(e);
		for(Entity e : large)
			blocks.add(e);
		
		Entity e = getClosest(blocks);
		
		float dx = getWidth() / 2;
		float dy = getHeight() / 2;
		
		boolean left = e != null && getX() < e.getX();
		boolean right = e != null && getX() > e.getX();
		boolean up = e != null && getY() < e.getY();
		boolean down = e != null && getY() > e.getY();
		
		//Normalize (still needs fixing)
		if((left || right) && (up || down)) {
			float a = Geometry.getAngle(e.getX(), e.getY(), getX(), getY());
			
			if((a > 45 && a < 135) || (a > 225 && a < 315)) {
				up = false;
				down = false;
			}
			
			else {
				left = false;
				right = false;
			}
		}
				
		if(getX() < dx || getX() > 1920 - dx || left || right) {
			v.setX(0);
			
			if(right || getX() < dx)
				changeX(0.1f);
			if(left || getX() > 1920 - dx)
				changeX(-0.1f);
		}
		
		if(getY() < dy || getY() > 1080 - dy || up || down) {
			v.setY(0);
			
			if(down || getY() < dy)
				changeY(0.1f);
			if(up || getY() > 1080 - dy)
				changeY(-0.1f);
		}
		
		if(in.isKeyDown(Input.KEY_W) && getY() >= getHeight() / 2)
			v.changeY(-a.getY());
		if(in.isKeyDown(Input.KEY_A) && getX() >= getWidth() / 2)
			v.changeX(-a.getX());
		if(in.isKeyDown(Input.KEY_S) && getY() <= 1080 - getHeight() / 2)
			v.changeY(a.getY());
		if(in.isKeyDown(Input.KEY_D) && getX() <= 1920 - getWidth() / 2)
			v.changeX(a.getX());
		
		if(v.getX() > maxSpeed)
			v.setX(maxSpeed);
		if(v.getX() < -maxSpeed)
			v.setX(-maxSpeed);
		if(v.getY() > maxSpeed)
			v.setY(maxSpeed);
		if(v.getY() < -maxSpeed)
			v.setY(-maxSpeed);
		
		changeX(v.getX());
		changeY(v.getY());
	}
	
	public void shootProjectile(Input in) throws SlickException {
		float h = getHeight() * 0.75f;
		float dx = Geometry.getXComponent(h, getRotation());
		float dy = Geometry.getYComponent(h, getRotation());
		
		if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			getWorld().addObject(
				new PlasmaBolt(
					v.add(new Vector(10, getRotation(), 0)), 5
				), getX() + dx, getY() + dy
			);
	}
	
	public Entity getClosest(ArrayList<Entity> a) {
		if(a.isEmpty())
			return null;
		
		float d = 1000f;
		int i = 0;
		
		for(Entity e : a) {
			float dx = Math.abs(getX() - e.getX());
			float dy = Math.abs(getY() - e.getY());
			float dis = (float) Math.sqrt(dx * dx + dy * dy);
			
			if(dis < d) {
				d = dis;
				i = a.indexOf(e);
			}
		}
		
		return a.get(i);
	}
}
