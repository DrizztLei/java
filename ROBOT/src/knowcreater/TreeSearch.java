package knowcreater;

import net.paoding.analysis.FenciTest;

public class TreeSearch {

	/**
	 * The method is to search the match string .
	 * 
	 * @param word
	 */

	private Word[] word;
	private int length;

	public Word[] getWord() {
		return word;
	}

	public int getLength() {
		return length;
	}

	public TreeSearch(Word[] word) {
		// TODO Auto-generated constructor stub
		this.word = word;
		this.length = word.length;
	}

	public String[] match() throws Exception {
		// TODO Auto-generated method stub
		return processSimple();
	}

	private String[] processSimple() throws Exception {
		return FenciTest.getTrie().query(word);
	}
}