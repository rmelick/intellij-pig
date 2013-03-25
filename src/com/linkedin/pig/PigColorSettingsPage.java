package com.linkedin.pig;

/**
 * @author: rmelick <rmelick@linkedin.com>
 * Date: 3/20/13
 */

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class PigColorSettingsPage implements ColorSettingsPage {
  private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
    new AttributesDescriptor("Keywords", PigSyntaxHighlighter.KEYWORD),
    new AttributesDescriptor("Operators", PigSyntaxHighlighter.OPERATOR),
    new AttributesDescriptor("Comments", PigSyntaxHighlighter.COMMENT),
  };

  @Nullable
  @Override
  public Icon getIcon() {
    return PigIcons.FILE;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new PigSyntaxHighlighter();
  }

  @NotNull
  @Override
  public String getDemoText() {
    return "IMPORT 'myjar.jar';\n" +
      "\n" +
      "x = LOAD '/data/derived/blah#LATEST';\n" +
      "x = LOAD '/data/derived/blah#LATEST' USING com.linkedin.LiAvroStorage('date.range', 'num.days=40');\n" +
      "\n" +
      "STORE x INTO '/mydata/' USING PigStorage();\n" +
      "\n" +
      "DEFINE GetTreatment com.linkedin.GetTreatment('stuff');\n" +
      "\n" +
      "z = GROUP a BY (first);\n" +
      "\n" +
      "x = FILTER y BY (col1 == 'abc');\n" +
      "\n" +
      "z = DISTINCT a PARALLEL 1;\n" +
      "\n" +
      "Z = LIMIT a 10;\n" +
      "\n" +
      "Z = SAMPLE a 10.0;\n" +
      "\n" +
      "ORDER a BY col1 ASC;\n" +
      "\n" +
      "X = CROSS A, B;\n" +
      "\n" +
      "a = UNION a, b;\n" +
      "\n" +
      "B = STREAM A THROUGH `stream.pl -n 5`;\n" +
      "\n" +
      "B = MAPREDUCE 'wordcount.jar' STORE A INTO 'inputDir' LOAD 'outputDir' `org.myorg.WordCount inputDir outputDir`;\n" +
      "\n" +
      "A = JOIN x BY col1, y BY col2 USING 'replicated';\n" +
      "\n" +
      "A = FILTER a BY (col1 + (INT) col2) == 2;\n" +
      "\n" +
      "/*\n" +
      "Things\n" +
      "*/";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return null;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return DESCRIPTORS;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "Pig";
  }
}
