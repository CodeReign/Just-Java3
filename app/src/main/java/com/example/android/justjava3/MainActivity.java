package com.example.android.justjava3;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



/** 
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
    *This method is called when the plus button is clicked
     */
    public void increment(View view) {
        if (quantity==100){

            Toast.makeText(this,"You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     *This button is called when the minus button is clicked
     */

    public void decrement(View view) {
        if (quantity==1){

            Toast.makeText(this, "You cannot have less than one coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    /**`
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckedBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckedBox.isChecked();

        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage =  createOrderSummary(name, price,hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for" + name);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(priceMessage);

    }


        int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        if (addChocolate) {
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;
        }

    /**
     * create order summary
     * @param price of the order
     * @return text summary
     */

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
            String priceMessage = "Name : " + name;
            priceMessage += "\nAdd Whipped cream? " + addWhippedCream;
            priceMessage += "\nAdd Chocolate? " + addChocolate;
            priceMessage = priceMessage + "\nQuantity: " + quantity;
            priceMessage = priceMessage + " \nTotal: $" + price;
            priceMessage = priceMessage + "\nThank You!";
            return priceMessage;

        }

    /**
     * This method displays the given quantity value on the screen.
     *
     *
     */

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = findViewById (R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     *This method displays the given quantity value on the screen
     */



    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
                orderSummaryTextView.setText(message);
    }


}