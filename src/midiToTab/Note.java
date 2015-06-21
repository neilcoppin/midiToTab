package midiToTab;

public class Note {

	char name;
	int octave;
	boolean isSharp = false;
	boolean isFlat = false;
	char duration;
	char accidental;
	Exception e;
	
	public void setName(char name) {
		this.name = name;
	}
	
	public void setAccidental(char accidental) throws Exception {
		if(accidental == 'b') {
			isFlat = true;
			this.accidental = accidental;
		}
		else if(accidental == '#') {
			isSharp = true;
			this.accidental = accidental;
		}
		else {
			System.out.print("BAD ACCIDENTAL INPUT!");
			e.printStackTrace();
		}
	}
	
	public void setOctave(char octave){
		this.octave = Character.getNumericValue(octave);
	}
	
	public void setDuration(char duration){
		this.duration = duration;
	}
	
	public String toString(){
		String str = "Name: " + name + accidental + "; Octave: " + octave + "; Duration: " + duration;
		return str;
	}
	
	public void newNote(){
		name = '\u0000';
		octave = 0;
		isSharp = false;
		isFlat = false;
		duration = '\u0000';
		accidental = '\u0000';
	}
	
	private String notePitch(){
		String val;
		if(isSharp){
			val = name + accidental + Long.toString(octave);
		}
		else if (isFlat){
			val = name + accidental + Long.toString(octave);
		}
		else {
			val = name + Long.toString(octave);
		}
		return val;
	}

	public String toTabOutput(StringBuilder sb) {
		String outputStr = null;
		boolean noteWritten = false;
		while (!noteWritten){
			if(octave > 5){
				break;
			}
			else if(isSharp){
				for (int i = 0; i < Lute.SIXTH_COURSE_SHARPS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.SIXTH_COURSE_SHARPS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIFTH_COURSE_SHARPS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.FIFTH_COURSE_SHARPS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FOURTH_COURSE_SHARPS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.FOURTH_COURSE_SHARPS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.THIRD_COURSE_SHARPS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.THIRD_COURSE_SHARPS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.SECOND_COURSE_SHARPS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.SECOND_COURSE_SHARPS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIRST_COURSE_SHARPS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.FIRST_COURSE_SHARPS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
			}
			else if(isFlat){
				for (int i = 0; i < Lute.SIXTH_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.SIXTH_COURSE_FLATS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIFTH_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.FIFTH_COURSE_FLATS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FOURTH_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.FOURTH_COURSE_FLATS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.THIRD_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.THIRD_COURSE_FLATS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.SECOND_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.SECOND_COURSE_FLATS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIRST_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.FIRST_COURSE_FLATS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
			} else if (name == 'X'){
				sb.append("X" + duration);
				noteWritten = true;
			} else {
				for (int i = 0; i < Lute.SIXTH_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.SIXTH_COURSE_FLATS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIFTH_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.FIFTH_COURSE_FLATS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FOURTH_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.FOURTH_COURSE_FLATS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.THIRD_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.THIRD_COURSE_FLATS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.SECOND_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.SECOND_COURSE_FLATS[i])){
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				for (int i = 0; i < Lute.FIRST_COURSE_FLATS.length; i++) {
					String val = notePitch();
					if(val.equals(Lute.FIRST_COURSE_FLATS[i])){
						//sb.append(Lute.FRET_POSITIONS[i] + duration);
						outputStr = Character.toString(Lute.FRET_POSITIONS[i]) + Character.toString(duration);
						noteWritten = true;
					}
				}
				break;
			}
			
		}
		
		System.out.println("Here's the output: " + outputStr);
		return outputStr;
		
	}
	
}
