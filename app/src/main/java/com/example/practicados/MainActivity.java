package com.example.practicados;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Declaración de los elementos
    private TextView timer;
    private TextView formula;
    private TextView puntaje;
    private Button boton1;
    private Button boton2;
    private EditText respuesta;
    private Pregunta p;
    private int score=0;
   // private int conta=30;
   private boolean arraca=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciar

        timer=findViewById(R.id.timer);
        formula=findViewById(R.id.formula);
        puntaje=findViewById(R.id.puntaje);
        boton1=findViewById(R.id.boton1);
        boton2=findViewById(R.id.boton2);
        respuesta=findViewById(R.id.respuesta);

        p = new Pregunta();
        formula.setText(p.getPregunta());
        tiempo();



        boton1.setOnClickListener(

                v -> {
                    responder();

                }


        );



        boton2.setOnClickListener(
                v -> {
                   // arraca=true;
                    //conta=30;
                    tiempo();
                    score=0;
                    boton2.setVisibility(View.GONE);
                    boton1.setEnabled(true);
                    mostrarNuevaPregunta();
                }

        );

        formula.setOnTouchListener(

                (v, event) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            arraca=true;
                            new Thread(
                                    () -> {


                                        for(int i=0;i<20;i++){
                                            try {
                                                Thread.sleep(75);
                                                if(arraca==false){
                                                    runOnUiThread(

                                                            () -> {
                                                              Toast.makeText(this,"alzo el dedo",Toast.LENGTH_SHORT).show();
                                                            }
                                                    );
                                                    return;
                                                }else {}
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                        if(arraca==true) {
                                            runOnUiThread(

                                                    () -> {
                                                        mostrarNuevaPregunta();
                                                    }
                                            );
                                        }
                                    }
                            ).start();

                            break;

                        case MotionEvent.ACTION_UP:
                            arraca=false;
                            //Toast.makeText(this,"alzo el dedo",Toast.LENGTH_SHORT).show();
                            break;



                    }

                    return true;
                }





        );


    }

    public void responder(){
        String resp=respuesta.getText().toString();
        int respint= Integer.parseInt(resp);
        int respcorre= p.getRespuesta();

        if (respint==respcorre){
            Toast.makeText(this,"Correcto",Toast.LENGTH_SHORT).show();
            score=score+5;
            puntaje.setText("Puntaje: "+score);
            mostrarNuevaPregunta();

        }else {
            Toast.makeText(this,"Mal",Toast.LENGTH_SHORT).show();
            score=score-4;
            puntaje.setText("Puntaje: "+score);
            mostrarNuevaPregunta();

        }



    }

    public void mostrarNuevaPregunta(){

        p = new Pregunta();
        formula.setText(p.getPregunta());

    }

    public void tiempo(){
      
        new Thread(

                () -> {

                    for(int i=30;i>0;i--){

                        try {
                            Thread.sleep(1000);
                            int finalI = i;
                            runOnUiThread(
                                    () -> {
                                        //Toast.makeText(this,"Entro"+conta,Toast.LENGTH_SHORT).show();
                                        timer.setText(""+ finalI);

                                    }
                            );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    runOnUiThread(
                            () -> {

                               // arraca=false;
                                boton1.setEnabled(false);
                                boton2.setVisibility(View.VISIBLE);

                            }
                    );

                    return;
                }
        ).start();

    }




}