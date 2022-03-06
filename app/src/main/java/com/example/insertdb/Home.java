package com.example.insertdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.insertdb.data.MyDbHandler;
import com.example.insertdb.model.Items;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    ArrayList<String> arrayList=new ArrayList<String>();

    MyDbHandler db =new MyDbHandler(Home.this);
    EditText item,quan;
    Button add,update,delete,show;
    LinearLayout l1,l2;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        item = (EditText)findViewById(R.id.txtitem);
        quan = (EditText)findViewById(R.id.txtquantity);
        add=(Button)findViewById(R.id.add);
        update=(Button)findViewById(R.id.update);
        delete=(Button)findViewById(R.id.delete);
        show=(Button)findViewById(R.id.see);
        l1=(LinearLayout)findViewById(R.id.l1);
        l2=(LinearLayout)findViewById(R.id.l2);
        lv=(ListView) findViewById(R.id.lv);
        List<Items> allUsers = db.getAllUsers();
        for(Items items :allUsers) {
            String name = items.getItem();
            String quan = items.getQuan();
            arrayList.add("Item : " + name + "\nQuantity : " +quan);
        }
        ArrayAdapter<String> adt = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(adt);

    }


    public void addItem(View view) {
        Intent intent = new Intent(Home.this,MainActivity.class);
        startActivity(intent);
    }

    public void updateItem(View view) {
        Intent intent = new Intent(Home.this,update.class);
        startActivity(intent);

    }

    public void getAllItems(View view) {
        Intent intent = new Intent(Home.this,Home.class);
        startActivity(intent);
    }

    public void deleteItem(View view) {
        Intent intent = new Intent(Home.this,Delete.class);
        startActivity(intent);
    }
}
