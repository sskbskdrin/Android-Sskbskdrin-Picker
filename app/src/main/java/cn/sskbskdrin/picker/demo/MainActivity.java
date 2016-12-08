package cn.sskbskdrin.picker.demo;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cn.sskbskdrin.base.BaseFragmentActivity;
import cn.sskbskdrin.pickers.PickerNumberView;
import cn.sskbskdrin.pickers.PickerView;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {
	PickerNumberView mPickerYearView;
	PickerNumberView mPickerMonthView;
	PickerNumberView mPickerDayView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPickerYearView = $(R.id.main_picker_year);
		mPickerYearView.setUnitText("年");
		mPickerMonthView = $(R.id.main_picker_month);
		mPickerMonthView.setUnitText("月");
		mPickerDayView = $(R.id.main_picker_day);
		mPickerDayView.setUnitText("日");

		mPickerYearView.setValue(1970, 2100);

		mPickerMonthView.setValue(1, 12);
		mPickerMonthView.setCycle(true);
		mPickerMonthView.setOnSelectListener(new PickerView.onSelectListener() {
			@Override
			public void onSelect(int position) {

			}

			@Override
			public void onTicker(int oldPosition, int newPosition) {
				if (oldPosition == 0 && newPosition == 11) {
					mPickerYearView.setCurrentSelect(mPickerYearView.getCurrentSelect() - 1);
				}
				if (oldPosition == 11 && newPosition == 0) {
					mPickerYearView.setCurrentSelect(mPickerYearView.getCurrentSelect() + 1);
				}

				Calendar c = new GregorianCalendar(mPickerYearView.getCurrentSelect() + 1970, newPosition, 1);
				mPickerDayView.setValue(1, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			}
		});

		mPickerDayView.setValue(1, 31);

		PickerView picker = $(R.id.main_picker);
		List<String> list = new ArrayList<>();
		for (int i = 'A'; i <= 'Z'; i++) {
			list.add((char)i+"");
		}
		picker.setDataList(list);
		picker.setCycle(true);
//		mPickerDayView.setCycle(true);
		//		number.setCycle(true);
	}

	@Override
	public void onClick(View view) {
		//		mPickerView.setCurrentSelect(5, true);
		//		mPickerView.computeOffset(480);
	}
}
