package edu.upc.dsa.calculadorav2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.android.material.button.MaterialButton;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    Creamos los diferentes elementos que vamos a utilizar en cuanto a codigo/operaciones

    TextView resultadoTv, solucionTv;
    MaterialButton buttonC, buttonParentesisAbrir, buttonParentesisCerrar, buttonAC,buttonPunto;
    MaterialButton buttonDiv, buttonMult, buttonSuma, buttonResta, buttonIgual;
    MaterialButton button0, button1, button2, button3, button4, button5,button6,button7,button8,button9;
    String numerosCalculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Buscamos las cosas en el Layout (nos chiva el IDE como encontrarlo usando el R.id bla bla, muy util)
        resultadoTv = findViewById(R.id.result_text);
        solucionTv = findViewById(R.id.solution_text);
//        Vaciamos para que no salga siempre el 0 al inicio
        solucionTv.setText("");
//        Hacer esto es un coñazo, debe haber una forma mas eficiente de meter los listener a cada boton, probar bucle
        assignarId(button0,R.id.button_0);
        assignarId(button1,R.id.button_1);
        assignarId(button2,R.id.button_2);
        assignarId(button3,R.id.button_3);
        assignarId(button4,R.id.button_4);
        assignarId(button5,R.id.button_5);
        assignarId(button6,R.id.button_6);
        assignarId(button7,R.id.button_7);
        assignarId(button8,R.id.button_8);
        assignarId(button9,R.id.button_9);
        assignarId(buttonC,R.id.button_C);
        assignarId(buttonParentesisAbrir,R.id.button_parentesis_abierto);
        assignarId(buttonParentesisCerrar,R.id.button_parentesis_cerrar);
        assignarId(buttonAC,R.id.button_ac);
        assignarId(buttonPunto,R.id.button_punto);
        assignarId(buttonDiv,R.id.button_division);
        assignarId(buttonMult,R.id.button_multi);
        assignarId(buttonSuma,R.id.button_suma);
        assignarId(buttonResta,R.id.button_resta);
        assignarId(buttonIgual,R.id.button_igual);

    }
//Funcion para asignar un listener a cada boton para hacer cosas cuando se pulsan
    void assignarId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString(); //Buscamos el texto de ese boton en concreto
        numerosCalculo = solucionTv.getText().toString(); //Nos lo guardamos

        solucionTv.setText(numerosCalculo); //Como se ejecuta en cada pulsacion de boton, va rellenando
//        Casos especiales👇
        if(buttonText.equals("AC")){
            solucionTv.setText("");
            resultadoTv.setText("0");
            return; //Deberia pregutnar pq sin esto no va?
        }
        if(buttonText.equals("=")){
            solucionTv.setText(resultadoTv.getText()); //CUando operamos, que suba el resultado y se quede "en memoria" como en la calcu
            return;
        }
        //Hacemos una substring que va de inicio al final-1, eliminando de forma efectiva el ultimo char
        if(buttonText.equals("C")){
            numerosCalculo = numerosCalculo.substring(0,numerosCalculo.length()-1);
        }
        else {
            //Concatenamos los elementos en la textView que no sean cosas especiales
            numerosCalculo=numerosCalculo + buttonText;
        }
        solucionTv.setText(numerosCalculo);
        resultadoTv.setText(getResult(numerosCalculo));
    }
    String getResult(String data){
        Expression e = new Expression(numerosCalculo);
        double v = e.calculate();
        String resul = String.valueOf(v);
        return resul;
    }
}