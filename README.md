# Epam-POC-Playwright

## Pre-requisites
This framework run on Java 11.

## Run your first Playwright test
1. Clone this repository
2. Install the dependencies on pom.xml
3. Run the sample script using:

`mvn compile exec:java -D exec.mainClass="org.example.Example"`

## Use Test Generator
Open terminal of your project and run the command line below:

`mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen"`

## Use Trace Viewer(Screenshot)
Open terminal of your project and run the command line below:

`mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace traces/<fileNameTest>.zip"`