package BreakOut_GameObjects;

import java.nio.FloatBuffer;

import com.badlogic.gdx.utils.BufferUtils;

public abstract class TGameObject {
	protected FloatBuffer FVertexBuffer = BufferUtils.newFloatBuffer(8);

	protected boolean FDead, FIndestructible;
	
	public float FX, FY;
	public float FWidth, FHeight;
	public float FSpeedX, FSpeedY;
	
	public abstract void UpdateState();	
	public abstract void Render();
	
	public TGameObject(float AX, float AY, float AWidth, float AHeight){    
		FX = AX;
		FY = AY;
		FWidth = AWidth;
		FHeight = AHeight;
		
	    FVertexBuffer.put(new float[] {0, 0, 0, FHeight, FWidth, 0, FWidth, FHeight});
	    FVertexBuffer.rewind();
	}
	
	public boolean Intersects(TGameObject AObject){
		boolean intersects = 
				FX<AObject.FX+AObject.FWidth &&
				FX+FWidth>AObject.FX &&
				FY<AObject.FY+AObject.FHeight &&
				FY+FHeight>AObject.FY;
		if (intersects){
			float dx = Math.max(Math.max(Math.abs((FX+FWidth/2)-(AObject.FX+AObject.FWidth/2)), FWidth), AObject.FWidth);
			float dy = Math.max(Math.max(Math.abs((FY+FHeight/2)-(AObject.FY+AObject.FHeight/2)), FHeight), AObject.FHeight);
			
			float tmp = dx*FSpeedY-dy*FSpeedX;
			if (tmp<=0){
				FSpeedY = -FSpeedY;
			}
			if (tmp>=0){
				FSpeedX = -FSpeedX;
			}
			
			// bounce
			/*if (FSpeedX<0){
				FX-= dx; 
			} else {
				FX+= dx;
			}
			if (FSpeedX<0){
				FY-= dy; 
			} else {
				FY+= dy;
			}*/
		}
		return intersects;
	}
	
	public boolean IsIndestructible(){
		return FIndestructible;
	}
	
	public void KillObject(){
		if (!FIndestructible){
			FDead = true;
		}
	}
	
	public boolean IsDead(){
		return FDead;
	}
}
