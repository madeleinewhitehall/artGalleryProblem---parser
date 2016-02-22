package artGalleryProblem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class artParser {
	
	public static HashMap<Integer, ArrayList<Double>> parser() throws FileNotFoundException{
		
		Scanner scanner = new Scanner(new File("guards.txt"));	
		HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
		HashMap<Integer, ArrayList<Double>> mapFinal = new HashMap<Integer, ArrayList<Double>>();
		
		//skips the random 8 lines at start of file
		ArrayList<String> stuff = new ArrayList<String>();
		int lineCount = 0;
		while(lineCount<8){
			scanner.nextLine();
			lineCount++;
		}
		
		//saves strings minus the polygon number
		while(scanner.hasNextLine()){
			String y = scanner.nextLine();
			int colon_index = y.indexOf(':');
			String new_y = y.substring(colon_index + 2,y.length()-1);
			//System.out.println(new_y);
			stuff.add(new_y);
		}
		
		//splits the string into smaller point strings
		for(int i = 0; i < stuff.size();i++){
			int front_bracket = 0;
			ArrayList<String> points = new ArrayList<String>();
			
			for(int j = 0; j < stuff.get(i).length(); j++){
				if(stuff.get(i).charAt(j) == '('){
					front_bracket = j;
				}
				else if(stuff.get(i).charAt(j) == ')'){
					points.add(stuff.get(i).substring(front_bracket, j+1));
				}
			}	
			map.put(i, points);
					
		}	
		
		//saves number of vertices of each polygon
		int [] numOfVertices = new int [map.size()];
		for(int k = 0; k < map.size(); k++){
			numOfVertices[k] = map.get(k).size();			
		}		
					
		for(int n = 0; n < map.size(); n++){			
			ArrayList<Double> coords = new ArrayList<Double>();
			
			for(int m=0;m<map.get(n).size();m++){
				int front_bracket = 0;
				int comma = 0;		
				
				for(int p=0;p<map.get(n).get(m).length();p++){
					if(map.get(n).get(m).charAt(p) == '('){
						front_bracket = p;						
					}
					else if(map.get(n).get(m).charAt(p) == ','){
						comma = p;
					}
					else if(map.get(n).get(m).charAt(p) == ')'){
						coords.add(Double.parseDouble(map.get(n).get(m).substring(front_bracket+1, comma)));
						coords.add(Double.parseDouble(map.get(n).get(m).substring(comma+2, p)));				
					}
				}					
			}			
			mapFinal.put(n, coords);		
		}
		scanner.close();
		return mapFinal;
	}	
		
		
		
		
	

	public static void main(String[] args) throws FileNotFoundException {	
		System.out.println(parser());
	}
		

}
