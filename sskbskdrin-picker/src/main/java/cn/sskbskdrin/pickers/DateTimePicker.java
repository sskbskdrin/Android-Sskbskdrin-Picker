package cn.sskbskdrin.pickers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Calendar;

/**
 * Created by sskbskdrin on 2017/十月/5.
 */
public class DateTimePicker extends LinearLayout {
	PickerNumberView mPickerYearView;
	PickerNumberView mPickerMonthView;
	PickerNumberView mPickerDayView;
	PickerNumberView mPickerHourView;
	PickerNumberView mPickerMinuteView;

	private Calendar mCurrentTime;

	public DateTimePicker(Context context) {
		this(context, null);
	}

	public DateTimePicker(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mCurrentTime = Calendar.getInstance();
		setOrientation(HORIZONTAL);
		mPickerYearView = new PickerNumberView(context);
		mPickerMonthView = new PickerNumberView(context);
		mPickerDayView = new PickerNumberView(context);
		mPickerHourView = new PickerNumberView(context);
		mPickerMinuteView = new PickerNumberView(context);
		addView(mPickerYearView, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
		addView(mPickerMonthView, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
		addView(mPickerDayView, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
		addView(mPickerHourView, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
		addView(mPickerMinuteView, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

		mPickerYearView.setValue(1970, 2100);
		mPickerYearView.setUnitText("年");
		mPickerYearView.setCurrentSelect(mCurrentTime.get(Calendar.YEAR) - 1970, false);
		mPickerYearView.setOnSelectListener(new PickerView.SimpleOnSelectListener() {
			@Override
			public void onTicker(int oldPosition, int newPosition) {
				if (mPickerMonthView.getCurrentSelect() == 1 && ((newPosition + 1970) % 4 == 0 || (oldPosition + 1970)
					% 4 == 0)) {
					updateDayOfMonth(newPosition + 1970, mPickerMonthView.getCurrentSelect());
				}
			}
		});

		mPickerMonthView.setValue(1, 12);
		mPickerMonthView.setUnitText("月");
		mPickerMonthView.setCycle(true);
		mPickerMonthView.setCurrentSelect(mCurrentTime.get(Calendar.MONTH), false);
		mPickerMonthView.setOnSelectListener(new PickerView.SimpleOnSelectListener() {
			@Override
			public void onTicker(int oldPosition, int newPosition) {
				int currentYear = mPickerYearView.getCurrentSelect();
				if (oldPosition == 0 && newPosition == 11) {
					mPickerYearView.setCurrentSelect(currentYear - 1);
				}
				if (oldPosition == 11 && newPosition == 0) {
					mPickerYearView.setCurrentSelect(currentYear + 1);
				}
				updateDayOfMonth(mPickerYearView.getCurrentSelect() + 1970, newPosition);
			}
		});
		mPickerDayView.setValue(1, mCurrentTime.getActualMaximum(Calendar.DAY_OF_MONTH));
		mPickerDayView.setUnitText("日");
		mPickerDayView.setCurrentSelect(mCurrentTime.get(Calendar.DAY_OF_MONTH) - 1, false);

		mPickerHourView.setValue(0, 23);
		mPickerHourView.setUnitText("时");
		mPickerHourView.setCycle(true);
		mPickerHourView.setCurrentSelect(mCurrentTime.get(Calendar.HOUR_OF_DAY), false);

		mPickerMinuteView.setValue(0, 59);
		mPickerMinuteView.setUnitText("分");
		mPickerMinuteView.setCycle(true);
		mPickerMinuteView.setCurrentSelect(mCurrentTime.get(Calendar.MINUTE), false);
	}

	private void updateDayOfMonth(int year, int month) {
		mCurrentTime.set(year, month, 1);
		mPickerDayView.setValue(1, mCurrentTime.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	public void setEnable(boolean date, boolean time) {
		if (date) {
			mPickerYearView.setVisibility(VISIBLE);
			mPickerMonthView.setVisibility(VISIBLE);
			mPickerDayView.setVisibility(VISIBLE);
		} else {
			mPickerYearView.setVisibility(GONE);
			mPickerMonthView.setVisibility(GONE);
			mPickerDayView.setVisibility(GONE);
		}
		if (time) {
			mPickerHourView.setVisibility(VISIBLE);
			mPickerMinuteView.setVisibility(VISIBLE);
		} else {
			mPickerHourView.setVisibility(GONE);
			mPickerMinuteView.setVisibility(GONE);
		}
	}

	public long getTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(mPickerYearView.getCurrentSelect() + 1970, mPickerMonthView.getCurrentSelect(), mPickerDayView
			.getCurrentSelect() + 1, mPickerHourView.getCurrentSelect(), mPickerMinuteView.getCurrentSelect());
		return calendar.getTimeInMillis();
	}
}
