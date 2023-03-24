package org.example.commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {
    String description() default "WIP";

    boolean option1() default false;
    boolean option2() default false;
    boolean option3() default false;

    OptionType option1Type() default OptionType.STRING;
    OptionType option2Type() default OptionType.STRING;
    OptionType option3Type() default OptionType.STRING;

    String option1Name() default "option1";
    String option2Name() default "option2";
    String option3Name() default "option3";

    String option1Desc() default "WIP";
    String option2Desc() default "WIP";
    String option3Desc() default "WIP";

}
