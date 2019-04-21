package test;

import alon.flightsim.Environment;
import alon.flightsim.language.command.Command;
import alon.flightsim.language.interpreter.Parser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ParserTest {

    private List<String> log = new ArrayList<>();

    class LogCommand implements Command {

        @Override
        public int execute(List<String> words) {
            log.addAll(words.subList(1, 4));
            return 4;
        }

        @Override
        public String getName() {
            return "log3";
        }
    }


    @Test
    public void test() {

        Parser parser = new Parser(new Environment());
        parser.addCommand(new LogCommand());

        List<String> SCRIPT = Arrays.asList(
                "log3", "1", "2", "3",
                "log3", "4", "5", "6"
        );

        parser.parse(SCRIPT);

        assertThat(log.toString(), is("[1, 2, 3, 4, 5, 6]"));

    }
}
