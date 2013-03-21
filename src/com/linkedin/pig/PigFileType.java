package com.linkedin.pig;

/**
 * @author: rmelick <rmelick@linkedin.com>
 * Date: 3/19/13
 */
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PigFileType extends LanguageFileType {
  public static final PigFileType INSTANCE = new PigFileType();

  private PigFileType() {
    super(PigLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return "Pig file";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Pig language file";
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return "pig";
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return PigIcons.FILE;
  }
}
