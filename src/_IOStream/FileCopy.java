package _IOStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class FileCopy {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("대상 파일 : ");
		String src = sc.nextLine();
		System.out.print("사본 이름 : ");
		String dst = sc.nextLine();

		try (InputStream in = new FileInputStream(src); 
				OutputStream out = new FileOutputStream(dst)) {
//			int data;
			byte buf[] = new byte[1024];
			int len;
			while (true) {
//				data = in.read(); //Read 1byte from the File
				len = in.read(buf);
//				if (data == -1)
				if (len ==-1)
				{ // If when there is no more data to read 
					break;
				}
//				out.write(data); // 1byte is written to the file.
				out.write(buf, 0, len);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
