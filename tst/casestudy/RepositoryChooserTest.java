package casestudy;

import org.junit.Test;

public class RepositoryChooserTest{

	@Test
	public void 保存場所を任意に指定する() {

		RepositoryChooser repChooser = new RepositoryChooser();
		repChooser.actionPerformed("テスト");

		System.out.println(repChooser.getDir());
		System.out.println(repChooser.getFileName());

	}
}
