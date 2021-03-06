/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package token;

public enum TokenType {
    NUM_INT,
    NUM_FLOAT,
    LITERAL,
    ID,
    RELOP,
    ARIT_AS,
    ARIT_MD,
    ASSIGN,
    TERM,
    L_PAR,
    R_PAR,
    LOGIC_VAL,
    LOGIC_OP,
    TYPE,
    PROGRAM,
    END_PROG,
    BEGIN,
    END,
    IF,
    THEN,
    ELSE,
    FOR,
    WHILE,
    DECLARE,
    TO,
    EOF,
    ERROR
}
