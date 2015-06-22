package midiToTab;

//Represents Renaissance lute tuned in G
public class Lute {

	// Courses up to fret 5 with flats
	public static final byte[] SIXTH_COURSE = { 31, 32, 33, 34, 35 };
	public static final byte[] FIFTH_COURSE = { 36, 37, 38, 39, 40 };
	public static final byte[] FOURTH_COURSE = { 41, 42, 43, 44, 45 };
	public static final byte[] THIRD_COURSE = { 45, 46, 47, 48, 49 };
	public static final byte[] SECOND_COURSE = { 50, 51, 52, 53, 54 };
	public static final byte[] FIRST_COURSE = { 55, 56, 57, 58, 59, 60, 61, 62,
			63, 64, 65, 66, 67, 68, 69 };

	// Courses up to fret 5 with flats
	public static final String[] SIXTH_COURSE_FLATS_STRING = { "G2", "Ab2",
			"A2", "Bb2", "B2" };
	public static final String[] FIFTH_COURSE_FLATS_STRING = { "C3", "Db3",
			"D3", "Eb3", "E3" };
	public static final String[] FOURTH_COURSE_FLATS_STRING = { "F3", "Gb3",
			"G3", "Ab3", "A3" };
	public static final String[] THIRD_COURSE_FLATS_STRING = { "A3", "Bb3",
			"B3", "C4", "Db4" };
	public static final String[] SECOND_COURSE_FLATS_STRING = { "D4", "Eb4",
			"E4", "F4", "Gb4" };
	public static final String[] FIRST_COURSE_FLATS_STRING = { "G4", "Ab4",
			"A4", "Bb4", "B4", "C5", "Db5", "D5", "Eb5", "E5", "F5", "Gb5",
			"G5", "Ab5", "A5" };

	// Courses up to fret 5 with sharps
	public static final String[] SIXTH_COURSE_SHARPS_STRING = { "G2", "G#2",
			"A2", "A#2", "B2" };
	public static final String[] FIFTH_COURSE_SHARPS_STRING = { "C3", "C#3",
			"D3", "D#3", "E3" };
	public static final String[] FOURTH_COURSE_SHARPS_STRING = { "F3", "F#3",
			"G3", "G#3", "A3" };
	public static final String[] THIRD_COURSE_SHARPS_STRING = { "A3", "A#3",
			"B3", "C4", "C#4" };
	public static final String[] SECOND_COURSE_SHARPS_STRING = { "D4", "D#4",
			"E4", "F4", "F#4" };
	public static final String[] FIRST_COURSE_SHARPS_STRING = { "G4", "G#4",
			"A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5",
			"G5", "G#5", "A5" };

	// Fret positions (DO NOT USE J)
	public static final char[] FRET_POSITIONS = { 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p' };

}
