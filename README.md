## Problem Statement
New Relic Code Challenge
Write a server (“Application”) in Java that opens a socket and restricts input to at most 5 concurrent clients. Clients will connect to the Application and write any number of 9 digit numbers, and then close the connection. The Application must write a de-duplicated list of these numbers to a log file in no particular order.

## Solution Approach

The Application script's path is ./src/main/java/com/newrelic/codingchallenge/Main.java. 

Solution uses java.io.* java.net.* java.util.*.

## Key features
Below are the key features and high level implementation strategy.

1. Limit thread pool to number 5 : The Server is limited to a Fixed Thread pool, by using the Executors package.
2. The Server does not allow any more than 5 clients to connect at a time. 
3. Read Input streams :The Server has a Client Handler class that handles and reads the input streams for each of the clients. 
4. Blocking Queues if limit reaches N:BlockingQueues are used here to improve speed and to be thread safe. Executor Utility class which provides factory methods
   Internally manages thread pool of MAX_CLIENTS


## Instructions for building application successfully

1.Go the command prompt/Terminal where the project is saved in local.

2.Set up the Java 8 path by running the below command
export JAVA_HOME=$(/usr/libexec/java_home -v1.8)

3../gradlew build-- Build is successful

## Run
Go to IntelliJ and Run the Application 

After Running the application, and tests we should be able to see the LOG file created as numbers.log file and see the entries loaded to LOG file as shown in the below screenprint

![image](https://user-images.githubusercontent.com/92757034/152666844-228bdab9-70df-41fb-b2cc-ef5564a7e351.png)


Optional arguments: -port PORT and -file LOG can be used, 

## Test
Added below tests to test the Application and Client code.
1. testThroughput
2. testInvalidInput
3. testDuplicates
4. givenClient2_whenServerResponds_thenCorrect


All the above tests are running successfully.

## References
http://www.baeldung.com/a-guide-to-java-sockets
