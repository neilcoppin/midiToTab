package midiToTab;

public class Note {

	public static final char[] VALID_NOTES = { 'A', 'B', 'C', 'D', 'E', 'F',
			'G' };
	public static final char[] VALID_OCTAVES = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8' };
	public static final String[] SHARPS = { "C", "C#", "D", "D#", "E", "F",
			"F#", "G", "G#", "A", "A#", "B" };
	public static final String[] FLATS = { "C", "Db", "D", "Eb", "E", "F",
			"Gb", "G", "Ab", "A", "Bb", "B" };
	public static final String[] NATURALS = { "C", "D", "E", "F", "G", "A", "B" };

	public static final byte[] OCTAVE_0 = { 0, 2, 4, 5, 7, 9, 11 };
	public static final byte[] OCTAVE_1 = { 12, 14, 16, 17, 19, 21, 23 };
	public static final byte[] OCTAVE_2 = { 24, 26, 28, 29, 31, 33, 35 };
	public static final byte[] OCTAVE_3 = { 36, 38, 40, 41, 43, 45, 47 };
	public static final byte[] OCTAVE_4 = { 48, 50, 52, 53, 55, 57, 59 };
	public static final byte[] OCTAVE_5 = { 60, 62, 64, 65, 67, 69, 71 };
	public static final byte[] OCTAVE_6 = { 72, 74, 76, 77, 79, 81, 83 };
	public static final byte[] OCTAVE_7 = { 84, 86, 88, 89, 91, 93, 95 };
	public static final byte[] OCTAVE_8 = { 96, 98, 100, 101, 103, 105, 107 };
	public static final byte[] OCTAVE_9 = { 108, 110, 112, 113, 115, 117, 119 };
	public static final byte[] OCTAVE_10 = { 120, 122, 124, 125, 127 };

	// Depreciated, no longer used
	public static final byte[] ALL_VALUES_OCTAVE_0 = { 0, 1, 2, 3, 4, 5, 6, 7,
			8, 9, 10, 11 };
	public static final byte[] ALL_VALUES_OCTAVE_1 = { 12, 13, 14, 15, 16, 17,
			18, 19, 20, 21, 22, 23 };
	public static final byte[] ALL_VALUES_OCTAVE_2 = { 24, 25, 26, 27, 28, 29,
			30, 31, 32, 33, 34, 35 };
	public static final byte[] ALL_VALUES_OCTAVE_3 = { 36, 37, 38, 39, 40, 41,
			42, 43, 44, 45, 46, 47 };
	public static final byte[] ALL_VALUES_OCTAVE_4 = { 48, 49, 50, 51, 52, 53,
			54, 55, 56, 57, 58, 59 };
	public static final byte[] ALL_VALUES_OCTAVE_5 = { 60, 61, 62, 63, 64, 65,
			66, 67, 68, 69, 70, 71 };
	public static final byte[] ALL_VALUES_OCTAVE_6 = { 72, 73, 74, 75, 76, 77,
			78, 79, 80, 81, 82, 83 };
	public static final byte[] ALL_VALUES_OCTAVE_7 = { 84, 85, 86, 87, 88, 89,
			90, 91, 92, 93, 94, 95 };
	public static final byte[] ALL_VALUES_OCTAVE_8 = { 96, 97, 98, 99, 100,
			101, 102, 103, 104, 105, 106, 107 };
	public static final byte[] ALL_VALUES_OCTAVE_9 = { 108, 109, 110, 111, 112,
			113, 114, 115, 116, 117, 118, 119 };
	public static final byte[] ALL_VALUES_OCTAVE_10 = { 120, 121, 122, 123,
			124, 125, 126, 127 };
	public static final char[] MIDI_DURATIONS = { 'w', 'h', 'q', 'i', 's', 't',
		'x', 'o' };
	public static final char[] TAB_DURATIONS = { 'W', 'w', '0', '1', '2',
		'3', '4', '5' };

