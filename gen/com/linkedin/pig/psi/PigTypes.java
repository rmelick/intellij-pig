// This is a generated file. Not intended for manual editing.
package com.linkedin.pig.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.linkedin.pig.psi.impl.*;

public interface PigTypes {

  IElementType PROPERTY = new PigElementType("PROPERTY");

  IElementType COMMENT = new PigTokenType("COMMENT");
  IElementType CRLF = new PigTokenType("CRLF");
  IElementType KEY = new PigTokenType("KEY");
  IElementType SEPARATOR = new PigTokenType("SEPARATOR");
  IElementType VALUE = new PigTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == PROPERTY) {
        return new PigPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
