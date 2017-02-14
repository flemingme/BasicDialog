package tw.com.chainsea.learndialog;

import android.app.DatePickerDialog;
import android.content.Context;

/**
 * MyDatePickerDialog
 * Created by Fleming on 2017/2/14.
 */

public class MyDatePickerDialog extends DatePickerDialog {

    public MyDatePickerDialog(Context context, OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, listener, year, month, dayOfMonth);
    }

    public MyDatePickerDialog(Context context, int themeResId, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, themeResId, listener, year, monthOfYear, dayOfMonth);
    }

    //解决兼容性问题，即onDateSet方法回调两次的bug
    @Override
    protected void onStop() {

    }
}
