package net.anweisen.commandmanager.listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CommandManager developed on 08-01-2020
 * https://github.com/anweisen
 * @author anweisen
 * @since 1.2
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

}
