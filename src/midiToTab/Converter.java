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
		PrintWriter pw2 = new PrintWriter(new FileWriter("midiOutput.txt"), true);
		pw.println("b");
		
		Scanner scanner = new Scanner(MidiParser.midiToString(midiFile));
		scanner.useDelimiter("\\s");
		
		String event = new String();
		
		boolean foundNote = false;
		
		
		//K = key sig; V = voice; I = instrument; T = tempo; 
		while (!foundNote) {
			event = scanner.next();
			for (int i=0; i < Note.VALID_NOTES.length; i++ ) {
			if(event.charAt(0)==Note.VALID_NOTES[i]) {
				foundNote = true;
				break;
			} else if ((event.length() > 4) && (event.substring(0, 5).equals("TIME:"))) {
				System.out.println("Found Time Sig");
				trsc.setTimeSignature(event.substring(5));
				break;
			} else if ( event.charAt(0) == 'T') {
				System.out.println("Found Tempo");
				trsc.setTempo(Integer.parseInt(event.substring(1)));
				break;
			} else if ( event.charAt(0) == 'K') {
				trsc.setKeySignature(event.substring(1));
				break;
			} else {
				//do nothing
			}
			
		}
		}
		
		
		while (scanner.hasNextLine()) {
			
			System.out.println("Raw data: " + event);
			pw2.println(event);
			
			trsc.transcribeEvent(event, pw);
			event = scanner.next();
		}
		System.out.println("All done, ending doc");
		pw.println("b");
		pw.println("e");
		scanner.close();
		pw.close();
		pw2.close();

	}

}
