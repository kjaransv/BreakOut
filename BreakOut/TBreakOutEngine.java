package BreakOut;

import org.lwjgl.opengl.GL11;

import BreakOut_Frame.TGameFrame;
import BreakOut_Frame.THighScoreFrame;
import BreakOut_Frame.TMainFrame;
import BreakOut_Frame.TFrame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class TBreakOutEngine implements ApplicationListener{
	private TFrame FActiveFrame;
	
	private TMainFrame FMain;
	private TGameFrame FGame;
	private THighScoreFrame FScore;
	
	@Override
	public void create() {
		FMain = new TMainFrame(this);
		FGame = new TGameFrame(this);
		FScore = new THighScoreFrame();	
		
		//NewGame();
		Main();
	}
	
	public void Main(){
		FActiveFrame = FMain;
		FActiveFrame.Activate();
	}
	
	public void NewGame(){
		FActiveFrame = FGame;
		FGame.Reset();
		FActiveFrame.Activate();
	}
	
	public void Continue(){
		FActiveFrame = FGame;
		FActiveFrame.Activate();
	}
	
	public void HighScore(){
		FActiveFrame = FScore;
		FActiveFrame.Activate();
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
