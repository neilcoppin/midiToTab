package midiToTab;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.sound.midi.InvalidMidiDataException;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
//import org.jfugue.player.Player;

public class MidiParser {
	
	public static String midiToString(File midiFile) throws IOException, InvalidMidiDataException {
		
		//Player player = new Player();
		Pattern pattern = MidiFileManager.loadPatternFromMidi(midiFile);
	    //System.out.println(pattern);
		
	    
	    String output = pattern.toString();
	    //System.out.println(output);
	    
	    return output;
		
	}
	
	public static char decDurationToChar(double val){
		String strDur = org.jfugue.theory.Note.getDurationString(round(val, 1));
		System.out.println("strDur = " + strDur);
		char duration = strDur.charAt(0);
					
		return duration;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
