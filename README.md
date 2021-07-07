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

## Maven
```xml
<repository>
    <id>lumaserv</id>
    <url>https://maven.lumaserv.cloud</url>
</repository>
```
```xml
<dependency>
    <groupId>com.lumaserv</groupId>
    <artifactId>netflow-java</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```