package cn.sskbskdrin.pickers;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by ayke on 2016/10/14 0014.
 */
public class PickerNumberView extends PickerView {

	private int mMax = 0;
	private int mMin = 0;

	public PickerNumberView(Context context) {
		super(context);
	}

	public PickerNumberView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setValue(int max, int min) {
		mMax = max > min ? max : min;
		mMin = min < max ? min : max;
		postInvalidate();
	}

	@Override
	protected String getItem(int position) {
		if (position < 0)
			return "";
		return (position + mMin) + "";
	}

	@Override
	protected int getItemCount() {
		return mMax - mMin + 1;
	}
}
