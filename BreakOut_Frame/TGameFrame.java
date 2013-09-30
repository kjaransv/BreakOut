package BreakOut_Frame;

import java.util.ArrayList;
import java.util.List;

import BreakOut.TBreakOutEngine;
import BreakOut_GameObjects.TBall;
import BreakOut_GameObjects.TGameObject;
import BreakOut_GameObjects.TRacket;
import BreakOut_Input.TInputUtils;
import BreakOut_Level.TGenerator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;

public class TGameFrame extends TFrame{
	private TBreakOutEngine FEngine;
	
	private boolean FPaused;
	
    private SpriteBatch FSpriteBatch = new SpriteBatch();
    private BitmapFont FFont = new BitmapFont();
	
	private List<TGameObject> FGameObjects;
	private TRacket FRacket;
	private TBall FBall;
	private int FLives;
	private boolean FGameOver;
	private float FSpeedXOriginal = 1;
	
	private float FWidthOfScreen = Gdx.graphics.getWidth();
	private float FHeightOfScreen = Gdx.graphics.getHeight();
	
	public TGameFrame(TBreakOutEngine AEngine){
		FEngine = AEngine;
		
		FRacket = new TRacket(0, 50, 60, 10);
		FBall = new TBall(10, 50, 10);
	
		Reset();
		
		FGameOver = true;
	}
	
	private void Test(){
		// TODO see comment
		// also determine between objects what object is hit first (or even same time)
		// find delta time and move ball accordingly and then see if there is another
		// collision in the remaining time frame
		
		// find ball constants
		float cx = FBall.FX+FBall.FWidth;
		float cy = FBall.FY+FBall.FHeight;
		
		float m = FBall.FSpeedY/FBall.FSpeedX;
		float k = cy-m*cx;
		
		// for each object
		for (int i=0; i<FGameObjects.size(); i++){
			TGameObject obj = FGameObjects.get(i);
			
			// expand target object, thus shrinking the ball to 0 size
			float b_width = FBall.FWidth+obj.FWidth;
			float b_height = FBall.FHeight+obj.FHeight;
			
			// calculate when each of the object lines will intersect with the ball vector
			float collision_left = m*obj.FX+k;
			float collision_right = m*(obj.FX+b_width)+k;
			float collision_bottom = (obj.FY-k)/m;
			float collision_top = (obj.FY+b_height-k)/m;;
			
			// determine if there is an actual collision and then at what time
			float[] collision_time = {1, 1, 1, 1};
			if (obj.FY <= collision_left && collision_left <= obj.FY+b_height) {
				collision_time[0] = (collision_left-cy)/FBall.FSpeedY;
			}
			if (obj.FY <= collision_right && collision_right <= obj.FY+b_height){
				collision_time[1] = (collision_right-cy)/FBall.FSpeedY;
			}
			if (obj.FX <= collision_bottom && collision_bottom <= obj.FX+b_width){
				collision_time[2] = (collision_bottom-cx)/FBall.FSpeedX;
			}
			if (obj.FX <= collision_top && collision_top <= obj.FX+b_width){
				collision_time[3] = (collision_top-cx)/FBall.FSpeedX;
			}
			
			// new speed, depending on collision
			int min = 0;
			for (int j=1; j<collision_time.length; j++){
				if (collision_time[j]>=0 && collision_time[j]<collision_time[min]){
					min = j;
				}
			}
			if (collision_time[min]>=0 && collision_time[min]<1){
				switch (min){
					// side hit
					case 0:
					case 1: FBall.FSpeedX*= -1; break;
					// top/bot hit
					case 2:
					case 3: FBall.FSpeedY*= -1; break;
				}
				System.out.println(collision_time[min]);
				obj.KillObject();
				FBall.IncreaseSpeed();
				
				if (obj == FRacket){
					CheckRacketCollision();
				}
			}
			
			
			/*
			if (
					(obj.FY <= collision_left && collision_left <= obj.FY+b_height && collision_left-cy<FBall.FSpeedY) ||
					(obj.FY <= collision_right && collision_right <= obj.FY+b_height && collision_right-cy<FBall.FSpeedY) ||
					(obj.FX <= collision_bottom && collision_bottom <= obj.FX+b_width && collision_bottom-cx<FBall.FSpeedX) ||
					(obj.FX <= collision_top && collision_top <= obj.FX+b_width && collision_top-cx<FBall.FSpeedX)
			){
				obj.KillObject();
			}
			*/
		}
	}
	
