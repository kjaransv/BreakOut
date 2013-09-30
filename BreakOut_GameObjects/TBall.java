package BreakOut_GameObjects;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;

public class TBall extends TGameObject{
	public TBall(float AX, float AY, float ASize){
		super(AX, AY, ASize, ASize);
	}
	
	public void IncreaseSpeed(){
		FSpeedX*= 1.025;
		FSpeedY*= 1.025;
	}
		
	@Override
	public void UpdateState() {
		// move
		FX+= FSpeedX;
		FY+= FSpeedY;
		
		// check bounds
		// if ball is out of frame it dies
		if (
			FX<0 || FX>Gdx.graphics.getWidth() ||
			FY<0 || FY>Gdx.graphics.getHeight()
		){
			KillObject();
		}
	}

	public void Reset(){
		FSpeedX = 1.5f;
		FSpeedY = 1.5f;
				
		FDead = false;
	}
	
	@Override
	public void Render() {
		Gdx.gl11.glPushMatrix();
		
	    Gdx.gl11.glColor4f(1, 1, 1, 1);
	    
    	Gdx.gl11.glTranslatef(FX, FY, 0);

    	Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, FVertexBuffer);
    	Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
    	
    	Gdx.gl11.glPopMatrix();
	}

}
