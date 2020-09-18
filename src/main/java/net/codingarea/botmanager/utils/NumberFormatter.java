package net.codingarea.botmanager.utils;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.text.DecimalFormat;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 2.2
 */
public interface NumberFormatter {

	@Nonnull
	@CheckReturnValue
	String format(double value);

	@Nonnull
	@CheckReturnValue
	default String format(long value) {
		return format((double) (value));
	}

	public static final NumberFormatter
			DEFAULT = fromPattern("0.##", ""),
			FLOATING_POINT = fromPattern("0.0", ""),
			PERCENTAGE = fromPattern("0.##", "%"),
			MIDDLE_NUMBER = fromPattern("###,###,###,###,###,###,###,###,###,###,###,##0.#", ""),

			/* days, hours, minutes, seconds */
			TIME = value -> {

				int seconds = (int) value;
				int minutes = seconds / 60;
				int hours = minutes / 60;
				int days = hours / 24;

				seconds %= 60;
				minutes %= 60;
				hours %= 24;

				return (days > 0 ? days + "d " : "")
					 + (hours > 0 ? hours + "h " : "")
					 + (minutes > 0 ? minutes + "m " : "")
					 + (days == 0 ? seconds + "s" : "");

			},

			/* days, hours, minutes */
			BIG_TIME = value -> {

				int seconds = (int) value;
				int minutes = seconds / 60;
				int hours = minutes / 60;
				int days = hours / 24;
				minutes %= 60;
				hours %= 24;

				return (days > 0 ? days + "d " : "")
						+ (hours > 0 ? hours + "h " : "")
						+ (minutes > 0 ? minutes + "m " : "");

			},

			/* billion, million, thousand, number */
			BIG_NUMBER = value -> {

				DecimalFormat format = new DecimalFormat("0.##");
				double divide;
				String ending = "";

				// Normal number
				if (value < 1000) {
					divide = 1;
					format = new DecimalFormat("0.#");
				// Thousand
				} else if (value < 1000000) {
					divide = 1000;
					ending = "k";
				// Million
				} else if (value < 1000000000) {
					divide = 1000000;
					ending = "m";
				// Billion
				} else {
					divide = 1000000000;
					ending = "b";
				}

				value /= divide;
				return format.format(value) + ending;

			};

	@Nonnull
	@CheckReturnValue
	public static NumberFormatter fromPattern(@Nonnull String pattern, @Nonnull String ending) {
		return value -> new DecimalFormat(pattern).format(value) + ending;
	}

}
