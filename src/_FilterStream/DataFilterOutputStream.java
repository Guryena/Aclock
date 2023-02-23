package _FilterStream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class DataFilterOutputStream {

	public static void main(String[] args) {
		
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream("data.dat"))){
			out.writeInt(370);
			out.writeDouble(3.14);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
