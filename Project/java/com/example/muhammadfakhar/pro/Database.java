package com.example.muhammadfakhar.pro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private final static String DATABASE_NAME = "theDB.db";
    private final static int DB_VER = 1;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteQueryBuilder sqLiteQueryBuilder;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DB_VER);
        sqLiteDatabase = getReadableDatabase();
        sqLiteQueryBuilder = new SQLiteQueryBuilder();
    }

    public List<OrderDetail> getOrderedItems()
    {
        String[] selectStmt = {"ProductId", "ProductName", "Quantity", "Price", "Discount"};
        String table = "OrderDetails";

        sqLiteQueryBuilder.setTables(table);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, selectStmt, null,
                null, null, null, null);
        final List<OrderDetail> results = new ArrayList<>();
        while (cursor.moveToNext())
        {
            OrderDetail nOrder = new OrderDetail(cursor.getString(cursor.getColumnIndex("ProductId")),
                    cursor.getString(cursor.getColumnIndex("ProductName")),
                    cursor.getString(cursor.getColumnIndex("Quantity")),
                    cursor.getString(cursor.getColumnIndex("Price")),
                    cursor.getString(cursor.getColumnIndex("Discount")));
            results.add(nOrder);
        }
        return results;
    }

    public void addToCart(OrderDetail anOrder)
    {
        String query = String.format("INSERT INTO OrderDetails(ProductId, ProductName, " +
                "Quantity, Price, Discount) VALUES('%s', '%s', '%s', '%s', '%s')",
                anOrder.getProductId(), anOrder.getProductName(), anOrder.getQuantity(),
                anOrder.getPrice(), anOrder.getDiscount());
        sqLiteDatabase.execSQL(query);
    }

    public void emptyCart()
    {
        String query = String.format("DELETE FROM OrderDetails");
        sqLiteDatabase.execSQL(query);
    }
}
