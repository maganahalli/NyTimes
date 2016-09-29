package com.mac.nytimes.customDatePicker;

/**
 * Models GEICO date picker cell status (Enabled or Disabled) for user
 * selection.
 *
 * @author Venky Maganahalli
 */
public enum DatePickerCellStatus {
	DISABLED {
		@Override
		public boolean isEnabled() {
			return false;
		}
	},
	ENABLED {
		@Override
		public boolean isEnabled() {
			return true;
		}
	};

	public static DatePickerCellStatus fromString(String accountType) {
		return "DISABLED".equals(accountType) ? DISABLED : ENABLED;
	}

	public boolean isEnabled() {
		return false;
	}

}
