import java.util.Arrays;

/**
 * This class provides tests for the Eliza program.
 *
 * &lt;p&gt;Bugs: None known.
 *
 * @author Rebekah Klemm
 */
public class ElizaTests {

	/**
	 * This method checks to ensure that Eliza.chooseString randomly picks one
	 * of the strings from the list, and that it can handle null lists (returns
	 * null), an empty list (returns null), and a list with only one single
	 * string.
	 */
	public static void testChooseString() {

		// is one of the 3 strings chosen?
		String[] strList = { "The", "happy", "cat" };
		String choice = Eliza.chooseString(strList);
		if (choice != null && (choice.equals("The") || choice.equals("happy") 
				|| choice.equals("cat"))) {
			System.out.println("testChooseString 1 passed.");
		} else {
			System.out.println("testChooseString 1 failed.");
		}

		// if I call it 100 times, are the choices approximately random?
		int countThe = 0;
		int countHappy = 0;
		int countCat = 0;
		for (int i = 1; i <= 100; i++) {
			choice = Eliza.chooseString(strList);
			if (choice != null) {
				if (choice.equals("The")) {
					countThe++;
				} else if (choice.equals("happy")) {
					countHappy++;
				} else if (choice.equals("cat")) {
					countCat++;
				}
			}
		}
		if (countThe >= 20 && countThe <= 45 && countHappy >= 20 
				&& countHappy <= 45 && countCat >= 20 && countCat <= 45) {
			System.out.println("testChooseString 2 passed.");
		} else {
			System.out.println("testChooseString 2 failed.");
		}

		// if I pass a null value, does it return null?
		choice = Eliza.chooseString(null);
		if (choice == null) {
			System.out.println("testChooseString 3 passed.");
		} else {
			System.out.println("testChooseString 3 failed.");
		}

		// if I pass an empty string value, does it return null?
		String[] empty = {};
		choice = Eliza.chooseString(empty);

		if (choice == null) {
			System.out.println("testChooseString 4 passed.");
		} else {
			System.out.println("testChooseString 4 failed.");
		}

		// if I pass an array with a single string, does it return the single
		// string?
		String[] single = { "one" };
		choice = Eliza.chooseString(single);
		if (choice != null && (choice.equals("one"))) {
			System.out.println("testChooseString 5 passed.");
		} else {
			System.out
			.println("testChooseString 5 failed");
		}
	}

	/**
	 * This method checks to ensure that Eliza.inList can correctly identify a
	 * word from an array and return the index of the word. If there are
	 * multiple matches, this checks that the first matching word is returned.
	 * If a word within the array is a zero length string, it returns the
	 * correct index. If a word within the array is a null value, it is ignored.
	 */
	public static void testInList() {

		// Check return value using word from the list
		String[] quitWords = { "bye", "goodbye", "quit", "bye" };
		int index = Eliza.inList("bye", quitWords);
		if (index >= 0) {
			System.out.println("testInList 1 passed.");
		} else {
			System.out.println("testInList 1 failed.");
		}

		// Check return value using a word NOT from the list
		index = Eliza.inList("seeya", quitWords);
		if (index == -1) {
			System.out.println("testInList 2 passed.");
		} else {
			System.out.println("testInList 2 failed.");
		}

		// Check to see what happens if "good" is looked for within the
		// quitWords above
		index = Eliza.inList("good", quitWords);
		if (index == -1) {
			System.out.println("testInList 3 passed.");
		} else {
			System.out.println("testInList 3 failed.");
		}

		// Confirm that the correct index is being passed if a word is listed
		// multiple times
		index = Eliza.inList("bye", quitWords);
		if (index == 0) {
			System.out.println("testInList 4 passed.");
		} else {
			System.out.println("testInList 4 failed.");
		}

		// Checks if a word in the array is a zero length string, it returns
		// correct index
		String[] emptyStringQuitWords = { "bye", "goodbye", "", "quit", "bye" };
		index = Eliza.inList("", emptyStringQuitWords);
		if (index == 2) {
			System.out.println("testInList 5 passed.");
		} else {
			System.out.println("testInList 5 failed.");
		}

		// Checks if a word in the array is null, it is ignored.
		String[] nullQuitWords = { "bye", null, "goodbye", "", "quit", "bye" };
		index = Eliza.inList("goodbye", nullQuitWords);
		if (index == 2) {
			System.out.println("testInList 6 passed.");
		} else {
			System.out.println("testInList 6 failed.");
		}
	}

