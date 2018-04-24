package br.com.magnojr.noescape.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.magnojr.noescape.models.Stage;

public class GameRepository {
	
	public static final String FILE_TYPE = ".save" ;

	public void save(Stage stage, String slot) {
		try (PrintWriter writer = new PrintWriter(new File(slot + FILE_TYPE))) {
			
			 Gson gson = new GsonBuilder().create();
			 gson.toJson(stage, writer);
			
		}catch (FileNotFoundException fnfe) {
		    fnfe.printStackTrace();
		}


	}

	public Stage get(String slot) {
		Stage result = null;		
		try(Scanner scanner = new Scanner(new File(slot + FILE_TYPE) ) ){
			Gson gson = new GsonBuilder().create();
			while(scanner.hasNext()){
				String row = scanner.nextLine();
				result = gson.fromJson(row, Stage.class);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

}
