package org.ssglobal.training.codes.itemC;

import java.util.Arrays;

public class TestStudentRecords {
	
	public static void main(String[] args) {	
		StudentRecords rc = new StudentRecords();
		System.out.println(Arrays.toString(rc.queryHighestAverage("./src/files/sem-1-2021.txt")));
	}
}
