package casestudy;

/**
 * 自作クラス 速度情報をもつ
 */
public class Velocity {

	/** 垂直方向の動き 0：下降、1：上昇 */
	private int s_Vr_;

	/** 垂直方向の速さ[kt] */
	private double vr_;

	/** 水平方向の向き(方位) 0～360[度] */
	private int deg_;

	/** 水平方向の速さ[kt] */
	private double vel_;

	/**
	 * コンストラクタ
	 * @param s_Vr
	 * @param vr
	 * @param deg
	 * @param vel
	 */
	Velocity(int s_Vr, double vr, double deg, double vel){
		this.s_Vr_ = s_Vr;
		this.vr_ = vr;
		this.deg_ = (int)deg;
		this.vel_ = vel;
	}

	/**
	 * s_Vr_のgetter
	 * @return s_Vr_
	 */
	public int getS_Vr() {
		return s_Vr_;
	}

	/**
	 * vr_のgetter
	 * @return vr_
	 */
	public double getVr() {
		return vr_;
	}

	/**
	 * deg_のgetter
	 * @return deg_
	 */
	public int getDeg() {
		return deg_;
	}

	/**
	 * vel_のgetter
	 * @return vel_
	 */
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