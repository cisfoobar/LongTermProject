package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import utility.Vector;

public class PlasmaBolt extends ProjectileEntity {

	public PlasmaBolt(Vector v, float d) throws SlickException {
		super(v, d);
		
		setRadius(20);
		setBlastTime(30);
		setImage(new Image("images/plasmabolt.png").getScaledCopy(0.6f));
	}

	@Override
	public int getCap() {
		return (int) Math.pow(2, -0.01 * timeInWorld() + 8) + 15;
	}
}
