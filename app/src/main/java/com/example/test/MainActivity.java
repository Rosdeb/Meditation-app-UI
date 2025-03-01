package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.room.Appdatabase;
import com.example.test.room.User;
import com.example.test.room.UserDao;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText ed1,ed2,ed3;
    int requestcode=120;

    TextView toast,show;

    MaterialButton imports,fatch,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1=findViewById(R.id.ed1);
        toast=findViewById(R.id.toast);
        ed2=findViewById(R.id.ed2);
        imports=findViewById(R.id.impost);
        imports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              new Thread(() ->{
                  Appdatabase db = Appdatabase.getInstance(getApplicationContext());
                  UserDao userDao = db.dao();
                  userDao.inSertAll(new User(ed1.getText().toString(),ed2.getText().toString()));
                  Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
              });

            }
        });
        fatch=findViewById(R.id.fatch);
        fatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(() -> {
                    Appdatabase db = Appdatabase.getInstance(getApplicationContext());
                    List<User> users = db.dao().getAlluser();
                    System.out.println("Fetched users: " + users.size());
                }).start();
            }
        });
        image=findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,requestcode);
            }
        });
        show=findViewById(R.id.show);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==requestcode && resultCode==RESULT_OK && data!=null){
            Uri uri= data.getData();
            String imageuri=getRealPathFromUri(uri);
            ed3.setText(imageuri);

        }
    }
    private String getRealPathFromUri(Uri uri){

        String result= null;
        Cursor cursor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            cursor = getContentResolver().query(uri,null,null,null);
        }
        if (cursor!=null){
            cursor.moveToNext();
            int index=cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            if (index!= -1){
                result=cursor.getString(index);

            }
            cursor.close();
        }
        return result;
    }
}