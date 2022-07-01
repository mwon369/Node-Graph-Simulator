package nz.ac.auckland.se281.a4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nz.ac.auckland.se281.a4.ds.Graph;
import nz.ac.auckland.se281.a4.ds.Node;

//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************
public class TweetGraph extends Graph {

	protected List<Tweet> tweets;
	// Change this to map
	protected Map<TwitterHandle, List<Tweet>> nodeTweets;

	public TweetGraph(List<String> edges, List<Tweet> tweets, Map<TwitterHandle, List<Tweet>> map) throws Exception {
		super(edges);
		this.tweets = tweets;
		// changed to LinkedHashMap for fixed order
		this.nodeTweets = new LinkedHashMap<>();
		nodeTweets = map;
	}

	public List<Tweet> getTweets(Node n) {
		return nodeTweets.get(n);
	}

	public List<String> getTweetsTexts(TwitterHandle n){
		List<String> texts = new ArrayList<>(); // Only allowed to use ArrayList HERE !!!
		for(Tweet t : getTweets(n)){
			texts.add(t.getTextString());
		}
		return texts;
	}

	// search for a keyword in a tweet starting from a given node
	public String searchTweet(TwitterHandle user, String tweetKeyword) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("No successor of ");
		sb.append(user.getName());
		sb.append("tweeted ");
		sb.append(tweetKeyword);
		String tweetSearchResult = sb.toString();
		
		List<Node<String>> allUsers = depthFirstSearch(user, true);
		for (Node<String> users : allUsers) {
			List<Tweet> tweets = this.getTweets(users);
			for (Tweet tweet : tweets) {
				if (tweet.getTextString().contains(tweetKeyword)) {
					sb.setLength(0);
					sb.append("The tweet string found is: ");
					sb.append(tweet.getTextString());
					sb.append("\n");
					sb.append("User ");
					sb.append(user.getName());
					sb.append(" {");
					sb.append(user.getID());
					sb.append("} ");
					sb.append("tweeted ");
					sb.append(tweetKeyword);
					tweetSearchResult = sb.toString();
					return tweetSearchResult;
				}
			}
		}
		
		return tweetSearchResult;
	}
}
