package nz.ac.auckland.se281.a4.cli;

//*******************************
//YOU SHOULD NOT MODIFY THIS CLASS
//*******************************

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonIOException;

import nz.ac.auckland.se281.a4.Tweet;
import nz.ac.auckland.se281.a4.TwitterHandle;
import nz.ac.auckland.se281.a4.ds.Node;

/**
 * This is the view class in this program using MVC The SetUI class reads a file
 * in dot format It also has a method to read user commands over this file
 *
 * @author PARTHA ROOP
 */
public class GraphUI {
	// This stores the file 
	private String fileName;
	private String filePath;
	// This is set to true if a valid file has been opened
	private boolean fileStatus;
	// These store the lines, the elements of the set and the elements of the
	// relation
	// when the file is successfully opened
	private final List<String> fileLines;
	private final List<String> setElements;
	private final List<String> relationElements;
	// private final List<String> weightElements;

	// fields for the tweets
	// private final CreateJsonTweets cJT;

	public static Scanner scanner = new Scanner(System.in);

	public GraphUI() {
		System.out.println("\033[H\033[2J"); // Clear the screen
		printTopicScreen();
		fileName = null;
		filePath = null;
		fileLines = new ArrayList<>();
		fileStatus = false;
		setElements = new ArrayList<>();
		relationElements = new ArrayList<>();
	}

	/**
	 * @return command from user as string
	 */
	public String getCommand() {
		System.out.print(">> ");
		return scanner.nextLine();
	}

	/**
	 * This method opens the file 
	 * @param file
	 */
	public void open(String file) {
		setFileName(file);

		if (!processFile()) {
			System.out.println("File doesn't exist");
			System.out.println("Enter a valid file name");
		} else {
			// refresh the vectors every time a new file is opened
			setElements.clear();
			relationElements.clear();
			// weightElements.clear();
			createSetElements();
			makeTokensGraph();
		}
	}

	public void list() {
		listSetMembers();
		listRelationMembers();
	}

	/**
	 * 
	 * @param elements
	 * @return String of elements separated by ","
	 */
	private String concatenateElements(Enumeration<String> elements) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		while (elements.hasMoreElements()) {
			String nextEl = (String) elements.nextElement();
			sb.append(nextEl);
			if (elements.hasMoreElements()) {
				sb.append(",");
			}
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * This method sets the file name
	 * @param file
	 */
	public void setFileName(String file) {
		fileName = file;
		filePath = createFileName(file);
	}

	/**
	 * This methods gets the name of
	 * the file
	 * 
	 * @return file name
	 */
	public String getFileName() {
		return fileName;
	}	

	/**
	 * This method gets the file path
	 * @return file path
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * This method gets the file status
	 * @return file status
	 */
	public boolean getFileStatus() {
		return fileStatus;
	}

	/**
	 * This method sets the file status
	 * to true
	 */
	public void setFileStatusTrue() {
		fileStatus = true;
	}

	/**
	 * This method sets the file status
	 * to false
	 */
	public void setFileStatusFalse() {
		fileStatus = false;
	}

	/**
	 * This method gets the set elements
	 * @return set elements
	 */
	public List<String> getSetElements() {
		return setElements;
	}

	/**
	 * This method returns the set elements with associated user names
	 * @return
	 */
	public List<String> getListTwitterHandle(){
		List<String> listTwitterHandle = new ArrayList<>();
		for (String s : setElements) {
			StringBuilder sb = new StringBuilder();
			sb.append(s + " {" + new TwitterHandle(s).getName() + "}");
			listTwitterHandle.add(sb.toString());
		}
		return listTwitterHandle;
	}

	/**
	 * This method gets the relation elements
	 * @return relation elements
	 */
	public List<String> getRelationElements() {
		return relationElements;
	}

	/**
	 * This method lists the set members
	 */
	private void listSetMembers() {
		System.out.println("The set elements are: " + concatenateElements(Collections.enumeration(setElements)));
	}

	/**
	 * This method lists the eq-class
	 * @param node
	 * @param set
	 */
	protected void listEqClass(String node, List<String> set) {
		System.out.println("The eq-class of: [" + node + "] = " + concatenateElements(Collections.enumeration(set)));
	}

	/**
	 * This method lists the nodes visited (in order) using DFS
	 * @param dFSOrderedList
	 */
	protected void listDFS(List<Node<String>> dFSOrderedList) {
		for (Node<String> nextUser : dFSOrderedList) {
			System.out.println("The next user visited in DFS order is: " + "[" + nextUser.getValue() + "]");
		}
	}
	/**
	 * This method lists the nodes visited (in order) using BFS
	 * @param bFSOrderedList
	 */
	protected void listBFS(List<Node<String>> bFSOrderedList) {
		for (Node<String> nextUser : bFSOrderedList) {
			System.out.println("The next user visited in BFS order is: " + "[" + nextUser.getValue() + "]");
		}
	}

	/**
	 * This method lists the relation members
	 */
	private void listRelationMembers() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		int size = relationElements.size();
		for (String rel : relationElements) {
			sb.append("(");
			sb.append(rel);
			sb.append(")");
			if (size > 1) {
				sb.append(",");
			}
			size--;
		}
		sb.append("}");
		System.out.println("The relational elements are: " + sb.toString());

	}

