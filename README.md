# Fennec 🦊 (Work-in-Progress)

[![Build Status](https://dev.azure.com/fennecdb/fennec/_apis/build/status/fennecdb.fennec?branchName=master)](https://dev.azure.com/fennecdb/fennec/_build/latest?definitionId=2&branchName=master)

Fennec aims to be a general purpose timeseries database.

> ⚠️ This project is in its early development infancy and should not be used (in its current state) for any data that you care about. This warning will be removed once its stability/reliability is battle proven.

## Features
* TCP client for the JVM / Java
* Grafana support (Datasource API & Plugin),️ _Plugin not public yet_

## In Progress
* Dashboard

## Roadmap
* Clients for Go, Node, Ruby and Python
* REST API 
* Distributed (backup/replica) 
* Prometheus exporter 
* CLI administration 

## Installation
#### Requirements
* JDK 8
* WiredTiger 3.1.0 ([repo](https://github.com/wiredtiger/wiredtiger))
* Gradle

In order to install fennec you will have to install the key-value storage WiredTiger 3.1 first.

Use the following scripts for convenience:
1. `scripts/install_wt.sh` to install WiredTiger
2. `scripts/install_fe.sh` to install Fennec


__OR__ check the [Wiki for a more detailed guidance](https://github.com/fennecdb/fennec/wiki/Installation).

## Getting Started

In this example, it is assumed that the server is reachable over localhost under port 64733:

```java
public static void main(String[] args) {
    try (FennecClient client = new FennecClient("localhost", 64733)) {
        client.connect();
        String myField = "my_field";
        String myNamespace = "my_namespace";
        // insert sth
        Instant now = Instant.now();
        Iterable<FData> data = Collections.singletonList(new FData(1.0, now.toEpochMilli()));
        client.insert(data, myField, myNamespace);

        // do a query
        Instant oneHourAgo = now.minus(1, ChronoUnit.HOURS);
        InRange range = new InRange(oneHourAgo.toEpochMilli(), now.toEpochMilli());
      	FQuery fQuery = new FSelection(myField, myNamespace, range).toQuery();
        client.query(fQuery);

    } catch (FennecException | IOException e) {
        // to handle
    }
}
```
__OR__ check the [Wiki for further explanations](https://github.com/fennecdb/fennec/wiki/Getting-Started).


## License
[Apache 2.0](https://github.com/fennecdb/fennec/blob/master/LICENSE)
