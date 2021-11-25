# WordParser
A Java Program to take a text file, parse it and output information on the distribution of word lengths.

# Assumptions
- Avoided the following characters , : ; ! ? Space
  - Justification for this was that punctuation wouldn't count towards words (bar those symbols that acted as placeholder for words e.g. &). So only punctuation that could be used in number formatting is permitted.
- Average is rounded to the third decimal place

# To Use
To create the jar from the maven source directory run the two following lines in succession.
```
mvn compile
mvn package
```
This should compile a jar called WordParser-1.0.jar in the target folder of the maven directory. You can then run the jar using the following line.
```
java -jar WordParser-1.0 "URL TO YOUR TXT FILE"
```

The result should output directly to the console you are calling the jar from.
