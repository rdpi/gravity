package dal.gravity;

public class GravityConstant implements GravityModel {
	
	private double g;
	
	public GravityConstant(double g) {
		this.g = g;
	}

	public double getGravitationalField(){
		return g;
	}
	
	public void setGravitationalField(double g){
		this.g = g;
	}
}