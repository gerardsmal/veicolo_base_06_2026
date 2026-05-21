package com.betacom.veicoli.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Utils {

//	public static List<String> readFile(String path){
//		List<String> r = new ArrayList<String>();
//		try (BufferedReader reader = new BufferedReader(new FileReader(path))){
//			String line = reader.readLine();
//			while (line != null) {
//				r.add(line);
//				line = reader.readLine();
//			}
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//		}
//		return r;
//	}

	public static List<String> readFile(String path) {

	    try (Stream<String> lines = Files.lines(Path.of(path))) {     // Files.lines -> read all lines . result in putting into stream
	        return lines.toList();
	    } catch (IOException e) {
	        System.err.println(e.getMessage());
	        return Collections.emptyList();
	    }
	}
	
	public static void writeFile(String path,String inp, boolean mode) {
		
		try (FileWriter o = new FileWriter(path, mode)){
			o.write(inp);
			o.write("\n");
		
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	public static boolean isNumeric(String numero) {
		if (!numero.matches("\\d+")) return false;
		return true;
	}
	
	public static String buildClassName(String par) {
		 if (par == null || par.isBlank()) return par;
		    
		return par.substring(0, 1).toUpperCase() + par.substring(1).toLowerCase() + "Impl";
	}
}
