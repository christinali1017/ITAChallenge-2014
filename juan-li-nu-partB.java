import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class ExtractGzip {

	public static void main(String[] args) {
		String gzip_filepath = "C:\\wish\\juan-li-nu-partA.gzip";
		String decopressed_filepath = "C:\\wish\\juan-li-nu-partA.txt";
		
		ExtractGzip gZipFile = new ExtractGzip();
		gZipFile.unGunzipFile(gzip_filepath, decopressed_filepath);
	}

	public void unGunzipFile(String compressedFile, String decompressedFile) {
		byte[] buffer = new byte[1024];
		try {
			FileInputStream fileIn = new FileInputStream(compressedFile);
			GZIPInputStream in = new GZIPInputStream(fileIn);
			FileOutputStream out = new FileOutputStream(decompressedFile);
			int count;
			while ((count = in.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}

			in.close();
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
