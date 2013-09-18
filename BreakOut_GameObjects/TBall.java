package BreakOut_GameObjects;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

public class TBall extends TGameObject{
	private FloatBuffer FVertexBuffer = BufferUtils.newFloatBuffer(8);

	public TBall(float AX, float AY, float ASize, float ASpeedX, float ASpeedY){
		super(AX, AY, ASize, ASize);
		FSpeedX = ASpeedX;
		FSpeedY = ASpeedY;

	    FVertexBuffer.put(new float[] {0, 0, 0, FHeight, FWidth, 0, FWidth, FHeight});
	    FVertexBuffer.rewind();
	}
	
	public void IncreaseSpeed(){
		if (FSpeedX<0){
			FSpeedX-= 0.1;
		} else {
			FSpeedX+= 0.1;
		}
		
		if (FSpeedY<0){
			FSpeedY-= 0.1;
		} else {
			FSpeedY+= 0.1;
		}
	}
	
	private float GetSlope(TGameObject AObject){
		float m = 0;
		if (AObject.FSpeedX!=0) m = AObject.FSpeedY/AObject.FSpeedX;
		return m;
	}
	
	private float GetB(TGameObject AObject, float ASlope){
		float b = AObject.FY-ASlope*AObject.FX;
		return b;
	}
	
	@Override
	public boolean Intersects(TGameObject AObject){
		//y=mx+b
		float m1 = GetSlope(this);
		float m2 = GetSlope(AObject);
		
		float b1 = GetB(this, m1);
		float b2 = GetB(AObject, m2);
		
		float x = (b1-b2)/(m2-m1);
		
		float w = FWidth/2+AObject.FWidth/2;
		
		float c = x-w;
		
		
		
		return false;
	}
	
	@Override
	public void UpdateState() {
		// move
		FX+= FSpeedX;
		FY+= FSpeedY;
		
		// check bounds
		/*if (FX+FWidth>Gdx.graphics.getWidth()){
			FX-= FX-Gdx.graphics.getWidth()+FWidth;		
			FSpeedX = -FSpeedX;
		} else if (FX<0){
			FX = -FX;
			FSpeedX = -FSpeedX;
		}
		
		if (FY+FHeight>Gdx.graphics.getHeight()){
			FY-= FY-Gdx.graphics.getHeight()+FHeight;
			FSpeedY = -FSpeedY;
		} else*/ if (FY<0){
			KillObject();
			//FY = -FY;
			//FSpeedY = -FSpeedY;
		}
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
