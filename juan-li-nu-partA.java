import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class getData {
	public static void main(String[] args) throws IOException {
		URL myURL = new URL("http://2014.fallchallenge.org/");
		HttpURLConnection myURLConnection = (HttpURLConnection) myURL
				.openConnection();
		myURLConnection.setRequestMethod("GET");
		myURLConnection.setRequestProperty("X-TechChallenge", "true");
		myURLConnection.setRequestProperty("content-type", "binary/data");
		InputStream in = myURLConnection.getInputStream();
		String saveFilePath = "C:/wish/";
		FileOutputStream out = new FileOutputStream(saveFilePath + "juan-li-nu-partA.zip");

		byte[] b = new byte[1024];
		int count;

		while ((count = in.read(b)) > 0) {
			out.write(b, 0, count);
		}
		out.close();
		in.close();

	}

}
