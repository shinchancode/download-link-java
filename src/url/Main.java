package url;
import java.io.File;

public class Main {

	public static void main(String[] args) {
		String link = "https://www.tutorialspoint.com/cplusplus/cpp_tutorial.pdf";
		
		File out = new File("C:\\Users\\Aarti\\Downloads\\cplusplus.pdf");
		
		new Thread(new download(link,out)).start();;
	}

}
