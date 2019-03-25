package lexical.parser;

import loader.FileLoader;
import token.Token;

import java.io.IOException;

public interface Parser {

    Token parse(FileLoader fileLoader) throws IOException;

}