	/**
	 * Tests to see if the program can combine the strings together with a space
	 * between each and return the resulting string. Confirms that a list with 0
	 * elements returns a string with length 0 (""). Confirms that if any
	 * elements within the list are null or zero length strings, they are
	 * ignored. Confirms that if the passed array is null, null is returned.
	 * 
	 * 
	 */
	public static void testAssemblePhrase() {
		String[] words = { "This", "is a", "sentence" };
		String sentence = Eliza.assemblePhrase(words);

		String expectedSentence = "This is a sentence";

		if (sentence.equals(expectedSentence)) {
			System.out.println("testAssemblePhrase 1 passed.");
		} else {
			System.out.println("testAssemblePhrase 1 failed.");
		}

		// Tests what happens when an array has zero elements (is empty)
		String[] emptyArray = {};
		String nullSentence = Eliza.assemblePhrase(emptyArray);
		expectedSentence = "";
		if (nullSentence.equals(expectedSentence)) {
			System.out.println("testAssemblePhrase 2 passed.");
		} else {
			System.out.println("testAssemblePhrase 2 failed.");
		}

		// If any elements within the list are null or zero length strings,
		// then ignore them.
		String[] ignoreNull = { "This", "", "is", null, "test 3" };
		String test3 = Eliza.assemblePhrase(ignoreNull);
		expectedSentence = "This is test 3";
		if (test3.equals(expectedSentence)) {
			System.out.println("testAssemblePhrase 3 passed.");
		} else {
			System.out.println("testAssemblePhrase 3 failed.");
		}

		// Confirms that program returns null if the array is null
		String test4Sentence = Eliza.assemblePhrase(null);
		expectedSentence = null;
		if (test4Sentence == expectedSentence) {
			System.out.println("testAssemblePhrase 4 passed.");
		} else {
			System.out.println("testAssemblePhrase 4 failed.");

		}
	}

	/**
	 * Checks that the program returns the longest sentence, based on the String
	 * length, from the array of sentences. Checks that if sentences is null or
	 * has a length of 0 then null is returned.
	 */
	public static void testFindLongest() {
		// Check to ensure longest string is returned
		String[] sentences = { "short", "This is longer.", 
				"This is the longest one.", "s" };
		String longest = Eliza.findLongest(sentences);
		if (longest == sentences[2]) {
			System.out.println("testFindLongest 1 passed.");
		} else {
			System.out.println("testFindLongest 1 failed.");
			System.out.println(longest);
		}

		// Check to ensure an empty (zero length) array returns null
		String[] emptyArray = {};
		longest = Eliza.findLongest(emptyArray);
		if (longest == null) {
			System.out.println("testFindLongest 2 passed.");
		} else {
			System.out.println("testFindLongest 2 failed.");
		}

		// Check to ensure a null array returns null
		longest = Eliza.findLongest(null);
		if (longest == null) {
			System.out.println("testFindLongest 3 passed.");
		} else {
			System.out.println("testFindLongest 3 failed.");
		}
	}

