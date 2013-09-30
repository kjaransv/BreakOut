package BreakOut_Level;

import java.util.ArrayList;

import BreakOut_GameObjects.*;

import com.badlogic.gdx.Gdx;

public class TGenerator {
	// creates an array of TGameObject that is used to represent a level
	
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
				obj.add(new TBrick(
					EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT,
					w, h,
					0, 1, 1
				));
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
					obj.add(new TEdge(
						EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT, w, h));
				} else {
					obj.add(new TBrick(
						EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT,
						w, h,
						0, 1, 1
					));
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
		
		//Generate Blocks for level 3
		for (int i=0; i<20; i++){
			for (int j=1; j<6; j++){
				if (i==j || 19-i==j){
					obj.add(new TEdge(EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT, w, h));
				} else {
					obj.add(new TBrick(
						EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT,
						w, h,
						0, 1, 1
					));
				}
			}
		}
		return obj;
	}

	public static ArrayList<TGameObject> Level4(){
		ArrayList<TGameObject> obj = new ArrayList<TGameObject>();
		
		float EdgeWidth = 10;
		
		obj.add(new TRacket(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-EdgeWidth-HUD_HEIGHT, 60, 10));
		obj.add(new TRacketVertical(0, Gdx.graphics.getHeight()/2, 10, 60));
		obj.add(new TRacketVertical(Gdx.graphics.getWidth()-EdgeWidth, Gdx.graphics.getHeight()/2, 10, 60));
		
		float w = 39;
		float h = 39;
		
		//Generate Blocks for level 4	
		float x;
		float y;
		float width;
		float height;
		
		
		
		x = EdgeWidth+2;
		y = Gdx.graphics.getHeight()-EdgeWidth-HUD_HEIGHT-h*5+2;
		width = w*10-4;
		height = h*5-4;
		obj.add(new TEdge(x+width, y, 2, height));
		obj.add(new TBrick(x, y, width, height, 1f, 0.75f, 0));
		obj.add(new TBrick(x+width+2, y, width, height, 1f, 0.75f, 0));

		//obj.add(new TBrick(EdgeWidth, Gdx.graphics.getHeight()-EdgeWidth-HUD_HEIGHT-h*5, w*20, h*5, 1, 1, 0));
		
		/*for (int i=0; i<20; i++){
			for (int j=1; j<6; j++){
				if (i==j || 19-i==j){
					obj.add(new TEdge(EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT, w, h));
				} else {
					obj.add(new TBrick(EdgeWidth+i*w, Gdx.graphics.getHeight()-EdgeWidth-j*h-HUD_HEIGHT, w, h));
				}
			}
		}*/
		return obj;
	}

}