	private int FLevel;
	private void CheckAndSetNextLevel(){		
		// check if there are destructible object in the list
		for (int i=0; i<FGameObjects.size(); i++){
			if (!FGameObjects.get(i).IsIndestructible()){
				return;
			}
		}
		
		// generate next level
		switch (FLevel){
			case 0: FGameObjects = TGenerator.Level1(); break;
			case 1: FGameObjects = TGenerator.Level2(); break;
			case 2: FGameObjects = TGenerator.Level3(); break;
			case 3: FGameObjects = TGenerator.Level4(); break;
			
			default: FGameOver = true; return;
		}
		FLevel++;
		
		// reward player with an extra life
		FLives++;
		
		// reset ball / racket
		FGameObjects.add(FRacket);
		FRacket.FX = Gdx.graphics.getWidth()/2;
		FRacket.FY = 10;
		
		FBall.Reset();
		FBall.FX = FRacket.FX+FRacket.FWidth/2;
		FBall.FY = FRacket.FY+FRacket.FHeight+1;
		
		FPaused = true;
	}
	
	public void Reset(){
		FGameObjects = new ArrayList<TGameObject>();
		FGameOver = false;
		FLives = 1;
		
		FLevel = 0;
		CheckAndSetNextLevel();
	}
	
	@Override
	public void Activate() {
		FPaused = true;
	}
	
	/**
	 * A method that detects if the ball collides with the left,right or
	 * top edge of the screen.
	 */
	public void CheckEdgeCollision()
	{
		if(FBall.FX < 0 + FBall.FWidth || FBall.FX > FWidthOfScreen - FBall.FWidth)
		{
			FBall.FSpeedX *= -1;
		}
		else if(FBall.FY > FHeightOfScreen - FBall.FHeight)
		{
			FBall.FSpeedY *= -1;
		}
		else return;
	}
	
