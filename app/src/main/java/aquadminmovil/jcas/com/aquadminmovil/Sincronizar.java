package aquadminmovil.jcas.com.aquadminmovil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Sincronizar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sincronizar, menu);
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
            Intent i = new Intent(Sincronizar.this, MyActivity.class);
            i.putExtras(sendBundle);
            startActivity(i);
        }
        if (id == R.id.action_buscar) {
            Intent i = new Intent(Sincronizar.this, Buscar.class);
            i.putExtras(sendBundle);
            startActivity(i);
        }
        if (id == R.id.action_settings) {
            Intent i = new Intent(Sincronizar.this, Configuraciones.class);
            i.putExtras(sendBundle);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void Sync (View v){

    }
}
