package cn.sskbskdrin.picker.demo;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.sskbskdrin.base.BaseFragmentActivity;
import cn.sskbskdrin.pickers.DateTimePicker;
import cn.sskbskdrin.pickers.PickerView;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		PickerView picker = $(R.id.main_picker);
		List<String> list = new ArrayList<>();
		for (int i = 'A'; i <= 'Z'; i++) {
			list.add((char) i + "");
		}
		picker.setDataList(list);
		picker.setCycle(true);

		DateTimePicker dateTimePicker = $(R.id.main_date_time);
		dateTimePicker.setEnable(true, true);

	}

	@Override
	public void onClick(View view) {
		//		mPickerView.setCurrentSelect(5, true);
		//		mPickerView.computeOffset(480);
	}
}
