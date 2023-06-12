# Epam-POC-Playwright

## Pre-requisites
This framework run on Java 11.

## Run your first Playwright test
1. Clone this repository
2. Install the dependencies on pom.xml
3. Choose the command line below to run test on Web or Mobile:

On Web:
`mvn test -DisMobile=false`

On Mobile:
`mvn test -DisMobile=true`

## Use Test Generator
Open terminal of your project and run the command line below:

`mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen"`

## Use Trace Viewer(Screenshot)
Open terminal of your project and run the command line below:

`mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace traces/<fileNameTest>.zip"`

## Generate Allure report
When running the tests, `allure-results` folder is generated.
To view report, use command:

`allure serve allure-results`

### The list test cases supported run on Mobile:
1. HeaderLocationTest