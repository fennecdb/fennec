#!/usr/bin/env bash

git clone https://github.com/wiredtiger/wiredtiger
cd wiredtiger/
git checkout 81305b4ade4b92bde247931334c99c32fb148c44

sudo apt install -y autoconf automake libtool swig build-essential

sh autogen.sh
./configure -enable-java
make
make install

cp /usr/local/lib/libwiredtiger* /usr/local/share/java/wiredtiger-3.1.0/