
# Genie

[![Build Status](https://travis-ci.org/rbehjati/genie.svg?branch=master)](https://travis-ci.org/rbehjati/genie)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.rbehjati/genie.svg?label=Maven%20Central)](https://maven-badges.herokuapp.com/maven-central/io.github.rbehjati/genie)
[![License](https://img.shields.io/github/license/mashape/apistatus.svg)](http://www.opensource.org/licenses/mit-license.php)

Genie provides a Java API around [jenny](http://burtleburtle.net/bob/math/jenny.html), a pairwise test case generation tool written in C.
Genie uses a slightly different variant of jenny.c, with an additional method that makes the integration with Java easier.
This file is included here in directory src/main/resources/native.

## Usage
To generate test cases using Genie one must first define the interacting factors.
Each factor is defined by its name, and has two or more possible values.
```java
    Factor age = new Factor("age", "<18", "18-33", ">33");
    Factor gender = new Factor("gender", "male", "female");
```

After that, a call to ```generateCombinations``` of an instance of ```CombinationGenerator``` is all 
you need to generate the combinations that cover all t-wise interactions. 

```java
    int t = 2;
    List<Combination> combinations = new CombinationGenerator()
            .generateCombinations(t, Arrays.asList(age, gender));
```
 
The next step is to convert these combinations into input values 
that the unit or system under test can process. 

Genie is designed with equivalence class testing in mind. So each value 
is assumed to be a label representing an equivalence class.
To retrieve the value of a factor in a combination, the method ```getEquivalenceClass```
must be used:

```java
    combinations.foreach(combo -> {
        String ageValue = combo.getEquivalenceClass(age);
        // do something with age
        // assert something
    });
```

Note that using combinatorial testing at the unit-test level essentially means multiple assertions in one test method, 
which is against the recommended practice of one-assertion-per-test. 
So it is advisable to separate these tests from the rest of your unit tests,
for example by associating them with a specific jUnit category.

## Development
### Generating the libjenny.so file

```
g++ -I"/usr/lib/jvm/java-8-oracle/include" -I"/usr/lib/jvm/java-8-oracle/include/linux" \
    -c -fPIC <path-to>/io_rbehjati_genie_Jenny.cpp>

g++ io_rbehjati_genie_Jenny.o -shared -o libjenny.so
```
