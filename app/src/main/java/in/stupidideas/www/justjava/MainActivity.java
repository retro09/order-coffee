package in.stupidideas.www.justjava;

import android.content.Intent;
import android.content.pm.InstrumentationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.NumberFormat;
import java.text.StringCharacterIterator;
import static android.R.id.message;
import static in.stupidideas.www.justjava.R.id.OrderSummary;
import static in.stupidideas.www.justjava.R.id.name;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    int quantity=1;
    TextView textView;
    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.inc);
        btn2=(Button)findViewById(R.id.dec);
        textView=(TextView)findViewById(R.id.quantity_text_view);
    }

    /**
     * method is called when + button is called
     */

    public void Increment(View view)
    {
        quantity=quantity+1;
        displayQuantity(quantity);
    }
    /**
     * method is called when - button is called
     */
    public void Decrement(View view)
    {
        if(quantity==1)
        {
            Toast.makeText(this, "Order at least one coffee!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            {
                quantity = quantity - 1;
                displayQuantity(quantity);
            }
    }
    private int Calculate(int quantity, boolean addWhippedCream, boolean addChocolate,boolean addNutella,boolean addNutmeg)
    {
        int baseprice=0;
        if(addWhippedCream==true)
        {
            baseprice+=2;
        }
        if(addChocolate==true)
        {
            baseprice+=5;
        }
        if(addNutella==true)
        {
            baseprice+=7;
        }
        if(addNutmeg==true)
        {
            baseprice+=9;
        }
        price= (quantity*5)+(baseprice*quantity);
        return price;
    }
    /*
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream= (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox chocolate= (CheckBox) findViewById(R.id.chocolate);
        CheckBox nutella= (CheckBox) findViewById(R.id.nutella);
        CheckBox nutmeg= (CheckBox) findViewById(R.id.nutmeg);
       boolean hasWhippedCream=whippedCream.isChecked();
       boolean hasChocolate=chocolate.isChecked();
        boolean hasNutella=nutella.isChecked();
        boolean hasNutmeg=nutmeg.isChecked();

        createOrderSummary(price, hasWhippedCream, hasChocolate, hasNutella, hasNutmeg);
        //displayPrice(price);

        Toast.makeText(this, "Enjoy your "+ textView.getText().toString() +" cup(s) of coffee, have a nice day!", Toast.LENGTH_SHORT).show();





    }

    public void createOrderSummary(int price,boolean addWhippedCream, boolean addChocolate,boolean addNutella,boolean addNutmeg)
    {

        EditText name=(EditText)findViewById(R.id.name);
        String value=name.getText().toString();
        String pmessage="Name:"+value+"\n";
        pmessage+="Quantity="+textView.getText().toString();
        pmessage+="\nAdd Whipped Cream?"+ addWhippedCream;
        pmessage+="\nAdd Chocolate?"+ addChocolate;
        pmessage+="\nAdd Nutella?"+ addNutella;
        pmessage+="\nAdd Nutmeg?"+ addNutmeg;
        pmessage+="\nTotal: $"+ Calculate(quantity, addWhippedCream, addChocolate, addNutella, addNutmeg);
        pmessage+="\nThank you!";
        displayPrice(pmessage);
        Intent intent = new Intent(Intent.ACTION_SENDTO); //ACION_SENDTO is a constant
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for you!");
        intent.putExtra(Intent.EXTRA_TEXT, pmessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     * @param number
     */

    private void displayPrice(String number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        //priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
        priceTextView.setText(number);
    }
    /**
     * This method displays the given text on screen.
     */
    private void displayMessage(String message)
    {
        TextView priceTextView=(TextView) findViewById(R.id.price_text_view);      //Display the whatever the text written in the Text View whose reference id is "price_text_view"
        priceTextView.setText(message);
    }
}