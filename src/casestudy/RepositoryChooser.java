package casestudy;

import java.awt.FileDialog;
import java.awt.Frame;

public class RepositoryChooser {

	private String dir;
	private String fileName;

	public void actionPerformed(String type) {

		//親フレームの作成
		Frame frm = new Frame();

		//FileDialogの作成
		FileDialog fd = new FileDialog(frm , type + "を保存" , FileDialog.SAVE);

		//表示する
		fd.setVisible(true);

		//ディレクトリーの取得
		dir = fd.getDirectory();

		//File名の取得
		fileName = fd.getFile();

//		//ファイル名の設定が無ければ処理中止
//		if(fileName == null) System.exit(0);

	}

	//ディレクトリーのゲッター
	public String getDir() {
		return dir;
	}

	//ファイル名のゲッター
	public String getFileName() {
		return fileName;
	}

}
