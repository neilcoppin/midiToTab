package midiToTab;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Transcriber {

	String outputFilename = "output.txt";
	int eventCounter = 0;
	int commitCounter = 0;
	String lastTimeStamp;
	StringBuilder stringBuilder = new StringBuilder();
	TabBuilder tab = new TabBuilder();
	boolean pauseCommit = false;

	public void transcribeEvent(String event, PrintWriter pw) throws Exception {

		char eventFirstChar = event.charAt(0);
		System.out.println("Event counter: " + eventCounter);
		System.out.println("eventFirstChar: " + eventFirstChar);

		if (eventFirstChar == '@' || pauseCommit == true) {

			System.out.println("Same event, hold press!");

		} else {
			if (eventCounter >= 1) {
				System.out.println("Gonna write now... ");
				commitEventToTranscript(pw);
			}
		}

		// Check for valid note
		boolean functionFound = false;
		while (!functionFound) {
			int searchNum = 1;
		for (int i = 0; i < Note.VALID_NOTES.length; i++) {

			if (eventFirstChar == Note.VALID_NOTES[i]) {
				System.out.println("TRSC: FOUND NOTE");
				Note note = getNoteDetails(eventFirstChar, event);
				// System.out.println("TRANSCRIBER: Calls note.getCourseAndFret = "
				// + note.getCourseAndFret());
				// System.out.println("TRANSCRIBER: Calls note.getDuration = " +
				// note.getDuration());
				tab.addNote(note.getCourseAndFret(), note.getDuration());

				// stringBuilder.append(tabString);
				note.newNote();
				pauseCommit = false;
				eventCounter++;
				functionFound = true;
			} else if (eventFirstChar == 'R') {
				// Handle rest note
				System.out.println("Skipping rest note");
				functionFound = true;
			} else if (eventFirstChar == '@') {
				String stringTimeStamp = event.substring(1);
				String newTimeStamp = stringTimeStamp;
				if (!newTimeStamp.equals(lastTimeStamp)) {
					System.out.println("Same event! Event counter: "
							+ eventCounter);
				}
				lastTimeStamp = newTimeStamp;
				pauseCommit = true;
				functionFound = true;
			} else if ( searchNum == Note.VALID_NOTES.length ) {
				System.out.println("BAD EVENT: " + eventFirstChar);
			} else {
				searchNum++;
			}
			
		}
		}

	}

	private void writeBreakLine(PrintWriter pw) {
		pw.println("b");
		pw.println("");
		pw.println("b");
	}

	private void commitEventToTranscript(PrintWriter pw) {
		System.out.println("Attempting write: " + tab.commitLine());
		pw.println(tab.commitLine());
		commitCounter++;
		if (commitCounter >= 12) {
			writeBreakLine(pw);
			commitCounter = 0;
		}

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

		// handle decimal value NOT COMPLETE! DEFAULTS TO x
		if (currentChar == '/') {
			System.out
					.println("ALERT DECIMAL VALUE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			Note.decimalDurationToString(Double.parseDouble(event
					.substring(i + 1)));
		}
		note.setDuration(currentChar);
		return note;

	}

	public void getNotePosition(String note) {

	}

	public void setTimeSignature(String val) {
		System.out.println("Time Sig is: " + val);

	}

	public void setTempo(Integer val) {
		System.out.println("Tempo is: " + val);

	}

	public void setKeySignature(String val) {
		System.out.println("Key Sig is: " + val);
	}

}