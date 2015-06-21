package midiToTab;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Converter {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File midiFile = new File("test.mid");
		Transcriber trsc = new Transcriber();
		PrintWriter pw = new PrintWriter(new FileWriter("output.txt"), true);
		
		Scanner scanner = new Scanner(MidiParser.midiToString(midiFile));
		scanner.useDelimiter("\\s");

		
		
		while (scanner.hasNextLine()) {
			String event = scanner.next();
			System.out.println("Raw data: " + event);
			
			trsc.transcribeEvent(event, pw);
		}

		scanner.close();

	}

}
