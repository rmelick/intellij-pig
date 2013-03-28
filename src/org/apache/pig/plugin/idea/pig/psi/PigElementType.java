package org.apache.pig.plugin.idea.pig.psi;

/**
 * @author: rmelick <rmelick@linkedin.com>
 * Date: 3/19/13
 */
import com.intellij.psi.tree.IElementType;
import org.apache.pig.plugin.idea.pig.PigLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class PigElementType extends IElementType {
  public PigElementType(@NotNull @NonNls String debugName) {
    super(debugName, PigLanguage.INSTANCE);
  }
}
