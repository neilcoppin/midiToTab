package midiToTab;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Transcriber {

	
	

	String outputFilename = "output.txt";
	int eventCounter = 0;
	String lastTimeStamp;
	StringBuilder stringBuilder = new StringBuilder();
	TabBuilder tab = new TabBuilder();

	public void transcribeEvent(String event, PrintWriter pw) throws Exception {

		char eventFirstChar = event.charAt(0);
		
		if (eventFirstChar == '@') {
			String stringTimeStamp = event.substring(1);
			String newTimeStamp = stringTimeStamp;
			if (!newTimeStamp.equals(lastTimeStamp)) {
				System.out.println("New event! Event counter: " + eventCounter);
				if (eventCounter >= 1) {
					System.out.println("Gonna write now...");
					commitEventToTranscript(pw);
				}
				if (eventCounter%16==0){
					writeBreakLine(pw);
				}
			}

			lastTimeStamp = newTimeStamp;
		}

		// Check for valid note
		for (int i = 0; i < Note.VALID_NOTES.length; i++) {

			if (eventFirstChar == Note.VALID_NOTES[i]) {
				Note note =	getNoteDetails(eventFirstChar, event);
				System.out.println("TRANSCRIBER: Calls note.getCourseAndFret = " + note.getCourseAndFret());
				System.out.println("TRANSCRIBER: Calls note.getDuration = " + note.getDuration());
				tab.addNote(note.getCourseAndFret(),note.getDuration()); 
				
				//stringBuilder.append(tabString);
				note.newNote();
				eventCounter++;				
				break;
			} else if (eventFirstChar == 'R') {
				//Handle rest note
				System.out.println("Skipping rest note");
				break;
			}
			
		}
	}

	private void writeBreakLine(PrintWriter pw) {

		pw.println("");
		
	}

	private void commitEventToTranscript(PrintWriter pw) {
		System.out.println("Attempting write: " + tab.commitLine());
		pw.println(tab.commitLine());
		tab.newLine();
		System.out.println("TRANSCRIBER: tab.newLine = " + tab.commitLine());
	}

	private Note getNoteDetails(char noteName, String event) throws Exception {

		Note note = new Note();

		// Handle rest notes
		if (noteName == 'R') {
			note.setName('X');
			char currentChar = event.charAt(1);
			note.setDuration(currentChar);
			System.out.println(note.toString());
			return note;
		}

		note.setName(noteName);

		int i = 1;
		char currentChar = event.charAt(i);

		if (currentChar == 'b') {
			note.setAccidental(currentChar);
			i++;
			currentChar = event.charAt(i);
		} else if (currentChar == '#') {
			note.setAccidental(currentChar);
			i++;
			currentChar = event.charAt(i);
		}
		// else {}

		note.setOctave(currentChar);
		i++;
		currentChar = event.charAt(i);

		// handle decimal value NOT COMPLETE! DEFAULTS TO q
		if (currentChar == '/') {
			// convertDecimalDuration(event.substring(i+1));
			currentChar = 'x';
		}
		note.setDuration(currentChar);
		return note;

	}

	public void getNotePosition(String note) {

	}

}