/*
 *  UCF COP3330 Summer 2021 Assignment 10 Solution
 *  Copyright 2021 Paul Shannon
 */
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class App {
    Scanner product = new Scanner(System.in);
    public int i = 1, counter = 0;
    public final Double taxRate = .055;

    public static void main(String[] args) {
        //instances
        App SelfCheckout = new App();
        Product item1 = new Product();
        Product item2 = new Product();
        Product item3 = new Product();

        //scan in items, quantity and price, and converts to BigDecimal for accurate use

        item1.setItemPrice(SelfCheckout.convert(SelfCheckout.input()));
        item1.setItemQuantity(SelfCheckout.convert(SelfCheckout.input()));
        item2.setItemPrice(SelfCheckout.convert(SelfCheckout.input()));
        item2.setItemQuantity(SelfCheckout.convert(SelfCheckout.input()));
        item3.setItemPrice(SelfCheckout.convert(SelfCheckout.input()));
        item3.setItemQuantity(SelfCheckout.convert(SelfCheckout.input()));

        //calculate subtotal from the quantity times the price
        BigDecimal subtotal = SelfCheckout.summation(item1,item2,item3);

        //calculate 5.5 percent taz
        BigDecimal tax = SelfCheckout.tax(subtotal);

        //total
        BigDecimal total = SelfCheckout.Total(tax,subtotal);

        //build output
        SelfCheckout.Output(tax,subtotal,total);
    }

    public String input()
    {
        if(counter == 0) {
                //prompt for price
                System.out.print("Enter the price of item " + i + ": ");
                counter = 1;
                return product.nextLine();
        }
        else
        {
                //prompt for price
                System.out.print("Enter the quantity of item " + i + ": ");
                counter = 0;
                i ++;
                return product.nextLine();

        }

    }

    public BigDecimal convert(String str)
    {
        return new BigDecimal(str);
    }

    public BigDecimal summation(Product item1, Product item2, Product item3)
    {
        //calculate total price
        BigDecimal total1 = item1.getItemQuantity().multiply(item1.getItemPrice());
        BigDecimal total2 = item2.getItemQuantity().multiply(item2.getItemPrice());
        BigDecimal total3 = item3.getItemQuantity().multiply(item3.getItemPrice());

        //returns subtotal
        return total1.add(total2).add(total3);
    }

    public BigDecimal tax(BigDecimal subtotal)
    {
        //convert double to bigDecimal and multiply by subtotal
        BigDecimal taxBig = BigDecimal.valueOf(taxRate);
        return subtotal.multiply(taxBig);
    }

    public BigDecimal Total(BigDecimal tax, BigDecimal subtotal)
    {
        return tax.add(subtotal);
    }

    public void Output(BigDecimal tax, BigDecimal subtotal, BigDecimal total)
    {
        NumberFormat nf = new DecimalFormat("$0.00");
        String strTax = nf.format(tax);
        String strSubtotal = nf.format(subtotal);
        String strTotal = nf.format(total);
        StringBuilder output = new StringBuilder("Subtotal: " + strSubtotal);
        output.append("\nTax: " + strTax);
        output.append("\nTotal: " + strTotal);
        System.out.println(output);
    }
}

