package com.mac.nytimes.customDatePicker;

/**
 * For special handling for January and December months.
 *
 * @author u1d090
 *
 */
public enum MonthYearDeterminer implements AceApplicability<Integer> {
	DECEMBER {
		@Override
		public boolean isApplicable(Integer context) {
			return 11 == context;
		}

		@Override
		public boolean isDecember() {
			return true;
		}
	},
	JANUARY {
		@Override
		public boolean isApplicable(Integer context) {
			return 0 == context;
		}

		@Override
		public boolean isJanuary() {
			return true;
		}
	},
	REST_OF_MONTHS {
		@Override
		public boolean isApplicable(Integer context) {
			return true;
		}

		@Override
		public boolean isRestOfMonths() {
			return true;
		}
	};

	public static MonthYearDeterminer fromMonthIndex(Integer index) {
		for (MonthYearDeterminer currentMonth : MonthYearDeterminer.values()) {
			if (currentMonth.isApplicable(index)) {
				return currentMonth;
			}
		}
		return MonthYearDeterminer.REST_OF_MONTHS;
	}

	public boolean isDecember() {
		return false;
	}

	public boolean isJanuary() {
		return false;
	}

	public boolean isRestOfMonths() {
		return false;
	}

}
