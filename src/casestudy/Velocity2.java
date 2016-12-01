package casestudy;

public class Velocity2 {


	private String modes;



	private float h_velo;

	private float v_velo;

	private float h_dir;

	private float v_dir;

	public Velocity2(String modes, float h_velo, float v_velo,float h_dir, float v_dir ){
		this.modes = modes;
		this.h_velo = h_velo;
		this.v_velo = v_velo;
		this.h_dir = h_dir;
		this.v_dir = v_dir;
	}

	public String getModes() {
		return modes;
	}

	public void setModes(String modes) {
		this.modes = modes;
	}

	public float getH_velo() {
		return h_velo;
	}

	public void setH_velo(float h_velo) {
		this.h_velo = h_velo;
	}

	public float getV_velo() {
		return v_velo;
	}

	public void setV_velo(float v_velo) {
		this.v_velo = v_velo;
	}

	public float getH_dir() {
		return h_dir;
	}

	public void setH_dir(float h_dir) {
		this.h_dir = h_dir;
	}

	public float getV_dir() {
		return v_dir;
	}

	public void setV_dir(float v_dir) {
		this.v_dir = v_dir;
	}
}
