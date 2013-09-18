package BreakOut_GameObjects;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.BufferUtils;

public class TRacket extends TGameObject{
	private FloatBuffer FVertexBuffer = BufferUtils.newFloatBuffer(8);
	private int FSpeed = 6;
	
	public TRacket(){
		super(0, 50, 60, 10);
		
	    FVertexBuffer.put(new float[] {0, 0, 0, FHeight, FWidth, 0, FWidth, FHeight});
	    FVertexBuffer.rewind();
	}

	@Override
	public void UpdateState() {
		if (Gdx.input.isKeyPressed(Keys.A) | Gdx.input.isKeyPressed(Keys.LEFT)){
			FX-= FSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.D) | Gdx.input.isKeyPressed(Keys.RIGHT)){
			FX+= FSpeed;
		}
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