package com.mac.nytimes.customDatePicker;

/**
 * API for objects that may apply to a given situation
 *
 * @author Venky
 * @param <C> Type of Situation - typically a context
 */
public interface AceApplicability<C> {

	/**
	 * Answer whether the receiver applies to the situation
	 *
	 * @param context the context to which the receiver may apply
	 * @return true/false
	 */
	boolean isApplicable(C context);

}
