
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WordStatistics {

	Map<String, Integer> wordCounts = new HashMap<String, Integer>();
	static StringBuilder sb = new StringBuilder();

	static Scanner sc = new Scanner(System.in);
	static int characterCount = 0;

	public Map<String, Integer> counter(String words[]) throws IOException {

		for (String word : words) {
			// The length of the word gives the count of characters

			characterCount += word.length();

			// I am assuming all the characters are in lower case.

			word = word.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

			// If the word occurs for the first time then we consider the count as 1
			if (!wordCounts.containsKey(word)) {
				wordCounts.put(word, 1);

			} else {
				wordCounts.put(word, wordCounts.get(word) + 1); // If the word occurs already then we increment its
																// count
			}

		}
		
		return wordCounts;

	}

	public String replaceWord(String words[], String toBeReplaced, String toBeReplacedBy) {

		for (String word : words) {
			word = word.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
			if (word.equals(toBeReplaced)) {
				 sb.append(toBeReplacedBy).append(" ").toString();

			} else {
				 sb.append(word).append(" ").toString();

			}

		}
       
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}

	private void writeFile(String fileName, String finalResponse) throws IOException {
		FileWriter writer = new FileWriter(fileName);
		writer.write(finalResponse);
		writer.close();

		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String text = "";
		while ((text = reader.readLine()) != null) {
			System.out.println(text);
		}
		
		reader.close();
	}
	
	

	public static void main(String[] args) throws IOException {
		
		WordStatistics wordStatistics = new WordStatistics();
		wordStatistics.processFile();
		
	}

	private void processFile() {
		BufferedReader reader;
		try {
			// Scanner sc = new Scanner(System.in);
			System.out.print("name of the text file : ");
			String fileName = sc.nextLine();
			
			reader = new BufferedReader(new FileReader(fileName)); // reads the file
			String line = " ";
			int lineCount = 0;
			String words[] = null;
			System.out.println("enter the word which is to be replaced");
			String toBeReplaced = sc.next();
			System.out.println("enter the word which is to be replaced by");
			String toBeReplacedBy = sc.next();
			
			while ((line = reader.readLine()) != null) {

				lineCount++;
				words = line.trim().split("\\s+");
				counter(words);
				replaceWord(words, toBeReplaced, toBeReplacedBy);
			}
			String finalResponse = sb.toString();
		
			System.out.println("linecount :" + lineCount);
			System.out.println("Total character count :" + characterCount);
			System.out.println("\n ***Word count***");

			for (String Word : wordCounts.keySet()) {
				System.out.println(Word + "\t" + wordCounts.get(Word)); // prints the word along with its count
			}
			System.out.println("***File Content After Replacement***\n");
			writeFile(fileName, finalResponse);
			sc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
}
