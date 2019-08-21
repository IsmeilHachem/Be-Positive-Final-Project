package co.grandcircus.bepositive;

import java.util.HashSet;
import java.util.Set;

public class WordFilter {

	public static boolean badwordfinder(String words) {

		boolean flag = false;
		Set<String> wordSet = new HashSet<String>();
		wordSet.add("anal");
		wordSet.add("ass");
		wordSet.add("ball sucking");
		wordSet.add("bastard");
		wordSet.add("big tits");
		wordSet.add("bitches");
		wordSet.add("blowjob");
		wordSet.add("blow job");
		wordSet.add("boner");
		wordSet.add("boob");
		wordSet.add("boobs");
		wordSet.add("bullshit");
		wordSet.add("butthole");
		wordSet.add("cock");
		wordSet.add("cocks");
		wordSet.add("cunt");
		wordSet.add("faggot");
		wordSet.add("asshole");
		wordSet.add("fuck");
		wordSet.add("fuckin");
		wordSet.add("fucking");
		wordSet.add("god damn");
		wordSet.add("damn");
		wordSet.add("bitch");
		wordSet.add("hooker");
		wordSet.add("nigger");
		wordSet.add("jack off");
		wordSet.add("motherfucker");
		wordSet.add("penis");
		wordSet.add("piece of shit");
		wordSet.add("pussy");
		wordSet.add("schlong");
		wordSet.add("shit");
		wordSet.add("slut");
		wordSet.add("sucks");
		wordSet.add("suck");
		wordSet.add("tits");
		wordSet.add("titties");
		words = words.toLowerCase();
		for (String s : wordSet) {
			if (words.contains(s)) {
				flag = true;
			}
		}
		return flag;
	}
}
