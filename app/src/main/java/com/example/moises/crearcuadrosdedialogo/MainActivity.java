package com.example.moises.crearcuadrosdedialogo;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    /*atributos*/
    CharSequence[] items = {"Google","Apple","Microsoft"};
    boolean[] itemsCheked = new boolean[items.length];
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//fin del metodo

    public void onClick(View v){
        showDialog(0);
    }

    public void onClick2(View v){
        //--muestra el cuadro de dialogo
        final ProgressDialog dialog = ProgressDialog.show(this,
                "Cuadro de dialgo de progreso_Carga", "Porfavor espere ...", true);
        new Thread(new Runnable(){
            public void run() {
                try {
                    Thread.sleep(5000);
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }//fin try-catch

            }
        }).start();
    }

    public void onClick3(View v){
        showDialog(1);
        progressDialog.setProgress(0);//estable la barra de progreso en cero
        new Thread(new Runnable() {
            public void run() {
                for(int i=1; i<=15; i++){
                    try{
                        Thread.sleep(1000);
                        progressDialog.incrementProgressBy((int)(100/15));
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();
                }//fin del for
            }
        }).start();
    }

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case 0:
                Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("Este es un cuadro de Dialogo. Seleccione ....");

                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int whichButton){
                                Toast.makeText(getBaseContext(), "OK Clicked!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                Toast.makeText(getBaseContext(), "Cancel Clicked!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                builder.setMultiChoiceItems(items,itemsCheked,
                        new DialogInterface.OnMultiChoiceClickListener(){
                            public void onClick(DialogInterface dialog, int which, boolean isChecked){
                                Toast.makeText(getBaseContext(),
                                        items[which] + (isChecked? " cheked!": "uncheked!"),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                return builder.create();

            case 1:
                progressDialog = new ProgressDialog(this);
                progressDialog.setIcon(android.R.drawable.progress_horizontal);
                progressDialog.setTitle("Descargando archivos ...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                //--establecer boton de afirmacion
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                Toast.makeText(getBaseContext(), "OK Clicked!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                //--Establecer el boton de negacion
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "Cancel Clicked!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                return progressDialog;
        }//fin switch
        return null;
    }//fin del metodo


}//fin de la clase
