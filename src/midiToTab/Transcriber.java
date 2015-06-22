package midiToTab;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Transcriber {

	public static final char[] MIDI_DURATIONS = { 'w', 'h', 'q', 'i', 's', 't',
			'x', 'o' };
	public static final String[] TAB_DURATIONS = { "W", "w", "0", "1", "2",
			"3", "4", "5" };
	

	String outputFilename = "output.txt";
	int eventCounter = 0;
	String lastTimeStamp;
	StringBuilder stringBuilder = new StringBuilder();

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
			}

			lastTimeStamp = newTimeStamp;
		}

		// Check for valid note
		for (int i = 0; i < Note.VALID_NOTES.length; i++) {

			if (eventFirstChar == Note.VALID_NOTES[i]) {
				Note note =	getNoteDetails(eventFirstChar, event);
				note.toTabOutput(stringBuilder);
				System.out.println();
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

	private void commitEventToTranscript(PrintWriter pw) {
		System.out.println("Attempting write");
		pw.println(stringBuilder);
		stringBuilder = null;
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
			currentChar = 'q';
		}
		note.setDuration(currentChar);
		return note;

	}

	private char convertDecimalDuration(String decValue) {
		System.out.println("decValue = " + decValue);
		char returnChar;
		double dVal = Double.parseDouble(decValue);
		returnChar = MidiParser.decDurationToChar(dVal);
		System.out.println("returnChar = " + returnChar);
		return returnChar;
	}

	public void getNotePosition(String note) {

	}

}