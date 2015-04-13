package aquadminmovil.jcas.com.aquadminmovil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.jcas.utilerias.Global;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class Buscar extends Activity {

    public File ruta_sd = Environment.getExternalStorageDirectory();
    public File f = new File("/mnt/sdcard/Lecturas", "Lect1.txt");

    private EditText txtBuscar;
    private RadioButton rbCuenta;
    private RadioButton rbMedidor;
    private RadioButton rbNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        txtBuscar = (EditText) findViewById(R.id.txtBuscar);
        rbCuenta=(RadioButton) findViewById(R.id.rbCuenta);
        rbNombre=(RadioButton) findViewById(R.id.rbNombre);
        rbMedidor=(RadioButton) findViewById((R.id.rbMedidor));
    }


    @Override
    public void onBackPressed() {
        Bundle sendBundle = new Bundle();
        sendBundle.putLong("value", 10);
        Intent in = new Intent(Buscar.this, MyActivity.class);
        in.putExtras(sendBundle);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.buscar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Bundle sendBundle = new Bundle();
        sendBundle.putLong("value", 10);
        if (id == R.id.action_lecturas) {
            Intent i = new Intent(Buscar.this, MyActivity.class);
            i.putExtras(sendBundle);
            startActivity(i);
        }
        if (id == R.id.action_settings) {
            Intent i = new Intent(Buscar.this, Configuraciones.class);
            i.putExtras(sendBundle);
            startActivity(i);
        }
        if(id==R.id.action_sync){
            Intent i =new Intent(Buscar.this,Sincronizar.class);
            i.putExtras(sendBundle);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void Buscar (View v){
        final Global global=(Global) getApplicationContext();
        String[] encontrado=null;
        String[] arr=null;
        String line=null;
        int i=0;
        int local=-1;

        try{
            if(rbCuenta.isChecked()){
                BufferedReader fin =new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                while((line = fin.readLine()) != null){
                    arr=line.split(",");
                    String cuenta=arr[10];
                    if(cuenta.trim().equals(txtBuscar.getText().toString().trim())){
                        global.setBuscar(1);
                        global.setCursor(i);
                    }
                    i++;
                }
                fin.close();
            }
            if(rbMedidor.isChecked()){
                BufferedReader fin =new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                while((line = fin.readLine()) != null){
                    arr=line.split(",");
                    String medidor=arr[20];
                    if(medidor.trim().equals(txtBuscar.getText().toString().trim())){
                        global.setBuscar(1);
                        global.setCursor(i);
                    }
                    i++;
                }
                fin.close();
            }
            if(rbNombre.isChecked()){
                BufferedReader fin =new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                while((line = fin.readLine()) != null){
                    arr=line.split(",");
                    String nombre=arr[11];
                    if(nombre.trim().substring(1).startsWith(txtBuscar.getText().toString().trim())){
                        global.setBuscar(1);
                        global.setCursor(i);
                    }
                    i++;
                }
                fin.close();
            }


            if(global.getBuscar()==1) {
                Bundle sendBundle = new Bundle();
                sendBundle.putLong("value", 10);
                Intent in = new Intent(Buscar.this, MyActivity.class);
                in.putExtras(sendBundle);
                startActivity(in);
            }

        }catch(Exception ex){
            Log.e("Ficheros", "Error: " + ex.getMessage());
        }
    }
}
