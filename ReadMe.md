# Cron Parser Application
A Java application that parses and validates cron expressions. It supports minute, hour, day of month, month, and day of week fields.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Tests](#tests)

## Installation
To run this application, clone the repository and compile it using a Java compiler. You might need to include any dependencies in your project's build path:
```bash
git clone https://github.com/IshanRTripathi/CronParserAssignment
cd CronParserAssignment
```
## Usage
To use the application, make sure you're on the project root. 
Run it with a valid cron expression as an argument:
```bash
java -cp target/CronParserAssignment-1.0-SNAPSHOT.jar org.cronparser.CronParserApplication "*/15 0 1,15 * 1-5 /usr/bin/find"
```
This will print out the output in which the event should occur.

## Tests
The application includes unit tests to validate its functionality. To run these, use a Java testing framework such as JUnit:
```bash
src/test/java/org/cronparser/CronParserApplicationTest.java
src/test/java/org/cronparser/validator/ExpressionValidatorTest.java
```
Each test validates different parts of the cron expression syntax and ensures that the parser behaves correctly under various scenarios.
