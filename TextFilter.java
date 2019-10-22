public class TextFilter{

	static String exceptionCharacters;
	
	public TextFilter(){
		exceptionCharacters = "[-+.^:,];?-/\'\"!\'_";
	}
	
	
	//______________________mystery value of 20007 was present, I don't know what this was
	public static String filterText(String word){
		//makes the string lower case
		word = word.toLowerCase();
		
		String newWord = "";
	
		//operation to be performed on every character in the word
		for(int i = 0; i < word.length(); i++){
			//if there is not an exception character
			if(!checkForException(word.charAt(i)) && Character.isAlphabetic((word.charAt(i)))){
				newWord += word.charAt(i);
			}
		}
		return newWord;
	}
	
	/*checks for exception characters*/
	private static boolean checkForException(char currentChar){
		for(int k = 0; k< exceptionCharacters.length(); k++){
			if(exceptionCharacters.charAt(k) == currentChar){
				return true;
			}
		}
		return false;
	}

}
