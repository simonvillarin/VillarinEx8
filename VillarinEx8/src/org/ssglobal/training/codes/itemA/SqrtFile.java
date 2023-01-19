package org.ssglobal.training.codes.itemA;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class SqrtFile {
	private double num;
	private double result;
	
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number: ");
		num = sc.nextDouble();
		sc.close();
	}
	
	
	public void squareRoot() {
		result = Math.sqrt(num);
		
		System.out.println("Square root of %.1f = %.1f".formatted(num, result));
		System.out.println("The square root value %.1f wrote to the file \"number.dat\"".formatted(result));
	}
	
	public void squareNumber() {
		File file = new File("./src/files/number.dat");
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeDouble(result);
				
			dos.flush();
			dos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			
			num = dis.readDouble();
			result = Math.pow(num, 2);
			
			System.out.println("-".repeat(60));
			System.out.println("The value read from the file \"number.dat\": %.1f".formatted(num));
			System.out.println("A square number of %.1f = %.1f".formatted(num, result));
			
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
 