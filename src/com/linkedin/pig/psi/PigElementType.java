package com.linkedin.pig.psi;

/**
 * @author: rmelick <rmelick@linkedin.com>
 * Date: 3/19/13
 */
import com.intellij.psi.tree.IElementType;
import com.linkedin.pig.PigLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class PigElementType extends IElementType {
  public PigElementType(@NotNull @NonNls String debugName) {
    super(debugName, PigLanguage.INSTANCE);
  }
}
