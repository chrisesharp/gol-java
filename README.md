# gol-java
Game Of Life in Java

This is an example implementation of Conway's Game of Life in Java, using Set and Map structures, and making use of Streams.
Principally, though, to emphasise TDD as a practice.

To run the implementation, firstly build it:

`mvn package`

and then run it:

`./gol.sh`

*Usage*
```
gol.sh (options)
  -f (--file)  : Fully qualified path and name of input txt file.
  -s (--speed) : Framerate in miliseconds (default: 100)
  -t (--turns) : Number of turns to run simulation (default: 1)
```