	/**
	 * Checks that the program counts the correct number of times a substring is
	 * in the str. Makes sure it does not count overlapping substrings
	 * separately. Makes sure that if either parameter substring or str is null
	 * then -1 is returned. Makes sure that if the substring is the empty string
	 * ("") then the length of the string is returned.
	 */
	public static void testHowMany() {
		// checks that the correct number of spaces are returned
		int countSpaces = Eliza.howMany(" ", " you me ");
		if (countSpaces == 3) {
			System.out.println("testHowMany 1 passed.");
		} else {
			System.out.println("testHowMany 1 failed.");
		}

		// checks that the string <2> is returned once
		int countNum = Eliza.howMany("<2>", "What makes you think I am <2>?");
		if (countNum == 1) {
			System.out.println("testHowMany 2 passed.");
		} else {
			System.out.println("testHowMany 2 failed.");
		}

		// checks that overlapping substrings are not counted separately
		// hahah and hah should count once
		int countHah = Eliza.howMany("hah", "hahah");
		if (countHah == 1) {
			System.out.println("testHowMany 3 passed.");
		} else {
			System.out.println("testHowMany 3 failed.");
		}

		// hahah and ha should count twice
		int countHa = Eliza.howMany("ha", "hahah");
		if (countHa == 2) {
			System.out.println("testHowMany 4 passed.");
		} else {
			System.out.println("testHowMany 4 failed.");
		}

		// If parameter substring is null then -1 is returned.
		int countNull = Eliza.howMany(null, "Testing for null substring");
		if (countNull == -1) {
			System.out.println("testHowMany 5 passed.");
		} else {
			System.out.println("testHowMany 5 failed");
		}

		// If parameter str is null then -1 is returned.
		int countNull2 = Eliza.howMany("testing", null);
		if (countNull2 == -1) {
			System.out.println("testHowMany 6 passed.");
		} else {
			System.out.println("testHowMany 6 failed");
		}

		// Makes sure that if the substring is an empty string ("") then
		// the length of the string is returned.
		int countEmpty = Eliza.howMany("", "This is the string to test");
		if (countEmpty == 26) {
			System.out.println("testHowMany 7 passed.");
		} else {
			System.out.println("testHowMany 7 failed.");
		}

	}

	/**
	 * Checks that the filterChars method substitutes spaces for all characters
	 * except alphabetic characters, numbers, and ' , ! . and ? . Checks that
	 * (,!?.) characters were changed to (!). Parenthesis not included. Checks
	 * that if the userInput is null then return null.
	 */
	public static void testFilterChars() {
		String userInput = "w? #t   i't e   4t m-s!";
		char[] expectedChars = { 'w', '!', ' ', ' ', 't', ' ', ' ', ' ', 'i',
				'\'', 't', ' ', 'e', ' ', ' ', ' ', '4',
				't', ' ', 'm', ' ', 's', '!' };
		char[] characters = Eliza.filterChars(userInput);
		if (userInput.length() == characters.length) {
			System.out.println("testFilterChars 1 passed.");
		} else {
			System.out.println("testFilterChars 1 failed.");
		}
		boolean error = false;
		for (int i = 0; i < expectedChars.length; i++) {
			if (expectedChars[i] != characters[i]) {
				System.out.print(
					String.format("characters[%d] '%c' expected " + "'%c'\n", 
							i, characters[i], expectedChars[i]));
				error = true;
			}
		}
		if (error) {
			System.out.println("testFilterChars 2 failed.");
		} else {
			System.out.println("testFilterChars 2 passed.");
		}

		// if userInput is null, return null
		char[] nullArray = Eliza.filterChars(null);
		if (nullArray == null) {
			System.out.println("testFilterChars 3 passed.");
		} else {
			System.out.println("testFilterChars 3 failed.");
		}

	}

	/**
	 * Checks that all sequences of 2 or more spaces are reduced to 1 space
	 * within the characters array. Checks that if any spaces were removed then
	 * the same number of Null character '\u0000' fills the elements at the end
	 * of the array.
	 */
	public static void testRemoveDuplicateSpaces() {
		char[] chars1 = { 'a', 't', ' ', '.', ' ', ' ', 'g', ' ', ' ', 
				'h', ' ' };
		Eliza.removeDuplicateSpaces(chars1);
		char[] expectedResult = { 'a', 't', ' ', '.', ' ', 'g', ' ', 'h', 
				' ', '\u0000', '\u0000' };

		boolean error = false;
		String errorStr = "";
		for (int i = 0; i < expectedResult.length; i++) {
			if (chars1[i] != expectedResult[i]) {
				errorStr += String.format("chars1[%d] '%c' expected '%c'\n", 
						i, chars1[i], expectedResult[i]);
				error = true;
			}
		}
		if (error) {
			System.out.println("testRemoveDuplicateSpaces 1 failed.");
		} else {
			System.out.println("testRemoveDuplicateSpaces 1 passed.");
		}

		// testing if three in a row are empty
		char[] chars2 = { 'a', 't', '.', ' ', ' ', ' ', 'g', ' ', ' ', 'h', 
				' ' };
		Eliza.removeDuplicateSpaces(chars2);
		char[] expectedResult2 = { 'a', 't', '.', ' ', 'g', ' ', 'h', ' ', 
				'\u0000', '\u0000', '\u0000' };

		boolean error2 = false;
		String errorStr2 = "";
		for (int i = 0; i < expectedResult2.length; i++) {
			if (chars2[i] != expectedResult2[i]) {
				errorStr2 += String.format("chars1[%d] '%c' expected '%c'\n", 
						i, chars2[i], expectedResult2[i]);
				error2 = true;
			}
		}
		if (error2) {
			System.out.println("testRemoveDuplicateSpaces 2 failed.");
		} else {
			System.out.println("testRemoveDuplicateSpaces 2 passed.");
		}

	}

