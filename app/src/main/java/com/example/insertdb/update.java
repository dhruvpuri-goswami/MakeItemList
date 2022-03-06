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

public class update extends AppCompatActivity {

    ArrayList<String> arrayList=new ArrayList<String>();

    MyDbHandler db =new MyDbHandler(update.this);
    EditText item,quan;
    Button update,procupdate,home;
    TextView t3;
    LinearLayout l1,l3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        item = (EditText)findViewById(R.id.txtitem);
        quan = (EditText)findViewById(R.id.txtquantity);
        update=(Button)findViewById(R.id.update);
        procupdate=(Button) findViewById(R.id.procupdate);
        l1=(LinearLayout)findViewById(R.id.l1);
        home = (Button)findViewById(R.id.gohome);
        l3=(LinearLayout)findViewById(R.id.l3);
        t3=(TextView)findViewById(R.id.t3);

    }


    public void updateItem(View view) {
        String itemname=item.getText().toString();
        String quantity = quan.getText().toString();
        Items items=new Items();
        items.setItem(itemname);
        items.setQuan(quantity);
        int ar = db.updateitem(items);
        if(ar==1){
            t3.setText("Item Quantity Updated Successfully");
            home.setVisibility(View.VISIBLE);
        }

    }


    public void OnProcessUpdate(View view) {
        l3.setVisibility(View.VISIBLE);
    }

    public void gotoHome(View view) {
        Intent intent = new Intent(update.this,Home.class);
        startActivity(intent);
    }
}
