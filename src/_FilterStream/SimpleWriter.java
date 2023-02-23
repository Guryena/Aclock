package _FilterStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class SimpleWriter {

	public static void main(String[] args) {
		try (Writer out = new FileWriter("data.txt");) {
			out.write('A');
			out.write('한');
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try (Reader in = new FileReader("data.txt")) {
			int data = 0;
			while (true) {
				if (data == -1) {
					break;
				}
				data = in.read();
				System.out.print((char)data);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}