	/**
	 * This method creates the file name
	 * @param file
	 * @return
	 */
	private String createFileName(String file) {
		String line = System.getProperty("user.dir");
		System.out.println("The current directory is " + line);
		line = line + File.separator + "testcases" + File.separator + file;
		System.out.println("The full path name is: " + line);
		return line;
	}

	/**
	 * This method prints the topic screen
	 */
	public void printTopicScreen() {
		System.out.println("\u001b[36;1m----------------------------------------------------------------------");
		System.out.println("The Tweeter Simulator. To know available commands, please type 'help'");
		System.out.println("----------------------------------------------------------------------\u001b[0m");
	}

	/**
	 * This method opens the file and If successful sets the file status to true
	 * File status is false otherwise
	 * 
	 * @return true if file is opened successfully
	 */
	private boolean processFile() {
		fileLines.clear();
		try {
			File f = new File(filePath);
			if (!f.exists()) {
				setFileStatusFalse();
				return false;
			} else {
				setFileStatusTrue();
			}
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				fileLines.add(s.nextLine());
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File reading error");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * This method creates the set elements
	 */
	private void createSetElements() {
		String parts[] = fileLines.get(0).split("//");
		String tokens[] = parts[1].split(",");
		int i = 0;
		while (i < tokens.length) {
			tokens[i] = tokens[i].trim();
			setElements.add(tokens[i]);
			++i;
		}
	}

	/**
	 * This method creates tokens bases on the 
	 * graph edge
	 * 
	 * @return String[] of tokens
	 */
	private String[] makeTokensGraphEdge(String line) {
		String out[] = line.split("->");
		out[0] = out[0].trim();
		out[1] = out[1].trim();

		int i;
		if (out[1].contains(";") && !(out[1].contains("["))) {
			i = out[1].indexOf(";");
			out[1] = out[1].substring(0, i);
		} else if (out[1].contains("[")) {
			i = out[1].indexOf("[");
			out[1] = out[1].substring(0, i);
		}
		out[0] = out[0].trim();
		out[1] = out[1].trim();
		return out;
	}

	/**
	 * This method creates tokens of the graph
	 */
	private void makeTokensGraph() {

		for (String line : fileLines) {
			line = line.trim();
			if (!line.equals("digraph testgraph{") && !line.equals("}") && line.charAt(0) != '/') {
				String adjNodes[] = makeTokensGraphEdge(line);

				String pair = adjNodes[0].trim() + "," + adjNodes[1].trim();
				pair = pair.replaceAll("\\s+", "");
				relationElements.add(pair);
			}
		}

	}

	/**
	 * This method loads tweets into the graph
	 * @return List of tweets
	 * @throws JsonIOException
	 * @throws IOException
	 */
	public List<Tweet> loadTweets() throws JsonIOException, IOException {
		// List<Tweet> loadedTweets = cJT.loadTweetsFromJson();
		List<Tweet> loadedTweets = Dataloader.loadTweetsFromJson();
		return loadedTweets;
	}
}