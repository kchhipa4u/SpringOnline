package com.onlinelearning.day8.conditional;

import java.util.Locale;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LocaleConditionCanada implements Condition {

    @Override
    public boolean matches (ConditionContext context, AnnotatedTypeMetadata metadata) {
        return Locale.getDefault()
                     .equals(Locale.CANADA);
    }
}