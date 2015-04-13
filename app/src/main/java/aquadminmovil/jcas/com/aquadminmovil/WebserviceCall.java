package aquadminmovil.jcas.com.aquadminmovil;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;

import com.jcas.utilerias.Organismo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


/**
 * Created by MBrandle on 10/10/2014.
 */
public class WebserviceCall {
    private String url = "http://www.webservicex.net/ConvertWeight.asmx";

    String SOAP_ACTION;
    SoapObject request = null, objMessages = null;
    SoapSerializationEnvelope envelope;
    AndroidHttpTransport androidHttpTransport;

    WebserviceCall() {
    }

    protected void SetEnvelope() {

        try {

            // Creating SOAP envelope
            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            //You can comment that line if your web service is not .NET one.
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);
            androidHttpTransport = new AndroidHttpTransport(url);
            androidHttpTransport.debug = true;

        } catch (Exception e) {
            System.out.println("Soap Exception---->>>" + e.toString());
        }


    }



    /*public String[] getOrganismos(){
        String[] organismos=null;
        String webMethName="prueba";
        try{

            File ff = new File("/mnt/sdcard/Lecturas", "BD.txt");
            BufferedReader txtReader = new BufferedReader(new InputStreamReader(new FileInputStream(ff)));

            txtReader.readLine();
            String line=txtReader.readLine();
            String[] arr=line.split(",");
            String URL=arr[1];
            txtReader.close();txtReader.close();

            String NAMESPACE = "http://aquadmin.jcas.com";
            String SOAP_ACTION = "http://aquadmin.jcas.com";

            String resTxt = null;
            SoapObject request = new SoapObject(NAMESPACE, webMethName);
            PropertyInfo sayHelloPI = new PropertyInfo();


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            androidHttpTransport.call(SOAP_ACTION+webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            // Assign it to resTxt variable static variable
            organismos = response.getAttribute("pruebaResponse");

        }catch (Exception ex){

        }
        return organismos;
    }*/
}
