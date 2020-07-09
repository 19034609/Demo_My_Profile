package sg.edu.rp.c346.id19034609.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = etName.getText().toString();
                Float floatGPA = Float.parseFloat(etGPA.getText().toString());
                int intGender = rgGender.getCheckedRadioButtonId();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("username", strName);
                prefEdit.putFloat("gpa", floatGPA);
                prefEdit.putInt("gender", intGender);
                prefEdit.commit();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        String strName = etName.getText().toString();
        Float floatGPA = Float.parseFloat(etGPA.getText().toString());
        int intGender = rgGender.getCheckedRadioButtonId();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("username", strName);
        prefEdit.putFloat("gpa", floatGPA);
        prefEdit.putInt("gender", intGender);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String msgUsername = prefs.getString("username", "John");
        Float msgGPA = prefs.getFloat("gpa", 0);
        Integer msgGender = prefs.getInt("gender", R.id.radioButtonGenderMale);
        etName.setText(msgUsername);
        etGPA.setText(String.valueOf(msgGPA));
        rgGender.check(msgGender);
    }

}
