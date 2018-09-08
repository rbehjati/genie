
# Genie

[![Build Status](https://travis-ci.org/rbehjati/genie.svg?branch=master)](https://travis-ci.org/rbehjati/genie)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.rbehjati/genie/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.rbehjati/genie)
[![License](https://img.shields.io/github/license/mashape/apistatus.svg)](http://www.opensource.org/licenses/mit-license.php)
[![Maven Central](curl -X PURGE https://maven-badges.herokuapp.com/maven-central/io.github.rbehjati/genie/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.rbehjati/genie)

Genie provides a Java API around [jenny](http://burtleburtle.net/bob/math/jenny.html), a pairwise test case generation tool written in C.
Genie uses a slightly different variant of jenny.c, with an additional method that makes the integration with Java easier.
This file is included here in directory src/main/resources/native.

## Usage
TBA

### Generating the libjenny.so file

```
g++ -I"/usr/lib/jvm/java-8-oracle/include" -I"/usr/lib/jvm/java-8-oracle/include/linux" \
    -c -fPIC <path-to>/io_rbehjati_genie_Jenny.cpp>

g++ io_rbehjati_genie_Jenny.o -shared -o libjenny.so
```
