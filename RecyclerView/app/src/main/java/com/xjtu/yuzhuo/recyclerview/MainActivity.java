package com.xjtu.yuzhuo.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PersonAdapter.OnRecyclerViewListener , View.OnClickListener{
    private RecyclerView recyclerView = null;
    private PersonAdapter adapter;
    private List<Person> personList = null;
    private Button clickBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_test_rv) ;
        clickBtn = (Button) findViewById(R.id.button_click);
        clickBtn.setOnClickListener(this);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        initData();
        adapter = new PersonAdapter(personList);
        adapter.setOnRecyclerViewListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public boolean onItemLongClick(int position) {
        adapter.notifyItemRemoved(position);
        personList.remove(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
        return true;//return true is not to transport for click event,or not;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_click:
                Person person = new Person();
                person.setName("ZhuoYe");
                person.setAge(25);
                adapter.notifyItemInserted(2);
                personList.add(2, person);
                adapter.notifyItemRangeChanged(2, adapter.getItemCount());
                break;
        }
    }

    public void initData(){
        personList = new ArrayList<>();
        for (int i = 0;i<20;i++){
            Person person = new Person();
            person.setAge(i+10);
            person.setName("demo"+i);
            personList.add(person);
        }
    }
}
