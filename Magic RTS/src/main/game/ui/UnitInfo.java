package main.game.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.entities.Entity;
import main.entities.SelectableEntity;
import main.game.player.Player;

public class UnitInfo extends UIElement {

	private Player player;
	
	
	public UnitInfo(UI ui, Point pos) {
		super(ui, pos);
		this.player = ui.getPlayer();
	}

	@Override
	public void render(Graphics g) {
		boolean singleSelected = (player.getSelected() != null);
		boolean groupSelected = (player.getSelectedGroup().size() != 0);
		
		float marginX = 24;
		float marginY = 24;
		
		if(singleSelected) {
			SelectableEntity selected = (SelectableEntity)player.getSelected();
			
			float x = pos.getX() + marginX;
			float y = pos.getY() + marginY;
			g.drawImage(selected.getSprite(), x, y);
		} else if(groupSelected) {
			for(int i = 0; i < player.getSelectedGroup().size(); i++) {
				Entity e = player.getSelectedGroup().get(i);
				
				float x = pos.getX() + (e.getSprite().getWidth() + marginX) * i;
				float y = pos.getY() + marginY;
				
				g.drawImage(e.getSprite(), x, y);
			}
		}
	}

	@Override
	public void tick() {
		
	}

	
	
}
