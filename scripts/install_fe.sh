#!/usr/bin/env bash

git clone https://github.com/fennecdb/fennec
cd fennec/

./gradlew build
./gradlew shadowJar

sudo mkdir /etc/fennec
sudo chown -R `whoami` /etc/fennec
mv build/libs/fennec.jar /etc/fennec
mv deps/* /etc/fennec/deps/
