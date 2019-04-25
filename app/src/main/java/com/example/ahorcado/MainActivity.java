package com.example.ahorcado;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String palabraOculta = "CETYS";
    int numeroDeFallos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            //
            getSupportFragmentManager().beginTransaction().add(R.id.ventanaJuego, new VentanaAhorcado()).commit();
        }


    }

    public void botonPulsado(View vista) {
        Button boton = (Button) findViewById(vista.getId());
        boton.setClickable(false);


        chequeaLetra(boton);

        //NotificationCompat.Builder = new NotificationCompat.Builder(this,"0");

    }

    private void chequeaLetra(Button boton) {
        ImageView imagenAhorcadoIV = findViewById(R.id.imagenAhorcado);
        String nombreImagen = imagenAhorcadoIV.toString();
        TextView textoOcultoTV = findViewById(R.id.palabraConGuiones);
        String palabraConGuiones = textoOcultoTV.getText().toString();
        String letra = boton.getText().toString();
        boton.setBackgroundColor(getResources().getColor(R.color.white));
        boolean acierto = false;
        for (int i = 0; i < palabraOculta.length(); i++) {
            if (palabraOculta.charAt(i) == letra.charAt(0)) {
                palabraConGuiones = palabraConGuiones.substring(0, 2 * i) + letra
                        + palabraConGuiones.substring(2 * i + 1);
                acierto = true;
                boton.setTextColor(getResources().getColor(R.color.green));
            }
        }
        textoOcultoTV.setText(palabraConGuiones);
        if (!acierto) {
            boton.setTextColor(getResources().getColor(R.color.red));
            numeroDeFallos++;
            pintaFallos(numeroDeFallos);

        }
        //si el if se cumple es porque ya no hay guiones en el display
        if (!palabraConGuiones.contains("_")) {
            pintaFallos(-1);
            bloqueaBotones();

        }
    }

    private void pintaFallos(int numeroFallos) {
        ImageView imagenAhorcadoIV = findViewById(R.id.imagenAhorcado);
        switch (numeroFallos) {
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
            default:
                imagenAhorcadoIV.setImageResource(R.drawable.looser);
                break;

        }
    }

    private void bloqueaBotones() {
        LinearLayout container = findViewById(R.id.contenedor);
        ArrayList<LinearLayout> arrayListLL = new ArrayList<>();
        for (int i = 0; i < container.getChildCount(); i++) {
            if(container.getChildAt(i)instanceof LinearLayout) {
                arrayListLL.add((LinearLayout)container.getChildAt(i));
            }
        }
        bloqueaBotonesDelArrayList(arrayListLL);


    }

    private void bloqueaBotonesDelArrayList(ArrayList<LinearLayout> arrayListLL) {
        for (LinearLayout l:arrayListLL) {
            for (int i = 0; i < l.getChildCount(); i++) {
                if (l.getChildAt(i)instanceof Button) {
                    Button b = (Button) l.getChildAt(i);
                    b.setClickable(false);
                }
            }
        }
    }
}

