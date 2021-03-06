package BreakOut_GameObjects;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class TRacket extends TGameObject{	
	public TRacket(float AX, float AY, float AWidth, float AHeight) {
		super(AX, AY, AWidth, AHeight);
		
		FIndestructible = true;
	}

	private int FSpeed = 8;

	@Override
	public void UpdateState() {
		FSpeedX = 0;
		if (Gdx.input.isKeyPressed(Keys.A) | Gdx.input.isKeyPressed(Keys.LEFT)){
			FSpeedX-= FSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.D) | Gdx.input.isKeyPressed(Keys.RIGHT)){
			FSpeedX+= FSpeed;
		}
	
		FX+= FSpeedX;
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