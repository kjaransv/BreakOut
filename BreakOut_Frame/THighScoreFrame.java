package BreakOut_Frame;

import BreakOut.TBreakOutEngine;
import BreakOut_Input.TInputUtils;

import com.badlogic.gdx.Input.Keys;

public class THighScoreFrame extends TFrame{
	private TBreakOutEngine FEngine;

	public THighScoreFrame(TBreakOutEngine AEngine){
		FEngine = AEngine;
	}
	
	@Override
	public void Activate() {
	}

	@Override
	public void UpdateState() {
		if (TInputUtils.WasKeyJustPressed(Keys.ESCAPE)){
			FEngine.ActivateMainFrame();
			return;
		}
	}

	@Override
	public void RenderFrame() {
	}

}
