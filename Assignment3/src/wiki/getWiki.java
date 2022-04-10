package wiki;

import Jwiki.Jwiki;

public class getWiki {
	public static String parseString(String str) {
		// for now this will look for the biggest letter in the string, and assume that
		// is the title , the person wants to search up for.
		if (str == null || str.length() < 1)
			return null;

		String maxWord = "";
		int maxLength = 0;
		for (String word : str.split(" ")) {
			if (word.length() > maxLength) {
				maxLength = word.length();
				maxWord = word;
			}
		}
		if (maxWord.length() < 1) {
			return "psychatrist";
		}
		return maxWord;
	}

	public static String info(String title) {
		if (title.length() < 1 || title == null)
			return "Invalid Input";
		title = verifyTitle(title);
		Jwiki jwiki = new Jwiki(title);
		String resp = jwiki.getExtractText();
		if (resp == null) {
			resp = "Could not find any relevant matches on wikipedia";
		}
		return resp;
	}

	// these methods , can be used for further use.
	public static String imgUrl(String title) {
		title = verifyTitle(title);
		Jwiki jwiki = new Jwiki(title);
		String resp = jwiki.getImageURL();
		return resp;
	}

	private static String verifyTitle(String title) {
		title = title.replace("  ", " ");
		title = title.replace("[^A-Za-z0-9 -]", "");
		title = title.toLowerCase();
		return title;
	}

}
