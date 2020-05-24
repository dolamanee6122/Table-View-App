package com.example.mytableview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.mytableview.TableModal.createTableModal;

public class MainActivity extends AppCompatActivity {

    private Button btnSelection;
    private List<TableModal> tableModalList;
    private Button prevBtn;
    private Button nextBtn;
    private int currpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currpage =1;
        tableModalList = getTableContent((currpage-1)*10);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);
        //recyclerView.setHasFixedSize(true);

        final TableViewAdapter adapter = new TableViewAdapter(tableModalList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
        prevBtn = findViewById(R.id.prevBtn);

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currpage>1)currpage-=1;
                toggleButton();
                tableModalList = getTableContent((currpage-1)*10);
                RecyclerView recyclerView1 = findViewById(R.id.recyclerViewDeliveryProductList);

                final TableViewAdapter adapter = new TableViewAdapter(tableModalList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
                recyclerView1.setLayoutManager(linearLayoutManager);

                recyclerView1.setAdapter(adapter);

            }
        });

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currpage<10)currpage+=1;
                toggleButton();
                tableModalList = getTableContent((currpage-1)*10);
                RecyclerView recyclerView1 = findViewById(R.id.recyclerViewDeliveryProductList);

                final TableViewAdapter adapter = new TableViewAdapter(tableModalList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
                recyclerView1.setLayoutManager(linearLayoutManager);

                recyclerView1.setAdapter(adapter);

            }
        });




    }
    private List<TableModal> getTableContent(int from)
    {
        List<TableModal> dataList = new ArrayList<>();
        int num=10;
        dataList = createTableModal(from,num);
        return dataList;
    }


    private void toggleButton(){
        if(currpage==1){
            prevBtn.setActivated(false);
            nextBtn.setActivated(true);
        }
        else if(currpage==10){
            prevBtn.setActivated(true);
            nextBtn.setActivated(false);
        }
        else{
            prevBtn.setActivated(true);
            nextBtn.setActivated(true);
        }
    }
}
