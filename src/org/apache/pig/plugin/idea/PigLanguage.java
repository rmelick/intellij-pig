package org.apache.pig.plugin.idea;

import com.intellij.lang.Language;

/**
 * @author: rmelick <rmelick@linkedin.com>
 * Date: 3/19/13
 */

public class PigLanguage extends Language {
  public static final PigLanguage INSTANCE = new PigLanguage();

  private PigLanguage() {
    super("Pig");
  }
}
