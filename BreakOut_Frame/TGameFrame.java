// Ball bounce off objects
// multiple hits in one
// different angle when hitting racket
// levels
// status bar, lives level etc

// TODO TODOS

package BreakOut_Frame;

import java.util.ArrayList;
import java.util.List;

import BreakOut.TBreakOutEngine;
import BreakOut_GameObjects.TBall;
import BreakOut_GameObjects.TBrick;
import BreakOut_GameObjects.TEdge;
import BreakOut_GameObjects.TGameObject;
import BreakOut_GameObjects.TRacket;
import BreakOut_Input.TInputUtils;
import BreakOut_Level.TGenerator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class TGameFrame extends TFrame{
	private TBreakOutEngine FEngine;
	
	private List<TGameObject> FGameObjects;
	private TRacket FRacket;
	private TBall FBall;
	private int FLives;
	private boolean FGameOver;
	
	public TGameFrame(TBreakOutEngine AEngine){
		FEngine = AEngine;
		
		FRacket = new TRacket(0, 50, 60, 10);
		FBall = new TBall(10, 50, 10);

		Reset();
	}
	
	public void Reset(){
		FGameObjects = TGenerator.Level1();
		FGameOver = false;
		FLives = 2;
		
		FBall.Reset(FRacket);
		// TODO set level 1 reset racket and ball
	}
	
	@Override
	public void Activate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateState() {
		if (TInputUtils.WasKeyJustPressed(Keys.ESCAPE)){
			FEngine.Main();
			return;
		}
		
		FRacket.UpdateState();
		
		/*for (int i=0; i<FGameObjects.size(); i++){
			FGameObjects.get(i).UpdateState();
		}*/
		
		// Racket
		/*if (FRacket.Intersects(FEdgeLeft)){
			FRacket.FX = FEdgeLeft.FX+FEdgeLeft.FWidth;
		} else if (FRacket.Intersects(FEdgeRight)){
			FRacket.FX = FEdgeRight.FX-FRacket.FWidth;
		}*/

		// Ball		
		for (int i=0; i<FGameObjects.size(); i++){
			TGameObject obj = FGameObjects.get(i);
			if (FBall.Intersects(obj)){
				obj.KillObject();
				FBall.IncreaseSpeed();
			}
		}
		FBall.UpdateState();
		
		for (int i=FGameObjects.size()-1; i>0; i--){
			if (FGameObjects.get(i).IsDead()){
				FGameObjects.remove(i);
			}
		}
		
		if (FBall.IsDead()){
			FLives--;
			if (FLives<0){
				FGameOver = true;
			} else {
				FBall.Reset(FRacket);
			}
		}
	}

	@Override
	public void RenderFrame() {
		Gdx.gl11.glPushMatrix();
		
		FBall.Render();
		FRacket.Render();
		
		for (int i=0; i<FGameObjects.size(); i++){
			FGameObjects.get(i).Render();
		}
		
		Gdx.gl11.glPopMatrix();
	}

	public boolean InProgress(){
		return true; // TODO check game state
	}
}
