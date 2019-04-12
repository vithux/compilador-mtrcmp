/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
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
