package aquadminmovil.jcas.com.aquadminmovil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.Toast;

import com.jcas.utilerias.Global;

import static android.app.PendingIntent.getActivity;

public class MyActivity extends Activity {

    protected static final int CAMERA_PIC_REQUEST = 0;

    private Button bGuardar;
    private Button bSig;
    private Button bAnt;
    private Button bCamara;
    private EditText TRuta;
    private EditText TDireccion;
    private EditText TCuenta;
    private EditText TNombre;
    private EditText TMedidor;
    private EditText TLAnt;
    private EditText TPromedio;
    public EditText TLAct;
    public EditText TProb;
    public String arr[];
    private String leer;
    public int lactual=1,lant,ra,c,siguiente,cuantos=0,ICount=-1;
    public File ruta_sd = Environment.getExternalStorageDirectory();
    public File f = new File("/mnt/sdcard/Lecturas", "Lect1.txt");

    public void Guardar()
    {
        int i;
        long yourDateMillis = System.currentTimeMillis();
        Time yourDate = new Time();
        yourDate.set(yourDateMillis);
        String formattedDate = yourDate.format("%Y-%m-%d");
        String formattedHour=yourDate.format("%H:%M");
        String line;
        try
        {
            File fN = new File("/mnt/sdcard/Lecturas", "Copia2.txt");
            OutputStreamWriter txtWriter =new OutputStreamWriter(new FileOutputStream(fN));
            File ff = new File("/mnt/sdcard/Lecturas", "Lect1.txt");
            BufferedReader txtReader =new BufferedReader(new InputStreamReader(new FileInputStream(ff)));
            if(ra==0)
            {
                line=txtReader.readLine();
                arr=line.split(",");
                txtWriter.write(""+","+arr[1]+","+arr[2]+","+arr[3]+","+arr[4]+","+arr[5]+","+arr[6]+","+arr[7]+","+arr[8]+","+arr[9]+","+arr[10]+","+arr[11]+","+arr[12]+","+arr[13]+","+arr[14]+","+arr[15]+","+arr[16]+","+arr[17]+","+arr[18]+","+arr[19]+","+arr[20]+","+arr[21]+","+TProb.getText().toString()+","+TLAct.getText().toString()+","+arr[24]+","+formattedDate+","+formattedHour+","+arr[27]+","+arr[28]+","+arr[29]+","+arr[30]+","+arr[31]+"\r\n");
                for(i=1;i<=ICount;i++)
                {
                    txtWriter.write(txtReader.readLine()+"\r\n");
                }
            }
            else
            {
                for(i=0;i<=ICount;i++)
                {
                    line=txtReader.readLine();
                    arr=line.split(",");
                    String strCuenta=TCuenta.getText().toString();
                    strCuenta=strCuenta.trim();
                    String strCuentaActual=arr[10];
                    strCuentaActual=strCuentaActual.trim();

                    if(TLAct.getText().toString().trim().equals("")){
                        TLAct.setText("0");
                    }

                    if(strCuentaActual.equals(strCuenta))
                    {
                        txtWriter.write(""+","+arr[1]+","+arr[2]+","+arr[3]+","+arr[4]+","+arr[5]+","+arr[6]+","+arr[7]+","+arr[8]+","+arr[9]+","+arr[10]+","+arr[11]+","+arr[12]+","+arr[13]+","+arr[14]+","+arr[15]+","+arr[16]+","+arr[17]+","+arr[18]+","+arr[19]+","+arr[20]+","+arr[21]+","+TProb.getText().toString()+","+TLAct.getText().toString()+","+arr[24]+","+formattedDate+","+formattedHour+","+arr[27]+","+arr[28]+","+arr[29]+","+arr[30]+","+arr[31]+"\r\n");
                    }
                    else
                    {
                        txtWriter.write(line);
                        txtWriter.write("\r\n");
                    }
                }
            }
            txtWriter.close();
            txtReader.close();
            ff.delete();
            fN.renameTo(ff);
            TLAct.setSelection(0);
            TLAct.requestFocusFromTouch();
            TLAct.setSelection(0);
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error: "+ex.getMessage());
        }
    }

