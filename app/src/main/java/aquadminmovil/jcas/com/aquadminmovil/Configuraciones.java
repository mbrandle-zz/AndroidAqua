package aquadminmovil.jcas.com.aquadminmovil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;


public class Configuraciones extends Activity {

    private EditText txtWsdl;
    private CheckBox cbLinea;
    private Button btnGuardar;
    public File ruta_sd = Environment.getExternalStorageDirectory();
    public File f = new File("/mnt/sdcard/Lecturas", "LectEnviar.txt");

    @Override
    public void onBackPressed() {
        Bundle sendBundle = new Bundle();
        sendBundle.putLong("value", 10);
        Intent in = new Intent(Configuraciones.this, MyActivity.class);
        in.putExtras(sendBundle);
        startActivity(in);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);
        txtWsdl = (EditText) findViewById(R.id.txtWsdl);
        cbLinea=(CheckBox) findViewById(R.id.cbLinea);
        btnGuardar=(Button) findViewById(R.id.btnGuardar);

        try {
            File ff = new File("/mnt/sdcard/Lecturas", "BD.txt");
            BufferedReader txtReader = new BufferedReader(new InputStreamReader(new FileInputStream(ff)));

            txtReader.readLine();
            String line=txtReader.readLine();
            String[] arr=line.split(",");
            txtWsdl.setText(arr[1]);

            if(arr[0].equals("0")){
                cbLinea.setChecked(false);
            }else{
                cbLinea.setChecked(true);
            }

            txtReader.close();

        }catch(Exception ex){
            Log.e("Ficheros", "Error: " + ex.getMessage());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.configuraciones, menu);
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
            Intent i = new Intent(Configuraciones.this, MyActivity.class);
            i.putExtras(sendBundle);
            startActivity(i);
        }
        if (id == R.id.action_buscar) {
            Intent i = new Intent(Configuraciones.this, Buscar.class);
            i.putExtras(sendBundle);
            startActivity(i);
        }
        if(id==R.id.action_sync){
            Intent i =new Intent(Configuraciones.this,Sincronizar.class);
            i.putExtras(sendBundle);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void Gruardar (View v){
        try {
            String strLinea="";
            String strWsdl="";

            if(cbLinea.isChecked()){
                strLinea="1";
            }else{
                strLinea="0";
            }
            strWsdl=txtWsdl.getText().toString();

            File fN = new File("/mnt/sdcard/Lecturas", "BD2.txt");
            OutputStreamWriter txtWriter = new OutputStreamWriter(new FileOutputStream(fN));
            File ff = new File("/mnt/sdcard/Lecturas", "BD.txt");
            BufferedReader txtReader = new BufferedReader(new InputStreamReader(new FileInputStream(ff)));

            txtWriter.write(txtReader.readLine()+"\r\n");
            txtWriter.write(strLinea+","+strWsdl+"\r\n");

            txtReader.close();
            txtWriter.close();

            ff.delete();
            fN.renameTo(ff);

            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, "Datos Guardados Correctamente", duration);
            toast.show();
        }catch(Exception ex){
            Log.e("Ficheros", "Error: " + ex.getMessage());

            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, "Error: " + ex.getMessage(), duration);
            toast.show();
        }
    }
}
