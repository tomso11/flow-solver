package algorithms;
import java.io.BufferedReader;
import java.io.FileReader;

public class Reader {
	
	public String readTxt(String direction){ 
			
			String text = "";
			
			try{
				BufferedReader bf = new BufferedReader(new FileReader(direction));
				String temp = "";
				String bfRead;
				while((bfRead= bf.readLine())!= null){ 
					temp = temp+bfRead+"*";
				}
				text=temp;
				bf.close();
			}catch (Exception e){ 
				System.err.println("No se encontro Archivo");
			}
			return text;
		}
}
