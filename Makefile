all: build run clean

ex1: build
	java -XX:+UseSerialGC -Xmx508m -Xss4M Maze < input1
	rm Maze.class

ex2: build
	java -XX:+UseSerialGC -Xmx508m -Xss4M Maze < input2
	rm Maze.class

build: 
	javac -encoding utf8 Maze.java

run: 
	java -XX:+UseSerialGC -Xmx508m -Xss4M Maze

clean: 
	rm Maze.class
