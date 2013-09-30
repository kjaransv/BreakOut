package BreakOut_Level;

import java.util.ArrayList;

import BreakOut_GameObjects.*;

import com.badlogic.gdx.Gdx;

public class TGenerator {
	public static final int HUD_HEIGHT = 50;
	
	public static ArrayList<TGameObject> Level1(){
		ArrayList<TGameObject> obj = new ArrayList<TGameObject>();
		
		float EdgeWidth = 10;
		obj.add(new TEdge(0, 0, EdgeWidth, Gdx.graphics.getHeight()-HUD_HEIGHT));
		obj.add(new TEdge(Gdx.graphics.getWidth()-EdgeWidth, 0, EdgeWidth, Gdx.graphics.getHeight()-HUD_HEIGHT));
		obj.add(new TEdge(0, Gdx.graphics.getHeight()-EdgeWidth-HUD_HEIGHT, Gdx.graphics.getWidth(), EdgeWidth));
		
		float w = 39;
		float h = 39;
		
		//Generate Blocks for level 1
		for (int i=0; i<20; i++){
			for (int j=1; j<6; j++){
				obj.add(new TBrick(EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT, w, h));
			}
		}
		return obj;
	}
	
	public static ArrayList<TGameObject> Level2(){
		ArrayList<TGameObject> obj = new ArrayList<TGameObject>();
		
		float EdgeWidth = 10;
		obj.add(new TEdge(0, 0, EdgeWidth, Gdx.graphics.getHeight()-HUD_HEIGHT));
		obj.add(new TEdge(Gdx.graphics.getWidth()-EdgeWidth, 0, EdgeWidth, Gdx.graphics.getHeight()-HUD_HEIGHT));
		obj.add(new TEdge(0, Gdx.graphics.getHeight()-EdgeWidth-HUD_HEIGHT, Gdx.graphics.getWidth(), EdgeWidth));
		
		float w = 39;
		float h = 39;
		
		//Generate Blocks for level 2
		for (int i=0; i<20; i++){
			for (int j=1; j<6; j++){
				if (i==j || 19-i==j){
					obj.add(new TEdge(EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT, w, h));
				} else {
					obj.add(new TBrick(EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT, w, h));
				}
			}
		}
		return obj;
	}
	
	public static ArrayList<TGameObject> Level3(){
		ArrayList<TGameObject> obj = new ArrayList<TGameObject>();
		
		float EdgeWidth = 10;
		
		obj.add(new TRacket(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-EdgeWidth-HUD_HEIGHT, 60, 10));
		obj.add(new TRacketVertical(0, Gdx.graphics.getHeight()/2, 10, 60));
		obj.add(new TRacketVertical(Gdx.graphics.getWidth()-EdgeWidth, Gdx.graphics.getHeight()/2, 10, 60));
		
		float w = 39;
		float h = 39;
		
		//Generate Blocks for level 1
		for (int i=0; i<20; i++){
			for (int j=1; j<6; j++){
				if (i==j || 19-i==j){
					obj.add(new TEdge(EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT, w, h));
				} else {
					obj.add(new TBrick(EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT, w, h));
				}
			}
		}
		return obj;
	}


}
