package org.apache.pig.plugin.idea;

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
    new AttributesDescriptor("Binary Operators", PigSyntaxHighlighter.BINARY_OPERATOR),
    new AttributesDescriptor("Comments", PigSyntaxHighlighter.COMMENT),
    new AttributesDescriptor("Constant Numbers", PigSyntaxHighlighter.NUMBER),
    new AttributesDescriptor("Exec commands", PigSyntaxHighlighter.EXEC_COMMAND),
    new AttributesDescriptor("Filenames", PigSyntaxHighlighter.FILENAME),
    new AttributesDescriptor("Keywords", PigSyntaxHighlighter.KEYWORD),
    new AttributesDescriptor("Preprocessor Commands", PigSyntaxHighlighter.PREPROCESSOR_COMMAND),
    new AttributesDescriptor("Strings", PigSyntaxHighlighter.STRING),
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
    return "set mapred.reduce.slowstart.completed.maps 0.999;\n" +
      "set job.name 'queue messages for sending';\n" +
      "\n" +
      "%declare today `date \"+%Y/%m/%d\"`\n" +
      "%declare kernel `uname -s`\n" +
      "%declare todayseconds `bash -c 'if [[ \"$kernel\" == \"Linux\" ]]; then date -d $today \"+%s\"; else date -jf \"%Y/%m/%d\" $today \"+%s\"; fi'`\n" +
      "%default todayUTCDate `date \\-\\-utc \"+%Y-%m-%d\"`\n" +
      "\n" +
      "/*\n" +
      "A job that computes things\n" +
      "*/\n" +
      "\n" +
      "REGISTER @fatjar@;\n" +
      "\n" +
      "inputData = LOAD '$input_data' USING BinaryJSON();\n" +
      "\n" +
      "emailSchedule = load '$email_schedule/#LATEST' using LiAvroStorage();\n" +
      "todaysSchedule = filter emailSchedule by receiveUTCDate == '$todayUTCDate';\n" +
      "\n" +
      "scheduledRecipients = filter todaysSchedule by campaign == '$email_key';\n" +
      "\n" +
      "outputData = join scheduledRecipients by memberId, inputData by recipientID PARALLEL 50;\n" +
      "\n" +
      "\n" +
      "-- reorder input to match output schema then send to production with Kafka writer\n" +
      "-- also rename template_id to emailKey\n" +
      "reordered = FOREACH outputData GENERATE\n" +
      "\t(long) $todayseconds * 1000 as expectedDeliveryDate:long,\n" +
      "\t(int) recipientID as recipientID:int,\n" +
      "\t-8 as gmtOffset:int,\n" +
      "\t(int) 2 as categoryID:int,\n" +
      "\t(int) 0 as priority:int,\n" +
      "\t'$email_key' as emailKey:chararray,\n" +
      "\tCONCAT('$email_key','$todayseconds') as contentID:chararray,\n" +
      "\tbody as body:chararray\n" +
      ";\n" +
      "\n" +
      "\n" +
      "\n" +
      "STORE reordered INTO '$kafkahost' USING kafka.KafkaStorage('$kafkaschema');\n" +
      "\n";
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
