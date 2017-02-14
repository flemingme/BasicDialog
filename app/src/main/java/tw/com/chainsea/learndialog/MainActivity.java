package tw.com.chainsea.learndialog;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static tw.com.chainsea.learndialog.R.array.gender;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private ListView lvDialog;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDialog = ((ListView) findViewById(R.id.listView));
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.dialogs));
        lvDialog.setAdapter(arrayAdapter);
        lvDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        showRawDialog();
                        break;
                    case 1:
                        showRadioDialog();
                        break;
                    case 2:
                        showCheckBoxDialog();
                        break;
                    case 3:
                        showIconDialog();
                        break;
                }
            }
        });
    }

    private void showRawDialog() {
        final String[] arrayColor = getResources().getStringArray(R.array.colors);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请选择");
        builder.setItems(arrayColor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: " + arrayColor[i]);
                toast("你选择的是：" + arrayColor[i]);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showRadioDialog() {
        final String[] arrayGender = getResources().getStringArray(gender);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请选择");
        builder.setSingleChoiceItems(arrayGender, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: " + arrayGender[i]);
                toast("你选择的是：" + arrayGender[i]);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showCheckBoxDialog() {
        final String[] arrayFruits = getResources().getStringArray(R.array.fruits);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请问你喜欢吃什么水果？");
        final boolean[] pos = new boolean[arrayFruits.length];
        builder.setMultiChoiceItems(arrayFruits, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                pos[i] = b;
            }
        });
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0, length = pos.length; i < length; i++) {
                    if (pos[i]) {
                        sb.append(arrayFruits[i]);
                        sb.append("、");
                    }
                }
                Log.d(TAG, "onClick: " + sb.deleteCharAt(sb.length() - 1).toString());
                toast("你选择的是：" + sb.deleteCharAt(sb.length() - 1).toString());
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showIconDialog() {
        final String[] arrayColor = getResources().getStringArray(R.array.colors);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请选择");
        builder.setAdapter(new ListItemAdapter(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toast("你选择的是：" + arrayColor[which]);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onDialogClick(View view) {
        switch (view.getId()) {
            case R.id.bt_alert:
                showAlertDialog();
                break;
            case R.id.bt_pgs:
                showPgsDialog();
                break;
            case R.id.bt_date:
                showDatePickerDialog();
                break;
            case R.id.bt_time:
                showTimePickerDialog();
                break;
            case R.id.bt_custom:
                showCustomDialog();
                break;
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("您有一笔2千万的汇款在处理，请问是您亲自操作的吗？");
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: " + i);
                toast(R.string.confirm);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                toast(R.string.cancel);
            }
        });
        builder.setNeutralButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: " + i);
                toast(R.string.exit);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showPgsDialog() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("下载提示");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.background), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 10;
                while (i <= 100) {
                    try {
                        Thread.sleep(100);
                        dialog.incrementProgressBy(1);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }
        }).start();
        dialog.show();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new MyDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                Log.d(TAG, "onDateSet: " + year + "." + (monthOfYear + 1) + "." + dayOfMonth);
                toast("你选择的日期是：" + year + "." + (monthOfYear + 1) + "." + dayOfMonth);
            }
        }, year, month, day);
        dialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new MyTimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                Log.d(TAG, "onTimeSet: " + hourOfDay + ":" + minute);
                toast("你选择的时间是：" + hourOfDay + ":" + minute);
            }
        }, hour, minute, true);
        dialog.show();
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录提示");
        final View root = LayoutInflater.from(this).inflate(R.layout.dialog_login, null);
        builder.setView(root);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = ((EditText) root.findViewById(R.id.et_name)).getText().toString();
                String pwd = ((EditText) root.findViewById(R.id.et_pwd)).getText().toString();
                if (TextUtils.isEmpty(name)) {
                    toast("账号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    toast("密码不能为空");
                    return;
                }
                toast("账号：" + name + ", 密码：" + pwd);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void toast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    private class ListItemAdapter extends BaseAdapter {

        int[] imgIds = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        @Override
        public int getCount() {
            return imgIds.length;
        }

        @Override
        public Integer getItem(int position) {
            return imgIds[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(MainActivity.this);
            textView.setText(getResources().getStringArray(R.array.colors)[position]);
            textView.setTextSize(24);
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT
            );
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setLayoutParams(layoutParams);
            textView.setCompoundDrawablesWithIntrinsicBounds(getItem(position), 0, 0, 0);
            textView.setCompoundDrawablePadding(8);
            return textView;
        }
    }
}
