

# Genie

Genie provides a Java API around [jenny](http://burtleburtle.net/bob/math/jenny.html), a pairwise test case generation tool written in C.
Genie uses a slightly different variant of jenny.c, with an additional method that makes the integration with Java easier.
This file is included here in directory src/main/resources/native.


### Generating the libjenny.so file

```
g++ -I"/usr/lib/jvm/java-8-oracle/include" -I"/usr/lib/jvm/java-8-oracle/include/linux" \
    -c -fPIC <path-to>/io_rbehjati_genie_Jenny.cpp>

g++ io_rbehjati_genie_Jenny.o -shared -o libjenny.so
```
