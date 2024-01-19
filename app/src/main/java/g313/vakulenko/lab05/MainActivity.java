package g313.vakulenko.lab05;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt;
    CheckBox cb_1, cb_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt = findViewById(R.id.et_data);
        cb_1 = findViewById(R.id.cb_one);
        cb_2 = findViewById(R.id.cb_two);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        // Обработка результата из другой активности
        if (requestCode == 555)
        {
            if (data != null)
            {
                // Получение данных из результата
                String s = data.getStringExtra("act1_s");
                boolean switch1 = data.getBooleanExtra("act1_c1", false);
                boolean switch2 = data.getBooleanExtra("act1_c2", false);

                // Отображение результата в Toast и установка данных в компоненты интерфейса
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                txt.setText(s);
                cb_1.setChecked(switch1);
                cb_2.setChecked(switch2);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void on_open_click(View v){
        // Получение данных из компонентов интерфейса
        String s = txt.getText().toString();
        Boolean check1 = cb_1.isChecked();
        Boolean check2 = cb_2.isChecked();

        // Создание интента для запуска другой активности с передачей данных
        Intent i = new Intent(this, activity_1.class);
        i.putExtra("main_s", s);
        i.putExtra("main_c1", check1);
        i.putExtra("main_c2", check2);

        // Запуск активности с ожиданием результата
        startActivityForResult(i, 555);
    }

    public void on_exit_click(View v){
        // Создание диалогового окна для подтверждения выхода
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        AlertDialog dlg = bld.create();
        dlg.setTitle("Выход");
        dlg.setMessage("Вы уверены, что хотите выйти?");
        dlg.setIcon(R.drawable.exit);

        // Установка кнопок "Да" и "Нет" в диалоговом окне
        dlg.setButton(Dialog.BUTTON_POSITIVE,"Да", (dialog, id) -> finish());
        dlg.setButton(Dialog.BUTTON_NEGATIVE,"Нет", (dialog, id) -> dialog.cancel());
        dlg.show();
    }
}
