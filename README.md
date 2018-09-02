# Booking.com Selenium Automation project
Selenium automation project for the booking.com searching features.

## Prerequisites

* Java 8
* Maven
* Chrome driver

Also should be configure environment variables:

* ```JAVA_HOME```
* ```webdriver.chrome.driver```

## Running tests

Running tests using chrome driver that configured in environment variable:
```
$ mvn clean test
```

Running tests using chrome driver from command line:
```
$ mvn clean test -Dwebdriver.chrome.driver=path/to/driver
```
