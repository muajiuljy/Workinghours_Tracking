package com.example.working_hours2;

        import androidx.appcompat.app.AppCompatActivity;
        import android.app.Fragment;


        import android.app.DatePickerDialog;
        import android.app.FragmentManager;
        import android.app.FragmentTransaction;
        import android.app.TimePickerDialog;
        import android.content.Intent;
        import android.icu.text.SimpleDateFormat;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.SystemClock;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CalendarView;
        import android.widget.Chronometer;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.Spinner;
        import android.widget.TimePicker;

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    EditText etDate, etStart_time, etStop_time;
    DatePickerDialog.OnDateSetListener setListener;
    Button btedit;
    ArrayList<String> categories = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //chronometer = findViewById(R.id.chronometer);
        etDate = findViewById(R.id.editTextDate);
        btedit = findViewById(R.id.buttonEdit);
        spinner = findViewById(R.id.spinner_wt1);




        if(btedit.performClick()){
            //Bundle bundle = getIntent().getExtras();
            //ArrayList<String>categories=bundle.getStringArrayList("categorylist");
            //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, categories);
            //spinner.setAdapter(adapter);
            ArrayList<String>categories = getIntent().getStringArrayListExtra("categorylist");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, categories);
            spinner.setAdapter(adapter);
        }
        else{

            //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, categories);
           // spinner.setAdapter(adapter);
            categories.add("Teaching");
            categories.add("Preparation for teaching");
            categories.add("Checking assignments and assessments");
            categories.add("Project work");
            categories.add("Administrative work");
            categories.add("Meeting");
            categories.add("Work-related trip");
            categories.add("Development own skills");
            categories.add("Thesis-related work");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, categories);
            spinner.setAdapter(adapter);
        }

      //  adapter.addAll("Checking assignments and assessment","Project work",
        //        "Administrative work","Meeting","Work-related trip","Development own skills","Thesis-related work");
       // adapter.notifyDataSetChanged();






        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        btedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
               // intent.putExtra("categorylist", categories);
                startActivity(intent);
            }
        });









    }



    public void ChangeFragment(View view){
        Fragment fragment;

        if(view == findViewById(R.id.button_fragment1)){
            fragment =  new FragmentThree();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place, fragment);
            ft.commit();
        }

        if(view == findViewById(R.id.button_fragment2)){
            fragment =  new FragmentTwo();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place, fragment);
            ft.commit();
        }
    }



   /* public String getFormatHMS(long time){
        time = time/1000;
        int s = (int)(time%60);
        int m = (int)(time/60);
        int h = (int)(time/3600);
        return String.format("%02d:%02d%02d",h,m,s);
    }

    public void onstart(View view){
        running = true;
    }
    public void onstop(View view){
        running = false;
    }

    private Runnable timeRunable = new Runnable() {
        @Override
        public void run() {
            seconds = seconds/1000;
            sec = (int)(seconds%60);
            min = (int)(seconds/60);
            hour = (int)(seconds/3600);
            chronometer.setText(String.format("%02d",hour) + ":" +String.format("%02d",min) + ":" + String.format("%02d",sec));
            handler.postDelayed(this, 1000);
            seconds = seconds + 1000;



        }
    };

    private void startTimer(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                hour = (int)(seconds/3600);
                min = (int)(seconds%3600)/60;
                sec = (int)(seconds%60);
                chronometer.setText(String.format("%02d",hour) + ":" +String.format("%02d",min) + ":" + String.format("%02d",sec));
                if(running){
                    seconds ++;
                }
                handler.postDelayed(this, 1000);

            }
        });
    }*/

}
