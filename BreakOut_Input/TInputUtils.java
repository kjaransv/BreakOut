package BreakOut_Input;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;

public class TInputUtils {
	private static HashMap<Integer, Boolean> FPressedKeys = new HashMap<Integer, Boolean>();
	
	public static void Reset(){
		FPressedKeys = new HashMap<Integer, Boolean>();
	}
	
	public static boolean WasKeyJustPressed(int AKey){
		// remembers if a given key is pressed
		// returns true if the key is pressed but was previously not
		
		if (Gdx.input.isKeyPressed(AKey)){
			if (!FPressedKeys.containsKey(AKey) || !FPressedKeys.get(AKey)){
				FPressedKeys.put(AKey, true);
				return true;
			}
		} else {
			FPressedKeys.put(AKey, false);
		}
		return false;
	}
}
