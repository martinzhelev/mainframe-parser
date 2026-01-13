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

public class Main {

	public static void main(String[] args) {

		GenericFileParser parser = new GenericFileParser();
		Validator validator = new Validator();

		URL url = Main.class.getClassLoader().getResource("transactions.txt");
		System.out.println(">>> Loading transactions.txt from: " + url);

		run(parser, validator,
				"transactions.txt", Transaction.class);

		run(parser, validator,
				"customers.txt", Customer.class);

		run(parser, validator,
				"audits.txt", SecurityAudit.class);
	}

	private static <T> void run(
			GenericFileParser parser,
			Validator validator,
			String resource,
			Class<T> clazz
	) {
		System.out.println("\n=== " + clazz.getSimpleName() + " ===");

		List<T> objects = parser.parse(resource, clazz);
		Map<T, Set<String>> errors = validator.validate(objects);

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