import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class FilesUsed {
	public FileWriter f1;
	public BufferedWriter bw;
	public FilesUsed()throws IOException{
		f1= new FileWriter("Output.txt",true);
		bw = new BufferedWriter(f1);
	}

}
