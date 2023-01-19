package org.ssglobal.training.codes.itemB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class LocalBank {

	private String firstName;
	private String lastName;
	private String accountId;
	private double amount;
	private double balance;

	public void input() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("[D] Deposit");
			System.out.println("[W] Withdrawal");
			System.out.println("[C] Check Balance");
			System.out.println("[A] Add Account");
			System.out.println("[R] Remove Account");
			System.out.println("[Q] Quit");
			System.out.print("Enter choice: ");
			String choice = sc.next();

			if (choice.equalsIgnoreCase("D")) {
				Scanner sc1 = new Scanner(System.in);
				System.out.print("Enter account ID: ");
				accountId = sc1.nextLine();
				System.out.print("Enter deposit amount: ");
				amount = sc1.nextDouble();

				deposit();
			} else if (choice.equalsIgnoreCase("W")) {
				Scanner sc1 = new Scanner(System.in);
				System.out.print("Enter account ID: ");
				accountId = sc1.nextLine();
				System.out.print("Enter widthdrawal amount: ");
				amount = sc1.nextDouble();
				
				withdraw();
			} else if (choice.equalsIgnoreCase("C")) {
				Scanner sc1 = new Scanner(System.in);
				System.out.print("Enter account ID: ");
				accountId = sc1.nextLine();

				checkBalance();
			} else if (choice.equalsIgnoreCase("A")) {
				Scanner sc1 = new Scanner(System.in);
				System.out.print("First Name: ");
				firstName = sc1.nextLine();
				System.out.print("Last Name: ");
				lastName = sc1.nextLine();
				System.out.print("Beginning balance: ");
				balance = sc1.nextDouble();

				char firstLetter = firstName.charAt(0);
				accountId = firstLetter + lastName;
				
				System.out.println("Account created. Account ID is: %s".formatted(accountId));
				createAccount();
			} else if (choice.equalsIgnoreCase("R")) {
				Scanner sc1 = new Scanner(System.in);
				System.out.print("Enter account ID: ");
				accountId = sc1.nextLine();

				removeAccount();
			} else if (choice.equalsIgnoreCase("Q")) {
				System.out.println("Thank you.");
				break;
			} else {
				System.out.println("Invalid input.");
			}
			System.out.println("");
		}
		sc.close();
	}

	public void createAccount() {
		File file = new File("./src/files/localbankdb.txt");

		try {
			FileWriter fw = new FileWriter(file, true);

			if (file.exists()) {
				fw.write(accountId + "\n");
				fw.write(firstName + "\n");
				fw.write(lastName + "\n");
				fw.write(Double.toString(balance) + "\n");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deposit() {
		try {
			File file = new File("./src/files/localbankdb.txt");
			RandomAccessFile rac = new RandomAccessFile(file, "rw");

			String line = rac.readLine();
			int length = 0;
			
			while (line != null) {
				if (accountId.equals(line)) {
					firstName = rac.readLine().trim();
					lastName = rac.readLine().trim();
					balance = Double.parseDouble(rac.readLine().trim());
					balance += amount;
					rac.seek(length);
					rac.writeUTF(accountId + "\n");
					rac.writeUTF(firstName + "\n"); 
					rac.writeUTF(lastName + "\n");
					rac.writeUTF(Double.toString(balance) + "\n");
				}
				length++;
				line = rac.readLine();
			}
			rac.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void withdraw() {
		try {
			File file = new File("./src/files/localbankdb.txt");
			RandomAccessFile rac = new RandomAccessFile(file, "rw");

			String line = rac.readLine();
			int length = 0;

			while (line != null) {
				if (accountId.equals(line)) {
					firstName = rac.readLine().trim();
					lastName = rac.readLine().trim();
					balance = Double.parseDouble(rac.readLine().trim());
					balance -= amount;
					rac.seek(length);
					rac.writeUTF(accountId + "\n");
					rac.writeUTF(firstName + "\n"); 
					rac.writeUTF(lastName + "\n");
					rac.writeUTF(Double.toString(balance) + "\n");
				} 
				length++;
				line = rac.readLine();
			}
			rac.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void checkBalance() {
		try {
			File file = new File("./src/files/localbankdb.txt");
			RandomAccessFile rac = new RandomAccessFile(file, "rw");

			String line = rac.readLine();

			while (line != null) {
				if (accountId.equals(line)) {
					firstName = rac.readLine().trim();
					lastName = rac.readLine().trim();
					balance = Double.parseDouble(rac.readLine().trim());
					
					System.out.println(line);
					System.out.println("%s %s".formatted(firstName, lastName));
					System.out.println("Balance: \u0024%.2f".formatted(balance));
				}
				line = rac.readLine();
			}		
			rac.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeAccount() {
		try {
			File file = new File("./src/files/localbankdb.txt");
			RandomAccessFile rac = new RandomAccessFile(file, "rw");

			String line = rac.readLine();
			int length = 0;

			while (line != null) {
				if (accountId.equals(line)) {
					rac.seek(length);
					rac.writeUTF("\n");
					rac.writeUTF("\n"); 
					rac.writeUTF("\n");
					rac.writeUTF("\n");
				}
				length++;
				line = rac.readLine();
			}	
			rac.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
