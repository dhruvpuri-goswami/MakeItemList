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
import android.widget.TextView;

import com.example.insertdb.data.MyDbHandler;
import com.example.insertdb.model.Items;

import java.util.ArrayList;
import java.util.List;

public class Delete extends AppCompatActivity {

    ArrayList<String> arrayList=new ArrayList<String>();

    MyDbHandler db =new MyDbHandler(Delete.this);
    EditText item,quan;
    Button update,procupdate,home;
    TextView t3;
    LinearLayout l5,l3,l4;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        item = (EditText)findViewById(R.id.txtitem);
        quan = (EditText)findViewById(R.id.txtquantity);
        update=(Button)findViewById(R.id.update);
        procupdate=(Button) findViewById(R.id.procupdate);
        l5=(LinearLayout)findViewById(R.id.l5);
        home = (Button)findViewById(R.id.gohome);
        l3=(LinearLayout)findViewById(R.id.l3);
        l4=(LinearLayout)findViewById(R.id.l4);
        t3=(TextView)findViewById(R.id.t3);
        lv=(ListView)findViewById(R.id.lv);
    }

    public void deleteItem(View view) {
        String itemname=item.getText().toString();
        db.deleteitem(itemname);
        t3.setText("Item Deleted Successfully");
        l4.setVisibility(View.VISIBLE);
    }

    public void showItem(View view) {
        List<Items> allUsers = null;
        allUsers = db.getAllUsers();
        for(Items items :allUsers) {
            String name = items.getItem();
            String quan = items.getQuan();
            arrayList.add("Item : " + name + "\nQuantity : " +quan);
        }
        ArrayAdapter<String> adt = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(adt);
        l5.setVisibility(View.VISIBLE);
    }

    public void gotoHome(View view) {
        Intent intent = new Intent(Delete.this,Home.class);
        startActivity(intent);
    }
}
