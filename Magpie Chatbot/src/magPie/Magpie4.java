/*
 * WARNING MR. GROSSI, I AM AN OVER ACHIEVER AND MADE THIS PROGRAM OVER 1,000 LINES LONG.
 * A+ FOR THE YEAR!!!
 */

package magPie;

import java.util.ArrayList;

/**
 * @author Joshua Ciffer
 * @version 12/05/2017
 * 
 *          A program to carry on conversations with a human user. This version:
 *          <ul>
 *          <li>Uses advanced search for keywords</li>
 *          <li>Will transform statements as well as react to keywords</li>
 *          </ul>
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
	private ArrayList<String> likes = new ArrayList<>(0);

	/**
	 * Stores things that the user tells the computer that they dislike.
	 * 
	 * @author Joshua Ciffer
	 */
	private ArrayList<String> dislikes = new ArrayList<>(0);

	/**
	 * Stores the users favorite thing.
	 * 
	 * @author Joshua Ciffer
	 */
	private String favoriteTvShow, favoriteColor, favoriteMovie, favoriteTeam, favoriteClass;

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
		if (statement.length() < 1) {
			response = "Say something, please.";
		} 
		else if (findKeyword(statement, "What do I like") >= 0) {
			response = transformWhatDoILikeStatement(statement);
		} 
		else if (findKeyword(statement, "What do I love") >= 0) {
			response = transformWhatDoILoveStatement(statement);
		} 
		else if (findKeyword(statement, "What do I dislike") >= 0) {
			response = transformWhatDoIDislikeStatement(statement);
		} 
		else if (findKeyword(statement, "What do I hate") >= 0) {
			response = transformWhatDoIHateStatement(statement);
		} 
		else if (findKeyword(statement, "I like") >= 0) {
			response = transformILikeStatement(statement);
		} 
		else if (findKeyword(statement, "I love") >= 0) {
			response = transformILoveStatement(statement);
		} 
		else if (findKeyword(statement, "I dislike") >= 0) {
			response = transformIDislikeStatement(statement);
		} 
		else if (findKeyword(statement, "I hate") >= 0) {
			response = transformIHateStatement(statement);
		} 
		else if (findKeyword(statement, "What is my favorite TV show") >= 0) {
			response = transformWhatIsMyFavoriteTvShow(statement);
		} 
		else if (findKeyword(statement, "What is my favorite color") >= 0) {
			response = transformWhatIsMyFavoriteColor(statement);
		} 
		else if (findKeyword(statement, "What is my favorite movie") >= 0) {
			response = transformWhatIsMyFavoriteMovie(statement);
		} 
		else if (findKeyword(statement, "What is my favorite team") >= 0) {
			response = transformWhatIsMyFavoriteTeam(statement);
		} 
		else if (findKeyword(statement, "What is my favorite class") >= 0) {
			response = transformWhatIsMyFavoriteClass(statement);
		} 
		else if (findKeyword(statement, "What is your favorite TV show") >= 0) {
			response = transformWhatIsYourFavoriteTvShow();
		} 
		else if (findKeyword(statement, "What is your favorite color") >= 0) {
			response = transformWhatIsYourFavoriteColor();
		} 
		else if (findKeyword(statement, "What is your favorite movie") >= 0) {
			response = transformWhatIsYourFavoriteMovie();
		} 
		else if (findKeyword(statement, "What is your favorite team") >= 0) {
			response = transformWhatIsYourFavoriteTeam();
		} 
		else if (findKeyword(statement, "What is your favorite class") >= 0) {
			response = transformWhatIsYourFavoriteClass();
		} 
		else if (findKeyword(statement, "favorite TV show") >= 0) {
			response = transformFavoriteTvShowStatement(statement);
		} 
		else if (findKeyword(statement, "favorite color") >= 0) {
			response = transformFavoriteColorStatement(statement);
		} 
		else if (findKeyword(statement, "favorite movie") >= 0) {
			response = transformFavoriteMovieStatement(statement);
		}
		else if (findKeyword(statement, "favorite team") >= 0) {
			response = transformFavoriteTeamStatement(statement);
		} 
		else if (findKeyword(statement, "favorite class") >= 0) {
			response = transformFavoriteClassStatement(statement);
		} 
		else if (findKeyword(statement, "No") >= 0) {
			response = "Why so negative?";
		} 
		else if (findKeyword(statement, "Mother") >= 0 || findKeyword(statement, "Father") >= 0 || findKeyword(
				statement, "Brother") >= 0 || findKeyword(statement, "Sister") >= 0) {
			response = "Tell me more about your family.";
		} 
		else if (findKeyword(statement, "Dog") >= 0 || findKeyword(statement, "Cat") >= 0) {
			response = "Tell me more about your pets.";
		} 
		else if (findKeyword(statement, "Grossi") >= 0) {
			response = "He sounds like a programming good teacher.";
		} 
		else if (findKeyword(statement, "Hello") >= 0) {
			response = "Hello there!";
		} 
		else if (findKeyword(statement, "I hate you") >= 0) {
			response = "I hate you too.";
		} 
		else if (findKeyword(statement, "I love you") >= 0) {
			response = "Ewww.";
		}
		else if (findKeyword(statement, "Oof") >= 0) {
			response = "Oof";
		}
		else if (findKeyword(statement, "I want to") >= 0) {
			response = transformIWantToStatement(statement);
		} 
		else if (findKeyword(statement, "I want") >= 0) {
			response = transformIWantStatement(statement);
		} 
		else {
			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);
			if (psn >= 0 && findKeyword(statement, "me", psn) >= 0) {
				response = transformYouMeStatement(statement);
			} else if (psn >= 0 && findKeyword(statement, "I", 0) >= 0) {
				response = transformIYouStatement(statement);
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
	 * @param statement
	 *            The statement the user enters.
	 * @return Returns the computer's response to the user statement.
	 */
	private String transformIWantStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		int psn = findKeyword(statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 7).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}

	/**
	 * Takes a statement where the user enters "I like" something, and the computer
	 * responds with "Why do you like" something? Adds what the user likes to the
	 * ArrayList likes, and removes it from dislikes if it was there.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The statement the user enters.
	 * @return Returns the computer's response to the statement.
	 */
	private String transformILikeStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		int psn = findKeyword(statement, "I like", 0);
		String restOfStatement = statement.substring(psn + 7).trim();
		for (int i = 0 ; i < this.dislikes.size() ; i++) {
			if (this.dislikes.get(i).equalsIgnoreCase(restOfStatement)) {
				this.dislikes.remove(i);
			}
		}
		this.likes.add(restOfStatement);
		String otherLikes = "";
		if (this.likes.size() > 1) {
			for (int i = 0 ; i < this.likes.size() ; i++) {
				if (this.likes.size() == 1) {
					otherLikes = otherLikes + this.likes.get(i);
				} else if (i == this.likes.size() - 1 && this.likes.size() > 1) {
					otherLikes = otherLikes + "and " + this.likes.get(i) + ".";
				} else {
					otherLikes = otherLikes + this.likes.get(i) + ", ";
				}
			}
			return "Why do you like " + restOfStatement + "?" + "\nThese are the things I know you like: " + otherLikes;
		}
		return "Why do you like " + restOfStatement + "?";
	}

	/**
	 * Takes a statement where the user enters "I love" something, and the computer
	 * responds with "Why do you love" something? Adds what the user likes to the
	 * ArrayList likes, and removes it from dislikes if it was there.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The statement the user enters.
	 * @return Returns the computer's response to the statement.
	 */
	private String transformILoveStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		int psn = findKeyword(statement, "I love", 0);
		String restOfStatement = statement.substring(psn + 7).trim();
		for (int i = 0 ; i < this.dislikes.size() ; i++) {
			if (this.dislikes.get(i).equalsIgnoreCase(restOfStatement)) {
				this.dislikes.remove(i);
			}
		}
		this.likes.add(restOfStatement);
		String otherLikes = "";
		if (this.likes.size() > 1) {
			for (int i = 0 ; i < this.likes.size() ; i++) {
				if (this.likes.size() == 1) {
					otherLikes = otherLikes + this.likes.get(i);
				} else if (i == this.likes.size() - 1 && this.likes.size() > 1) {
					otherLikes = otherLikes + "and " + this.likes.get(i) + ".";
				} else {
					otherLikes = otherLikes + this.likes.get(i) + ", ";
				}
			}
			return "Why do you love " + restOfStatement + "?" + "\nThese are the things I know you like: " + otherLikes;
		}
		return "Why do you love " + restOfStatement + "?";
	}

	/**
	 * Takes a statement where the user enters "I dislike" something, and the
	 * computer responds with "Why do you dislike" something? Adds what the user
	 * dislikes to the ArrayList dislikes, and removes it from likes if it was
	 * there.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The statement the user enters.
	 * @return Returns the computer's response to the statement.
	 */
	private String transformIDislikeStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		int psn = findKeyword(statement, "I dislike", 0);
		String restOfStatement = statement.substring(psn + 10).trim();
		for (int i = 0 ; i < likes.size() ; i++) {
			if (this.likes.get(i).equalsIgnoreCase(restOfStatement)) {
				this.likes.remove(i);
			}
		}
		this.dislikes.add(restOfStatement);
		String otherLikes = "";
		if (this.dislikes.size() > 1) {
			for (int i = 0 ; i < this.dislikes.size() ; i++) {
				if (this.dislikes.size() == 1) {
					otherLikes = otherLikes + dislikes.get(i);
				} else if (i == this.dislikes.size() - 1 && this.dislikes.size() > 1) {
					otherLikes = otherLikes + "and " + this.dislikes.get(i) + ".";
				} else {
					otherLikes = otherLikes + this.dislikes.get(i) + ", ";
				}
			}
			return "Why do you dislike " + restOfStatement + "?" + "\nThese are the things I know you don't like: "
					+ otherLikes;
		}
		return "Why do you dislike " + restOfStatement + "?";
	}

	/**
	 * Takes a statement where the user enters "I hate" something, and the computer
	 * responds with "Why do you hate" something? Adds what the user dislikes to the
	 * ArrayList dislikes, and removes it from likes if it was there.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The statement the user enters.
	 * @return Returns the computer's response to the statement.
	 */
	private String transformIHateStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		int psn = findKeyword(statement, "I hate", 0);
		String restOfStatement = statement.substring(psn + 7).trim();
		for (int i = 0 ; i < likes.size() ; i++) {
			if (this.likes.get(i).equalsIgnoreCase(restOfStatement)) {
				this.likes.remove(i);
			}
		}
		this.dislikes.add(restOfStatement);
		String otherLikes = "";
		if (this.dislikes.size() > 1) {
			for (int i = 0 ; i < this.dislikes.size() ; i++) {
				if (this.dislikes.size() == 1) {
					otherLikes = otherLikes + dislikes.get(i);
				} else if (i == this.dislikes.size() - 1 && this.dislikes.size() > 1) {
					otherLikes = otherLikes + "and " + this.dislikes.get(i) + ".";
				} else {
					otherLikes = otherLikes + this.dislikes.get(i) + ", ";
				}
			}
			return "Why do you hate " + restOfStatement + "?" + "\nThese are the things I know you don't like: "
					+ otherLikes;
		}
		return "Why do you hate " + restOfStatement + "?";
	}

	/**
	 * This method returns all of the user's likes stored in an ArrayList.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The user's statement.
	 * @return Returns all of the things the user likes.
	 */
	private String transformWhatDoILikeStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		String whatILike = "";
		if (this.likes.size() > 0) {
			for (int i = 0 ; i < this.likes.size() ; i++) {
				if (this.likes.size() == 1) {
					whatILike = whatILike + this.likes.get(i);
				} else if (i == this.likes.size() - 1 && this.likes.size() > 1) {
					whatILike = whatILike + "and " + this.likes.get(i) + ".";
				} else {
					whatILike = whatILike + this.likes.get(i) + ", ";
				}
			}
			return "You like " + whatILike + ".";
		}
		return "You haven't told me anything you like yet!";
	}

	/**
	 * This method returns all of the user's likes stored in an ArrayList.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The user's statement.
	 * @return Returns all of the things the user likes.
	 */
	private String transformWhatDoILoveStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		String whatILike = "";
		if (this.likes.size() > 0) {
			for (int i = 0 ; i < this.likes.size() ; i++) {
				if (this.likes.size() == 1) {
					whatILike = whatILike + this.likes.get(i);
				} else if (i == this.likes.size() - 1 && this.likes.size() > 1) {
					whatILike = whatILike + "and " + this.likes.get(i) + ".";
				} else {
					whatILike = whatILike + this.likes.get(i) + ", ";
				}
			}
			return "You like " + whatILike + ".";
		}
		return "You haven't told me anything you like yet!";
	}

	/**
	 * This method returns all of the user's dislikes stored in an ArrayList.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The user's statement.
	 * @return Returns all of the things the user dislikes.
	 */
	private String transformWhatDoIDislikeStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		String whatIDislike = "";
		if (this.dislikes.size() > 0) {
			for (int i = 0 ; i < this.dislikes.size() ; i++) {
				if (this.dislikes.size() == 1) {
					whatIDislike = whatIDislike + this.dislikes.get(i);
				} else if (i == this.dislikes.size() - 1 && this.dislikes.size() > 1) {
					whatIDislike = whatIDislike + "and " + this.dislikes.get(i) + ".";
				} else {
					whatIDislike = whatIDislike + this.dislikes.get(i) + ", ";
				}
			}
			return "You dislike " + whatIDislike + ".";
		}
		return "You haven't told me anything you dislike yet!";
	}

	/**
	 * This method returns all of the user's dislikes stored in an ArrayList.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The user's statement.
	 * @return Returns all of the things the user dislikes.
	 */
	private String transformWhatDoIHateStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		String whatIDislike = "";
		if (this.dislikes.size() > 0) {
			for (int i = 0 ; i < this.dislikes.size() ; i++) {
				if (this.dislikes.size() == 1) {
					whatIDislike = whatIDislike + this.dislikes.get(i);
				} else if (i == this.dislikes.size() - 1 && this.dislikes.size() > 1) {
					whatIDislike = whatIDislike + "and " + this.dislikes.get(i) + ".";
				} else {
					whatIDislike = whatIDislike + this.dislikes.get(i) + ", ";
				}
			}
			return "You don't like " + whatIDislike + ".";
		}
		return "You haven't told me anything you don't like yet!";
	}

	/**
	 * Takes a statement where the user states their favorite TV show. Sets the
	 * user's input as their favorite, and computer responds why.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The statement the user enters.
	 * @return Returns the computer's response.
	 */
	private String transformFavoriteTvShowStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		int psn = findKeyword(statement, "favorite TV show is", 0);
		String restOfStatement = statement.substring(psn + 20).trim();
		this.favoriteTvShow = restOfStatement;
		return getRandomResponse() + " My favorite TV show is Spongebob.";
	}

	/**
	 * Takes a statement where the user states their favorite color. Sets the user's
	 * input as their favorite, and computer responds why.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The statement the user enters.
	 * @return Returns the computer's response.
	 */
	private String transformFavoriteColorStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		int psn = findKeyword(statement, "favorite color is", 0);
		String restOfStatement = statement.substring(psn + 18);
		this.favoriteColor = restOfStatement;
		return getRandomResponse() + " My favorite color is blue.";
	}

	/**
	 * Takes a statement where the user states their favorite movie. Sets the user's
	 * input as their favorite, and computer responds why.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The statement the user enters.
	 * @return Returns the computer's response.
	 */
	private String transformFavoriteMovieStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		int psn = findKeyword(statement, "favorite movie is", 0);
		String restOfStatement = statement.substring(psn + 18);
		this.favoriteMovie = restOfStatement;
		return getRandomResponse() + " My favorite movie is Star Wars.";
	}

	/**
	 * Takes a statement where the user states their favorite team. Sets the user's
	 * input as their favorite, and computer responds why.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The statement the user enters.
	 * @return Returns the computer's response.
	 */
	private String transformFavoriteTeamStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		int psn = findKeyword(statement, "favorite team is", 0);
		String restOfStatement = statement.substring(psn + 17);
		this.favoriteTeam = restOfStatement;
		return getRandomResponse() + " My favorite team is the New York Giants.";
	}

	/**
	 * Takes a statement where the user states their favorite class. Sets the user's
	 * input as their favorite, and computer responds why.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The statement the user enters.
	 * @return Returns the computer's response.
	 */
	private String transformFavoriteClassStatement(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		int psn = findKeyword(statement, "favorite class is", 0);
		String restOfStatement = statement.substring(psn + 18);
		this.favoriteClass = restOfStatement;
		return getRandomResponse() + " My favorite class is Mr. Grossi's Computer Science Class.";
	}

	/**
	 * This method tells the user what their favorite TV show is.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The user's statement.
	 * @return Returns the user's favorite TV show.
	 */
	private String transformWhatIsMyFavoriteTvShow(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		if (this.favoriteTvShow != null) {
			return "Your favorite TV show is " + this.favoriteTvShow + ".";
		} else {
			return "You haven't told me your favorite TV show yet!";
		}
	}

	/**
	 * This method tells the user what their favorite color is.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The user's statement.
	 * @return Returns the user's favorite color.
	 */
	private String transformWhatIsMyFavoriteColor(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		if (this.favoriteColor != null) {
			return "Your favorite color is " + this.favoriteColor + ".";
		} else {
			return "You haven't told me your favorite color yet!";
		}
	}

	/**
	 * This method tells the user what their favorite movie is.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The user's statement.
	 * @return Returns the user's favorite movie.
	 */
	private String transformWhatIsMyFavoriteMovie(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		if (this.favoriteMovie != null) {
			return "Your favorite movie is " + this.favoriteMovie + ".";
		} else {
			return "You haven't told me your favorite movie yet!";
		}
	}

	/**
	 * This method tells the user what their favorite team is.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The user's statement.
	 * @return Returns the user's favorite team.
	 */
	private String transformWhatIsMyFavoriteTeam(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		if (this.favoriteTeam != null) {
			return "Your favorite team is " + this.favoriteTeam + ".";
		} else {
			return "You haven't told me your favorite team yet!";
		}
	}

	/**
	 * This method tells the user what their favorite class is.
	 * 
	 * @author Joshua Ciffer
	 * @param statement
	 *            The user's statement.
	 * @return Returns the user's favorite class.
	 */
	private String transformWhatIsMyFavoriteClass(String statement) {
		statement = statement.trim(); // Removes any unnecessary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		if (this.favoriteClass != null) {
			return "Your favorite class is " + this.favoriteClass + ".";
		} else {
			return "You haven't told me your favorite class yet!";
		}
	}

	/**
	 * The computer tells its favorite TV show.
	 * 
	 * @author Joshua Ciffer
	 * @return The computer's favorite TV show.
	 */
	private String transformWhatIsYourFavoriteTvShow() {
		return "My favorite TV show is Spongebob.";
	}

	/**
	 * The computer tells its favorite color.
	 * 
	 * @author Joshua Ciffer
	 * @return The computer's favorite color.
	 */
	private String transformWhatIsYourFavoriteColor() {
		return "My favorite color is blue.";
	}

	/**
	 * The computer tells its favorite movie.
	 * 
	 * @author Joshua Ciffer
	 * @return The computer's favorite movie.
	 */
	private String transformWhatIsYourFavoriteMovie() {
		return "My favorite movie is Star Wars.";
	}

	/**
	 * The computer tells its favorite team.
	 * 
	 * @author Joshua Ciffer
	 * @return The computer's favorite team.
	 */
	private String transformWhatIsYourFavoriteTeam() {
		return "My favorite team is the New York Giants.";
	}

	/**
	 * The computer tells its favorite class.
	 * 
	 * @author Joshua Ciffer
	 * @return The computer's favorite class.
	 */

	private String transformWhatIsYourFavoriteClass() {
		return "My favorite class is Mr. Grossi's Computer Science Class.";
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
	 * @param statement
	 *            The statement the user enters.
	 * @return The computer's response to the user's statement.
	 */
	private String transformIYouStatement(String statement) {
		statement = statement.trim(); // Removes any unessecary whitespace.
		String lastChar = statement.substring(statement.length() - 1); // Checks to see if last char is a .
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1); // Removes . if it is the last char in the
																		// string.
		}
		int psnOfI = findKeyword(statement, "I", 0);
		int psnOfYou = findKeyword(statement, "you", psnOfI + 1);
		String restOfStatement = statement.substring(psnOfI + 3, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
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
	 * @return a non-commital string
	 */
	private String getRandomResponse() {
		final int NUMBER_OF_RESPONSES = 18;
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
		} else if (whichResponse == 4) {
			response = "Go on, continue.";
		} else if (whichResponse == 5) {
			response = "Intriguing.";
		} else if (whichResponse == 6) {
			response = "Outstanding.";
		} else if (whichResponse == 7) {
			response = "I don't understand.";
		} else if (whichResponse == 8) {
			response = "But, why?";
		} else if (whichResponse == 9) {
			response = "MAKE AMERICA GREAT AGAIN.";
		} else if (whichResponse == 10) {
			response = "Hi Grossi.";
		} else if (whichResponse == 11) {
			response = "Can you say that again?";
		} else if (whichResponse == 12) {
			response = "What was that?";
		} else if (whichResponse == 13) {
			response = "What else?";
		} else if (whichResponse == 14) {
			response = "Holy crap this program is long.";
		} else if (whichResponse == 15) {
			response = "That's awesome.";
		} else if (whichResponse == 16) {
			response = "What else do you want to tell me?";
		} else if (whichResponse == 17) {
			response = "I just added all these responses so the program would hit 1,000 lines long. BOOM. A+ FOR THE YEAR GROSSI.";
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
		return this.likes;
	}

	/**
	 * Returns things the user dislikes.
	 * 
	 * @author Joshua Ciffer
	 * @return Array of things user dislikes.
	 */
	public ArrayList<String> getDislikes() {
		return this.dislikes;
	}

	/**
	 * Returns user's favorite TV show.
	 * 
	 * @author Joshua Ciffer
	 * @return Favorite TV show
	 */
	public String getFavoriteTvShow() {
		return this.favoriteTvShow;
	}

	/**
	 * Returns user's favorite color.
	 * 
	 * @author Joshua Ciffer
	 * @return Favorite color
	 */
	public String getFavoriteColor() {
		return this.favoriteColor;
	}

	/**
	 * Returns user's favorite movie.
	 * 
	 * @author Joshua Ciffer
	 * @return Favorite movie
	 */
	public String getFavoriteMovie() {
		return this.favoriteMovie;
	}

	/**
	 * Returns user's favorite team.
	 * 
	 * @author Joshua Ciffer
	 * @return Favorite team
	 */
	public String getFavoriteTeam() {
		return this.favoriteTeam;
	}

	/**
	 * Returns user's favorite class.
	 * 
	 * @author Joshua Ciffer
	 * @return Favorite class.
	 */
	public String getFavoriteClass() {
		return this.favoriteClass;
	}

}