package serverSentence;

public class SentenceProcessor {

	public int countWords(String sentence) {
		
		String words[] = sentence.split(" ");
		
		return words.length;
		
	}
	
	
}