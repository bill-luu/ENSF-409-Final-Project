package com.ensf409.calculator;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	    private TextView valueTextView;
	    private Button oneButton;
        private Button twoButton;
        private Button threeButton;
		private Button fourButton;
		private Button fiveButton;
		private Button sixButton;
		private Button sevenButton;
		private Button eightButton;
		private Button nineButton;
		private Button zeroButton;
		private Button clearButton;
		private Button addButton;
		private Button divideButton;
		private Button multiplyButton;
		private Button minusButton;
		private Button equalsButton;
        private Button decimalButton;
        private Button negativeButton;
        private Calculator calc;
        final int MAX_INT_SIZE = 11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupView();
    }

    public void checkTextSize()
    {
        if(valueTextView.getText().length() > MAX_INT_SIZE)
        {
            valueTextView.setTextSize(20);
        }
        else
        {
            valueTextView.setTextSize(50);
        }
    }

    public void setupView()
    {
        calc = new Calculator();

    	valueTextView = (TextView)findViewById(R.id.ValueTextView);
        //Initialize Buttons
        oneButton = (Button)findViewById(R.id.OneButton);
        twoButton = (Button)findViewById(R.id.TwoButton);
        threeButton = (Button)findViewById(R.id.ThreeButton);
		fourButton = (Button)findViewById(R.id.FourButton);
		fiveButton = (Button)findViewById(R.id.FiveButton);
		sixButton = (Button)findViewById(R.id.SixButton);
		sevenButton = (Button)findViewById(R.id.SevenButton);
		eightButton = (Button)findViewById(R.id.EightButton);
		nineButton = (Button)findViewById(R.id.NineButton);
		zeroButton = (Button)findViewById(R.id.ZeroButton);
		clearButton = (Button)findViewById(R.id.ClearButton);
		addButton = (Button)findViewById(R.id.AddButton);
		divideButton = (Button)findViewById(R.id.DivideButton);
		multiplyButton = (Button)findViewById(R.id.MultiplyButton);
		minusButton = (Button)findViewById(R.id.MinusButton);
		equalsButton = (Button)findViewById(R.id.EqualsButton);
        decimalButton = (Button)findViewById((R.id.DecimalButton));
        negativeButton = (Button)findViewById((R.id.NegativeButton));

        //Add listeners
        oneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed(1));
                checkTextSize();
            }
        });

        twoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed(2));
                checkTextSize();
            }
        });

        threeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed(3));
                checkTextSize();
            }
        });

        fourButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed(4));
            }
        });

        fiveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed(5));
                checkTextSize();
            }
        });

        sixButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed(6));
                checkTextSize();
            }
        });

        sevenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed(7));
                checkTextSize();
            }
        });

        eightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed(8));
                checkTextSize();
            }
        });

        nineButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed(9));
                checkTextSize();
            }
        });

        zeroButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed(0));
                checkTextSize();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed('+'));
                checkTextSize();
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed('-'));
                checkTextSize();
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed('/'));
                checkTextSize();
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed('*'));
                checkTextSize();
            }
        });

        decimalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed('.'));
                checkTextSize();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed('C'));
                checkTextSize();
            }
        });

        equalsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                valueTextView.setText(calc.ButtonPressed('='));
                checkTextSize();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                valueTextView.setText(calc.ButtonPressed('S'));
                checkTextSize();
            }
        });
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
}