	/**
	 * Checks that if any of the quit words are found then true is returned,
	 * otherwise false is returned.
	 * 
	 */
	public static void testFindAnyWords() {
		String[] someWords = { "Going", "now", "goodbye" };
		boolean found = Eliza.findAnyWords(someWords, Config.QUIT_WORDS);
		if (found) {
			System.out.println("testFindAnyWords 1 passed.");
		} else {
			System.out.println("testFindAnyWords 1 failed.");
		}

		String[] someMoreWords = { "Hello", "how", "are", "you" };
		found = Eliza.findAnyWords(someMoreWords, Config.QUIT_WORDS);
		if (!found) {
			System.out.println("testFindAnyWords 2 passed.");
		} else {
			System.out.println("testFindAnyWords 2 failed.");
		}

		// Checks to make sure the word "good" (a partial QUIT_WORDS) returns
		// false.
		String[] someMoreWords2 = { "good", "how", "are", "you" };
		found = Eliza.findAnyWords(someMoreWords2, Config.QUIT_WORDS);
		if (!found) {
			System.out.println("testFindAnyWords 3 passed.");
		} else {
			System.out.println("testFindAnyWords 3 failed.");
		}
	}

	/**
	 * Checks to ensure that the methods up to this point are working correctly.
	 */
	public static void testInitialProcessing() {
		String sentence = Eliza.initialProcessing("What? This isn't the " + 
				"    4th messy-sentence!");
		if (sentence != null && 
				sentence.equals("this isn't the 4th messy sentence")) {
			System.out.println("testInitialProcessing 1 passed.");
		} else {
			System.out.println("testInitialProcessing 1 failed:");
		}

		// test 2
		String sentence2 = Eliza
				.initialProcessing("Short sentence? This is " + 
						"supposed to return the longest sentence!");
		if (sentence2 != null && 
				sentence2.equals("this is supposed to return the longest " + 
						"sentence")) {
			System.out.println("testInitialProcessing 2 passed.");
		} else {
			System.out.println("testInitialProcessing 2 failed:");
		}

	}

	/**
	 * Checks to ensure that words are correctly identified and replaced with
	 * the proper words from the afterList.
	 */
	public static void testReplacePairs() {
		String[] someWords = { "i'm", "cant", "recollect" };
		String[] beforeList = { "dont", "cant", "wont", "recollect", "i'm" };
		String[] afterList = { "don't", "can't", "won't", "remember", "i am" };
		String[] result = Eliza.replacePairs(someWords, beforeList, afterList);
		if (result != null && result[0].equals("i") && result[1].equals("am") &&
				result[2].equals("can't") && result[3].equals("remember")) {
			System.out.println("testReplacePairs 1 passed.");
		} else {
			System.out.println("testReplacePairs 1 failed.");
		}

		// check null works properly
		String[] someWords2 = null;
		String[] beforeList2 = { "dont", "cant", "wont", "recollect", "i'm" };
		String[] afterList2 = { "don't", "can't", "won't", "remember", "i am" };
		String[] result2 = Eliza.replacePairs(someWords2, beforeList2, 
				afterList2);
		if (result2 == null) {
			System.out.println("testReplacePairs 2 passed.");
		} else {
			System.out.println("testReplacePairs 2 failed.");
		}

	}

