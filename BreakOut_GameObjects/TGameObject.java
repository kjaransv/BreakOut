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
