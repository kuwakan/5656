package casestudy;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SensorAccessObject {
	private String SbsIp;
	private int SbsPort;
	private Socket echoSocket = null;
	private BufferedInputStream inBynary = null;

	/**
	 * コンストラクタ
	 * @param SbsIp ソケット接続時のIP
	 * @param SbsPort ポート番号
	 */
	public SensorAccessObject(String SbsIp,int SbsPort){
		this.SbsIp = SbsIp;
		this.SbsPort = SbsPort;
	}

	/**
	 * 接続
	 */
	public void connect(){
        try {
            echoSocket = new Socket(SbsIp, SbsPort);
            inBynary = new BufferedInputStream(echoSocket.getInputStream());
            } catch (UnknownHostException e) {
            System.err.println("Don't know about host: SbsIp = " + SbsIp);
            } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: SbsIp = " + SbsIp);
            }
	}

	/**
	 * クローズ
	 */
	public void close(){
		try{
			inBynary.close();
			echoSocket.close();
		} catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException: " + e);
		}
	}

	/**
	 * データの読み込み
	 * @return スペース区切りのHexデータ
	 */
	public String readSensor(){
		StringBuilder sb = new StringBuilder();
		String hex2 = null;
		String hex = null;
		try{

            // 読み込み用バイト配列
            byte[] buf = new byte[1024];
            // 入力ストリームからの読み込み（ファイルの読み込み）
            int len = inBynary.read(buf);

            // 読み込んだデータを16進形式で表示

	       	for ( int i = 0; i < len; i++ ) {
	       		hex = String.format("%1$x ", buf[i]);
	       		hex = hex.length() == 2 ? "0" + hex : hex;
	       		sb.append(hex);

	       	}
	       	hex2 = sb.toString();

		} catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }
		return hex2;
	}

}