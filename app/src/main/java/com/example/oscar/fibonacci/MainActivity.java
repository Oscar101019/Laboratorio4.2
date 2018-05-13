package com.example.oscar.fibonacci;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText num;
    TextView res;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.editText_num);
        res = findViewById(R.id.editText_result);
        start = findViewById(R.id.button_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTarea asyncTarea = new AsyncTarea();
                asyncTarea.execute(Integer.parseInt(num.getText().toString()));
            }
        });
    }

    private void UnSegundo() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class  AsyncTarea extends AsyncTask<Integer, String,Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            res.setText("");
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            int aux1 =0;
            int aux2 =0;
            int aux3 =0;
            String result = ""+aux1+" \n";
            publishProgress(result);
            aux2 = aux1 +1;
            result+=aux2+" \n";
            UnSegundo();
            publishProgress(result);
            for (int i=2; i<Integer.parseInt(num.getText().toString()); i++){
                UnSegundo();
                aux3 = aux1;
                aux1 = aux2;
                aux2 = aux3+aux2;
                result+=aux2+" \n";
                publishProgress(result);
                if (isCancelled()){
                    break;
                }
            }
            return true;
        }


        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //Actualizar la barra de progreso
            res.setText(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            //super.onPostExecute(aVoid);

            if (aVoid){
                Toast.makeText(getApplicationContext(),"Tarea finaliza AsyncTask",Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();

            Toast.makeText(getApplicationContext(),"Tarea NO finaliza AsyncTask",Toast.LENGTH_SHORT).show();

        }


    }


}
