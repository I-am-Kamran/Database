package com.example.marvin.database;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    EditText productId,productName,productPrice;
    Button add,retreive,single,update,delete;
    MyDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**EDITTEXT**/
        productId=findViewById(R.id.ProductId);
        productName=findViewById(R.id.productName);
        productPrice=findViewById(R.id.ProductPrice);

        /*BUTTONS*/
        add=findViewById(R.id.Insert);
        retreive=findViewById(R.id.Retreive);
        single=findViewById(R.id.single);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);

        myDB=new MyDatabase(this);

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
              int id=Integer.parseInt(productId.getText().toString());
              String name=productName.getText().toString();
              String price=productPrice.getText().toString();

              Product product=new Product();
              product.setId(id);
              product.setName(name);
              product.setPrice(price);
              myDB.addProduct(product);
                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });

        retreive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               List<Product> data = myDB.getAllProduct();
                for (Product ref:data)
                {
                    Toast.makeText(MainActivity.this,
                            "Id:-"+ref.getId()+"\nName:-"+ref.getName()+"\nPrice:-"+ref.getPrice(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(productId.getText().toString());

             Product product= myDB.getsingleProduct(id);

             int i=product.getId();
             String n=product.getName();
             String p=product.getPrice();

                Toast.makeText(MainActivity.this,"Id:-"+i+"\nName"+n+"\nPrice"+p, Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(productId.getText().toString());
                String name=productName.getText().toString();
                String price=productPrice.getText().toString();

                Product product=new Product();
                product.setId(id);
                product.setName(name);
                product.setPrice(price);

                int res=myDB.updateRecord(product);

                if(res>0)
                {
                    Toast.makeText(MainActivity.this, "Good", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Bad", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(productId.getText().toString());

                myDB.deleteProduct(id);
            }
        });

    }


}