	/**
	 * Checks to see if Eliza correctly recognizes the pattern in a sentence,
	 * even if the pattern word comes last, first, or middle. It also checks to
	 * make sure Eliza can handle patterns of more than one word, and returns
	 * the correct response based on whether the pattern is in the right order
	 * (returns array) or the wrong order (returns null).
	 * 
	 */
	public static void testFindPatternInSentence() {
		//finds pattern when it's the last word in sentence
		String[] pattern1 = { "computer" };
		String[] sentence1 = { "are", "you", "a", "computer" };

		String[] matches = Eliza.findPatternInSentence(pattern1, sentence1);
		if (matches != null && matches.length == 2 && 
				matches[0].equals("are you a") && matches[1].equals("")) {
			System.out.println("testFindPatternInSentence 1 passed.");
		} else {
			System.out.println("testFindPatternInSentence 1 failed.");
			System.out.println(Arrays.toString(matches));
		}

		//finds pattern when it's the first word in sentence
		String[] pattern2 = { "computer" };
		String[] sentence2 = { "computer", "are", "you" };

		matches = Eliza.findPatternInSentence(pattern2, sentence2);
		if (matches != null && matches.length == 2 && matches[0].equals("") && 
				matches[1].equals("are you")) {
			System.out.println("testFindPatternInSentence 2 passed.");
		} else {
			System.out.println("testFindPatternInSentence 2 failed.");
			System.out.println(Arrays.toString(matches));
		}

		//finds pattern when it's the middle word in the sentence
		String[] pattern3 = { "computer" };
		String[] sentence3 = { "does", "that", "computer", "on", "your", 
				"desk", "work" };
		matches = Eliza.findPatternInSentence(pattern3, sentence3);
		if (matches != null && matches.length == 2 && 
				matches[0].equals("does that") && 
				matches[1].equals("on your desk work")) {
			System.out.println("testFindPatternInSentence 3 passed.");
		} else {
			System.out.println("testFindPatternInSentence 3 failed.");
			System.out.println(Arrays.toString(matches));
		}

		//finds patterns with two words in the correct order
		String[] pattern4 = { "you", "me" };
		String[] sentence4 = { "why", "don't", "you", "like", "me" };
		matches = Eliza.findPatternInSentence(pattern4, sentence4);
		if (matches != null && matches.length == 3 
				&& matches[0].equals("why don't") && matches[1].equals("like")
				&& matches[2].equals("")) {
			System.out.println("testFindPatternInSentence 4 passed.");
		} else {
			System.out.println("testFindPatternInSentence 4 failed.");
			System.out.println(Arrays.toString(matches));
		}

		//does NOT identify the pattern when it's in the wrong order
		String[] pattern5 = { "you", "me" };
		String[] sentence5 = { "me", "don't", "like", "you" };
		matches = Eliza.findPatternInSentence(pattern5, sentence5);
		if (matches == null) {
			System.out.println("testFindPatternInSentence 5 passed.");
		} else {
			System.out.println("testFindPatternInSentence 5 failed.");
			System.out.println(Arrays.toString(matches));
		}

		//finds pattern when it requires returning 3 strings
		String[] pattern6 = { "i", "dreamed" };
		String[] sentence6 = { "do", "you", "know", "that", "i", "have", 
				"dreamed", "of", "being", "an", "astronaut" };
		matches = Eliza.findPatternInSentence(pattern6, sentence6);
		if (matches != null && matches.length == 3 
				&& matches[0].equals("do you know that") 
				&& matches[1].equals("have")
				&& matches[2].equals("of being an astronaut")) {
			System.out.println("testFindPatternInSentence 6 passed.");
		} else {
			System.out.println("testFindPatternInSentence 6 failed.");
			System.out.println(Arrays.toString(matches));
		}

		//returns two empty strings in a row when pattern is first two words
		String[] pattern7 = { "you", "are" };
		String[] sentence7 = { "you", "are", "very", "observant" };
		matches = Eliza.findPatternInSentence(pattern7, sentence7);
		if (matches != null && matches.length == 3 
				&& matches[0].equals("") && matches[1].equals("")
				&& matches[2].equals("very observant")) {
			System.out.println("testFindPatternInSentence 7 passed.");
		} else {
			System.out.println("testFindPatternInSentence 7 failed.");
			System.out.println(Arrays.toString(matches));
		}
		
		//returns two empty strings in first and last positions
		String[] pattern8 = { "you", "me" };
		String[] sentence8 = { "you", "don't", "argue", "with", "me" };
		matches = Eliza.findPatternInSentence(pattern8, sentence8);
		if (matches != null && matches.length == 3 
				&& matches[0].equals("") 
				&& matches[1].equals("don't argue with")
				&& matches[2].equals("")) {
			System.out.println("testFindPatternInSentence 8 passed.");
		} else {
			System.out.println("testFindPatternInSentence 8 failed.");
			System.out.println(Arrays.toString(matches));
		}

		// test "my boyfriend made me come here" to trace what was wrong with 
		//my program
		String[] pattern9 = { "my" };
		String[] sentence9 = { "my", "boyfriend", "made", "me", "come", "here"};
		matches = Eliza.findPatternInSentence(pattern9, sentence9);
		if (matches != null && matches.length == 2 && matches[0].equals("")
				&& matches[1].equals("boyfriend made me come here")) {
			System.out.println("testFindPatternInSentence 9 passed.");
		} else {
			System.out.println("testFindPatternInSentence 9 failed.");
			System.out.println(Arrays.toString(matches));
		}

		// test "you don't seem to say much" to trace what was wrong with my
		//program
		String[] pattern10 = { "you" };
		String[] sentence10 = { "you", "don't", "seem", "to", "say", "much" };
		matches = Eliza.findPatternInSentence(pattern10, sentence10);
		if (matches != null && matches.length == 2 && matches[0].equals("")
				&& matches[1].equals("don't seem to say much")) {
			System.out.println("testFindPatternInSentence 10 passed.");
		} else {
			System.out.println("testFindPatternInSentence 10 failed.");
			System.out.println(Arrays.toString(matches));
		}
		
		
	}

