import org.junit.Test;
import org.synalogik.word_parser.WordParser;
import org.synalogik.word_parser.WordParserException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class Test_WordParser {

    private final String path = "src\\test\\resources\\";

    @Test
    public void test_contextualPunctuation() throws WordParserException {
        assertEquals("Word count = 6\n" +
                        "Average word length = 4.5\n" +
                        "Number of words of length 2 is 1\n" +
                        "Number of words of length 3 is 1\n" +
                        "Number of words of length 4 is 1\n" +
                        "Number of words of length 5 is 2\n" +
                        "Number of words of length 8 is 1\n" +
                        "The most frequently occurring word length is 2, for word length of 5",
                WordParser.parseFile(path + "test.txt"));
    }

    @Test
    public void test_example() throws WordParserException {
        assertEquals("Word count = 9\n" +
                            "Average word length = 4.556\n" +
                            "Number of words of length 1 is 1\n" +
                            "Number of words of length 2 is 1\n" +
                            "Number of words of length 3 is 1\n" +
                            "Number of words of length 4 is 2\n" +
                            "Number of words of length 5 is 2\n" +
                            "Number of words of length 7 is 1\n" +
                            "Number of words of length 10 is 1\n" +
                            "The most frequently occurring word length is 2, for word lengths of 4 & 5",
            WordParser.parseFile(path + "example.txt"));
    }

    @Test
    public void test_bible() throws WordParserException {
        assertEquals("Word count = 792384\n" +
                        "Average word length = 4.111\n" +
                        "Number of words of length 1 is 18287\n" +
                        "Number of words of length 2 is 130347\n" +
                        "Number of words of length 3 is 219826\n" +
                        "Number of words of length 4 is 174044\n" +
                        "Number of words of length 5 is 96700\n" +
                        "Number of words of length 6 is 53590\n" +
                        "Number of words of length 7 is 41257\n" +
                        "Number of words of length 8 is 25596\n" +
                        "Number of words of length 9 is 17175\n" +
                        "Number of words of length 10 is 7922\n" +
                        "Number of words of length 11 is 4009\n" +
                        "Number of words of length 12 is 1823\n" +
                        "Number of words of length 13 is 916\n" +
                        "Number of words of length 14 is 735\n" +
                        "Number of words of length 15 is 115\n" +
                        "Number of words of length 16 is 20\n" +
                        "Number of words of length 17 is 9\n" +
                        "Number of words of length 18 is 7\n" +
                        "Number of words of length 19 is 3\n" +
                        "Number of words of length 20 is 3\n" +
                        "The most frequently occurring word length is 219826, for word length of 3",
            WordParser.parseFile(path + "bible_daily.txt"));
    }

    @Test
    public void test_emptyFile() {
        assertThrows(WordParserException.class, () -> WordParser.parseFile(path + "mallicous.txt"));
    }

    // Considered writing tests for the encapsulated-logic methods in WordParser but deemed this unnecessary for this context
}
