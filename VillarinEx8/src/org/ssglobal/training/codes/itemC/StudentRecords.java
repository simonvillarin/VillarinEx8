package org.ssglobal.training.codes.itemC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class StudentRecords {
	
	public String[] queryHighestAverage(String filename) {
		int[] quizScores = new int[4];
		int[] numQuiz = new int[4];
		String[] students = new String[4];
		float[] avg = new float[4];
		
		Arrays.fill(students, "");
		
		try {
			FileReader fr = new FileReader(filename);
			StreamTokenizer token = new StreamTokenizer(fr);
			int index = -1;
			int tokens = token.nextToken();
			while (!(tokens == StreamTokenizer.TT_EOF)) {
				if (tokens == StreamTokenizer.TT_NUMBER) {
					if (index != -1) {
						quizScores[index] += (int) token.nval;
						numQuiz[index]++;
					}
				} else if (tokens == StreamTokenizer.TT_WORD){
					String studnetName = token.sval;
					index = switch (studnetName) {
					case "Rossi" -> 0;
					case "Bianchi" -> 1;
					case "Verdi" -> 2;
					case "Domins" -> 3;
					default -> -1;
					};
					students[index] = studnetName;
				}
				tokens = token.nextToken();
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int quizes = 1;
		for(int i = 0; i < numQuiz.length; i++) {
			if (quizes < numQuiz[i]) {
				quizes = numQuiz[i];
			}
		}

		for (int i = 0; i < quizScores.length; i++) {
			avg[i] = quizScores[i] / quizes;
		}
		float average = 0.0f;
		for(int i = 0; i < avg.length; i++) {
			if(average <= avg[i]) {
				average = avg[i];
			}
		}
		
		int count = 0;
		for(int i = 0; i < avg.length; i++) {
			if(average == avg[i]) {
				count++;
			}
		}
		int highIndex = 0;
		String[] highStudent = new String[count];
		for(int i = 0; i < avg.length; i++) {
			if(average == avg[i]) {
				highStudent[highIndex] = students[i];
				highIndex++;
			}
		}	
		return highStudent;		
	}
}
