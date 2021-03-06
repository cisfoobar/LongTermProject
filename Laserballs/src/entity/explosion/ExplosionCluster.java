package entity.explosion;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import entity.Entity;
import java.util.Random;

public class ExplosionCluster extends Entity {
	private Random r;
	private int counter, time, range;
	private boolean showRadius;
	
	public ExplosionCluster(int time, int range) throws SlickException {
		this.time = time;
		this.range = range;
		
		counter = 0;
		showRadius = false;
		r = new Random();
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame gs, int d)
			throws SlickException {
		if(getWorld() != null && !showRadius) {
			getWorld().addObject(new BlastWave(time, 30), getX(), getY());
			showRadius = true;
		}
		
		if(r.nextInt(100) < 20) {
			float x = range - r.nextInt(2 * range) + getX();
			float y = range - r.nextInt(2 * range) + getY();
			float size = (r.nextInt(100) + 1) / 100f + 2;
			
			getWorld().addObject(new Explosion(size), x, y);
		}
		
		if(counter > time)
			getWorld().removeObject(this);
		counter++;
	}

}
