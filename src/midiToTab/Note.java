package midiToTab;

public class Note {

	public static final char[] VALID_NOTES = { 'A', 'B', 'C', 'D', 'E', 'F',
			'G'};
	public static final char[] VALID_OCTAVES = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8' };
	public static final String[] SHARPS = { "C", "C#", "D", "D#", "E", "F",
			"F#", "G", "G#", "A", "A#", "B" };
	public static final String[] FLATS = { "C", "Db", "D", "Eb", "E", "F",
			"Gb", "G", "Ab", "A", "Bb", "B" };
	public static final byte[] OCTAVE_0 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
			11 };
	public static final byte[] OCTAVE_1 = { 12, 13, 14, 15, 16, 17, 18, 19, 20,
			21, 22, 23 };
	public static final byte[] OCTAVE_2 = { 24, 25, 26, 27, 28, 29, 30, 31, 32,
			33, 34, 35 };
	public static final byte[] OCTAVE_3 = { 36, 37, 38, 39, 40, 41, 42, 43, 44,
			45, 46, 47 };
	public static final byte[] OCTAVE_4 = { 48, 49, 50, 51, 52, 53, 54, 55, 56,
			57, 58, 59 };
	public static final byte[] OCTAVE_5 = { 60, 61, 62, 63, 64, 65, 66, 67, 68,
			69, 70, 70 };
	public static final byte[] OCTAVE_6 = { 72, 73, 74, 75, 76, 77, 78, 79, 80,
			81, 82, 83 };
	public static final byte[] OCTAVE_7 = { 84, 85, 86, 87, 88, 89, 90, 91, 92,
			93, 94, 95 };
	public static final byte[] OCTAVE_8 = { 96, 97, 98, 99, 100, 101, 102, 103,
			104, 105, 106, 107 };
	public static final byte[] OCTAVE_9 = { 108, 109, 110, 111, 112, 113, 114,
			115, 116, 117, 118, 119 };
	public static final byte[] OCTAVE_10 = { 120, 121, 122, 123, 124, 125, 126,
			127 };

	char name;
	int octave;
	boolean isSharp = false;
	boolean isFlat = false;
	char duration;
	char accidental;
	byte[] octaveValues;
	Exception e;

	public void setName(char name) {
		this.name = name;
	}

	public void setAccidental(char accidental) throws Exception {
		if (accidental == 'b') {
			isFlat = true;
			this.accidental = accidental;
		} else if (accidental == '#') {
			isSharp = true;
			this.accidental = accidental;
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

	public void setDuration(char duration) {
		this.duration = duration;
	}

	public String toString() {
		String str = "Name: " + name + accidental + "; Octave: " + octave
				+ "; Duration: " + duration;
		return str;
	}

	public void newNote() {
		name = '\u0000';
		octave = 0;
		isSharp = false;
		isFlat = false;
		duration = '\u0000';
		accidental = '\u0000';
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

		if (isSharp) {
			for (int i = 0; i < SHARPS.length; i++) {
				String val = Character.toString(name);
				System.out.println("Converting this: " + val);
				if (val.equals(SHARPS[i])) {
					pitchValue = octaveValues[i];
					break;
				}
			}
		} else {
			for (int i = 0; i < FLATS.length; i++) {
				String val = Character.toString(name);
				System.out.println("Converting this: " + val);
				if (val.equals(FLATS[i])) {
					System.out.println("Octave Values " + octaveValues);
					pitchValue = octaveValues[i];
					break;
				}
			}
		}

		System.out.println("... into this "+ pitchValue);
		return pitchValue;
	}

	public String toTabOutput(StringBuilder sb) {
		String outputStr = null;
		boolean noteWritten = false;
		byte val = notePitch();
		while (!noteWritten) {
			if (octave > 5) {
				break;
			} else if (isSharp) {
				for (int i = 0; i < Lute.SIXTH_COURSE.length; i++) {

					if (val == Lute.SIXTH_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIFTH_COURSE.length; i++) {

					if (val == Lute.FIFTH_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FOURTH_COURSE.length; i++) {

					if (val == Lute.FOURTH_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.THIRD_COURSE.length; i++) {

					if (val == Lute.THIRD_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.SECOND_COURSE.length; i++) {

					if (val == Lute.SECOND_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIRST_COURSE.length; i++) {

					if (val == Lute.FIRST_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
			} else if (isFlat) {
				for (int i = 0; i < Lute.SIXTH_COURSE.length; i++) {

					if (val == Lute.SIXTH_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIFTH_COURSE.length; i++) {

					if (val == Lute.FIFTH_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FOURTH_COURSE.length; i++) {

					if (val == Lute.FOURTH_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.THIRD_COURSE.length; i++) {

					if (val == Lute.THIRD_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.SECOND_COURSE.length; i++) {

					if (val == Lute.SECOND_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIRST_COURSE.length; i++) {

					if (val == Lute.FIRST_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
			} else if (name == 'X') {
				sb.append("X" + duration);
				noteWritten = true;
			} else {
				for (int i = 0; i < Lute.SIXTH_COURSE.length; i++) {

					if (val == Lute.SIXTH_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIFTH_COURSE.length; i++) {

					if (val == Lute.FIFTH_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FOURTH_COURSE.length; i++) {

					if (val == Lute.FOURTH_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.THIRD_COURSE.length; i++) {

					if (val == Lute.THIRD_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.SECOND_COURSE.length; i++) {

					if (val == Lute.SECOND_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIRST_COURSE.length; i++) {

					if (val == Lute.FIRST_COURSE[i]) {
						outputStr = Character.toString(Lute.FRET_POSITIONS[i])
								+ Character.toString(duration);
						noteWritten = true;
					}
				}
				break;
			}

		}

		System.out.println("Val is " + val);
		System.out.println("Here's the output: " + outputStr);
		return outputStr;

	}

}
