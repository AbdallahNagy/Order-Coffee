/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the Plus button is clicked.
     */
    public void increment(View view) {
        if(quantity < 100 ){
            quantity = quantity + 1;
            display(quantity);
        }else{
            Context context = getApplicationContext();
            CharSequence text = "can't go further!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity > 1 ){
            quantity = quantity - 1;
            display(quantity);
        }else{
            Context context = getApplicationContext();
            CharSequence text = "Are you kidding me";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //bringing name
        EditText editText = (EditText) findViewById(R.id.Edit_view);
        String editedText = editText.getText().toString();

        //has whippedCream?
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //has chocolate?
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean chocolate = hasChocolate.isChecked();

        int price = calculatePrice(chocolate, hasWhippedCream);

        displayMessage(createOrderSummary(price, hasWhippedCream, chocolate, editedText));
    }

    //returns total price
    private int calculatePrice(boolean chocolate, boolean whippedCream){
        int price = 5;
        if(chocolate)
            price +=2;
        if(whippedCream)
            price +=1;
        return price * quantity;
    }

    // order summary
    private String createOrderSummary(int price, boolean whippedCream, boolean chocolate, String name){
        String message = getString(R.string.order_summary_name, name);
        message += "\nhas whipped cream? " + whippedCream;
        message += "\nhas chocolate? " + chocolate;
        message += "\nQuantity: " + quantity;
        message +="\nPrice: " + price;
        message +="\n" + getString(R.string.thank_you);
        return message;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}