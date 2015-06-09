package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import utility.ReferenceFrame;
import utility.Vector;

import java.util.ArrayList;

import entity.Entity;

public abstract class World extends BasicGameState {
	private ArrayList<Entity> entitiesInWorld, addList, removeList;
	private ReferenceFrame rf;
	private Color backgroundColor;
	
	@Override
	public void init(GameContainer gc, StateBasedGame gs)
			throws SlickException {
		entitiesInWorld = new ArrayList<Entity>();
		removeList = new ArrayList<Entity>();
		addList = new ArrayList<Entity>();
		
		rf = new ReferenceFrame(null, 1920, 1080);
		backgroundColor = new Color(0, 0, 0);
		
		init();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame gs, Graphics g)
			throws SlickException {
		//Render entities
		for(Entity e : entitiesInWorld)
			e.render(gc, gs, g);
		
		//Render background color
		g.setBackground(backgroundColor);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame gs, int d)
			throws SlickException {
		//Update entities
		for(Entity e : entitiesInWorld)
			e.update(gc, gs, d);
		
		//Add new entities from addList
		for(Entity e : addList)
			entitiesInWorld.add(e);
		
		//Remove entities in removeList from world
		for(Entity e : removeList)
			entitiesInWorld.remove(e);
		
		//Clear lists
		addList.clear();
		removeList.clear();
	}
	
	public void addObject(Entity e, float x, float y) {
		addList.add(e);
		
		e.setWorld(this);
		e.setLocation(new Vector(x, y));
	}
	
	public void removeObject(Entity e) {
		removeList.add(e);
	}
	
	public void removeObjects(@SuppressWarnings("rawtypes") Class cls) {
		for(Entity e : entitiesInWorld)
			if(e.getClass().equals(cls))
				removeList.add(e);
	}
	
	public void setBackgroundColor(Color c) {
		backgroundColor = c;
	}
	
	public void setReferenceFrame(ReferenceFrame rf) {
		this.rf = rf;
	}
	
	public ArrayList<Entity> getEntitiesInWorld() {
		return entitiesInWorld;
	}
	
	public ReferenceFrame getReferenceFrame() {
		return rf;
	}
	
	abstract public void init() throws SlickException;
}
