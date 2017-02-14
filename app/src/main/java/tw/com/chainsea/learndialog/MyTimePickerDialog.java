package tw.com.chainsea.learndialog;

import android.app.TimePickerDialog;
import android.content.Context;

/**
 * MyTimePickerDialog
 * Created by Fleming on 2017/2/14.
 */

public class MyTimePickerDialog extends TimePickerDialog {

    public MyTimePickerDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
    }

    public MyTimePickerDialog(Context context, int themeResId, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, themeResId, listener, hourOfDay, minute, is24HourView);
    }

    //解决兼容性问题，即onTimeSet方法回调两次的bug
    @Override
    protected void onStop() {

    }
}
