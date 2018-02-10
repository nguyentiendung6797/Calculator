package com.example.pc.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nhap;
    TextView kq;

    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;

    Button btn_add;
    Button btn_sub;
    Button btn_mul;
    Button btn_div;

    Button btn_com;
    Button btn_C;
    Button btn_AC;
    Button btn_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setEventClickViews();
    }
    public void init(){
        nhap = (EditText) findViewById(R.id.tv_input);
        kq = (TextView) findViewById(R.id.tv_result);

        btn_0 = (Button) findViewById(R.id.btn0);
        btn_1 = (Button) findViewById(R.id.btn1);
        btn_2 = (Button) findViewById(R.id.btn2);
        btn_3 = (Button) findViewById(R.id.btn3);
        btn_4 = (Button) findViewById(R.id.btn4);
        btn_5 = (Button) findViewById(R.id.btn5);
        btn_6 = (Button) findViewById(R.id.btn6);
        btn_7 = (Button) findViewById(R.id.btn7);
        btn_8 = (Button) findViewById(R.id.btn8);
        btn_9 = (Button) findViewById(R.id.btn9);

        btn_add = (Button) findViewById(R.id.btnAdd);
        btn_sub = (Button) findViewById(R.id.btnSub);
        btn_mul = (Button) findViewById(R.id.btnMul);
        btn_div = (Button) findViewById(R.id.btnDiv);

        btn_com = (Button) findViewById(R.id.btnCom);
        btn_C = (Button) findViewById(R.id.btnC);
        btn_AC = (Button) findViewById(R.id.btnAC);
        btn_res = (Button) findViewById(R.id.btnRes);
    }

    public void setEventClickViews(){
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);

        btn_com.setOnClickListener(this);
        btn_C.setOnClickListener(this);
        btn_AC.setOnClickListener(this);
        btn_res.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn0:
                nhap.append("0");
                break;
            case R.id.btn1:
                nhap.append("1");
                break;
            case R.id.btn2:
                nhap.append("2");
                break;
            case R.id.btn3:
                nhap.append("3");
                break;
            case R.id.btn4:
                nhap.append("4");
                break;
            case R.id.btn5:
                nhap.append("5");
                break;
            case R.id.btn6:
                nhap.append("6");
                break;
            case R.id.btn7:
                nhap.append("7");
                break;
            case R.id.btn8:
                nhap.append("8");
                break;
            case R.id.btn9:
                nhap.append("9");
                break;
            case R.id.btnAdd:
                nhap.append("+");
                break;
            case R.id.btnSub:
                nhap.append("-");
                break;
            case R.id.btnMul:
                nhap.append("*");
                break;
            case R.id.btnDiv:
                nhap.append("/");
                break;
            case R.id.btnCom:
                nhap.append(".");
                break;
            case R.id.btnC:
                BaseInputConnection text = new BaseInputConnection(nhap,true);
                text.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_DEL));
                break;
            case R.id.btnAC:
                nhap.setText("");
                kq.setText("");
                break;
            case R.id.btnRes:
                DecimalFormat format = new DecimalFormat("###.#####");
                double result = 0;
                addOperation(nhap.getText().toString());
                addNumber(nhap.getText().toString());

                if(arrOperation.size()>=arrNumber.size()){
                    Toast.makeText(this, "Lỗi định dạng", Toast.LENGTH_SHORT).show();
                } else
                    for (int i=0;i<(arrNumber.size()-1);i++){
                        switch (arrOperation.get(i)){
                            case "+":
                                if(i==0){
                                    result = arrNumber.get(i)+arrNumber.get(i+1);
                                }else{
                                    result=result+arrNumber.get(i+1);
                                }
                                break;
                            case "-":
                                if(i==0){
                                    result = arrNumber.get(i)-arrNumber.get(i+1);
                                }else{
                                    result=result-arrNumber.get(i+1);
                                }
                                break;
                            case "*":
                                if(i==0){
                                    result = arrNumber.get(i)*arrNumber.get(i+1);
                                }else{
                                    result=result*arrNumber.get(i+1);
                                }
                                break;
                            case "/":
                                if(i==0){
                                    result = arrNumber.get(i)/arrNumber.get(i+1);
                                }else{
                                    result=result/arrNumber.get(i+1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                kq.setText(format.format(result)+"");
                break;
        }
    }
    public ArrayList<String> arrOperation;
    public ArrayList<Double> arrNumber;

    public int addOperation(String input){
        arrOperation = new ArrayList<>();

        char[] cArray = input.toCharArray();
        for (int i=0;i<cArray.length;i++){
            switch (cArray[i]){
                case '+':
                    arrOperation.add(cArray[i]+"");
                    break;
                case '-':
                    arrOperation.add(cArray[i]+"");
                    break;
                case '*':
                    arrOperation.add(cArray[i]+"");
                    break;
                case '/':
                    arrOperation.add(cArray[i]+"");
                    break;
                default:
                    break;
            }
        }

        return 0;
    }
    public void addNumber(String input){
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(input);
        while (matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }
}
