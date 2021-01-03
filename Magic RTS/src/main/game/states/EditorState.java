package main.game.states;

import org.newdawn.slick.Graphics;

import main.game.State;
import main.game.map.editor.Editor;

public class EditorState extends State {

	private Editor editor;
	
	public EditorState() {
		super("Editor");
	}

	@Override
	public void init() {
		if(editor != null)
			return;
		editor = new Editor(null);
	}

	@Override
	public void draw(Graphics g) {
		
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

}
