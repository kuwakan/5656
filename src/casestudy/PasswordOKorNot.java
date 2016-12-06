package casestudy;

import java.io.Serializable;

public class PasswordOKorNot implements Serializable {

	public boolean isOk=true;

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

}