	char name;
	int octave;
	boolean isSharp = false;
	boolean isFlat = false;
	char duration;
	char accidental;
	byte[] octaveValues;
	byte accidentalValue = 0;
	Exception e;

	public void newNote() {
		name = '\u0000';
		octave = 0;
		isSharp = false;
		isFlat = false;
		duration = '\u0000';
		accidental = '\u0000';
		octaveValues = null;
		accidentalValue = 0;
		
	}
	
	public void setName(char name) {
		this.name = name;
	}

	public void setAccidental(char accidental) throws Exception {
		if (accidental == 'b') {
			isFlat = true;
			this.accidental = accidental;
			accidentalValue = -1;
		} else if (accidental == '#') {
			isSharp = true;
			this.accidental = accidental;
			accidentalValue = 1;
		} else {
			System.out.print("BAD ACCIDENTAL INPUT!");
			e.printStackTrace();
		}
	}

	public void setOctave(char octave) {
		this.octave = Character.getNumericValue(octave);
		if (this.octave == 0) {
			octaveValues = OCTAVE_0;
		} else if (this.octave == 1) {
			octaveValues = OCTAVE_1;
		} else if (this.octave == 2) {
			octaveValues = OCTAVE_2;
		} else if (this.octave == 3) {
			octaveValues = OCTAVE_3;
		} else if (this.octave == 4) {
			octaveValues = OCTAVE_4;
		} else if (this.octave == 5) {
			octaveValues = OCTAVE_5;
		} else if (this.octave == 6) {
			octaveValues = OCTAVE_6;
		} else if (this.octave == 7) {
			octaveValues = OCTAVE_7;
		} else {
			octaveValues = OCTAVE_8;
		}
	}

	public void setDuration(char dur) {
		char tabChar = 'x';
		
		for (int i = 0; i < MIDI_DURATIONS.length; i++) {
			if (dur == MIDI_DURATIONS[i]) {
				tabChar = TAB_DURATIONS[i];
				break;
			}
		}
		
		duration = tabChar;
	}

	public String toString() {
		String str = "Name: " + name + accidental + "; Octave: " + octave
				+ "; Duration: " + duration;
		return str;
	}

	private String getNotePitchString() {
		String val;
		if (isSharp) {
			val = name + accidental + Long.toString(octave);
		} else if (isFlat) {
			val = name + accidental + Long.toString(octave);
		} else {
			val = name + Long.toString(octave);
		}
		return val;
	}

	public byte notePitch() {
		byte pitchValue = -1;
		String val = Character.toString(name);
		System.out.println("NOTE: Converted this: " + getNotePitchString());

		for (int i = 0; i < NATURALS.length; i++) {
			if (val.equals(NATURALS[i])) {
				pitchValue = octaveValues[i];
				break;
			}
		}

		pitchValue = (byte) (pitchValue + accidentalValue);
		System.out.println("NOTE: ...into: " + pitchValue);
		return pitchValue;
	}

