package com.example.pet_book;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class BookActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone, editTextDate, editTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);

        Button buttonSubmit = findViewById(R.id.buttonsubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
                Intent intent = new Intent(BookActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    public void showDatePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                editTextDate.setText(selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    public void showTimePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                editTextTime.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);

        timePickerDialog.show();
    }

    public void submitForm() {
        // Retrieve data from EditText fields
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        String date = editTextDate.getText().toString();
        String time = editTextTime.getText().toString();

        // Perform further actions with the entered data, or display it as needed
        String message = "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\nDate: " + date + "\nTime: " + time;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
