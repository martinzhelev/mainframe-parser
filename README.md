# Generic File Parser & Validator

A reflection-based Java library designed to parse structured text files into strongly-typed objects and validate them using custom annotations.

## Building the Library

### Prerequisites

* **Java 21+**
* **Maven 3.9+**

### Build Commands

To build the project and run all tests (ensuring at least 65% coverage):

```bash
mvn clean verify

```

To install the library into your local Maven repository:

```bash
mvn clean install

```

### Dependency

To use this library in another Maven project, add the following to your `pom.xml`:

```xml
<dependency>
  <groupId>com.mse</groupId>
  <artifactId>Parser-CourseWork</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>

```

---

## File Format Requirements

Each input file must follow these rules:

* **One Record Per Line**: The parser processes the file line-by-line.
* **Consistent Delimiter**: Use the delimiter defined in the `@FileSource` annotation (e.g., `|` or `,`).
* **Column Mapping**: Data must match the index defined in the `@Column` annotations.
* **Typed Data**: Values must be convertible to the field types (e.g., `YYYY-MM-DD` for dates).

### Sample `transactions.txt`

```text
TX-1002|0.01|2024-01-02
TX-1003|9999999.99|2024-01-03
TX-NEG|-10.50|2024-01-04

```

---

## Library Usage

### 1. Annotate your Model

The library uses reflection to understand your data structure.

```java
@FileSource(delimiter = "|")
public class Transaction {
    @Column(index = 0)
    @NotNull
    private String id;

    @Column(index = 1)
    @Range(min = 0, max = 1000000)
    private Double amount; // Use Double to allow nulls during parsing
}

```

### 2. Parse and Validate

The logic is separated into two distinct phases:

```java
// Phase 1: Parsing (Malformed lines are skipped and logged)
GenericFileParser parser = new GenericFileParser();
List<Transaction> list = parser.parse("transactions.txt", Transaction.class);

// Phase 2: Validation (Checks business rules like @Range)
Validator validator = new Validator();
var results = validator.validate(list); 

```
