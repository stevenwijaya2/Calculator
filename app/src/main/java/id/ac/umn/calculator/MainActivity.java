package id.ac.umn.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String result;

    TextView tvResult,tvOperator;

    Button btnCE,btnC,btnBack,btnDivide,
    btnSeven,btnEight,btnNine,btnMulti,
    btnFour,btnFive,btnSix,btnMinus,
    btnOne,btnTwo,btnThree,btnPlus,
    btnReverse,btnZero,btnComma,btnEqual;

    double operand1,operand2=0.0;

    boolean flagDoubleClick,flagOP2;
    boolean flagAdd,flagMinus,flagDivide,flagMulti = false;
    boolean flagFirst = true;
    boolean flagFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        tvOperator = findViewById(R.id.tvOperator);
        flagFirst = true;
        btnCE = findViewById(R.id.btnCE);
        btnC = findViewById(R.id.btnC);
        btnBack = findViewById(R.id.btnBack);
        btnDivide = findViewById(R.id.btnDivide);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnnine);
        btnMulti = findViewById(R.id.btnMulti);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnMinus = findViewById(R.id.btnMinus);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnPlus = findViewById(R.id.btnPlus);
        btnReverse = findViewById(R.id.btnReverse);
        btnZero = findViewById(R.id.btnZero);
        btnComma = findViewById(R.id.btnComma);
        btnEqual = findViewById(R.id.btnEqual);


        btnCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("0");
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOperator.setText("");
                tvResult.setText("0");
                operand1 = 0.0;
                operand2 = 0.0;
                flagAdd = false;
                flagMinus = false;
                flagDivide = false;
                flagMulti = false;
                flagFirst = true;
                flagDoubleClick = false;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flagFinish){
                    flagFinish = false;
                    flagFirst = true;
                }
                flagOP2 = false;
                result = tvResult.getText().toString();
                if(tvResult.getText().toString().equals("Infinity") || tvResult.getText().toString().equals("NaN")){
                    result = "0";
                }
                if(result.length()>0){
                    result = result.substring(0,result.length()-1);
                }
                if(result.length()<=0){
                    result = "0";
                }
                tvResult.setText(result);
            }
        });

        btnComma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = tvResult.getText().toString();
                if (result.length() > 0) {
                    if (flagOP2) {
                        tvResult.setText("");
                        result = "0.";
                        flagOP2=false;
                    }
                    else if (isNumeric(result.substring(result.length() - 1)) && !result.contains(".")) {
                        result = result + ".";
                        flagOP2 = false;
                    }

                }

                tvResult.setText(result);
            }
        });

        btnReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = tvResult.getText().toString();
                double Temp = Double.parseDouble(result);
                Log.d("reverse","Flag Double click : "+flagDoubleClick);
                Log.d("reverse","Flagfirst : "+flagFirst);
                Log.d("reverse","Flag OP2 : "+flagOP2);
                if(flagFinish){
                    Log.d("reverse","finish on");
                    Temp *= (-1);
                    result = Double.toString(Temp);
                    operand1 = Temp;
                    flagDoubleClick = false;
                    flagFinish = false;
                    flagOP2 = true;
                    flagFirst = true;
                }
                else if(Double.parseDouble(result) == 0.0){
                    Log.d("reverse","masuk gan");
                }
                else if(!flagDoubleClick && flagOP2){
                    Log.d("reverse","masuk bos");
                }
                else {
                    Log.d("reverse","masuk cuy");
                    Temp *= (-1);
                    result = Double.toString(Temp);
                    flagDoubleClick = false;
                }
                tvResult.setText(result);
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagFinish = false;
                flagOP2 = true;
                if(!flagDoubleClick){
                    if(flagFirst){
                        operand1 = Double.parseDouble(tvResult.getText().toString());
                        flagFirst = false;
                        flagDoubleClick = false;
                    }
                    else {
                        operand2 = Double.parseDouble(tvResult.getText().toString());
                        hitung(operand1,operand2);
                        operand1 = Double.parseDouble(tvResult.getText().toString());
                        operand2 = 0.0;
                        flagDoubleClick = true;
                    }
                }
                tvOperator.setText("/");
                flagAdd = false;
                flagMinus = false;
                flagDivide = true;
                flagMulti = false;
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagFinish = false;
                flagOP2 = true;
                if(!flagDoubleClick){
                    if(flagFirst){
                        operand1 = Double.parseDouble(tvResult.getText().toString());
                        flagFirst = false;
                        flagDoubleClick = false;
                    }
                    else {
                        operand2 = Double.parseDouble(tvResult.getText().toString());
                        hitung(operand1,operand2);
                        operand1 = Double.parseDouble(tvResult.getText().toString());
                        operand2 = 0.0;
                        flagDoubleClick=true;
                    }
                }
                tvOperator.setText("*");
                flagMulti = true;
                flagAdd = false;
                flagMinus = false;
                flagDivide = false;
            }
        });


        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagFinish = false;
                flagOP2 = true;
                if(!flagDoubleClick){
                    if(flagFirst){
                        operand1 = Double.parseDouble(tvResult.getText().toString());
                        flagFirst = false;
                        flagDoubleClick = false;
                    }
                    else {
                        operand2 = Double.parseDouble(tvResult.getText().toString());
                        hitung(operand1,operand2);
                        operand1 = Double.parseDouble(tvResult.getText().toString());
                        operand2 = 0.0;
                        flagDoubleClick = true;
                    }
                }
                tvOperator.setText("-");
                flagMinus = true;
                flagAdd = false;
                flagDivide = false;
                flagMulti = false;
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagFinish = false;
                Log.d("plus","Flag Double click : "+flagDoubleClick);
                Log.d("plus","Flagfirst : "+flagFirst);
                if(!flagDoubleClick){
                    if(flagFirst){
                        operand1 = Double.parseDouble(tvResult.getText().toString());
                        flagFirst = false;
                        flagDoubleClick = false;
                        flagOP2 = true;
                    }
                    else {
                        Log.d("plus","operand 2 : "+Double.toString(operand2));
                        operand2 = Double.parseDouble(tvResult.getText().toString());
                        hitung(operand1,operand2);

                        operand1=Double.parseDouble(tvResult.getText().toString());
                        operand2=0.0;
                        flagDoubleClick=true;
                    }
                }
                tvOperator.setText("+");
                flagAdd = true;
                flagMinus = false;
                flagDivide = false;
                flagMulti = false;
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagOP2 = true;
                if(!flagDoubleClick){
                    if(flagFirst){
                        operand1 = Double.parseDouble(tvResult.getText().toString());
                        flagDoubleClick=true;
                    }
                    else {
                        tvOperator.setText("");
                        operand2 = Double.parseDouble(tvResult.getText().toString());
                        hitung(operand1, operand2);
                        operand1 = Double.parseDouble(tvResult.getText().toString());
                        operand2 = 0.0;
                        flagDoubleClick = true;
                        flagAdd = false;
                        flagMinus = false;
                        flagDivide = false;
                        flagMulti = false;
                    }
                    flagFinish = true;
                }
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDoubleClick = false;
                if (flagFinish) {
                    kelargan();
                }
                if(flagOP2){
                    tvResult.setText("");
                    flagOP2 = false;
                }
                result = tvResult.getText().toString();
                if(result.length() > 0){
                    if((result.substring(0,1).contains("0") && result.length() == 1)){
                        result = result.substring(0,result.length()-1);
                    }
                }
                result = result + "9";
                tvResult.setText(result);
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDoubleClick = false;
                if (flagFinish) {
                    kelargan();
                }
                if(flagOP2){
                    tvResult.setText("");
                    flagOP2=false;
                }
                result = tvResult.getText().toString();
                if(result.length() > 0){
                    if((result.substring(0,1).contains("0") && result.length() == 1)){
                        result = result.substring(0,result.length()-1);
                    }
                }
                result = result + "8";
                tvResult.setText(result);
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDoubleClick = false;
                if (flagFinish) {
                    kelargan();
                }
                if(flagOP2){
                    tvResult.setText("");
                    flagOP2=false;
                }
                result = tvResult.getText().toString();

                if(result.length() > 0){
                    if((result.substring(0,1).contains("0") && result.length() == 1)){
                        result = result.substring(0,result.length()-1);
                    }
                }
                result = result + "7";
                tvResult.setText(result);
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDoubleClick = false;
                if (flagFinish) {
                    kelargan();
                }
                if(flagOP2){
                    tvResult.setText("");
                    flagOP2=false;
                }
                result = tvResult.getText().toString();
                if(result.length() > 0){
                    if((result.substring(0,1).contains("0") && result.length() == 1)){
                        result = result.substring(0,result.length()-1);
                    }
                }
                result = result + "6";
                tvResult.setText(result);
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDoubleClick = false;
                if (flagFinish) {
                    kelargan();
                }
                if(flagOP2){
                    tvResult.setText("");
                    flagOP2=false;
                }
                result = tvResult.getText().toString();
                if(result.length() > 0){
                    if((result.substring(0,1).contains("0") && result.length() == 1)){
                        result = result.substring(0,result.length()-1);
                    }
                }
                result = result + "5";
                tvResult.setText(result);
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDoubleClick = false;
                if (flagFinish) {
                    kelargan();
                }
                if(flagOP2){
                    tvResult.setText("");
                    flagOP2=false;
                }
                result = tvResult.getText().toString();
                if(result.length() > 0){
                    if((result.substring(0,1).contains("0") && result.length() == 1)){
                        result = result.substring(0,result.length()-1);
                    }
                }
                result = result + "4";
                tvResult.setText(result);
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDoubleClick = false;
                if (flagFinish) {
                    kelargan();
                }
                if(flagOP2){
                    tvResult.setText("");
                    flagOP2=false;
                }
                result = tvResult.getText().toString();
                if(result.length() > 0){
                    if((result.substring(0,1).contains("0") && result.length() == 1)){
                        result = result.substring(0,result.length()-1);
                    }
                }
                result = result + "3";
                tvResult.setText(result);
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDoubleClick = false;
                if (flagFinish) {
                    kelargan();
                }
                if(flagOP2){
                    tvResult.setText("");
                    flagOP2=false;
                }
                result = tvResult.getText().toString();
                if(result.length() > 0){
                    if((result.substring(0,1).contains("0") && result.length() == 1)){
                        result = result.substring(0,result.length()-1);
                    }
                }
                result = result + "2";
                tvResult.setText(result);
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDoubleClick = false;
                if (flagFinish) {
                    kelargan();
                }
                if(flagOP2){
                    tvResult.setText("");
                    flagOP2=false;
                }
                result = tvResult.getText().toString();
                if(result.length() > 0){
                    if((result.substring(0,1).contains("0") && result.length() == 1)){
                        result = result.substring(0,result.length()-1);
                    }
                }
                result = result + "1";
                tvResult.setText(result);
            }
        });

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDoubleClick = false;
                if (flagFinish) {
                    kelargan();
                }
                if(flagOP2){
                    tvResult.setText("");
                    flagOP2=false;
                }
                result = tvResult.getText().toString();
                if(result.length() > 0){
                    if((result.substring(0,1).contains("0") && result.length() == 1)){
                        result = result.substring(0,result.length()-1);
                    }
                }
                result = result + "0";
                tvResult.setText(result);
            }
        });
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    protected void kelargan(){
        tvResult.setText("");
        flagDoubleClick = false;
        flagFirst = true;
        flagFinish = false;
    }

    protected double hitung(double operand1, double operand2){
        Log.d("hitung","operand 1 : "+Double.toString(operand1));
        Log.d("hitung","operand 2 : "+Double.toString(operand2));
        double result2=0.0;
        String resultSTR;
        if(flagAdd){
            result2 = operand1 + operand2 ;
            flagAdd = false;
        }
        if(flagDivide){
            result2 = operand1 / operand2 ;
            flagDivide = false;
        }
        if(flagMinus){
            result2 = operand1 - operand2 ;
            flagMinus = false;
        }
        if(flagMulti){
            result2 = operand1 * operand2 ;
            flagMulti = false;
        }
        resultSTR = Double.toString(result2);
        if(result2 == Math.floor(result2) && !Double.isInfinite(result2)){
            int resultInt = (int) result2;
            resultSTR = Integer.toString(resultInt);
        }
        tvResult.setText(resultSTR);
        return result2;
    }
}
