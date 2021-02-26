package com.example.pruebaonoff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pruebaonoff.interfaces.API_Rest_Post;
import com.example.pruebaonoff.adaptadores.AdapterPost;
import com.example.pruebaonoff.elementos.Post;
import com.example.pruebaonoff.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPrincipal extends AppCompatActivity {

    ArrayList<Post> listPost;
    RecyclerView recyclerPost;
    Spinner spinner;
    ConexioSQLiteHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        recyclerPost = (RecyclerView) findViewById(R.id.idRecyclerView);
        recyclerPost.setLayoutManager(new LinearLayoutManager(this));

        spinner = (Spinner) findViewById(R.id.idSpinner);

        llenarPost();
    }

    private void llenarPost() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create()).build();

        API_Rest_Post productoapi = retrofit.create(API_Rest_Post.class);

        Call<List<Post>> call = productoapi.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                try {
                    listPost = new ArrayList<>();
                    ArrayList<String> spinnerList = new ArrayList<String>();

                    ConexioSQLiteHelper conexion = new ConexioSQLiteHelper(MainPrincipal.this,
                            "Blog", null, 1);

                    SQLiteDatabase BaseDeDatos = conexion.getWritableDatabase();
                    ContentValues registro_bd = new ContentValues();

                    for (Post post : response.body()) {
                        listPost.add(post);
                        spinnerList.add(post.getTitle());

                        registro_bd.put(Utilidades.ID_USUARIO, String.valueOf(post.getUserId()));
                        registro_bd.put(Utilidades.ID_POST, String.valueOf(post.getIdPost()));
                        registro_bd.put(Utilidades.TITLE, post.getTitle());
                        registro_bd.put(Utilidades.BODY, post.getBody());

                        Long ideResultante = BaseDeDatos.insert(Utilidades.TABLA_BLOG, Utilidades.ID_POST, registro_bd);
                    }
                    BaseDeDatos.close();

                    AdapterPost adapterPost = new AdapterPost(listPost);

                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                            MainPrincipal.this, android.R.layout.simple_spinner_item, spinnerList);


                    adapterPost.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainPrincipal.this, "Seleccion "
                                            + listPost.get(recyclerPost.getChildAdapterPosition(v)).getIdPost()
                                    , Toast.LENGTH_SHORT).show();

                            //Enviar dato a otra activity
                            Intent i = new Intent(MainPrincipal.this, MainSecundaria.class);

                            i.putExtra("IdPost", listPost.get(recyclerPost.getChildAdapterPosition(v)).getIdPost());
                            i.putExtra("IdUser", listPost.get(recyclerPost.getChildAdapterPosition(v)).getUserId());
                            i.putExtra("Title", listPost.get(recyclerPost.getChildAdapterPosition(v)).getTitle());
                            i.putExtra("Body", listPost.get(recyclerPost.getChildAdapterPosition(v)).getBody());
                            startActivity(i);
                            finish();
                        }
                    });

                    recyclerPost.setAdapter(adapterPost);
                    spinner.setAdapter(spinnerAdapter);

                } catch (Exception ex) {
                    cargar_sql();
                }


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Toast.makeText(MainPrincipal.this, "Error", Toast.LENGTH_SHORT).show();


            }
        });

    }

    public void cargar_sql() {
        listPost = new ArrayList<>();
        ConexioSQLiteHelper conexion = new ConexioSQLiteHelper(MainPrincipal.this,
                "Blog", null, 1);

        SQLiteDatabase BaseDeDatos = conexion.getWritableDatabase();
        Post post = null;
        Cursor cursor = BaseDeDatos.rawQuery("SELECT FROM" + Utilidades.TABLA_BLOG, null);

        Toast.makeText(this, "Punto A", Toast.LENGTH_SHORT).show();

        while (cursor.moveToNext()) {
            post = new Post();
            post.getIdPost();
            post.getTitle();
            post.getBody();
            post.getUserId();

            listPost.add(post);
        }
        Toast.makeText(this, "Punto B", Toast.LENGTH_SHORT).show();

        AdapterPost adapterPost = new AdapterPost(listPost);
        recyclerPost.setAdapter(adapterPost);
    }

}