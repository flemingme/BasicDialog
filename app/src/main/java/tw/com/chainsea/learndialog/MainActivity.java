package tw.com.chainsea.learndialog;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private ListView lvDialog;
    private String[] s = new String[]{"普通列表对话框", "单选列表对话框", "多选列表对话框", "带图标的列表对话框"};
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDialog = ((ListView) findViewById(R.id.listView));
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, s);
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
        final String[] arrayColor = new String[]{"红色", "黄色", "蓝色"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请选择");
        builder.setItems(arrayColor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: "+arrayColor[i]);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: " + i);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showRadioDialog() {
        final String[] arrayGender = new String[]{"男生", "女生", "保密"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请选择");
        builder.setSingleChoiceItems(arrayGender, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: "+arrayGender[i]);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: " + i);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showCheckBoxDialog() {
        final String[] arrayFruits = {"苹果", "香蕉", "梨子", "葡萄"};
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
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pos.length; i++) {
                    if (pos[i]){
                        sb.append(arrayFruits[i]);
                        sb.append("、 ");
                    }
                }
                Log.d(TAG, "onClick: "+sb.toString());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showIconDialog() {

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
                break;
        }
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                Log.d(TAG, "onTimeSet: " + hourOfDay + ":" + minute);
            }
        }, hour, minute, true);
        dialog.show();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                Log.d(TAG, "onDateSet: " + year + "." + (monthOfYear + 1) + "." + dayOfMonth);
            }
        }, year, month, day);
        dialog.show();
    }

    private void showPgsDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("提示");
        dialog.setMessage("加载中...");
        dialog.show();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("您有一笔2千万的汇款在处理，请问是您亲自操作的吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: " + i);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNeutralButton("普通", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: " + i);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
