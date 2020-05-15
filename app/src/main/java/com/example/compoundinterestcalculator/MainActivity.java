/*@author Rudra Makwana (B00826991)   Mobile Computing (CSCI 5708)*/

package com.example.compoundinterestcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView textView1,textView2;
    private EditText ET1,ET2,ET3;
    double Amount;
    double r,Deposit,p,period;
    private String depo;
    private String interestRate;
    int Compounding_Freq,Deposit_Freq;
    private String principal;
    private Button calculate;
    private Spinner spinner;
    DecimalFormat precision=new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=(SeekBar)findViewById(R.id.SB1);
        textView1 = (TextView) findViewById(R.id.seekBarText);
        ET1 = (EditText) findViewById(R.id.ET1); //ET1 = Principal
        ET2 = (EditText) findViewById(R.id.ET2); //ET2 = Deposit
        ET3 = (EditText) findViewById(R.id.ET3); //ET3 = Annual Interest Rate

        calculate = (Button) findViewById(R.id.calculate);

        Compounding_Freq=1;

        //Spinner part(Dropdown box)
        List <String> Pay_Type = new ArrayList<>();
        spinner =(Spinner) findViewById(R.id.Spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinnerValue,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //Code for getting the value of period value
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress=0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue+1;
                period = progress;                      //set the value of periods
                textView1.setText(progress + "Years");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Setting the valu of deposit frequency
                String type=spinner.getSelectedItem().toString();
                if(type.equals("Yearly")){
                    Deposit_Freq=1;
                }
                else if(type.equals("Semi Anually")){
                    Deposit_Freq=2;
                }
                else if(type.equals("Quarterly")){
                    Deposit_Freq=4;
                }

                else if(type.equals("Monthly")){
                    Deposit_Freq=12;
                }

                //Calculation Part and setting the values of different variables given by users
                double x,y;
                principal = ET1.getText().toString();
                double princi = 0;
                princi = Double.parseDouble(principal);

                interestRate = ET3.getText().toString();
                double i = Double.parseDouble(interestRate);

                depo = ET2.getText().toString();
                Deposit = Double.parseDouble(depo);

                r =(double) i / 100;

                x = Math.pow((1+r),period);
                y = ((x-1)/r);

                y = (y*(Deposit*Deposit_Freq));
                x = (x*princi);

                Amount = x+y;

                textView2 = (TextView) findViewById(R.id.tv6);
                textView2.setText("Compound Interest:" + precision.format(Amount)); //Shows the compund interest
            }
        });
    }
}
