# netflow-java
netflow v9 parser and collector library for Java

## Usage
```java
NetFlowSession session = new NetFlowSession(source -> {
    source.listen((id, values) -> {
        // DO WHATEVER YOU WANT
    });
});
NetFlowCollector collector = new NetFlowCollector(session);
collector.join();
```