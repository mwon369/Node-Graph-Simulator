package nz.ac.auckland.se281.a4;

import java.util.Random;

import com.github.javafaker.Faker;

import nz.ac.auckland.se281.a4.ds.Node;

//*********************************
//YOU SHOULD NOT MODIFY THIS CLASS
//*********************************
public class TwitterHandle extends Node<String> {

	private String name;

	/**
	 * Creates a TwitterHandle based on the inputs
	 * 
	 * @param id
	 *            the id of the TwitterHandle (Number as String)
	 */
	public TwitterHandle(String id) {
		super(id);
		this.name = new Faker(new Random(Integer.parseInt(id))).name().fullName(); // Faker is an external Library found
																					// at:
																					// https://github.com/DiUS/java-faker

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return id of twitter handle
	 */
	public String getID() {
		return super.getValue();
	}

}
