package magPie;

import java.util.ArrayList ;

/**
 * @author Joshua Ciffer
 * @version 12/05/2017
 * 
 * A program to carry on conversations with a human user. This version:
 * <ul>
 * <li>Uses advanced search for keywords</li>
 * <li>Will transform statements as well as react to keywords</li>
 * </ul>
 * 
 * @author Laurie White
 * @version April 2012
 *
 */
public class Magpie4 {

	/**
	 * Stores things that the user tells the computer that they like.
	 * 
	 * @author Joshua Ciffer
	 */
	private ArrayList<String> likes = new ArrayList<>(0) ;
	
	/**
	 * Stores things that the user tells the computer that they dislike.
	 * 
	 * @author Joshua Ciffer
	 */
	private ArrayList<String> dislikes = new ArrayList<>(0) ;
	
	/**
	 * Stores the users favorite thing.
	 * 
	 * @author Joshua Ciffer
	 */
	private String favoriteTvShow, favoriteColor, favoriteMovie, favoriteTeam, favoriteClass ;
	
	/**
	 * Get a default greeting
	 * 
	 * @return a greeting
	 */
	public String getGreeting() {
		return "Hello, let's talk.";
	}

	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement) {
		String response = "";
		if (statement.length() == 0) {
			response = "Say something, please.";
		}

		else if (findKeyword(statement, "no") >= 0) {
			response = "Why so negative?";
		} else if (findKeyword(statement, "mother") >= 0 || findKeyword(statement, "father") >= 0 || findKeyword(
				statement, "sister") >= 0 || findKeyword(statement, "brother") >= 0) {
			response = "Tell me more about your family.";
		}

		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0) {
			response = transformIWantToStatement(statement);
		}
		
		else if (findKeyword(statement, "I want", 0) >= 0) {
			response = transformIWantStatement(statement) ;
		}
		
		else if (findKeyword(statement, "I like", 0) >= 0) {
			response = transformILikeStatement(statement) ;
		}
		
		else if (findKeyword(statement, "I dislike", 0) >= 0) {
			response = transformIDislikeStatement(statement) ;
		}
		
		else {
			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0 && findKeyword(statement, "me", psn) >= 0) {
				response = transformYouMeStatement(statement);
			} else if (psn >= 0 && findKeyword(statement, "I", 0) >= 0) {
				response = transformIYouStatement(statement) ;
			} else {
				response = getRandomResponse();
			}
		}
		return response;
	}

	/**
	 * Take a statement with "I want to <something>." and transform it into "What
	 * would it mean to <something>?"
	 * 
	 * @param statement
	 *            the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement) {
		// Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword(statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}

	/**
	 * Takes a statement where the user states "I want" something, and the computer
	 * responds with "Would you really be happy if you had" something?
	 * 
	 * @author Joshua Ciffer
	 * @param statement The statement the user enters.
	 * @return Returns the computer's response to the user statement.
	 */
	private String transformIWantStatement(String statement) {
		statement = statement.trim() ;	 // Removes any unessecary whitespace.
		String lastChar = statement.substring(statement.length() - 1) ;		// Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1) ;	// Removes . if it is the last char in the string.
		}
		int psn = findKeyword(statement, "I want", 0) ;
		String restOfStatement = statement.substring(psn + 7).trim() ;
		return "Would you really be happy if you had " + restOfStatement + "?" ;
	}
	
	/**
	 * Takes a statement where the user enters "I like" something, and the computer
	 * responds with "Why do you like" something?  Adds what the user likes to the
	 * ArrayList likes, and removes it from dislikes if it was there.
	 * 
	 * @author Joshua Ciffer
	 * @param statement The statement the user enters.
	 * @return Returns the computer's response to the statement.
	 */
	private String transformILikeStatement(String statement) {
		statement = statement.trim() ;	 // Removes any unessecary whitespace.
		String lastChar = statement.substring(statement.length() - 1) ;		// Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1) ;	// Removes . if it is the last char in the string.
		} 
		int psn = findKeyword(statement, "I like", 0) ;
		String restOfStatement = statement.substring(psn + 7).trim() ;
		for (int i = 0 ; i < dislikes.size() ; i++) {
			if (dislikes.get(i).equalsIgnoreCase(restOfStatement)) {
				dislikes.remove(i) ;
			}
		}
		likes.add(restOfStatement) ;
		String otherLikes = "" ;
		if (likes.size() > 1) {
			for (int i = 0 ; i < likes.size() ; i++) {
				if (likes.size() == 1) {
					otherLikes = otherLikes + likes.get(i) ;
				} else if (i == likes.size() - 1 && likes.size() > 1) {
					otherLikes = otherLikes + "and " + likes.get(i) + "." ;
				} else {
					otherLikes = otherLikes + likes.get(i) + ", " ;
				}
			}
			return "Why do you like " + restOfStatement + "?" + "\nYou also told me you like: " + otherLikes ;
		}
		return "Why do you like " + restOfStatement + "?" ;
	}
	
	/**
	 * Takes a statement where the user enters "I dislike" something, and the computer
	 * responds with "Why do you dislike" something?  Adds what the user dislikes to the
	 * ArrayList dislikes, and removes it from likes if it was there.
	 * 
	 * @author Joshua Ciffer
	 * @param statement The statement the user enters.
	 * @return Returns the computer's response to the statement.
	 */
	private String transformIDislikeStatement(String statement) {
		statement = statement.trim() ;	 // Removes any unessecary whitespace.
		String lastChar = statement.substring(statement.length() - 1) ;		// Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1) ;	// Removes . if it is the last char in the string.
		} 
		int psn = findKeyword(statement, "I dislike", 0) ;
		String restOfStatement = statement.substring(psn + 10).trim() ;
		for (int i = 0 ; i < likes.size() ; i++) {
			if (likes.get(i).equalsIgnoreCase(restOfStatement)) {
				likes.remove(i) ;
			}
		}
		dislikes.add(restOfStatement) ;
		String otherLikes = "" ;
		if (dislikes.size() > 1) {
			for (int i = 0 ; i < dislikes.size() ; i++) {
				if (dislikes.size() == 1) {
					otherLikes = otherLikes + dislikes.get(i) ;
				} else if (i == dislikes.size() - 1 && dislikes.size() > 1) {
					otherLikes = otherLikes + "and " + dislikes.get(i) + "." ;
				} else {
					otherLikes = otherLikes + dislikes.get(i) + ", " ;
				}
			}
			return "Why do you dislike " + restOfStatement + "?" + "\nYou also told me you dislike: " + otherLikes ;
		}
		return "Why do you dislike " + restOfStatement + "?" ;
	}
	
	/**
	 * Take a statement with "you <something> me" and transform it into "What makes
	 * you think that I <something> you?"
	 * 
	 * @param statement
	 *            the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement) {
		// Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}

		int psnOfYou = findKeyword(statement, "you", 0);
		int psnOfMe = findKeyword(statement, "me", psnOfYou + 3);

		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}

	/**
	 * Takes a statement where the user enters "I [...] you." and the computer
	 * responds with "Why do you [...] me?".
	 * 
	 * @author Joshua Ciffer
	 * @param statement The statement the user enters.
	 * @return The computer's response to the user's statement.
	 */
	private String transformIYouStatement(String statement) {
		statement = statement.trim() ;	 // Removes any unessecary whitespace.
		String lastChar = statement.substring(statement.length() - 1) ;		// Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1) ;	// Removes . if it is the last char in the string.
		}
		int psnOfI = findKeyword(statement, "I", 0) ;
		int psnOfYou = findKeyword(statement, "you", psnOfI + 1) ;
		String restOfStatement = statement.substring(psnOfI + 3, psnOfYou).trim() ;
		return "Why do you " + restOfStatement + " me?" ;
		
	}
	
	/**
	 * Search for one word in phrase. The search is not case sensitive. This method
	 * will check that the given goal is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 * 
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in statement or -1 if it's
	 *         not found
	 */
	private int findKeyword(String statement, String goal, int startPos) {
		String phrase = statement.trim();
		// The only change to incorporate the startPos is in the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);

		// Refinement--make sure the goal isn't part of a word
		while (psn >= 0) {
			// Find the string of length 1 before and after the word
			String before = " ", after = " ";
			if (psn > 0) {
				before = phrase.substring(psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length()) {
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}

			// If before and after aren't letters, we've found the word
			if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before is not a letter
					&& ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))) {
				return psn;
			}

			// The last position didn't work, so let's find the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);

		}

		return -1;
	}

	/**
	 * Search for one word in phrase. The search is not case sensitive. This method
	 * will check that the given goal is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no"). The search begins at the beginning
	 * of the string.
	 * 
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's
	 *         not found
	 */
	private int findKeyword(String statement, String goal) {
		return findKeyword(statement, goal, 0);
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * 
	 * @return a non-committal string
	 */
	private String getRandomResponse() {
		final int NUMBER_OF_RESPONSES = 4;
		double r = Math.random();
		int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0) {
			response = "Interesting, tell me more.";
		} else if (whichResponse == 1) {
			response = "Hmmm.";
		} else if (whichResponse == 2) {
			response = "Do you really think so?";
		} else if (whichResponse == 3) {
			response = "You don't say.";
		}

		return response;
	}

	/**
	 * Returns things the user likes.
	 * 
	 * @author Joshua Ciffer
	 * @return Array of things user likes.
	 */
	public ArrayList<String> getLikes() {
		return this.likes ;
	}
	
	/**
	 * Returns things the user dislikes.
	 * 
	 * @author Joshua Ciffer
	 * @return Array of things user dislikes.
	 */
	public ArrayList<String> getDislikes() {
		return this.dislikes ;
	}
	
	/**
	 * Returns user's favorite TV show.
	 * 
	 * @author Joshua Ciffer
	 * @return Favorite TV show
	 */
	public String getFavoriteTvShow() {
		return this.favoriteTvShow ;
	}
	
	/**
	 * Returns user's favorite color.
	 * 
	 * @author Joshua Ciffer
	 * @return Favorite color
	 */
	public String getFavoriteColor() {
		return this.favoriteColor ;
	}
	
	/**
	 * Returns user's favorite movie.
	 * 
	 * @author Joshua Ciffer
	 * @return Favorite movie
	 */
	public String getFavoriteMovie() {
		return this.favoriteMovie ;
	}
	
	/**
	 * Returns user's favorite team.
	 * 
	 * @author Joshua Ciffer
	 * @return Favorite team
	 */
	public String getFavoriteTeam() {
		return this.favoriteTeam ;
	}
	
	/**
	 * Returns user's favorite class.
	 * 
	 * @author Joshua Ciffer
	 * @return Favorite class.
	 */
	public String getFavoriteClass() {
		return this.favoriteClass ;
	}
	
}