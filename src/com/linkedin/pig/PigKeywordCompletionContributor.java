package com.linkedin.pig;

/**
 * @author: rmelick <rmelick@linkedin.com>
 * Date: 3/27/13
 */

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.linkedin.pig.psi.PigTypes;
import org.jetbrains.annotations.NotNull;

public class PigKeywordCompletionContributor extends CompletionContributor {
  public PigKeywordCompletionContributor() {
    extend(CompletionType.BASIC,
           PlatformPatterns.psiElement(PigTypes.ALIAS).withLanguage(PigLanguage.INSTANCE),
           new CompletionProvider<CompletionParameters>() {
             public void addCompletions(@NotNull CompletionParameters parameters,
                                        ProcessingContext context,
                                        @NotNull CompletionResultSet resultSet) {
               resultSet.addElement(LookupElementBuilder.create("Hello"));
             }
           }
    );
  }
}
