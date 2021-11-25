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
        assertEquals("Word count = 792344\n" +
                            "Average word length = 4.116\n" +
                            "Number of words of length 1 is 18278\n" +
                            "Number of words of length 2 is 129963\n" +
                            "Number of words of length 3 is 219689\n" +
                            "Number of words of length 4 is 173583\n" +
                            "Number of words of length 5 is 97116\n" +
                            "Number of words of length 6 is 53730\n" +
                            "Number of words of length 7 is 41348\n" +
                            "Number of words of length 8 is 25690\n" +
                            "Number of words of length 9 is 17226\n" +
                            "Number of words of length 10 is 7979\n" +
                            "Number of words of length 11 is 4048\n" +
                            "Number of words of length 12 is 1841\n" +
                            "Number of words of length 13 is 934\n" +
                            "Number of words of length 14 is 746\n" +
                            "Number of words of length 15 is 119\n" +
                            "Number of words of length 16 is 26\n" +
                            "Number of words of length 17 is 12\n" +
                            "Number of words of length 18 is 8\n" +
                            "Number of words of length 19 is 4\n" +
                            "Number of words of length 20 is 3\n" +
                            "Number of words of length 27 is 1\n" +
                            "The most frequently occurring word length is 219689, for word length of 3",
            WordParser.parseFile(path + "bible_daily.txt"));
    }

    @Test
    public void test_emptyFile() {
        assertThrows(WordParserException.class, () -> WordParser.parseFile(path + "mallicous.txt"));
    }

    // Considered writing tests for the encapsulated-logic methods in WordParser but deemed this unnecessary for this context
}
