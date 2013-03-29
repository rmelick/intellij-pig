package org.apache.pig.plugin.idea;

/**
 * @author: rmelick <rmelick@linkedin.com>
 * Date: 3/20/13
 */

import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class PigSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
  @NotNull
  @Override
  public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
    return new PigSyntaxHighlighter();
  }
}
