package BreakOut;

import org.lwjgl.opengl.GL11;

import BreakOut_Frame.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class TBreakOutEngine implements ApplicationListener{
	private TFrame FActiveFrame;
	
	private TMainFrame FMain;
	private TGameFrame FGame;
	private THighScoreFrame FScore;
	private TGameOverFrame FGameOver;
	
	@Override
	public void create() {
		FMain = new TMainFrame(this);
		FGame = new TGameFrame(this);
		FScore = new THighScoreFrame(this);
		FGameOver = new TGameOverFrame(this);

		ActivateMainFrame();
	}
	
	private void ActivateFrame(TFrame AFrame){
		FActiveFrame = AFrame;
		FActiveFrame.Activate();
	}
	
	public void ActivateMainFrame(){
		ActivateFrame(FMain);
	}
	
	public void ActivateGameOverFrame(){
		ActivateFrame(FGameOver);
	}
	
	public void ActivateGameFrame(boolean AReset){
		ActivateFrame(FGame);
		if (AReset){
			FGame.Reset();
		}
	}
	
	public void ActivateHighScoreFrame(){
		ActivateFrame(FScore);
	}
	
	public boolean GameInProgress(){
		return FGame.InProgress();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void render() {
		FActiveFrame.UpdateState();
		
	//create
        // Enable vertex array.
        Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
//resize
		//Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
		//Gdx.gl11.glLoadIdentity();
        Gdx.gl11.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.glu.gluOrtho2D(Gdx.gl11, 0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight());
//render
		Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW); //GL_PROJECTION
        Gdx.gl11.glLoadIdentity();
        FActiveFrame.RenderFrame();
	}

	@Override
	public void resize(int AWidth, int AHeight) {
		
	}

	@Override
	public void resume() {
	}
}