    public void Cursor(int j)
    {
        int i;
        try
        {
            BufferedReader fin =new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            if(j<=lactual)
            {
                for(i=0;i<j;i++)
                {
                    leer=fin.readLine();
                }
                leer=fin.readLine();
                arr=leer.split(",");
                TCuenta.setText(arr[10]);
                TRuta.setText(arr[24]);
                TNombre.setText(arr[11]);
                TDireccion.setText(arr[12]);
                TMedidor.setText(arr[20]);
                TLAnt.setText(arr[21]);
                if(arr[28].equals("S")) {
                    TPromedio.setText(arr[27]);
                }else{
                    TPromedio.setText("0");
                }

                if(arr[23].trim().equals("0")){
                    TLAct.setText("");
                }else{
                    TLAct.setText(arr[23]);
                }
                TProb.setText("");
                TProb.setText(arr[22]);
                fin.close();
                TLAct.setSelection(0);
                TLAct.requestFocusFromTouch();
                TLAct.setSelection(0);
            }
            else
            {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                alertbox.setMessage("Ultimo Contrato");
                alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                alertbox.show();
            }
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error: "+ex.getMessage());
            siguiente=100000;
            i=0;
            cuantos=0;
        }
    }

    public void Siguiente (int actual,String nombre)
    {
        int i=0;
        int siguiente=0;
        do
        {
            try
            {
                BufferedReader fin =new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                String[] arr=fin.readLine().split(",");
                if(arr[2].startsWith("\""+(nombre.toUpperCase())))
                {
                    siguiente++;
                }
                fin.close();
            }
            catch (Exception ex)
            {
                Log.e("Ficheros", "Error: "+ex.getMessage());
                siguiente=100000;
                i=0;
                cuantos=0;
            }
            i++;
        }while(actual>=siguiente);
        cuantos++;
        Cursor(i-1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        bAnt=(Button) findViewById(R.id.Ant);
        TRuta = (EditText) findViewById(R.id.TRuta);
        TDireccion = (EditText) findViewById(R.id.TDireccion);
        bGuardar = (Button) findViewById(R.id.Guardar);
        bSig=(Button) findViewById(R.id.Sig);
        TCuenta = (EditText) findViewById(R.id.TCuenta);
        TNombre = (EditText) findViewById(R.id.TNombre);
        TMedidor= (EditText) findViewById(R.id.TMedidor);
        TLAnt= (EditText) findViewById(R.id.TLectant);
        TPromedio= (EditText) findViewById(R.id.TPromedio);
        TLAct= (EditText) findViewById(R.id.TLectact);
        TProb= (EditText) findViewById(R.id.TProblema);

        TLAct.setSelection(0);
        TLAct.requestFocusFromTouch();
        TLAct.setSelection(0);

        final Global global=(Global) getApplicationContext();

        if(global.getBuscar()==1){
            global.setBuscar(0);
            String estado = Environment.getExternalStorageState();
            if (estado.equals(Environment.MEDIA_MOUNTED)) {
            } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            } else {
            }
            try {
                BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                try {
                    while ((leer = fin.readLine()) != null) {
                        ICount++;
                    }
                    fin.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }


            lactual = ICount;
            lant = 0;
            ra = global.getCursor();
            Cursor(ra);
            global.setCursor(-1);

        }else {

            String estado = Environment.getExternalStorageState();
            if (estado.equals(Environment.MEDIA_MOUNTED)) {
            } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            } else {
            }
            try {
                BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                try {
                    while ((leer = fin.readLine()) != null) {
                        ICount++;
                    }
                    fin.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }


            lactual = ICount;
            lant = 0;
            ra = 0;
            try {
                BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                String[] arr = fin.readLine().split(",");

                TCuenta.setText(arr[10]);
                TRuta.setText(arr[24]);
                TNombre.setText(arr[11]);
                TDireccion.setText(arr[12]);
                TMedidor.setText(arr[20]);
                TLAnt.setText(arr[21]);
                if(arr[28].equals("S")) {
                    TPromedio.setText(arr[27]);
                }else{
                    TPromedio.setText("0");
                }
                if(arr[23].trim().equals("0")){
                    TLAct.setText("");
                }else{
                    TLAct.setText(arr[23]);
                }
                TProb.setText(arr[22]);
                fin.close();
            } catch (Exception ex) {
                Log.e("Ficheros", "Error: " + ex.getMessage());
            }

        }

        bSig.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ra++;
                Cursor(ra);
            }
        });
        bAnt.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ra > 0)
                {
                    ra--;
                    Cursor(ra);
                }
            }
        });

        bGuardar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(TLAct.getText().toString().trim().equals("")){
                    TLAct.setText("0");
                }


                if (TProb.getText().toString().matches("") && TLAct.getText().toString().matches("0") )
                {
                    AlertDialog.Builder alertbox = new AlertDialog.Builder(MyActivity.this);
                    alertbox.setMessage("La Lectura Es Cero. ¿Desea Continuar?");
                    alertbox.setPositiveButton("Si", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface arg0, int arg1)
                        {
                            Guardar();
                            lant++;
                            ra++;
                            Cursor(ra);
                        }
                    });
                    alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            ///////////////////////////////////
                        }
                    });
                    alertbox.show();
                }else if(!TPromedio.getText().toString().trim().equals("0") || !TLAct.getText().toString().matches("0") || !TProb.getText().toString().matches("")){
                    if(TLAct.getText().toString().trim().equals("")){
                        TLAct.setText("0");
                    }

                    int lectPromedio=Integer.valueOf(TPromedio.getText().toString());
                    int lectAnterior=Integer.valueOf(TLAnt.getText().toString());
                    int lectActual=Integer.valueOf(TLAct.getText().toString());/////////////

                    if((lectActual-lectAnterior)>((lectPromedio*1.5))){
                        AlertDialog.Builder alertbox = new AlertDialog.Builder(MyActivity.this);
                        alertbox.setMessage("Lectura Alta. ¿Desea Continuar?");
                        alertbox.setPositiveButton("Si", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface arg0, int arg1)
                            {
                                Guardar();
                                lant++;
                                ra++;
                                Cursor(ra);
                            }
                        });
                        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                ///////////////////////////////////
                            }
                        });
                        alertbox.show();
                    }

                    else if(lectActual<lectAnterior){
                        if(!TProb.getText().toString().trim().equals("")){
                            TLAct.setText(TLAnt.getText().toString());
                            Guardar();
                            lant++;
                            ra++;
                            Cursor(ra);
                        }else {
                            AlertDialog.Builder alertbox = new AlertDialog.Builder(MyActivity.this);
                            alertbox.setMessage("Lectura Negativa");
                            alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    //////
                                }
                            });
                            alertbox.show();
                        }
                    }
                    else{
                        Guardar();
                        lant++;
                        ra++;
                        Cursor(ra);
                    }
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
        if (id == R.id.action_settings) {
            Intent i = new Intent(MyActivity.this, Configuraciones.class);
            i.putExtras(sendBundle);
            startActivity(i);

            finish();
        }
        if (id == R.id.action_buscar) {
            Intent i = new Intent(MyActivity.this, Buscar.class);
            i.putExtras(sendBundle);
            startActivity(i);

            finish();
        }
        if(id==R.id.action_sync){
            Intent i =new Intent(MyActivity.this,Sincronizar.class);
            i.putExtras(sendBundle);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void salirAplicacion (View v){
        finish();
        System.exit(0);
    }
}