	/**
	 * Checks that Eliza correctly replaces words in the phrase if they are in
	 * the Config.POST_PROCESS_BEFORE with the corresponding words from
	 * Config.POST_PROCESS_AFTER.
	 */
	public static void testPrepareUserPhrase() {
		String someWords = "i'm happy";
		String result = Eliza.prepareUserPhrase(someWords);
		if (result != null && result.equals("you are happy")) {
			System.out.println("testPrepareUserPhrase 1 passed.");
		} else {
			System.out.println("testPrepareUserPhrase 1 failed.");
		}

		// Check that other words in the Before/After lists are correctly
		// identified
		String someWords2 = "i love myself";
		String result2 = Eliza.prepareUserPhrase(someWords2);
		if (result2 != null && result2.equals("you love yourself")) {
			System.out.println("testPrepareUserPhrase 2 passed.");
		} else {
			System.out.println("testPrepareUserPhrase 2 failed.");
		}

		// Check that null returns null
		String result3 = Eliza.prepareUserPhrase(null);
		if (result3 == null) {
			System.out.println("testPrepareUserPhrase 3 passed.");
		} else {
			System.out.println("testPrepareUserPhrase 3 failed.");
		}
	}

	/**
	 * Checks to make sure that if draftResponse contains <1>, <2>, <3> or
	 * <4> then the corresponding userPhrase is substituted for the <1>, <2>,
	 * etc. It also checks to make sure that the userPhrase correctly exchanged
	 * words from the POST_PROCESS files. If draftResponse is null, then null is
	 * returned. If userPhrases is null then draftResponse is returned.
	 */
	public static void testPrepareResponse() {
		String draftResponse = "Really, <3>?";
		String[] userPhrases = { "", "", "about my dog" };
		String response = Eliza.prepareResponse(draftResponse, userPhrases);

		String expectedResponse = "Really, about your dog?";

		if (response.equals(expectedResponse)) {
			System.out.println("testPrepareResponse 1 passed.");
		} else {
			System.out.println("testPrepareResponse 1 failed.");
		}

		// test for null
		String draftResponse2 = null;
		String[] userPhrases2 = { "", "", "about my dog" };
		String response2 = Eliza.prepareResponse(draftResponse2, userPhrases2);

		String expectedResponse2 = null;

		if (response2 == expectedResponse2) {
			System.out.println("testPrepareResponse 2 passed.");
		} else {
			System.out.println("testPrepareResponse 2 failed.");
		}

		// test for userResponse null (return draftResponse)
		String draftResponse3 = "Really, <3>?";
		String[] userPhrases3 = null;
		String response3 = Eliza.prepareResponse(draftResponse3, userPhrases3);

		String expectedResponse3 = draftResponse3;

		if (response3.equals(expectedResponse3)) {
			System.out.println("testPrepareResponse 3 passed.");
		} else {
			System.out.println("testPrepareResponse 3 failed.");
		}

		
	}

