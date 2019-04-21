//package test;
//
//import alon.flightsim.Environment;
//import alon.flightsim.language.command.Command;
//import alon.flightsim.language.interpreter.Lexer;
//import alon.flightsim.language.interpreter.Parser;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static java.lang.Integer.parseInt;
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.assertThat;
//
//public class WhileCommandTest {
//
//    private final Environment env = new Environment();
//    private List<String> log = new ArrayList<>();
//
//    class LogCommand implements Command {
//
//        @Override
//        public int execute(List<String> words) {
//            log.add(words.get(1));
//            return 2;
//        }
//
//        @Override
//        public String getName() {
//            return "log";
//        }
//    }
//
//    class SetVar implements Command {
//
//        @Override
//        public int execute(List<String> words) {
//            env.getSymbolTable().put(words.get(1), words.get(3));
//            return 4;
//        }
//
//        @Override
//        public String getName() {
//            return "set";
//        }
//    }
//
//    class DecreaseCommand implements Command {
//
//        @Override
//        public int execute(List<String> words) {
//            //env.getSymbolTable().put(words.get(1), Integer.toString((parseInt(env.getSymbolTable().get(words.get(1))) - 1)));
//            return 2;
//        }
//
//        @Override
//        public String getName() {
//            return "decrease";
//        }
//    }
//
//    @Test
//    public void test_loop_1_time() {
//
//        Parser parser = new Parser(env);
//        env.setParser(parser);
//        parser.addCommand(new LogCommand());
//        parser.addCommand(new SetVar());
//        parser.addCommand(new DecreaseCommand());
//
//        String SCRIPT =
//                "set x = 5 \n" +
//                "while x!=2 { \n" +
//                "  decrease x \n" +
//                "}";
//
//        parser.parse(Lexer.Lexer(SCRIPT));
//
//        assertThat(env.getSymbolTable().get("x"), is("2"));
//
//
//    }
//}
