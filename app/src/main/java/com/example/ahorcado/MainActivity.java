package com.example.ahorcado;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String palabraOculta = "CETYS";
    int numeroDeFallos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null){
            //
            getSupportFragmentManager().beginTransaction().add(R.id.ventanaJuego, new VentanaAhorcado()).commit();
        }


        }
    public void botonPulsado(View vista){
        Button boton = (Button) findViewById(vista.getId());
        boton.setVisibility(View.INVISIBLE);

        chequeaLetra(boton.getText().toString());

        //NotificationCompat.Builder = new NotificationCompat.Builder(this,"0");

    }

    private void chequeaLetra(String letra) {
        ImageView imagenAhorcadoIV = findViewById(R.id.imagenAhorcado);
        String nombreImagen = imagenAhorcadoIV.toString();
        TextView textoOcultoTV = findViewById(R.id.palabraConGuiones);
        String palabraConGuiones = textoOcultoTV.getText().toString();
        boolean acierto = false;
        boolean partidaTerminada = false;
            for (int i=0; i<palabraOculta.length(); i++){
                if (palabraOculta.charAt(i) == letra.charAt(0)){
                    palabraConGuiones = palabraConGuiones.substring(0, 2*i) + letra
                            + palabraConGuiones.substring(2*i+1);
                    acierto=true;
                    }
                }
            textoOcultoTV.setText(palabraConGuiones);
        if (!acierto){
            numeroDeFallos++;
            pintaFallos(numeroDeFallos);
            if (numeroDeFallos >= 7){
                partidaTerminada = true;

            }
        }
        //si el if se cumple es porque ya no hay guiones en el display
        if (!palabraConGuiones.contains("_")){
            pintaFallos(-1);
            partidaTerminada = true;

        }
            }
    private void pintaFallos(int numeroFallos) {
        ImageView imagenAhorcadoIV = findViewById(R.id.imagenAhorcado);
        switch (numeroFallos){
            case 0:
                imagenAhorcadoIV.setImageResource(R.drawable.ahorcado_1);
                break;
            case 1:
                imagenAhorcadoIV.setImageResource(R.drawable.ahorcado_2);
                break;
            case 2:
                imagenAhorcadoIV.setImageResource(R.drawable.ahorcado_3);
                break;
            case 3:
                imagenAhorcadoIV.setImageResource(R.drawable.ahorcado_4);
                break;
            case 4:
                imagenAhorcadoIV.setImageResource(R.drawable.ahorcado_5);
                break;
            case 5:
                imagenAhorcadoIV.setImageResource(R.drawable.ahorcado_fin);
                break;
            case -1:
                imagenAhorcadoIV.setImageResource(R.drawable.acertaste_todo);
                break;
            default :
                imagenAhorcadoIV.setImageResource(R.drawable.looser);
                break;

        }
    }
    }

