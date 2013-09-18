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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class TGameFrame extends TFrame{
	private TBreakOutEngine FEngine;
	
	private List<TGameObject> FGameObjects;
	private TRacket FRacket;
	private TBall FBall;

	private TEdge FEdgeLeft, FEdgeRight, FEdgeTop;
	
	public TGameFrame(TBreakOutEngine AEngine){
		FEngine = AEngine;
		
		FRacket = new TRacket();
		FBall = new TBall(10, 50, 10, 1f, 1f);
		
		FGameObjects = new ArrayList<TGameObject>();
		FGameObjects.add(FRacket);
		FGameObjects.add(FBall);
		
		float EdgeWidth = 10;
		FEdgeLeft = new TEdge(0, 0, EdgeWidth, Gdx.graphics.getHeight());
		FEdgeRight = new TEdge(Gdx.graphics.getWidth()-EdgeWidth, 0, EdgeWidth, Gdx.graphics.getHeight());
		FEdgeTop = new TEdge(0, Gdx.graphics.getHeight()-EdgeWidth, Gdx.graphics.getWidth(), EdgeWidth);
		
		float w = 39;
		float h = 39;
		for (int i=0; i<20; i++){
			for (int j=1; j<6; j++){
				FGameObjects.add(new TBrick(EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h, w, h));
			}
		}
	}
	
	public void Reset(){
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
		for (int i=2; i<FGameObjects.size(); i++){ // TODO check loop if multiple balls
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
	}

	@Override
	public void RenderMenu() {
		Gdx.gl11.glPushMatrix();
		
		FEdgeLeft.Render();
		FEdgeRight.Render();
		FEdgeTop.Render();
		
		for (int i=0; i<FGameObjects.size(); i++){
			FGameObjects.get(i).Render();
		}
		
		Gdx.gl11.glPopMatrix();
	}

	public boolean InProgress(){
		return true; // TODO check game state
	}
}
