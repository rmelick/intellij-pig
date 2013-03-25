package com.linkedin.pig; /**
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
import com.linkedin.pig.psi.PigTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Reader;
import java.util.ArrayList;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class PigSyntaxHighlighter extends SyntaxHighlighterBase {
  public static final TextAttributesKey OPERATOR = createTextAttributesKey("PIG_OPERATOR", SyntaxHighlighterColors.OPERATION_SIGN);
  public static final TextAttributesKey COMMENT = createTextAttributesKey("PIG_COMMENT", SyntaxHighlighterColors.LINE_COMMENT);
  public static final TextAttributesKey KEYWORD = createTextAttributesKey("PIG_KEYWORD", SyntaxHighlighterColors.KEYWORD);
  public static final TextAttributesKey STRING = createTextAttributesKey("PIG_STRING", SyntaxHighlighterColors.STRING);
  public static final TextAttributesKey FILENAME = createTextAttributesKey("PIG_FILENAME", SyntaxHighlighterColors.STRING);
  public static final TextAttributesKey EXEC_COMMAND = createTextAttributesKey("PIG_EXEC_COMMAND", SyntaxHighlighterColors.STRING);

  static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("PIG_BAD_CHARACTER",
                                                                         new TextAttributes(Color.RED, null, null, null, Font.BOLD));

  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new FlexAdapter(new PigLexer((Reader) null));
  }

  @NotNull
  @Override
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    ArrayList<TextAttributesKey> attributes = new ArrayList<TextAttributesKey>();

    if (tokenType.equals(PigTypes.COMMENT)) {
      attributes.add(COMMENT);
    }
    if (tokenType.toString().endsWith("KEYWORD"))
    {
      attributes.add(KEYWORD);
    }

    if (tokenType.equals(PigTypes.STRING_LITERAL))
    {
      attributes.add(STRING);
    }

    if (tokenType.equals(PigTypes.FILENAME))
    {
      attributes.add(FILENAME);
    }

    if (tokenType.equals(TokenType.BAD_CHARACTER)) {
      attributes.add(BAD_CHARACTER);
    }

    if (attributes.size() < 1) {
      System.err.println("Unrecognized token type: " + tokenType);
    }

    return attributes.toArray(new TextAttributesKey[]{});
  }
}
