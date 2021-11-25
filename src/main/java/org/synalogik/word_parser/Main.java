package org.synalogik.word_parser;

public class Main {
    public static void main(String[] args) throws WordParserException {
        if(args.length > 0) { System.out.println(WordParser.parseFile(args[0])); }
    }
}