	/**
	 * Confirms that matchResponse can find the first pattern that matches the
	 * words and chooses a random response. Also confirms that the random
	 * response correctly replaces <1>, <2>, etc if necessary. Confirms that if
	 * the words parameter is null then a response from NO_MATCH_RESPONSES is
	 * chosen.
	 * 
	 */
	public static void testMatchResponse() {
		
		//matches first pattern correctly
		String[] words1 = { "are", "you", "a", "computer" };
		String response1 = Eliza.matchResponse(words1);
		if (Eliza.inList(response1, Config.RESPONSE_TABLE[0][1]) >= 0) {
			System.out.println("testMatchResponse 1 passed.");
		} else {
			System.out.println("testMatchResponse 1 failed." + response1);
		}

		//matches later patterns correctly
		String[] words2 = { "you", "are", "like", "my", "father" };
		String response2 = Eliza.matchResponse(words2);
		if (response2 != null && response2.contains("like your father")) {
			System.out.println("testMatchResponse 2 passed.");
		} else {
			System.out.println("testMatchResponse 2 failed." + response2);
		}

		// pass a null array
		String[] words3 = new String[5];
		String response3 = Eliza.matchResponse(words3);
		if (response3 != null) {
			System.out.println("testMatchResponse 3 passed.");
		} else {
			System.out.println("testMatchResponse 3 failed.");
		}

		// test "you don't argue with me"
		String[] words4 = { "you", "don't", "argue", "with", "me" };
		String response4 = Eliza.matchResponse(words4);
		if (response4 != null && response4.contains("argue")) {
			System.out.println("testMatchResponse 4 passed.");
		} else {
			System.out.println("testMatchResponse 4 failed.");
		}
		
		// test "yes"
				String[] words5 = { "yes" };
				String response5 = Eliza.matchResponse(words5);
				if (response5 != null && response5.contains("")) {
					System.out.println("testMatchResponse 5 passed.");
				} else {
					System.out.println("testMatchResponse 5 failed.");
				}

	}

	private static void testProblem(String problem) {
		// supporting method for testProcessInput
		System.out.println("Patient:  " + problem);
		System.out.println("Eliza: " + Eliza.processInput(problem));
	}

	/**
	 * Tests a whole series of sentences to ensure the proper responses are
	 * received.  When QUIT_WORDS occur, null is returned.
	 */
	public static void testProcessInput() {
		// note: the responses may vary as they are randomly selected and the
		// random generator results will vary based on the previous times it
		// has been called. Therefore, see if each response is appropriate.

		// The following are selected phrases from:
		// http://web.stanford.edu/group/SHR/4-2/text/dialogues.html
		testProblem("I like computers.");
		testProblem("What is your name?");
		testProblem("You remind me of a grumpy uncle.");
		testProblem("You don't seem to say much.");
		testProblem("You reflect me.");
		testProblem("I wish to believe you.");
		testProblem("I dreamed of going into space.");
		testProblem("I apologize for boring you.");
		testProblem("Because it seems the polite thing to do.");
		testProblem("Yes.");
		testProblem("I remember being in 1st grade.");
		testProblem("No, not often. Sometimes I remember being on a boat.");
		testProblem("My family liked to play cards.");
		testProblem("Do you remember growing up?");
		testProblem("Are you a Packers fan?");
		testProblem("I am sad to hear that.");
		testProblem("I cannot explain this.");
		testProblem("You seem to have a different perspective than many.");
		testProblem("I'm talking to my dog.");
		testProblem("goodbye");
	}

	/**
	 * This is where the program starts.
	 */
	public static void main(String[] args) {
		// feel free to comment out tests that are not of current interest
		// in order to focus on certain tests. Eventually, all the tests
		// should run successfully.

		testChooseString();

		testInList();
		testAssemblePhrase();
		testFindLongest();
		testHowMany();
		//
		testFilterChars();
		testRemoveDuplicateSpaces();
		testFindAnyWords();
		testInitialProcessing();
		//
		testReplacePairs();
		testFindPatternInSentence();
		testPrepareUserPhrase();
		testPrepareResponse();
		//
		testMatchResponse();
		testProcessInput();

	}
}
