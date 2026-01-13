package com.mse;

import com.mse.model.Customer;
import com.mse.model.SecurityAudit;
import com.mse.model.Transaction;
import com.mse.parser.GenericFileParser;
import com.mse.validation.Validator;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Entry point for the Multi-Source Extraction (MSE) framework.
 * Demonstrates the end-to-end workflow: resource loading, generic parsing,
 * and annotation-driven validation.
 */
public class Main {

	/**
	 * Initializes the framework components and processes sample data files.
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {

		GenericFileParser parser = new GenericFileParser();
		Validator validator = new Validator();

		// Verify resource visibility on the classpath
		URL url = Main.class.getClassLoader().getResource("transactions.txt");
		System.out.println(">>> Loading transactions.txt from: " + url);

		// Process different entities using the same generic logic
		run(parser, validator, "transactions.txt", Transaction.class);
		run(parser, validator, "customers.txt", Customer.class);
		run(parser, validator, "audits.txt", SecurityAudit.class);
	}

	/**
	 * Executes the parse-and-validate lifecycle for a specific model type.
	 * 1. Parses the flat file into objects using reflection.
	 * 2. Validates objects based on model annotations.
	 * 3. Reports results or specific constraint violations.
	 * * @param <T> The model type
	 * @param parser The generic parsing engine
	 * @param validator The strategy-based validation engine
	 * @param resource The filename on the classpath
	 * @param clazz The class type to instantiate
	 */
	private static <T> void run(
			GenericFileParser parser,
			Validator validator,
			String resource,
			Class<T> clazz
	) {
		System.out.println("\n=== " + clazz.getSimpleName() + " ===");

		// Step 1: Parsing
		List<T> objects = parser.parse(resource, clazz);

		// Step 2: Validation
		Map<T, Set<String>> errors = validator.validate(objects);

		// Step 3: Reporting
		if (errors.isEmpty()) {
			System.out.println("No validation errors ");
		} else {
			errors.forEach((obj, msgs) -> {
				System.out.println(obj);
				msgs.forEach(msg ->
						System.out.println("Error: " + msg));
			});
		}
	}
}