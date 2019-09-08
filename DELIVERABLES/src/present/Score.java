package present;
import java.io.*;
import java.util.*;


public class Score {
	
	private ArrayList<String> scoreList = new ArrayList<String>();
	private ArrayList<Integer> scoreListInteger = new ArrayList<Integer>();
	
	
	public Score() {
	
	}
	
	public void writeToFile(int score, String Name) {
		try {
			
			FileWriter fileWriter = new FileWriter(new File(this.getClass().getResource("/score/score1.txt").getPath()), true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(String.valueOf(score));
			bufferedWriter.newLine();
			bufferedWriter.close();
			
		} catch (IOException ex) {
			System.out.println("CANNOT WRITE TO FILE");
		}
	}
	
	public void fileReader() {
		
		try {
			InputStream fileReader = getClass().getResourceAsStream("/score/score1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fileReader));
			
			String temp;
			while ((temp = br.readLine()) != null) {
				scoreList.add(temp);
			}
			
			br.close();
		} catch (IOException e) {
			System.out.println("CANNOT READ FILE");
		} catch (NumberFormatException ex) {
			System.out.println("NUMBER FORMAT EXCEPTION");
		}
	}
	
	public ArrayList<String> getScore(){
		scoreListInteger.clear();
		for(int i = 0; i < scoreList.size(); i++) {
			scoreListInteger.add(Integer.parseInt(scoreList.get(i).trim()));
		}
		
		Collections.sort(scoreListInteger); 
		Collections.reverse(scoreListInteger);
		
		scoreList.clear();
		
		for (int i = 0; i < scoreListInteger.size(); i++) {
			scoreList.add(String.valueOf(scoreListInteger.get(i)));
		}
		
		return this.scoreList;
	}

}
