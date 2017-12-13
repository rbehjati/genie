

# Genie

Genie provides a Java API around [jenny](http://burtleburtle.net/bob/math/jenny.html), a pairwise test case generation tool written in C. 


### Generating the libjenny.so file

```
g++ -I"/usr/lib/jvm/java-8-oracle/include" -I"/usr/lib/jvm/java-8-oracle/include/linux" \
    -c -fPIC <path-to>/io_rbehjati_jenny_Jenny.cpp>

g++ io_rbehjati_jenny_Jenny.o -shared -o libjenny.so
```
