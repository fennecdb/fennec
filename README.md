# Fennec 🦊 (Work-in-Progress)

[![Build Status](https://travis-ci.com/fennecdb/fennec.svg?branch=master)](https://travis-ci.com/fennecdb/fennec)

Fennec aims to be a general purpose timeseries database.

> ⚠️ This project is in its early development infancy and should not be used (in its current state) for any data that you care about. This warning will be removed once its stability/reliability is battle proven.

## Goals
* TCP client for the JVM (✔️), Node, Ruby, C# and Python (❌)
* Grafana support (Datasource API & Plugin) ✔️ (Plugin not public yet)
* REST API ❌
* Distributed (backup/replica) ❌
* Prometheus exporter ❌
* CLI administration ❌

## Installation 

### Requirements
* JDK 8
* WiredTiger 3.1.0 ([repo](https://github.com/wiredtiger/wiredtiger))

### WiredTiger Installation


1. Clone the repository
    ```
    git clone https://github.com/wiredtiger/wiredtiger
    cd wiredtiger/
    git checkout 81305b4ade4b92bde247931334c99c32fb148c44
    ```
2. Install additional build requirements (apt for Ubuntu/Debian)
    ```
    sudo apt install -y autoconf automake libtool swig build-essential
    ```

3. Build the library
    ```
    sh autogen.sh
    ./configure -enable-java
    make
    make install
    ```
4. Merge to common directory
    ```
    cp /usr/local/lib/libwiredtiger* /usr/local/share/java/wiredtiger-3.1.0/
    ```
__OR__ alternatively use the script at `scripts/install_wt.sh` doing the steps above all at once.

### Fennec Installation
TODO

## Getting Started
TODO

## License
[Apache 2.0](https://github.com/fennecdb/fennec/blob/master/LICENSE)
