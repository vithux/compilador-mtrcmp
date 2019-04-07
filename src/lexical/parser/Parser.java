package lexical.parser;

import exceptions.DuplicatedIdentifierException;
import exceptions.ExpectedTokenException;
import exceptions.IllegalTokenException;
import exceptions.ReservedIdentifierException;
import loader.FileLoader;
import token.Token;

import java.io.IOException;

public interface Parser {

    Token parse(FileLoader fileLoader) throws IOException, IllegalTokenException, ExpectedTokenException, ReservedIdentifierException, DuplicatedIdentifierException;

}
