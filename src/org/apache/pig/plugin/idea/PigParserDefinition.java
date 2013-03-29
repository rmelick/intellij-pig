package org.apache.pig.plugin.idea;

/**
 * @author: rmelick <rmelick@linkedin.com>
 * Date: 3/20/13
 */

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.apache.pig.plugin.idea.parser.PigParser;
import org.apache.pig.plugin.idea.psi.PigFile;
import org.apache.pig.plugin.idea.psi.PigTypes;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;

public class PigParserDefinition implements ParserDefinition{
  public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
  public static final TokenSet COMMENTS = TokenSet.create(PigTypes.COMMENT);

  public static final IFileElementType FILE = new IFileElementType(Language.<PigLanguage>findInstance(PigLanguage.class));

  @NotNull
  @Override
  public Lexer createLexer(Project project) {
    return new FlexAdapter(new PigLexer((Reader) null));
  }

  @NotNull
  public TokenSet getWhitespaceTokens() {
    return WHITE_SPACES;
  }

  @NotNull
  public TokenSet getCommentTokens() {
    return COMMENTS;
  }

  @NotNull
  public TokenSet getStringLiteralElements() {
    return TokenSet.EMPTY;
  }

  @NotNull
  public PsiParser createParser(final Project project) {
    return new PigParser();
  }

  @Override
  public IFileElementType getFileNodeType() {
    return FILE;
  }

  public PsiFile createFile(FileViewProvider viewProvider) {
    return new PigFile(viewProvider);
  }

  public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }

  @NotNull
  public PsiElement createElement(ASTNode node) {
    return PigTypes.Factory.createElement(node);
  }
}
