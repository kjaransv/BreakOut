package BreakOut_GameObjects;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class TRacketVertical extends TGameObject{
	
	public TRacketVertical(float AX, float AY, float AWidth, float AHeight) {
		super(AX, AY, AWidth, AHeight);
		
		FIndestructible = true;
	}

	private int FSpeed = 8;

	@Override
	public void UpdateState() {
		FSpeedY = 0;
		if (Gdx.input.isKeyPressed(Keys.S) | Gdx.input.isKeyPressed(Keys.DOWN)){
			FSpeedY-= FSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.W) | Gdx.input.isKeyPressed(Keys.UP)){
			FSpeedY+= FSpeed;
		}
		
		FY+= FSpeedY;
	}

	@Override
	public void Render() {
		Gdx.gl11.glPushMatrix();
		
	    Gdx.gl11.glColor4f(1, 0, 1, 1);
	    
    	Gdx.gl11.glTranslatef(FX, FY, 0);

    	Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, FVertexBuffer);
    	Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
    	
    	Gdx.gl11.glPopMatrix();
	}
}