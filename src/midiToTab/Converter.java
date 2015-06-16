package midiToTab;

import java.io.File;

public class Converter {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File midiFile = new File("test.mid");
		
		MidiReader.parse(midiFile);
		
		
		

	}

}
