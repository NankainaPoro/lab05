package g313.vakulenko.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class activity_1 extends AppCompatActivity {

    EditText txt;
    Switch sw_1, sw_2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        txt = findViewById(R.id.et_data);
        sw_1 = findViewById(R.id.switch_one);
        sw_2 = findViewById(R.id.switch_two);

        // Получение данных из интента, переданных из MainActivity
        Intent i = getIntent();
        String s = i.getStringExtra("main_s");
        boolean check1 = i.getBooleanExtra("main_c1", false);
        boolean check2 = i.getBooleanExtra("main_c2", false);

        // Установка данных в компоненты интерфейса
        txt.setText(s);
        if (check1) sw_1.setChecked(true);
        if (check2) sw_2.setChecked(true);
    }

    public void on_ok_click(View v){
        // Создание интента для передачи данных обратно в MainActivity
        Intent i = new Intent();

        // Получение данных из компонентов интерфейса
        String s = txt.getText().toString();
        Boolean switch1 = sw_1.isChecked();
        Boolean switch2 = sw_2.isChecked();

        // Установка данных в интент и установка результата, а затем завершение активности
        i.putExtra("act1_s", s);
        i.putExtra("act1_c1", switch1);
        i.putExtra("act1_c2", switch2);
        setResult(RESULT_OK, i);
        finish();
    }

    public void on_cancel_click(View v){
        // Установка результата "Отмена" и завершение активности
        setResult(RESULT_CANCELED);
        finish();
    }
}