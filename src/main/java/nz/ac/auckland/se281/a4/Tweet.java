package nz.ac.auckland.se281.a4;

import java.time.LocalDate;

public class Tweet {
	private final LocalDate tweetDate;
	private final int countLikes;
	private final int countRetweets;
	private final String textString;
	private final boolean hasImage;
	private final boolean hasVideo;

	/**
	 * Creates tweet based on the inputs
	 * 
	 * @param tweetDate
	 *            The tweet Date
	 * @param countLikes
	 *            The amount of likes
	 * @param countRetweets
	 *            The amount of retweets
	 * @param textString
	 *            The tweet
	 * @param hasImage
	 *            Whether the tweet has an image
	 * @param hasVideo
	 *            Whether the tweet has a video
	 */
	public Tweet(LocalDate tweetDate, int countLikes, int countRetweets, String textString, boolean hasImage,
			boolean hasVideo) {
		super();
		this.tweetDate = tweetDate;
		this.countLikes = countLikes;
		this.countRetweets = countRetweets;
		this.textString = textString;
		this.hasImage = hasImage;
		this.hasVideo = hasVideo;
	}

	/**
	 * @return the tweetDate
	 */
	public LocalDate getTweetDate() {
		return tweetDate;
	}

	/**
	 * @return the countLikes
	 */
	public int getCountLikes() {
		return countLikes;
	}

	/**
	 * @return the countRetweets
	 */
	public int getCountRetweets() {
		return countRetweets;
	}

	/**
	 * @return the textString
	 */
	public String getTextString() {
		return textString;
	}

	/**
	 * @return the hasImage
	 */
	public boolean hasImage() {
		return hasImage;
	}

	/**
	 * @return the hasVideo
	 */
	public boolean hasVideo() {
		return hasVideo;
	}

	@Override
	public String toString() {
		return "Tweet [tweetDate=" + tweetDate + ", countLikes=" + countLikes + ", countRetweets=" + countRetweets
				+ ", textString=" + textString + ", hasImage=" + hasImage + ", hasVideo=" + hasVideo + "]";
	}

}
