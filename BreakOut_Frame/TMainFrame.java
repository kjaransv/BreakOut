package BreakOut_Frame;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import BreakOut.TBreakOutEngine;
import BreakOut_Input.TInputUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.BufferUtils;

public class TMainFrame extends TFrame{
	private TBreakOutEngine FEngine;
	private int FSelection;
	private String[] FMenuItems;
	private boolean[] FMenuItems_Active;
	
	public TMainFrame(TBreakOutEngine AEngine){
		FEngine = AEngine;
		FSelection = 0;
		FMenuItems = new String[]{
        		"Continue",
        		"New Game",
        		"High Score",
        		"",
        		"Exit"
        };
		FMenuItems_Active = new boolean[]{
			false,
			true,
			false, // TODO highscore disabled, implement score and re-enable
			false,
			true
		};
	}
	
	@Override
	public void UpdateState(){
		if (TInputUtils.WasKeyJustPressed(Keys.W) | TInputUtils.WasKeyJustPressed(Keys.UP)){
			do{
				FSelection = --FSelection % FMenuItems.length;
				if (FSelection<0) FSelection+= FMenuItems.length;
			} while (!FMenuItems_Active[FSelection]);
		}
		if (TInputUtils.WasKeyJustPressed(Keys.S) | TInputUtils.WasKeyJustPressed(Keys.DOWN)){
			do{
				FSelection = ++FSelection % FMenuItems.length;
			} while (!FMenuItems_Active[FSelection]);
		}
		if (TInputUtils.WasKeyJustPressed(Keys.SPACE) | TInputUtils.WasKeyJustPressed(Keys.ENTER)){
			switch (FSelection){
				case 0: FEngine.ActivateGameFrame(false); break; //continue
				case 1: FEngine.ActivateGameFrame(true); break; //new game
				case 2: FEngine.ActivateHighScoreFrame(); break; //high score
				case 4: Gdx.app.exit(); break; //exit
			}
		}
	}
	
	@Override
	public void RenderFrame(){
	    SpriteBatch FSpriteBatch = new SpriteBatch();
	    BitmapFont FFont = new BitmapFont();
		FloatBuffer FVertexBuffer = BufferUtils.newFloatBuffer(8);

//object
        Gdx.gl11.glPushMatrix();
        
        FSpriteBatch.begin();
        Gdx.gl11.glTranslatef(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
    	for (int i=0; i<FMenuItems.length; i++){
    		if (!FMenuItems_Active[i]){
    			continue;
    		}
    		
    		Gdx.gl11.glPushMatrix();
    		
    		TextBounds bounds = FFont.getBounds(FMenuItems[i]);
    		float y = bounds.height*(FMenuItems.length/2-i);
    		FFont.draw(FSpriteBatch, FMenuItems[i], -bounds.width/2, y);
    		
    		if (i==FSelection){
    		    Gdx.gl11.glColor4f(0, 1, 1, 1);
    		    FVertexBuffer.put(new float[] {0, 0, 0, bounds.height, bounds.height, 0, bounds.height, bounds.height});
    		    FVertexBuffer.rewind();
    		    
    	    	Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, FVertexBuffer);
    	    	Gdx.gl11.glTranslatef(-bounds.width/2-2*bounds.height, y-bounds.height, 0);
    	    	Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);		
    		}
    		
    		Gdx.gl11.glPopMatrix();
    	}
    	FSpriteBatch.end();
    	
    	Gdx.gl11.glPopMatrix();
	}

	@Override
	public void Activate() {
		FMenuItems_Active[0] = FEngine.GameInProgress();
		if (FMenuItems_Active[0]){
			FSelection = 0;
		} else {
			FSelection = 1;
		}
	}
}
