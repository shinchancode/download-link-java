package url;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

public class download implements Runnable {
	String link;
	File out;
	
	public download(String link, File out)
	{
		this.link=link;
		this.out=out;
	}

	@Override
	public void run() {
		try
		{
			URL url = new URL(link);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			double filesize = (double)http.getContentLengthLong();
			BufferedInputStream in = new BufferedInputStream(http.getInputStream());
			FileOutputStream fos = new FileOutputStream(this.out);
			BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
			byte[] buffer = new byte[1024];
			double downl= 0.00;
			int read =0;
			double percent=0.00;
			
			while((read=in.read(buffer,0,1024))>=0)
			{
				bout.write(buffer, 0, read);
				downl+=read;
				percent=(downl*100)/filesize;
				String perc = String.format("%.4f", percent);
				System.out.println("downloaded "+perc+"% of a file");
			}
			bout.close();
			in.close();
			System.out.println("download complete");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
	}
}
