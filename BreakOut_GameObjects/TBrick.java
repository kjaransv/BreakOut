package BreakOut_GameObjects;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

public class TBrick extends TGameObject{
	private FloatBuffer FVertexBuffer = BufferUtils.newFloatBuffer(8);
	private FloatBuffer FVertexBuffer_Border = BufferUtils.newFloatBuffer(8);
	
	private float FColor_R, FColor_G, FColor_B;
	
	public TBrick(float AX, float AY, float AWidth, float AHeight, float ARed, float AGreen, float ABlue){
		super(AX, AY, AWidth, AHeight);
		
		FColor_R = ARed;
		FColor_G = AGreen;
		FColor_B = ABlue;
		
	    FVertexBuffer.put(new float[] {0, 0, 0, FHeight-2, FWidth-2, 0, FWidth-2, FHeight-2});
	    FVertexBuffer.rewind();
	    FVertexBuffer_Border.put(new float[] {0, 0, 0, FHeight, FWidth, 0, FWidth, FHeight});
	    FVertexBuffer_Border.rewind();
	}

	@Override
	public void UpdateState() {
	
	}

	@Override
	public void Render() {
		Gdx.gl11.glPushMatrix();
			    
    	Gdx.gl11.glTranslatef(FX, FY, 0);

    	Gdx.gl11.glColor4f(1-FColor_R, 1-FColor_G, 1-FColor_B, 1);
    	Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, FVertexBuffer_Border);
    	Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);

    	Gdx.gl11.glTranslatef(1, 1, 0);
	    Gdx.gl11.glColor4f(FColor_R, FColor_G, FColor_B, 1);
    	Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, FVertexBuffer);
    	Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);

    	Gdx.gl11.glPopMatrix();
	}

}
