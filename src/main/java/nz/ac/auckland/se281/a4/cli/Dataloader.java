package nz.ac.auckland.se281.a4.cli;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import nz.ac.auckland.se281.a4.Tweet;
import nz.ac.auckland.se281.a4.TwitterHandle;
//*********************************
//YOU SHOULD NOT MODIFY THIS CLASS
//*********************************

public class Dataloader {
	final static String PATH_TO_JSON_FILE = "./testcases/tweets.json";

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 * 
	 * @return a list of tweets
	 * @throws JsonIOException
	 *             Cant read JSON file
	 * @throws IOException
	 *             Cant find JSON file
	 */
	public static List<Tweet> loadTweetsFromJson() throws JsonIOException, IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.create();
		Type listType = new TypeToken<List<Tweet>>() {
		}.getType();
		return gson.fromJson(Files.newBufferedReader(Paths.get(PATH_TO_JSON_FILE)), listType);
	}

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 * 
	 * @param nodes
	 *            a list of nodes
	 * @param tweets
	 *            a list of tweets
	 * @return A map of TwitterHandles to their tweets
	 */
	public static Map<TwitterHandle, List<Tweet>> allocateTweetsToUsers(Set<TwitterHandle> nodes, List<Tweet> tweets) {
		Map<TwitterHandle, List<Tweet>> map = new LinkedHashMap<TwitterHandle, List<Tweet>>();
		for (TwitterHandle node : nodes) {
			map.put(new TwitterHandle(node.getValue()), new ArrayList<Tweet>());
		}

		int index = 0;
		for (Tweet tweet : tweets) {
			map.get(nodes.toArray()[index]).add(tweet);
			index++;
			if (index == nodes.size()) {
				index = 0;
			}
		}
		return map;
	}
}