	public String getCourseAndFret() {
		String outputStr = null;
		boolean noteWritten = false;
		byte val = notePitch();

		while (val > 69) {
			System.out.println("Note too high!");
			val = (byte) (val - 12);
		}

		while (!noteWritten) {
			for (int i = 0; i < Lute.SIXTH_COURSE.length; i++) {

				if (val == Lute.SIXTH_COURSE[i]) {
					outputStr = "6"
							+ Character.toString(Lute.FRET_POSITIONS[i]);
					noteWritten = true;
				}
			}
			for (int i = 0; i < Lute.FIFTH_COURSE.length; i++) {

				if (val == Lute.FIFTH_COURSE[i]) {
					outputStr = "5"
							+ Character.toString(Lute.FRET_POSITIONS[i]);
					noteWritten = true;
				}
			}
			for (int i = 0; i < Lute.FOURTH_COURSE.length; i++) {

				if (val == Lute.FOURTH_COURSE[i]) {
					outputStr = "4"
							+ Character.toString(Lute.FRET_POSITIONS[i]);
					noteWritten = true;
				}
			}
			for (int i = 0; i < Lute.THIRD_COURSE.length; i++) {

				if (val == Lute.THIRD_COURSE[i]) {
					outputStr = "3"
							+ Character.toString(Lute.FRET_POSITIONS[i]);
					noteWritten = true;
				}
			}
			for (int i = 0; i < Lute.SECOND_COURSE.length; i++) {

				if (val == Lute.SECOND_COURSE[i]) {
					outputStr = "2"
							+ Character.toString(Lute.FRET_POSITIONS[i]);
					noteWritten = true;
				}
			}
			for (int i = 0; i < Lute.FIRST_COURSE.length; i++) {

				if (val == Lute.FIRST_COURSE[i]) {
					System.out.println("The val is: " + val + " so the fret number is i = " + i + " and fret letter " + Lute.FRET_POSITIONS[i]);
					outputStr = "1"
							+ Character.toString(Lute.FRET_POSITIONS[i]);
					noteWritten = true;
				}
			}
			break;
		}

		System.out.println("Val is " + val);
		System.out.println("NOTE: Here's the output: " + outputStr);
		return outputStr;

	}

	  public static String decimalDurationToString(double decimalDuration)
	  {
	    StringBuilder sb = new StringBuilder();
	    System.out.println("NOTE: p1 decimalDuration = " + decimalDuration);
	    if (decimalDuration >= 1.0D)
	    {
	      int countWholes = (int)Math.floor(decimalDuration);
	      sb.append("w");
	      if (countWholes > 1) {
	        sb.append(Integer.toString(countWholes));
	      }
	      decimalDuration -= countWholes;
	      System.out.println("NOTE: p2 decimalDuration = " + decimalDuration);
	    }
	    if (decimalDuration >= 0.75D) {
	      sb.append("h.");
	    } else if ((decimalDuration < 0.75) && (decimalDuration >= 0.5D)){
	    	sb.append("h");
	    } else if ((decimalDuration < 0.5D) && (decimalDuration >= 0.375D)){
	    	sb.append("q.");
	    } else if ((decimalDuration < 0.375D) && (decimalDuration >= 0.25D)){
	    	sb.append("q");
	    } else if ((decimalDuration < 0.25D) && (decimalDuration >= 0.1875D)){
	    	sb.append("i.");
	    } else if ((decimalDuration < 0.1875D) && (decimalDuration >= 0.125D)) {
	    	sb.append("i");
	    } else if ((decimalDuration < 0.125D) && (decimalDuration >= 0.09375D)) {
	    	sb.append("s.");
	    } else if ((decimalDuration < 0.09375D) && (decimalDuration >= 0.0625D)) {
	    	sb.append("s");
	    } else if ((decimalDuration < 0.0625D) && (decimalDuration >= 0.046875D)) {
	    	sb.append("t.");
	    } else if ((decimalDuration < 0.046875D) && (decimalDuration >= 0.03125D)) {
	    	sb.append("t");
	    } else if ((decimalDuration < 0.03125D) && (decimalDuration >= 0.0234375D)) {
	    	sb.append("x.");
	    } else if ((decimalDuration < 0.0234375D) && (decimalDuration >= 0.015625D)) {
	    	sb.append("x");
	    } else if ((decimalDuration < 0.015625D) && (decimalDuration >= 0.01171875D)) {
	    	sb.append("o.");
	    } else if ((decimalDuration < 0.01171875D) && (decimalDuration >= 0.0078125D)) {
	    	sb.append("o");
	    } else if ((decimalDuration < 0.0078125D) && (decimalDuration >= 0.0D)) {
	    	System.out.println("NOTE: Can't get decimal value, using whole note");
	    	return "w";
	    }
	    System.out.println("NOTE: Converted: " + decimalDuration + " into: " + sb.toString());
	    return sb.toString();
	  }

	public char getDuration() {
		char dur = duration;
		return dur;
	}

}
