package BreakOut_GameObjects;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

public class TEdge extends TGameObject{
	private FloatBuffer FVertexBuffer = BufferUtils.newFloatBuffer(8);
	
	public TEdge(float AX, float AY, float AWith, float AHeight){
		super(AX, AY, AWith, AHeight);
		
	    FVertexBuffer.put(new float[] {0, 0, 0, FHeight, FWidth, 0, FWidth, FHeight});
	    FVertexBuffer.rewind();
	    
	    FIndestructible = true;
	}

	@Override
	public void UpdateState() {
	}

	@Override
	public void Render() {
		Gdx.gl11.glPushMatrix();
	    
    	Gdx.gl11.glTranslatef(FX, FY, 0);

	    Gdx.gl11.glColor4f(0.5f, 0.5f, 0.5f, 1);
    	Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, FVertexBuffer);
    	Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);

    	Gdx.gl11.glPopMatrix();
	}

}
