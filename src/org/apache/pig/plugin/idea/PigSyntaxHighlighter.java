package org.apache.pig.plugin.idea;
/**
 * @author: rmelick <rmelick@linkedin.com>
 * Date: 3/20/13
 */

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.apache.pig.plugin.idea.psi.PigTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PigSyntaxHighlighter extends SyntaxHighlighterBase {
  public static final TextAttributesKey BINARY_OPERATOR = createTextAttributesKey("PIG_BINARY_OPERATOR", SyntaxHighlighterColors.OPERATION_SIGN);
  public static final TextAttributesKey COMMENT = createTextAttributesKey("PIG_COMMENT", SyntaxHighlighterColors.LINE_COMMENT);
  public static final TextAttributesKey KEYWORD = createTextAttributesKey("PIG_KEYWORD", SyntaxHighlighterColors.KEYWORD);
  public static final TextAttributesKey STRING = createTextAttributesKey("PIG_STRING", SyntaxHighlighterColors.STRING);
  public static final TextAttributesKey FILENAME = createTextAttributesKey("PIG_FILENAME", SyntaxHighlighterColors.STRING);
  public static final TextAttributesKey NUMBER = createTextAttributesKey("PIG_NUMBER", SyntaxHighlighterColors.NUMBER);

  public static final TextAttributesKey EXEC_COMMAND = TextAttributesKey.createTextAttributesKey("PIG_EXEC_COMMAND",
                                                                               new TextAttributes(Color.ORANGE, null, null, null, Font.PLAIN));

  public static final TextAttributesKey PREPROCESSOR_COMMAND = TextAttributesKey.createTextAttributesKey("PIG_PRE_PROCESSOR",
                                                                                       new TextAttributes(Color.BLUE, null, null, null, Font.PLAIN));

  static final TextAttributesKey BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("PIG_BAD_CHARACTER",
                                                                         new TextAttributes(Color.RED, null, null, null, Font.BOLD));

  private static TextAttributesKey createTextAttributesKey(String name, TextAttributesKey defaultAttributes)
  {
    try
    {
      return TextAttributesKey.createTextAttributesKey(name, defaultAttributes);
    }
    catch (NoSuchMethodError e)
    {
      return TextAttributesKey.createTextAttributesKey(name, defaultAttributes.getDefaultAttributes());
    }
  }

  private static final Set<IElementType> _numberTypes= getNumberTypes();

  private static Set<IElementType> getNumberTypes()
  {
    Set<IElementType> numberTypes = new HashSet<IElementType>(5);
    numberTypes.add(PigTypes.NUM_SCALAR);
    numberTypes.add(PigTypes.INTEGER_LITERAL);
    numberTypes.add(PigTypes.LONG_LITERAL);
    numberTypes.add(PigTypes.FLOAT_LITERAL);
    numberTypes.add(PigTypes.DOUBLE_LITERAL);
    return numberTypes;
  }

  private static final Set<IElementType> _binaryOperators = getBinaryOperatorTypes();

  private static final Set<IElementType> getBinaryOperatorTypes()
  {
    Set<IElementType> types = new HashSet<IElementType>(6);
    types.add(PigTypes.PLUS);
    types.add(PigTypes.MINUS);
    types.add(PigTypes.STAR);
    types.add(PigTypes.DIV);
    types.add(PigTypes.PERCENT);
    types.add(PigTypes.EQEQ);
    types.add(PigTypes.DCOLON);
    return types;
  }


  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new FlexAdapter(new PigLexer((Reader) null));
  }

  @NotNull
  @Override
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    System.out.println("Saw token type: " + tokenType);
    ArrayList<TextAttributesKey> attributes = new ArrayList<TextAttributesKey>();

    if (tokenType.equals(PigTypes.COMMENT) | tokenType.toString().endsWith("COMMENT")) {
      attributes.add(COMMENT);
    }

    if (tokenType.equals(PigTypes.DECLARE_COMMAND) || tokenType.equals(PigTypes.DEFAULT_COMMAND))
    {
      attributes.add(PREPROCESSOR_COMMAND);
    }

    if (tokenType.equals(PigTypes.LEXED_FILENAME) | tokenType.equals(PigTypes.FILENAME))
    {
      attributes.add(FILENAME);
    }

    if (tokenType.toString().endsWith("KEYWORD"))
    {
      attributes.add(KEYWORD);
    }

    if (tokenType.equals(PigTypes.STRING_LITERAL))
    {
      attributes.add(STRING);
    }

    if (_numberTypes.contains(tokenType))
    {
      attributes.add(NUMBER);
    }

    if (_binaryOperators.contains(tokenType))
    {
      attributes.add(BINARY_OPERATOR);
    }

    if (tokenType.equals(PigTypes.EXEC_LITERAL))
    {
      attributes.add(EXEC_COMMAND);
    }

    if (tokenType.equals(PigTypes.LPARENTH) || tokenType.equals(PigTypes.RPARENTH))
    {
      attributes.add(SyntaxHighlighterColors.PARENTHS);
    }

    if (tokenType.equals(PigTypes.LBRACE) || tokenType.equals(PigTypes.LBRACE))
    {
      attributes.add(SyntaxHighlighterColors.BRACES);
    }

    if (tokenType.equals(PigTypes.LBRACK) || tokenType.equals(PigTypes.LBRACK))
    {
      attributes.add(SyntaxHighlighterColors.BRACKETS);
    }

    if (tokenType.equals(PigTypes.COMMA))
    {
      attributes.add(SyntaxHighlighterColors.COMMA);
    }

    if (tokenType.equals(PigTypes.SEMICOLON))
    {
      attributes.add(SyntaxHighlighterColors.JAVA_SEMICOLON);
    }

    if (tokenType.equals(TokenType.BAD_CHARACTER)) {
      attributes.add(BAD_CHARACTER);
    }



    if (attributes.size() < 1) {
      System.err.println("Unrecognized token type: " + tokenType);
    }

    return attributes.toArray(new TextAttributesKey[attributes.size()]);
  }
}
