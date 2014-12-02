package minesweeper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserInteractions {

	public int[] chooseSquare(){
		String input;
		System.out.println("Välj ruta 'X Y' ");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			input = br.readLine();
		} catch (Exception e) {
			System.out.println("ntå blev fel");
			input ="1 1";
		}
		
		int[] coordinates = new int[2];
		int endIndex= input.indexOf(' ');
		coordinates[0]=Integer.valueOf(input.substring(0, endIndex));
		coordinates[1]=Integer.valueOf(input.substring(endIndex+1));
		return coordinates;
		
	}
	
}
