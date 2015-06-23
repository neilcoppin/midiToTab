package midiToTab;

public class TabBuilder {

	StringBuilder line = new StringBuilder("x      ");
		
	public void newLine(){
		line.delete(0, 7);
		line.append("x      ");
	}
	
	public void addNote(String str, char cha){
		
		int intCourse = Character.getNumericValue(str.charAt(0));
		byte course = (byte)intCourse;
		char fret = str.charAt(1);
		
		// write duration
		System.out.println("TABBUILDER: Duration line.insert: " + cha);
		line.deleteCharAt(0);
		line.insert(0, cha);
		
		//write finger position
		System.out.println("TABBUILDER: Fingering line.insert: " + course + fret);
		line.deleteCharAt(course);
		line.insert(course, fret);
	}
	
	public String commitLine(){
		//System.out.println("TABBUILDER: Committing: " + line.toString());
		return line.toString();
	}
	
	
		
}
