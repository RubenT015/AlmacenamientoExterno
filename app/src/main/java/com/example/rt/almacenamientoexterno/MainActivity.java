package com.example.rt.almacenamientoexterno;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    final String fichero = "texto.txt";
    EditText et;
    Button btUpper,btLower,btCapital,btGuadarInterno,btGuadarTarjeta,btCargarInterno,btCargarTarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.et);
        btUpper = (Button)findViewById(R.id.btUpper);
        btLower = (Button)findViewById(R.id.btLower);
        btCapital = (Button)findViewById(R.id.btCapital);
        btGuadarInterno = (Button)findViewById(R.id.btGuadarInterno);
        btGuadarTarjeta = (Button)findViewById(R.id.btGuadarTarjeta);
        btCargarInterno = (Button)findViewById(R.id.btCargarInterno);
        btCargarTarjeta = (Button)findViewById(R.id.btCargarTarjeta);
    }

    private boolean existe(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }

    public void guardarInterno(View v) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(fichero, Activity.MODE_PRIVATE));
            osw.write(et.getText().toString());
            osw.flush();
            osw.close();
            Toast.makeText(this, "Los datos fueron grabados correctamente",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo grabar",Toast.LENGTH_SHORT).show();
        }
    }

    public void guardarTarjeta(View v) {
        try {
            File tarjeta = Environment.getExternalStorageDirectory();
            Toast.makeText(this,tarjeta.getAbsolutePath(),Toast.LENGTH_LONG).show();
            File file = new File(tarjeta.getAbsolutePath(), fichero);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
            osw.write(et.getText().toString());
            osw.flush();
            osw.close();
            Toast.makeText(this, "Los datos fueron grabados correctamente",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo grabar",Toast.LENGTH_SHORT).show();
        }
    }

    public void cargarInterno(View v) {
        try {
            InputStreamReader archivo = new InputStreamReader(openFileInput(fichero));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String texto = "";
            while (linea != null) {
                texto += linea + "\n";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            et.setText(texto);
            Toast.makeText(this, "Los datos fueron cargados correctamente",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo cargar",Toast.LENGTH_SHORT).show();
        }
    }

    public void cargarTarjeta(View v) {
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), fichero);
        try {
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader archivo = new InputStreamReader(fIn);
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String texto = "";
            while (linea != null) {
                texto += linea + " ";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            et.setText(texto);
            Toast.makeText(this, "Los datos fueron cargados correctamente",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo cargar",Toast.LENGTH_SHORT).show();
        }
    }

    public void textoUpper(View v) {
        String textoModificado="";
        Character caracter;
        for(int i = 0;i<et.getText().length();i++){
            caracter = et.getText().charAt(i);
            textoModificado+=caracter.toString().toUpperCase();
        }
        et.setText(textoModificado);
    }
    public void textoLower(View v) {
        String textoModificado="";
        Character caracter;
        for(int i = 0;i<et.getText().length();i++){
            caracter = et.getText().charAt(i);
            textoModificado+=caracter.toString().toLowerCase();
        }
        et.setText(textoModificado);
    }
    public void textoCapital(View v) {
        String textoModificado="";
        Character caracter;
        boolean mayuscula = true;
        for(int i = 0;i<et.getText().length();i++){
            caracter = et.getText().charAt(i);
            if(caracter.equals(' ')){
                mayuscula = true;
            }
            else if(mayuscula){
                caracter = caracter.toString().toUpperCase().charAt(0);
                mayuscula = false;
            }
            else
            {
                caracter = caracter.toString().toLowerCase().charAt(0);
            }
            textoModificado+=caracter;
        }
        et.setText(textoModificado);
    }

}
