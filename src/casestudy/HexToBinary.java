package casestudy;

public class HexToBinary {
	public static String hexToBinary(String hex){
		String data = new String();
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < hex.length(); j++){
			int ch = hex.charAt(j);
			if((48 <= ch && ch <= 57) || (97 <= ch && ch <= 102)){
				String str = String.valueOf((char)ch);
				String binary = Integer.toBinaryString(Integer.parseInt(str, 16));
				binary = String.format("%04d", Integer.parseInt(binary));
				sb.append(binary);
			}
		}
		data = sb.toString();
		sb.delete(0, sb.length());
		return data;
	}
}
