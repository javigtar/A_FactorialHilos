package dam.org.hilos;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity {

    private EditText entrada;
    private TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrada = (EditText) findViewById(R.id.etEntrada);
        salida = (TextView) findViewById(R.id.tvSalida);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calcularOperacion(View view){

        int numero = Integer.parseInt(entrada.getText().toString());
        salida.setText("");

        MiHilo nuevoHilo = new MiHilo(numero);
        nuevoHilo.start();
    }

    class MiHilo extends Thread{

        private int numero;
        private int resultado;

        public MiHilo(int numero){
            this.numero = numero;
        }

        @Override
        public void run() {
            //super.run();


        factorialUltimosNumeros(5);
        }

        int i;

        //Calcula el factorial de los ultimos n numeros y los muestra por pantalla
        public void factorialUltimosNumeros(int numeros){

            if(numero >numeros){
                for( i= numero - numeros + 1 ; i<= numero; i++){

                    resultado = factorial(i);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            salida.append(i-1 + "! = ");
                            salida.append(resultado + "\n");

                        }
                    });
                }
                }else{
                for( i=1; i<= numero;  i++){

                    resultado = factorial(i);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            salida.append(i-1 + "! = ");
                            salida.append(resultado + "\n");

                        }
                    });
            }
        }}

        //Calcula el factorial de un numero
        public int factorial(int n) {

            int res=1;

            for (int i=1; i<=n; i++){
                res*=i;
                SystemClock.sleep(1000);
            }

            return res;
        }

    }

}
