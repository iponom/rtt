Round Trip Time Utils
=====================

* rtt-server - simple http server with two methods: `call` and  `timestamp`
* rtt-client - calls `call` method fo the rtt-server and log intervals
* delta-client - calls `timestamp` method fo the rtt-server, calculate and print delta between client and server timestamp

How to run
----------

```
nohup java -jar rtt-server-0.0.1-SNAPSHOT.jar &
nohup /opt/java/jdk1.8.0_171/bin/java -jar rtt-client-0.0.1-SNAPSHOT.jar &
/opt/java/jdk1.8.0_171/bin/java -jar delta-client-0.0.1-SNAPSHOT.jar
```


