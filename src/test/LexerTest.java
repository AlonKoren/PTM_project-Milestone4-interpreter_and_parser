package test;

import alon.flightsim.language.interpreter.Lexer;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class LexerTest {


    @Test
    public void test_that_whitespaces_are_ignored() {

        String SCRIPT =
                "\r\n   \n  \t  A     1 2   3 \n" +
                "B 6 7 8    ";

        List<String> words = Lexer.Lexer(SCRIPT);

        assertThat(words.toString(), is("[A, 1, 2, 3, B, 6, 7, 8]"));

    }

    @Test
    public void test_that_math_signs_should_separate() {
        String SCRIPT = "x = 5+4 ";
        List<String> words = Lexer.Lexer(SCRIPT);
        assertThat(words.toString(), is("[x, =, 5, +, 4]"));
    }

    @Test
    public void test_text_inside_quotes_is_not_splitted_by_math_signs() {
        String SCRIPT = "bind \"/a/b/c\"";
        List<String> words = Lexer.Lexer(SCRIPT);
        assertThat(words.toString(), is("[bind, \"/a/b/c\"]"));
    }
}
