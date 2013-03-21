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

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class PigSyntaxHighlighter extends SyntaxHighlighterBase {
  public static final TextAttributesKey SEPARATOR = createTextAttributesKey("PIG_SEPARATOR", SyntaxHighlighterColors.OPERATION_SIGN);
  public static final TextAttributesKey KEY = createTextAttributesKey("PIG_KEY", SyntaxHighlighterColors.KEYWORD);
  public static final TextAttributesKey VALUE = createTextAttributesKey("PIG_VALUE", SyntaxHighlighterColors.STRING);
  public static final TextAttributesKey COMMENT = createTextAttributesKey("PIG_COMMENT", SyntaxHighlighterColors.LINE_COMMENT);

  static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("PIG_BAD_CHARACTER",
                                                                         new TextAttributes(Color.RED, null, null, null, Font.BOLD));

  private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
  private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
  private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
  private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
  private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
  private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new FlexAdapter(new PigLexer((Reader) null));
  }

  @NotNull
  @Override
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    if (tokenType.equals(PigTypes.SEPARATOR)) {
      return SEPARATOR_KEYS;
    } else if (tokenType.equals(PigTypes.KEY)) {
      return KEY_KEYS;
    } else if (tokenType.equals(PigTypes.VALUE)) {
      return VALUE_KEYS;
    } else if (tokenType.equals(PigTypes.COMMENT)) {
      return COMMENT_KEYS;
    } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
      return BAD_CHAR_KEYS;
    } else {
      return EMPTY_KEYS;
    }
  }
}
