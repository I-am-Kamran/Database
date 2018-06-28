package com.example.marvin.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARVIN on 19-02-2018.
 */

public class MyDatabase extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="myDatabase";

    //DECLARE THE TABLE NAME
    private static final String TABLE_NAME="patanjali_store";

    //DECLARE THE COLOUMNS FOR TABLE
    private static final String PRODUCT_ID="id";

    private static final String PRODUCT_NAME="name";
    private static final  String PRODUCT_PRICE="price";
      /** IT IS USED TO CREATE THE DATABASE**/
   /* public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }*/

    public MyDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String query="CREATE TABLE " + TABLE_NAME + "(" + PRODUCT_ID + " INTEGER PRIMARY KEY," + PRODUCT_NAME + " TEXT," + PRODUCT_PRICE + " TEXT" + ");";
        sqLiteDatabase.execSQL(query);
    }

    /*THIS METHOD IS USED TO CHANGE THE QUERIES OF TABLE*/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        /*AGAIN THE TABLE RESTART AND CHANGES TAKES PLACE i.e TABLE UPGRADE*/
        onCreate(sqLiteDatabase);
    }

    public void addProduct(Product product)
    {
       SQLiteDatabase database =getWritableDatabase();

       //IT IS USED TO STORE OUR VALUES
        ContentValues  values=new  ContentValues();
        values.put(PRODUCT_ID,product.getId());
        values.put(PRODUCT_NAME,product.getName());
        values.put(PRODUCT_PRICE,product.getPrice());

        database.insert(TABLE_NAME,null,values);
        database.close();
    }


    public List<Product> getAllProduct()
    {
        ArrayList<Product> listOfData=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();

        String query="SELECT * FROM "+TABLE_NAME;
      Cursor cursor = db.rawQuery(query,null);

      if (cursor.moveToFirst())
      {
          do
          {
              Product product=new Product();

             /*IT IS SET THE CURSOR */
              product.setId(Integer.parseInt(cursor.getString(0)));
              product.setName(cursor.getString(1));
              product.setPrice(cursor.getString(2));
              listOfData.add(product);
          }

          while (cursor.moveToNext());
      }
        return listOfData;
    }

    /**TO GET THE SINGLE PRODUCT**/
    public Product getsingleProduct(int id)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new String[]{PRODUCT_ID,PRODUCT_NAME,PRODUCT_PRICE},PRODUCT_ID +"=?",new String[]{String.valueOf(id)},null,null,null);

        if (cursor!=null)
            cursor.moveToFirst();
        Product product=new Product(Integer.parseInt(cursor.getString(0)),
        cursor.getString(1),cursor.getString(2));

        return product;
    }

    /**TO UPDATE THE RECORD**/
    public int updateRecord(Product product)
    {
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(PRODUCT_NAME,product.getName());
        values.put(PRODUCT_PRICE,product.getPrice());

        return database.update(TABLE_NAME,values,PRODUCT_ID+"=?",new String[]{String.valueOf(product.getId())});
    }

    /*TO DELETE THE RECORD*/
    public  void deleteProduct(int id)
    {
        SQLiteDatabase database=getWritableDatabase();
        database.delete(TABLE_NAME,PRODUCT_ID+"=?",new String[]{String.valueOf(id)});

        database.close();
    }
}
