## Solution Approach

The Application script's path is ./src/main/java/com/newrelic/codingchallenge/Main.java. 

Solution uses java.io.* java.net.* java.util.*.

Below are the key features and high level implementation strategy.

1. Limit thread pool to number N : The Server is limited to a Fixed Thread pool, by using the Executors package.
2. The Server does not allow any more than 5 clients to connect at a time. 
3. Read Input streams :The Server has a Client Handler class that handles and reads the input streams for each of the clients. 
4. Blocking Queues if limit reaches N:BlockingQueues are used here to improve speed and to be thread safe. Executor Utility class which provides factory methods
   Internally manages thread pool of MAX_CLIENTS


## Instructions for successful Application Build

1.Go the command prompt/Terminal where the project is saved in local.

2.Set up the Java 8 path by running the below command
export JAVA_HOME=$(/usr/libexec/java_home -v11)

3../gradlew build-- Build is successful

## Run
Go to IntelliJ and Run the Application 

Optional arguments: -port PORT and -file LOG can be used, 

## Test
Added some tests to test the Application and Client code.
All the tests are running successfully

## References
http://www.baeldung.com/a-guide-to-java-sockets
