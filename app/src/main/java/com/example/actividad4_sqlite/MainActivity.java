package com.example.actividad4_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.actividad4_sqlite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding enlace;
    EditText dni, name, apellidos, edad, direccion;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enlace = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(enlace.getRoot());

        dni = enlace.dni;
        name = enlace.nombre;
        apellidos = enlace.apellidos;
        edad = enlace.edad;
        direccion = enlace.direccion;

        insert = enlace.btnInsert;
        update = enlace.btnUpdate;
        delete = enlace.btnDelete;
        view = enlace.btnView;

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dniTXT = dni.getText().toString();
                String nameTXT = name.getText().toString();
                String apellidosTXT = apellidos.getText().toString();
                String edadTXT = edad.getText().toString();
                String direccionTXT = direccion.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(dniTXT, nameTXT, apellidosTXT, edadTXT, direccionTXT);
                if(checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "Nueva entrada insertada", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "No se pudo insertar nada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dniTXT = dni.getText().toString();
                String nameTXT = name.getText().toString();
                String apellidosTXT = apellidos.getText().toString();
                String edadTXT = edad.getText().toString();
                String direccionTXT = direccion.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(dniTXT, nameTXT, apellidosTXT, edadTXT, direccionTXT);
                if(checkupdatedata==true){
                    Toast.makeText(MainActivity.this, "Entrada actualizada", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "No se pudo actualizar nada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dniTXT = dni.getText().toString();

                Boolean checkdeletedata = DB.deletedata(dniTXT);
                if(checkdeletedata==true){
                    Toast.makeText(MainActivity.this, "Entrada eliminada", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "No se pudo eliminar nada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No existen entradas", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("DNI :"+res.getString(0)+"\n");
                    buffer.append("Nombre :"+res.getString(1)+"\n");
                    buffer.append("Apellido :"+res.getString(2)+"\n");
                    buffer.append("Edad :"+res.getString(3)+"\n");
                    buffer.append("Dirección :"+res.getString(4)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Entrada de usuarios");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


    }
}