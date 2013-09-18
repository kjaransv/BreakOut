package BreakOut;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class TRunner {
	public static void main(String[] args){
		new LwjglApplication(
				new TBreakOutEngine(), 
				"BreakOut", 
				800, 600,
				false
		);
	}
}