	/**
	 * A method that detects where the ball collides with the racket and
	 * updates the FSpeedX accordingly
	 */
	public void CheckRacketCollision()
	{
		if(FBall.FY < FRacket.FY)
		{
			return;
		}
		
		if(FBall.FY < 70 && FBall.FY < FRacket.FY + FRacket.FHeight)
		{				
			//position of collision |x|-|-|-|-|-|-|-|-|-|-|-|
			if(FBall.FX > FRacket.FX && FBall.FX < FRacket.FX + FRacket.FWidth - 55)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= -2.2;
			}
			//position of collision |-|x|-|-|-|-|-|-|-|-|-|-|
			else if(FBall.FX > FRacket.FX + 5 && FBall.FX < FRacket.FX + FRacket.FWidth - 50)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= -1.5;
			}
			//position of collision |-|-|x|-|-|-|-|-|-|-|-|-|
			else if(FBall.FX > FRacket.FX + 10 && FBall.FX < FRacket.FX + FRacket.FWidth - 45)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= -1;
			}
			//position of collision |-|-|-|x|-|-|-|-|-|-|-|-|
			else if(FBall.FX > FRacket.FX + 15 && FBall.FX < FRacket.FX + FRacket.FWidth - 40)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= -0.8;
			}
			//position of collision |-|-|-|-|x|-|-|-|-|-|-|-|
			else if(FBall.FX > FRacket.FX + 20 && FBall.FX < FRacket.FX + FRacket.FWidth - 35)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= -0.6;
			}
			//position of collision |-|-|-|-|-|x|-|-|-|-|-|-|
			else if(FBall.FX > FRacket.FX + 25 && FBall.FX < FRacket.FX + FRacket.FWidth - 30)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= -0.2;
			}
			//position of collision |-|-|-|-|-|-|x|-|-|-|-|-|
			else if(FBall.FX > FRacket.FX + 30 && FBall.FX < FRacket.FX + FRacket.FWidth - 25)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= 0.2;
			}
			//position of collision |-|-|-|-|-|-|-|x|-|-|-|-|
			else if(FBall.FX > FRacket.FX + 35 && FBall.FX < FRacket.FX + FRacket.FWidth - 20)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= 0.6;
			}
			//position of collision |-|-|-|-|-|-|-|-|x|-|-|-|
			else if(FBall.FX > FRacket.FX + 40 && FBall.FX < FRacket.FX + FRacket.FWidth - 15)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= 0.8;
			}
			//position of collision |-|-|-|-|-|-|-|-|-|x|-|-|
			else if(FBall.FX > FRacket.FX + 45 && FBall.FX < FRacket.FX + FRacket.FWidth - 10)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= 1;
			}
			//position of collision |-|-|-|-|-|-|-|-|-|-|x|-|
			else if(FBall.FX > FRacket.FX + 50 && FBall.FX < FRacket.FX + FRacket.FWidth - 5)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= 1.5;
			}
			//position of collision |-|-|-|-|-|-|-|-|-|-|-|x|
			else if(FBall.FX > FRacket.FX + 55 && FBall.FX < FRacket.FX + FRacket.FWidth)
			{
				FBall.FSpeedY *= -1;
				FBall.FSpeedX = FSpeedXOriginal;
				FBall.FSpeedX *= 2.2;
			}
		
		
		}
	}
	
	/**
	 * Method checks if the racket collides with either the left or right edge of screen
	 */
	public void CheckRacketEdgeCollision()
	{
		if(FRacket.FX < 0)
		{
			FRacket.FX = 0;
		}
		else if(FRacket.FX > FWidthOfScreen - FRacket.FWidth)
		{
			FRacket.FX = FWidthOfScreen - FRacket.FWidth;
		}
		else return;
	}
	
	/**
	 * Method checks if the ball has hit a brick
	 */
	public void CheckBallBrickCollision()
	{
		for(int i = 0; i < FGameObjects.size(); i++)
		{
			
			TGameObject brick = FGameObjects.get(i);
			//System.out.println("Brick y: " + brick.FY);	
			//System.out.println("Ball y: " + FBall.FY);
			if (FBall.FX > brick.FX && FBall.FX < brick.FX + brick.FWidth
					&& FBall.FY + FBall.FHeight > brick.FY && FBall.FY < brick.FY + brick.FHeight){
				brick.KillObject();
				FBall.FSpeedY *= -1;
			}
		}
	}
	
	public boolean CheckBoundaries(TGameObject brick)
	{
		if(FBall.FY > brick.FY && FBall.FY < brick.FY + brick.FHeight)
		{
			//System.out.println("TRUE");
			return true;
		}
		//System.out.println("******FALSE");
		return false;
	}

	@Override
	public void UpdateState() {
		if (TInputUtils.WasKeyJustPressed(Keys.ESCAPE)){
			FEngine.ActivateMainFrame();
			return;
		}
		
		if (FGameOver){
			return;
		}
		
		if (FPaused){
			if (TInputUtils.WasKeyJustPressed(Keys.SPACE)){
				FPaused = false;
			} else {
				return;
			}
		}
		
		Test();
		
		/*
		//Check for edge/ball or ceiling collision
		CheckEdgeCollision();
		
		//Check for Racket/ball collision
		CheckRacketCollision();
		
		//Check for edge/racket collision
		CheckRacketEdgeCollision();
		
		//Check for ball/brick collision
		CheckBallBrickCollision();
		*/
		
		// update state of all objects
		//FRacket.UpdateState();

		for (int i=0; i<FGameObjects.size(); i++){
			FGameObjects.get(i).UpdateState();
		}
			
		FBall.UpdateState();
		
		// remove all dead objects
		for (int i=FGameObjects.size()-1; i>=0; i--){
			if (FGameObjects.get(i).IsDead()){
				FGameObjects.remove(i);
			}
		}
		
		// check end state, ball is dead
		if (FBall.IsDead()){
			FLives--;
			if (FLives<0){
				FGameOver = true;
			} else {
				FBall.Reset();
				FBall.FX = FRacket.FX+FRacket.FWidth/2;
				FBall.FY = FRacket.FY+FRacket.FHeight;
				FPaused = true;
			}
		}
		
		// check win state, all bricks are dead
		CheckAndSetNextLevel();
	}

	private void RenderHUD(){
        String t = "Level "+FLevel;
    	TextBounds bounds = FFont.getBounds(t);
    	FFont.draw(FSpriteBatch, t, 10, Gdx.graphics.getHeight()-bounds.height);
    	
        t = "Lives "+FLives;
    	bounds = FFont.getBounds(t);
    	FFont.draw(FSpriteBatch, t, 10, Gdx.graphics.getHeight()-bounds.height*2-4);
    	
    	if (FPaused){
            t = "[Press the spacebar to start]";
        	bounds = FFont.getBounds(t);
        	FFont.draw(FSpriteBatch, t, (Gdx.graphics.getWidth()-bounds.width)/2, Gdx.graphics.getHeight()-bounds.height*2);
    	}
	}
	
	@Override
	public void RenderFrame() {
		Gdx.gl11.glPushMatrix();
		
		FBall.Render();
		//FRacket.Render();
		
		for (int i=0; i<FGameObjects.size(); i++){
			FGameObjects.get(i).Render();
		}
		
        FSpriteBatch.begin();
        
        RenderHUD();

        if (FGameOver){
	       // Gdx.gl11.glPushMatrix();
	        
	        Gdx.gl11.glTranslatef(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
	    	
	        String t = "Game Over mate!";
	        if (FLives>=0) {
	        	t = "Victory!";
	        }
	    	TextBounds bounds = FFont.getBounds(t);
	    	FFont.draw(FSpriteBatch, t, -bounds.width/2, bounds.height);
	    	
	        t = "Press the ESC key";
	    	bounds = FFont.getBounds(t);
	    	FFont.draw(FSpriteBatch, t, -bounds.width/2, -bounds.height);
	    	
	    //	Gdx.gl11.glPopMatrix();
        }
    		
    	FSpriteBatch.end();
		
		Gdx.gl11.glPopMatrix();
	}

	public boolean InProgress(){
		return !FGameOver;
	}
}
