package com.example.working_hours2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

   // Spinner spinner;
    EditText etcategory;
    Button addBtn, updateBtn, removeBtn, backBtn;
    ArrayList<String> categorylist = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        listView = findViewById(R.id.spinner_worktype);
        etcategory = findViewById(R.id.editText_category);
        addBtn = findViewById(R.id.button_add);
        updateBtn = findViewById(R.id.button_update);
        removeBtn = findViewById(R.id.button_remove);
        backBtn = findViewById(R.id.buttonBack);




       // adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, categorylist);
        //spinner.setAdapter(adapter);

        //Bundle bundle = getIntent().getExtras();
        //final ArrayList<String>categorylist=bundle.getStringArrayList("categorylist");
        categorylist.add("Teaching");
        categorylist.add("Preparation for teaching");
        categorylist.add("Checking assignments and assessment");
        categorylist.add("Project work");
        categorylist.add("Administrative work");
        categorylist.add("Meeting");
        categorylist.add("Work-related trip");
        categorylist.add("Development own skills");
        categorylist.add("Thesis-related work");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,categorylist);
        listView.setAdapter(adapter);

       // adapter.addAll("Teaching", "Preparation for teaching", "Checking assignments and assessment","Project work",
       //               "Administrative work","Meeting","Work-related trip","Development own skills","Thesis-related work");

        adapter.notifyDataSetChanged();




       /* Intent intent = new Intent();
        ArrayList categorylist = getIntent().getStringArrayListExtra("categorylist");
        adapter.notifyDataSetChanged();


       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //  etcategory.setText(categorylist.get(position));
               int posss = parent.getSelectedItemPosition();
           }
       });
*/


       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               etcategory.setText(categorylist.get(position));
           }
       });




      addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                intent.putStringArrayListExtra("categorylist", categorylist);
                startActivity(intent);
            }
        });









    }


    private void add(){
        String category = etcategory.getText().toString();
        if(!category.isEmpty() && category.length()>0){
            adapter.add(category);
            adapter.notifyDataSetChanged();
            etcategory.setText("");
            Toast.makeText(getApplicationContext(), "Added " + category, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "!! Nothing to Add", Toast.LENGTH_SHORT).show();
        }

    }

    private void update(){
        String category = etcategory.getText().toString();

        int pos = listView.getCheckedItemPosition();
        if(!category.isEmpty() && category.length()>0){
            adapter.remove(categorylist.get(pos));
            adapter.insert(category,pos);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Updated " + category, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "!! Nothing to Update", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete(){
        int pos =  listView.getCheckedItemPosition();

        if(pos > -1){
            adapter.remove(categorylist.get(pos));
            adapter.notifyDataSetChanged();
            etcategory.setText("");
            Toast.makeText(getApplicationContext(), "Deleted ", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "!! Nothing to Delete", Toast.LENGTH_SHORT).show();
        }
    }

}
