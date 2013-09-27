package BreakOut_Frame;

import BreakOut.TBreakOutEngine;
import BreakOut_Input.TInputUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;

public class TGameOverFrame extends TFrame{
	private TBreakOutEngine FEngine;
	
    private SpriteBatch FSpriteBatch;
    private BitmapFont FFont;
	
	public TGameOverFrame(TBreakOutEngine AEngine){
		FEngine = AEngine;
		
	    FSpriteBatch = new SpriteBatch();
	    FFont = new BitmapFont();
	}

	@Override
	public void Activate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateState() {
		if (TInputUtils.WasKeyJustPressed(Keys.ESCAPE)){
			FEngine.ActivateMainFrame();
			return;
		}
	}

	@Override
	public void RenderFrame() {
        Gdx.gl11.glPushMatrix();
        
        FSpriteBatch.begin();
        Gdx.gl11.glTranslatef(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
    	
        String t = "Game Over mate!";
    	TextBounds bounds = FFont.getBounds(t);
    	FFont.draw(FSpriteBatch, t, -bounds.width/2, bounds.height);
    	
        t = "Press the ESC key";
    	bounds = FFont.getBounds(t);
    	FFont.draw(FSpriteBatch, t, -bounds.width/2, -bounds.height);
    		
    	FSpriteBatch.end();
    	
    	Gdx.gl11.glPopMatrix();
	}

}
