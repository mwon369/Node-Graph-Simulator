package nz.ac.auckland.se281.a4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.google.gson.JsonIOException;

import nz.ac.auckland.se281.a4.cli.*;
import nz.ac.auckland.se281.a4.ds.*;

@RunWith(Suite.class)
@SuiteClasses({

	StudentsTestsForMarking.Task1NodeStackAndQueueTest.class,
	StudentsTestsForMarking.Task1NodeStackAndQueueGenericsTest.class,
	StudentsTestsForMarking.Task1NodeStackAndQueueExceptionTest.class,

	StudentsTestsForMarking.Task2LinkedListTest.class,
	StudentsTestsForMarking.Task2LinkedListGenericsTest.class,

	StudentsTestsForMarking.Task3ReflexiveTest.class,
	StudentsTestsForMarking.Task3SymmetricTest.class,
	StudentsTestsForMarking.Task3TransitiveTest.class,
	StudentsTestsForMarking.Tast3EquivalenceTest.class,
	StudentsTestsForMarking.Task3EquivalenceClassTest.class,
	StudentsTestsForMarking.Task3BFSTest.class,
	StudentsTestsForMarking.Task3DFSTest.class,

	StudentsTestsForMarking.Task4SearchTweetTest.class,

})
public class StudentsTestsForMarking {
	/**
	 * NodeStackAndQueue
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task1NodeStackAndQueueTest {
		NodesStackAndQueue<TwitterHandle> stack;

		@Before
		public void setUp() {
			stack = new NodesStackAndQueue<TwitterHandle>();
		}

		@Test
		public void T1_01_Stack_Is_Empty() {
			assertEquals(true, stack.isEmpty());
		}

		@Test
		public void T1_02_Stack_Is_Not_Empty() {
			stack.append(new TwitterHandle("1"));
			assertEquals(false, stack.isEmpty());
		}

		@Test
		public void T1_03_Stack_Push() {
			stack.push(new TwitterHandle("5"));
			stack.push(new TwitterHandle("6"));
			assertEquals(new TwitterHandle("6"), stack.peek());

		}

		@Test
		public void T1_04_Stack_Pop() {
			stack.push(new TwitterHandle("5"));
			stack.push(new TwitterHandle("6"));
			assertEquals(new TwitterHandle("6"), stack.pop());
			assertEquals(new TwitterHandle("5"), stack.pop());
		}

		@Test
		public void T1_05_Queue_Is_Empty() {
			NodesStackAndQueue<TwitterHandle> queue = new NodesStackAndQueue<TwitterHandle>();
			assertEquals(true, queue.isEmpty());
		}

		@Test
		public void T1_05_Queue_Is_Not_Empty() {
			NodesStackAndQueue<TwitterHandle> queue = new NodesStackAndQueue<TwitterHandle>();
			queue.append(new TwitterHandle("4"));
			assertEquals(false, queue.isEmpty());
		}

		@Test
		public void T1_07_Queue_Append() {
			NodesStackAndQueue<TwitterHandle> queue = new NodesStackAndQueue<TwitterHandle>();
			queue.append(new TwitterHandle("5"));
			assertEquals(new TwitterHandle("5"), queue.pop());
		}
	}

	/**
	 * NodeStackAndQueue Generics UNCOMMENT THIS CODE TO RUN GENERICS TEST!!!
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task1NodeStackAndQueueGenericsTest {
		@Test
		public void T1_08_Is_Empty() {
			NodesStackAndQueue<Integer> stack = new NodesStackAndQueue<Integer>();
			assertEquals(true, stack.isEmpty());
		}

		@Test
		public void T1_09_Is_Not_Empty() {
			NodesStackAndQueue<Double> stack = new NodesStackAndQueue<Double>();
			stack.push(4.0);
			assertEquals(false, stack.isEmpty());
		}

		@Test
		public void T1_10_Push() {
			NodesStackAndQueue<Double> stack = new NodesStackAndQueue<Double>();
			stack.push(4.0);
			assertEquals((Double) 4.0, stack.peek());
		}

		@Test
		public void T1_11_Pop() {
			NodesStackAndQueue<Double> stack = new NodesStackAndQueue<Double>();
			stack.push(4.0);
			stack.push(5.0);
			assertEquals((Double) 5.0, stack.pop());
			assertEquals((Double) 4.0, stack.pop());
			assertEquals(stack.isEmpty(), true);
		}

		@Test
		public void T1_12_Is_Empty() {
			NodesStackAndQueue<Double> queue = new NodesStackAndQueue<Double>();
			assertEquals(true, queue.isEmpty());
		}

		@Test
		public void T1_13_Is_Not_Empty() {
			NodesStackAndQueue<Double> queue = new NodesStackAndQueue<Double>();
			queue.append((Double) 4.0);
			assertEquals(false, queue.isEmpty());
		}

		@Test
		public void T1_14_Append() {
			NodesStackAndQueue<Double> queue = new NodesStackAndQueue<Double>();
			queue.append((Double) 4.0);
			assertEquals(queue.pop(), (Double)(4.0));
		}


	}

	/**
	 * NodeStackAndQueue Exception Handling
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task1NodeStackAndQueueExceptionTest {
		// @Test(expected = EmptyStackException.class)
		@Test
		public void T1_15_Pop_Empty_Stack() {
			try {
				NodesStackAndQueue<Double> stack = new NodesStackAndQueue<Double>();
				stack.pop();
				fail("Exception not thrown");
			} catch (EmptyStackException e) {
				return;
			} catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}

			
		}

		@Test()
		public void T1_16_Peek_Empty_Stack() {

			try {
				NodesStackAndQueue<Double> stack = new NodesStackAndQueue<Double>();
				stack.peek();
				fail("Exception not thrown");
			} catch (EmptyStackException e) {
				return;
			} catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}
		}

		@Test()
		public void T1_17_Append_Then_Pop_Empty_Stack() {
			try {
				NodesStackAndQueue<Double> stack = new NodesStackAndQueue<Double>();
				stack.append((Double) 4.0);
				stack.pop();
				stack.pop();
				fail("Exception not thrown");
			} catch (EmptyStackException e) {
				return;
			} catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}
		}
	}

	/**
	 * LinkedList
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task2LinkedListTest {
		@Test
		public void T2_01_Is_Empty() {
			LinkedList<TwitterHandle> edges = new LinkedList<TwitterHandle>();
			assertEquals(0, edges.size());
		}

		@Test
		public void T2_02_Is_Not_Empty() {
			LinkedList<Edge<TwitterHandle>> edges = new LinkedList<Edge<TwitterHandle>>();
			edges.append(new Edge<TwitterHandle>(new TwitterHandle("1"), new TwitterHandle("2")));
			assertEquals(1, edges.size());
		}

		@Test
		public void T2_03_Append() {
			LinkedList<Edge<TwitterHandle>> edges = new LinkedList<Edge<TwitterHandle>>();
			edges.append(new Edge<TwitterHandle>(new TwitterHandle("1"), new TwitterHandle("2")));
			assertEquals(new Edge<>(new TwitterHandle("1"), new TwitterHandle("2")), edges.get(0));
		}

		@Test
		public void T2_04_Get() {
			LinkedList<Edge<TwitterHandle>> edges = new LinkedList<Edge<TwitterHandle>>();

			for (int i = 0; i < 10; i++) {
				edges.append(new Edge<>(new TwitterHandle(String.format("%d", i)),
						new TwitterHandle(String.format("%d", i + 1))));
				assertEquals(new Edge<>(new TwitterHandle(String.format("%d", i)),
						new TwitterHandle(String.format("%d", i + 1))), edges.get(i));
			}
		}

		@Test
		public void T2_05_Prepend() {
			LinkedList<Edge<TwitterHandle>> edges = new LinkedList<Edge<TwitterHandle>>();

			for (int i = 0; i < 10; i++) {
				edges.prepend(new Edge<>(new TwitterHandle(String.format("%d", i)),
						new TwitterHandle(String.format("%d", i + 1))));
				assertEquals(new Edge<>(new TwitterHandle(String.format("%d", i)),
						new TwitterHandle(String.format("%d", i + 1))), edges.get(0));
			}
		}

		@Test
		public void T2_06_Size() {
			LinkedList<Edge<TwitterHandle>> edges = new LinkedList<Edge<TwitterHandle>>();
			assertEquals(0, edges.size());

			for (int i = 0; i < 10; i++) {
				edges.append(new Edge<>(new TwitterHandle(String.format("%d", i)),
						new TwitterHandle(String.format("%d", i + 1))));
				assertEquals(i + 1, edges.size());
			}
		}

		@Test
		public void T2_07_Remove() {
			LinkedList<Edge<TwitterHandle>> edges = new LinkedList<Edge<TwitterHandle>>();
			edges.append(new Edge<>(new TwitterHandle("1"), new TwitterHandle("2")));
			edges.append(new Edge<>(new TwitterHandle("3"), new TwitterHandle("4")));
			edges.remove(0);
			assertEquals(new Edge<>(new TwitterHandle("3"), new TwitterHandle("4")), edges.get(0));
			assertEquals(1, edges.size());

			edges = new LinkedList<Edge<TwitterHandle>>();
			edges.append(new Edge<>(new TwitterHandle("1"), new TwitterHandle("2")));
			edges.append(new Edge<>(new TwitterHandle("3"), new TwitterHandle("4")));
			edges.remove(1);
			assertEquals(new Edge<>(new TwitterHandle("1"), new TwitterHandle("2")), edges.get(0));
			assertEquals(1, edges.size());

			edges = new LinkedList<Edge<TwitterHandle>>();
			edges.append(new Edge<>(new TwitterHandle("1"), new TwitterHandle("2")));
			edges.append(new Edge<>(new TwitterHandle("3"), new TwitterHandle("4")));
			edges.append(new Edge<>(new TwitterHandle("5"), new TwitterHandle("6")));
			edges.remove(2);
			edges.remove(0);
			assertEquals(new Edge<>(new TwitterHandle("3"), new TwitterHandle("4")), edges.get(0));
			assertEquals(1, edges.size());
		}

	}

	/**
	 * LinkedList Generics UNCOMMENT THIS CODE TO RUN GENERICS TEST!!!
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task2LinkedListGenericsTest {
		@Test
		public void T2_09_Is_Empty() {
			LinkedList<Integer> edges = new LinkedList<Integer>();
			assertEquals(0, edges.size());
		}

		@Test
		public void T2_10_Is_Not_Empty() {
			LinkedList<Integer> edges = new LinkedList<Integer>();
			edges.append(1);
			assertEquals(1, edges.size());
		}

		@Test
		public void T2_11_Append() {
			LinkedList<Integer> edges = new LinkedList<Integer>();
			edges.append(1);
			assertEquals((Integer) 1, edges.get(0));
		}

		@Test
		public void T2_12_Get() {
			LinkedList<Integer> edges = new LinkedList<Integer>();

			for (int i = 0; i < 10; i++) {
				edges.append(i);
				assertEquals((Integer) i, edges.get(i));
			}
		}

		@Test
		public void T2_15_Remove() {
			LinkedList<Integer> edges = new LinkedList<Integer>();
			edges.append(1);
			edges.append(2);
			edges.remove(0);
			assertEquals((Integer) 2, edges.get(0));
			assertEquals(1, edges.size());
		}

		@Test
		public void T2_16_Insert() {
			LinkedList<Integer> edges = new LinkedList<Integer>();
			edges.insert(0, 1);
			assertEquals((Integer) 1, edges.get(0));

			edges = new LinkedList<Integer>();
			edges.append(-1);

			for (int i = 0; i < 10; i++) {
				edges.insert(1, i);
				assertEquals((Integer) i, edges.get(1));
			}
		}
	}

	/**
	 * LinkedLists Exception Test
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task2LinkedListExceptionTest {
		@Test()
		public void T2_17_Get_Out_Of_Bounds() {
			try {
				LinkedList<Integer> edges = new LinkedList<Integer>();
				edges.get(0);
				fail("Exception not thrown");
			} catch (InvalidPositionException e) {
				return;
			} catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}
		
		}

		@Test()
		public void T2_19_Insert_Out_Of_Bounds() {
			try {
				LinkedList<Integer> edges = new LinkedList<Integer>();
				edges.insert(1, 1);
				fail("Exception not thrown");
			} catch (InvalidPositionException e) {
				return;
			} catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}

		}
	}

	/**
	 * Reflexive relation
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task3ReflexiveTest {

		ByteArrayOutputStream myOut;
		PrintStream origOut;
		GraphUI graphUI;

		static Map<String, Boolean> file2result;

		static {
			file2result = new HashMap<>();
			file2result.put("a.txt", true);
			file2result.put("c.txt", true);
			file2result.put("f.txt", false);
		}

		@Before
		public void setUp() {
			origOut = System.out;
			myOut = new ByteArrayOutputStream();
			System.setOut(new PrintStream(myOut));
		}

		@After
		public void tearDown() {
			System.setOut(origOut);
			if (myOut.toString().length() > 1) {
				System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator()
						+ myOut.toString());
			}
		}

		private void runTest(String fileName, String command) {
			GraphControl controller = new GraphControl();

			try {
				controller.processCommand("open " + fileName);
				assertEquals(file2result.get(fileName), controller.processCommand("check " + command).get(1));
			}  catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}
		}

		@Test
		public void T3_01_Reflexive_A() {
			runTest("a.txt", "-r");

		}

		@Test
		public void T3_03_Reflexive_C() {
			runTest("c.txt", "-r");
		}

		@Test
		public void T3_06_Reflexive_F() {
			runTest("f.txt", "-r");

		}

	}

	/**
	 * Symmetric Relation
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task3SymmetricTest {

		ByteArrayOutputStream myOut;
		PrintStream origOut;

		static Map<String, Boolean> file2result;
		static {
			file2result = new HashMap<>();
			file2result.put("a.txt", false);
			file2result.put("c.txt", true);
			file2result.put("f.txt", false);
		}

		@Before
		public void setUp() {
			origOut = System.out;
			myOut = new ByteArrayOutputStream();
			System.setOut(new PrintStream(myOut));
		}

		@After
		public void tearDown() {
			System.setOut(origOut);
			if (myOut.toString().length() > 1) {
				System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator()
						+ myOut.toString());
			}
		}

		private void runTest(String fileName, String command) {
			GraphControl controller = new GraphControl();

			try {
				controller.processCommand("open " + fileName);
				assertEquals(file2result.get(fileName), controller.processCommand("check " + command).get(1));
			} catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}
		}

		@Test
		public void T3_09_Symmetric_A() {
			runTest("a.txt", "-s");
		}

		@Test
		public void T3_11_Symmetric_C() {
			runTest("c.txt", "-s");
		}

		@Test
		public void T3_14_Symmetric_F() {
			runTest("f.txt", "-s");

		}
	}

	/**
	 * Transitive relation
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task3TransitiveTest {

		ByteArrayOutputStream myOut;
		PrintStream origOut;
		static Map<String, Boolean> file2result;
		static {
			file2result = new HashMap<>();
			file2result.put("a.txt", false);
			file2result.put("c.txt", true);
			file2result.put("f.txt", false);
		}

		@Before
		public void setUp() {
			origOut = System.out;
			myOut = new ByteArrayOutputStream();
			System.setOut(new PrintStream(myOut));
		}

		@After
		public void tearDown() {
			System.setOut(origOut);
			if (myOut.toString().length() > 1) {
				System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator()
						+ myOut.toString());
			}
		}

		private void runTest(String fileName, String command) {
			GraphControl controller = new GraphControl();

			try {
				controller.processCommand("open " + fileName);
				assertEquals(file2result.get(fileName), controller.processCommand("check " + command).get(1));
			} catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}
		}

		@Test
		public void T3_16_Transitive_A() {
			runTest("a.txt", "-t");
		}

		@Test
		public void T3_17_Transitive_C() {
			runTest("c.txt", "-t");
		}

		@Test
		public void T3_20_Transitive_F() {
			runTest("f.txt", "-t");
		}
	}

	/**
	 * Equivalent relation
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Tast3EquivalenceTest {

		ByteArrayOutputStream myOut;
		PrintStream origOut;
		static Map<String, Boolean> file2result;
		static {
			file2result = new HashMap<>();
			file2result.put("a.txt", false);
			file2result.put("c.txt", true);
			file2result.put("f.txt", false);
		}

		@Before
		public void setUp() {
			origOut = System.out;
			myOut = new ByteArrayOutputStream();
			System.setOut(new PrintStream(myOut));
		}

		@After
		public void tearDown() {
			System.setOut(origOut);
			if (myOut.toString().length() > 1) {
				System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator()
						+ myOut.toString());
			}
		}

		private void runTest(String fileName, String command) {
			GraphControl controller = new GraphControl();

			try {
				controller.processCommand("open " + fileName);
				assertEquals(file2result.get(fileName), controller.processCommand("check " + command).get(1));
			} catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}
		}

		@Test
		public void T3_22_A() {
			runTest("a.txt", "-e");
		}

		@Test
		public void T3_24_C() {
			runTest("c.txt", "-e");
		}

		@Test
		public void T3_27_F() {
			runTest("f.txt", "-e");

		}
	}

	/**
	 * Equivalence Class relation
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task3EquivalenceClassTest {

		ByteArrayOutputStream myOut;
		PrintStream origOut;
		static Map<String, Boolean> file2result;
		static {
			file2result = new HashMap<>();
			file2result.put("a.txt", false);
			file2result.put("c.txt", true);
			file2result.put("f.txt", false);
		}

		@Before
		public void setUp() {
			origOut = System.out;
			myOut = new ByteArrayOutputStream();
			System.setOut(new PrintStream(myOut));
		}

		@After
		public void tearDown() {
			System.setOut(origOut);
			if (myOut.toString().length() > 1) {
				System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator()
						+ myOut.toString());
			}
		}

		private void runTest(String fileName, String command) {
			GraphControl controller = new GraphControl();

			try {
				controller.processCommand("open " + fileName);
				assertEquals(file2result.get(fileName), controller.processCommand("eq-class " + command).get(1));
			} catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}
		}

		private void runTestGraph(String filename, String command, List<String> expected) {
			GraphUI graphUI = new GraphUI();
			graphUI.open(filename);
			try {
				TweetGraph tweetGraph = new TweetGraph(graphUI.getRelationElements(), graphUI.loadTweets(),
					Dataloader.allocateTweetsToUsers(new Graph(graphUI.getRelationElements()).getUsersFromNodes(), graphUI.loadTweets()));
				
				List<String> result = tweetGraph.computeEquivalence(command, graphUI.getSetElements(), graphUI.getRelationElements());
				Collections.sort(expected);
				Collections.sort(result);
				assertEquals(expected, result);

			} catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}
		}

		@Test
		public void T3_30_A() {
			runTest("a.txt", "1");
		}

		@Test
		public void T3_32_C() {
			runTest("c.txt", "1");
			runTestGraph("c.txt" , "1", new ArrayList<String>(){{
				add("2");
				add("3");
				add("1");
			}});
		}

		@Test
		public void T3_35_F() {
			runTest("f.txt", "1");
		}
	}

	/**
	 * BFS
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task3BFSTest {
		GraphUI graphUI;

		static Map<String, List<TwitterHandle>> file2result_BFS;
		static {
			file2result_BFS = new HashMap<>();
			file2result_BFS.put("a.txt", new ArrayList<TwitterHandle>() {
				{
					add(new TwitterHandle("0"));
					add(new TwitterHandle("1"));
					add(new TwitterHandle("2"));
					add(new TwitterHandle("3"));
					add(new TwitterHandle("4"));
					add(new TwitterHandle("5"));
				}
			});

			file2result_BFS.put("c.txt", new ArrayList<TwitterHandle>() {
				{
					add(new TwitterHandle("0"));
					add(new TwitterHandle("2"));
					add(new TwitterHandle("1"));
					add(new TwitterHandle("3"));
				}
			});

			file2result_BFS.put("f.txt", new ArrayList<TwitterHandle>() {
				{
					add(new TwitterHandle("0"));
					add(new TwitterHandle("1"));
					add(new TwitterHandle("2"));
					add(new TwitterHandle("3"));
					add(new TwitterHandle("4"));
					add(new TwitterHandle("5"));
					add(new TwitterHandle("6"));
					add(new TwitterHandle("7"));
				}
			});
		}

		private void runTest(String fileName) {
			graphUI.open(fileName);
			Graph graph = new Graph(graphUI.getRelationElements());
			List<Node<String>> result = graph.breadthFirstSearch();
			assertEquals(file2result_BFS.get(fileName), result);
		}

		@Before
		public void setUp() {
			graphUI = new GraphUI();
		}

		@Test
		public void T3_45_A() {
			runTest("a.txt");
		}

		@Test
		public void T3_47_C() {
			runTest("c.txt");
		}

		@Test
		public void T3_50_F() {
			runTest("f.txt");
		}
	}

	/**
	 * DFS
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task3DFSTest {
		GraphUI graphUI;

		static Map<String, List<TwitterHandle>> file2result_DFS;
		static {
			file2result_DFS = new HashMap<>();
			file2result_DFS.put("a.txt", new ArrayList<TwitterHandle>() {
				{
					add(new TwitterHandle("0"));
					add(new TwitterHandle("1"));
					add(new TwitterHandle("3"));
					add(new TwitterHandle("2"));
					add(new TwitterHandle("5"));
					add(new TwitterHandle("4"));
				}
			});

			file2result_DFS.put("c.txt", new ArrayList<TwitterHandle>() {
				{
					add(new TwitterHandle("0"));
					add(new TwitterHandle("2"));
					add(new TwitterHandle("3"));
					add(new TwitterHandle("1"));

				}
			});

			file2result_DFS.put("f.txt", new ArrayList<TwitterHandle>() {
				{
					add(new TwitterHandle("0"));
					add(new TwitterHandle("1"));
					add(new TwitterHandle("2"));
					add(new TwitterHandle("3"));
					add(new TwitterHandle("4"));
					add(new TwitterHandle("5"));
					add(new TwitterHandle("7"));
					add(new TwitterHandle("6"));

				}
			});
		}

		private void runTest(String fileName) {
			graphUI.open(fileName);
			Graph graph = new Graph(graphUI.getRelationElements());
			List<Node<String>> result = graph.depthFirstSearch();
			assertEquals(file2result_DFS.get(fileName), result);
		}

		@Before
		public void setUp() {
			graphUI = new GraphUI();
		}

		@Test
		public void T3_52_A() {
			runTest("a.txt");
		}

		@Test
		public void T3_54_C() {
			runTest("c.txt");
		}

		@Test
		public void T3_57_F() {
			runTest("f.txt");
		}
	}

	/**
	 * Search Tweet
	 */
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class Task4SearchTweetTest {
		static Map<String, String> file2result_tweet_search;
		static {
			file2result_tweet_search = new HashMap<>();

			file2result_tweet_search.put("a.txt",
					"The tweet string found is: Some month worryed under some chill by any step-grandmother mated the path.\n"
							+ "User Miss Natalie Bruen {0} tweeted mother");

			file2result_tweet_search.put("c.txt",
					"The tweet string found is: Any psychology landed into one otter on the branch invited any squirrel.\n"
							+ "User Lauretta Crona {2} tweeted otter");

			file2result_tweet_search.put("f.txt",
					"The tweet string found is: A girl afforded towards any bonsai over a jumper stroked the otter.\n"
							+ "User Hailey Bartoletti {7} tweeted girl");

		}

		private void runTest(String fileName, String user, String keyword) {
			GraphUI graphUI = new GraphUI();
			graphUI.open(fileName);
			try {
				TweetGraph graph = new TweetGraph(graphUI.getRelationElements(), graphUI.loadTweets(),
						Dataloader.allocateTweetsToUsers(new Graph(graphUI.getRelationElements()).getUsersFromNodes(),
								graphUI.loadTweets()));
				String tweet = graph.searchTweet(new TwitterHandle(user), keyword);
				assertEquals(file2result_tweet_search.get(fileName), tweet);

			} catch (Exception e) {
				fail("Exception thrown: " + e.getMessage());
			}

		}

		@Test
		public void T3_01_A() {
			runTest("a.txt", "0", "mother");
		}

		@Test
		public void T3_03_C() {
			runTest("c.txt", "2", "otter");
		}
		
		@Test
		public void T3_06_F() {
			runTest("f.txt", "7", "girl");
		}
	}

}
