package org.apache.pig.plugin.idea;

/**
 * @author: rmelick <rmelick@linkedin.com>
 * Date: 3/19/13
 */

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

public class PigFileTypeFactory extends FileTypeFactory{
  @Override
  public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
    fileTypeConsumer.consume(PigFileType.INSTANCE, "pig");
  }
}
