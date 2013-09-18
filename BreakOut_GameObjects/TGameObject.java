package BreakOut_GameObjects;

public abstract class TGameObject {
	private boolean FDead;
	public float FX, FY;
	public float FWidth, FHeight;
	public float FSpeedX, FSpeedY;
	
	public abstract void UpdateState();	
	public abstract void Render();
	
	public TGameObject(float AX, float AY, float AWidth, float AHeight){
		FX = AX;
		FY = AY;
		FWidth = AWidth;
		FHeight = AHeight;
	}
	
	public boolean Intersects(TGameObject AObject){
		boolean intersects = 
				FX<AObject.FX+AObject.FWidth &&
				FX+FWidth>AObject.FX &&
				FY<AObject.FY+AObject.FHeight &&
				FY+FHeight>AObject.FY;
		if (intersects){
			float dx = Math.max(Math.max(Math.abs((FX+FWidth/2)-(AObject.FX+AObject.FWidth/2)), FWidth), AObject.FWidth);
			float dy = Math.max(Math.max(Math.abs((FY+FHeight/2)-(AObject.FY+AObject.FHeight/2)), FHeight), AObject.FHeight);
			
			float tmp = dx*FSpeedY-dy*FSpeedX;
			if (tmp<=0){
				FSpeedY = -FSpeedY;
			}
			if (tmp>=0){
				FSpeedX = -FSpeedX;
			}
			
			// bounce
			/*if (FSpeedX<0){
				FX-= dx; 
			} else {
				FX+= dx;
			}
			if (FSpeedX<0){
				FY-= dy; 
			} else {
				FY+= dy;
			}*/
		}
		return intersects;
	}
	
	public void KillObject(){
		FDead = true;
	}
	
	public boolean IsDead(){
		return FDead;
	}
}
