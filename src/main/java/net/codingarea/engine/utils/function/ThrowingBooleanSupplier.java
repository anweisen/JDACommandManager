package net.codingarea.engine.utils.function;

import net.codingarea.engine.exceptions.ConsumeException;

import java.util.function.BooleanSupplier;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see java.util.function.BooleanSupplier
 */
@FunctionalInterface
public interface ThrowingBooleanSupplier extends BooleanSupplier {

	boolean getExceptionally() throws Exception;

	@Override
	default boolean getAsBoolean() {
		try {
			return getExceptionally();
		} catch (Exception ex) {
			throw new ConsumeException(ex);
		}
	}
}
