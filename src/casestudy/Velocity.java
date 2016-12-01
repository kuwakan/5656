package casestudy;

public class Velocity {

	private int s_Vr_;//垂直方向の動き
	private double vr_;//垂直方向の速さ

	private int deg_;//方位0～360
	private double vel_;//速さ

	//コンストラクタ
	Velocity(int s_Vr, double vr, double deg, double vel){
		this.s_Vr_ = s_Vr;
		this.vr_ = vr;
		this.deg_ = (int)deg;
		this.vel_ = vel;
	}

	public int getS_Vr() {
		return s_Vr_;
	}

	public double getVr() {
		return vr_;
	}

	public int getDeg() {
		return deg_;
	}

	public double getVel() {
		return vel_;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(s_Vr_);
		sb.append(",");
		sb.append(vr_);
		sb.append(",");
		sb.append(deg_);
		sb.append(",");
		sb.append(vel_);
		return (sb.toString());
	}

}