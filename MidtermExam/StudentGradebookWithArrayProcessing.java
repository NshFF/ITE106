package MidtermExamITE106;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradebookWithArrayProcessing {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int assignmentCount;
		int currentAssignment;
		float classAverage = 0;
		int studentCount; // used to keep track of how many students there are 
		int currentStudent; // used to keep track of which student the following information is for/from
		
		System.out.println("How many students are in the class?");
		studentCount = IntInput(false); // use method to avoid error
		System.out.println("How many assignments are in the subject?");
		assignmentCount = IntInput(false); // use method to avoid error
		input.nextLine(); // to clear the newline character left in the buffer
		
		// create arrays to hold student information which size is equal to the number of students
		String[] name = new String[studentCount];
		char[] letterGrade = new char[studentCount];
		float[][] assignmentGrade = new float[studentCount][assignmentCount];
		float[] averageGrade = new float[studentCount];
		
		// take inputs name, quiz 1 and 2 grade, homework 1 and 2 grade, and exam grade
		for (currentStudent = 0; currentStudent < studentCount; currentStudent++) {
			// ask name of current student
			System.out.printf("Student %d Name? ", currentStudent + 1);
			name[currentStudent] = StringInput();
			
			// ask for the grades for each assignment
			for (currentAssignment = 0; currentAssignment < assignmentCount; currentAssignment++) {
				System.out.printf("%s Grade in Assignment %d: ", name[currentStudent], currentAssignment + 1);
				// assigns the user input to the position of the assignment (by student) on the 2d array
				assignmentGrade[currentStudent][currentAssignment] = IntInput(true);
				input.nextLine();
				
				// calculate student average
				averageGrade[currentStudent] += assignmentGrade[currentStudent][currentAssignment];
			}
			averageGrade[currentStudent] /= assignmentCount;
			
			if (averageGrade[currentStudent] > 94) {
				letterGrade[currentStudent] = 'A';
			}
			else if (averageGrade[currentStudent] > 89) {
				letterGrade[currentStudent] = 'B';
			}
			else if (averageGrade[currentStudent] > 84) {
				letterGrade[currentStudent] = 'C';
			}
			else if (averageGrade[currentStudent] > 79) {
				letterGrade[currentStudent] = 'D';
			}
			else if (averageGrade[currentStudent] > 74) {
				letterGrade[currentStudent] = 'E';
			}
			else {
				letterGrade[currentStudent] = 'F';
			}
			
			// compute class average
			classAverage += averageGrade[currentStudent];
		}
		classAverage /= studentCount;
		
		// determine the lowest highest scores in class
		/* not needed anymore, as we would already have the lowest and highest grade after sorting the array 
		int lowestGradeStudent = 0;
		int lowestGrade = 0;
		int highestGradeStudent = 0;
		int highestGrade = 0;
		for (currentStudent = 0; currentStudent < studentCount; currentStudent++) {
			if (lowestGrade == 0 || averageGrade[currentStudent] < lowestGrade) {
				lowestGrade = averageGrade[currentStudent];
				lowestGradeStudent = currentStudent;
			}
			if (highestGrade == 0 || averageGrade[currentStudent] > highestGrade) {
				highestGrade = averageGrade[currentStudent];
				highestGradeStudent = currentStudent;
			}
		}
		*/
		
		// sorting the array		
		for (currentStudent = 0; currentStudent < studentCount; currentStudent++) {
			int smallestAverageIndex = 0; // holds the index of the element with the lowest average

			// First, i look for the smallest element in the array (lowest average) and compare it with each element starting from index 0
			for (int indexB = currentStudent; indexB < studentCount; indexB++) { // start at the current position of previous loop, NOT necessarily the first element/index[0]
				if (smallestAverageIndex == 0) { // initializes the smallest value as the value of the first element
					smallestAverageIndex = indexB;
				}
				// checks if the next elements are smaller than the current smallest element
				else if (averageGrade[indexB] < averageGrade[smallestAverageIndex]) {
					smallestAverageIndex = indexB;
				}
			}

			// put first the name, grades, average, and letter grade of the current student into temporary holder variables
			String nameHolder = name[currentStudent];
			char letterGradeHolder = letterGrade[currentStudent];
			float[] assignmentGradeHolder = assignmentGrade[currentStudent]; 
			float averageGradeHolder = averageGrade[currentStudent];
			// put the name, grades, average, and letter of the element with the smallest average to the current position
			name[currentStudent] = name[smallestAverageIndex];
			letterGrade[currentStudent] = letterGrade[smallestAverageIndex];
			assignmentGrade[currentStudent] = assignmentGrade[smallestAverageIndex];
			averageGrade[currentStudent] = averageGrade[smallestAverageIndex];
			// then put the name, grades, average, and letter of the previous student that is in the temporary holder variables into the previous position of the element with the smallest average
			name[smallestAverageIndex] = nameHolder;
			letterGrade[smallestAverageIndex] = letterGradeHolder;
			assignmentGrade[smallestAverageIndex] = assignmentGradeHolder;
			averageGrade[smallestAverageIndex] = averageGradeHolder;
		}

		System.out.println("================OUTPUT================");
		System.out.printf("|%-15s", "STUDENTS"); // print first STUDENTS to start first row
		for (currentStudent = 0; currentStudent < studentCount; currentStudent++) {
			/* %s to insert a string and -15 specifies that there are 15 characters for that string field AFTER '|'.
			 * This is to have UNIFORM spacing between names. Because using \t (tab) doesn't make the spacing uniform
			 */
			System.out.printf("|%-15s", name[currentStudent]); 
		}
		
		System.out.println();
		for (currentStudent = 0; currentStudent < studentCount + 1; currentStudent++) { // plus 1 from studentCount to compensate with the previous "STUDENTS" output
			System.out.printf("|%-15s", "---------------");
		}
		
		System.out.println();
		/* first loop iterates on the assignments - second loop iterates on students so
		 * first, all assignment 1 of each student. before proceeding to next assignment of each student and so on
		 * ASSIGNMENT FIRST - BEFORE STUDENT
		 * because listing them on console is ROW first NOT COLUMN
		 */
		for (currentAssignment = 0; currentAssignment < assignmentCount; currentAssignment++) {
			// print first assignment number
			System.out.printf("|%-15s", "ASSIGNMENT " + currentAssignment); // print first assignment number to start each row
			
			// print grade on current assignment, for each student before proceeding with the next assignment
			for (currentStudent = 0; currentStudent < studentCount; currentStudent++) {
				System.out.printf("|%-15.2f", assignmentGrade[currentStudent][currentAssignment]);
			}

			System.out.println();
			// to separate each assignments
			for (currentStudent = 0; currentStudent < studentCount + 1; currentStudent++) { // plus 1 from studentCount to compensate with the previous "ASSIGNMENT" output
				System.out.printf("|%-15s", "---------------");
			}
			System.out.println();
		}
		
		System.out.printf("|%-15s", "AVERAGE"); // print first STUDENTS to start first row
		for (currentStudent = 0; currentStudent < studentCount; currentStudent++) { // plus 1 from studentCount to compensate with the previous "STUDENTS" output
			System.out.printf("|%-15.2f", averageGrade[currentStudent]);
		}
		
		System.out.println();
		for (currentStudent = 0; currentStudent < studentCount + 1; currentStudent++) { // plus 1 from studentCount to compensate with the previous "STUDENTS" output
			System.out.printf("|%-15s", "---------------");
		}
		
		System.out.println();
		System.out.printf("CLASS AVERAGE: %.2f\n", classAverage);
		System.out.printf("LOWEST GRADE: %s - %.2f\n", name[0], averageGrade[0]);
		System.out.printf("HIGHEST GRADE: %s - %.2f\n", name[name.length - 1], averageGrade[averageGrade.length - 1]);
	}
	
	public static int IntInput(boolean isGrade) {
		int numberInput; // return value
		
		while (true) {
			try {
				numberInput = input.nextInt();
				if (numberInput <= 0) { // invalid input
					System.out.println("Invalid Input - Zero or Negative");
				}
				else if (isGrade && (numberInput < 60 || numberInput > 100)) { // invalid input
					System.out.println("Invalid Input - Grade must be (60 - 100)");
				}
				else { // valid input
					return numberInput;
				}
			}
			catch (InputMismatchException e) { // invalid input
				// e.printStackTrace(); // Prints the exception details to the console
				System.out.println("Invalid Input - Non-Numeric");
			}
			input.nextLine();
		}
	}
	
	public static String StringInput() {
		String nameInput; // return value
		
		while (true) {
			nameInput = input.nextLine();
			boolean validInput = true;
			
			/* toCharArray, a method of the String Class
			 * Converts the String input into character array
			 * Iterate on the character array using 'c' to traverse
			 */
			for (char c : nameInput.toCharArray()) {
				if (!Character.isLetter(c)) { // check if current character is NOT a letter
					System.out.println("Invalid Input - Numeric Character");
					validInput = false; // to not return a value and repeat the loop
					break;
				}
			}
			if (validInput) { // stops loop and returns valid input
				return nameInput;
			}
		}
	}
}










// LANADA
