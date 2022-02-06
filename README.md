## Solution Approach

The Application script is contained in ./src/main/java/com/newrelic/codingchallenge/Main.java. 
The entire solution uses java.io.* java.net.* java.util.* 
The Server was limited to a Fixed Thread pool, by using the Executors package. 
The Server does not allow any more than 5 clients to connect at a time. 
The Server has a Client Handler class that handles and reads the input streams for each of the clients. 
BlockingQueues are used here to improve speed and to be thread safe. Executor Utility class which provides factory methods
Internally manages thread pool of MAX_CLIENTS


##Instructions
1.Go the command prompt/Terminal where the project is saved in local.
2.Set up the Java 8 path by running the below command
export JAVA_HOME=$(/usr/libexec/java_home -v11)
3../gradlew build-- Build is successful

##Run
Go to IntelliJ and Run the Application 

Optional arguments: -port PORT and -file LOG can be used, 

##Test
Added some tests to test the Application and Client code.
All the tests are running successfully

##References
http://www.baeldung.com/a-guide-to-java-sockets
