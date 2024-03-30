package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

public class ConvertCurrency {

    private JPanel panel1;
    private JTextField txt_jmdAmount;
    private JComboBox cmdCurrency;
    private JTextField txtSum;
    private JButton btnConvert;
    private JButton btnClear;
public ConvertCurrency() {


 JFrame frame = new JFrame("Currency Converter");
 frame.setForeground(Color.blue);
    ///// mainMenu IS ALWAYS NULL WITHOUT THE NEXT LINE!!!!
    this.panel1 = new JPanel();  //recreating a new panel to overwrite the one created
  //  frame.setContentPane(this.panel1);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   frame.setMaximumSize(new Dimension(700,250));
   frame.setMinimumSize(new Dimension(300, 250));
    frame.setBounds(400,100,400,380);
    panel1.setBackground(Color.lightGray);
    panel1.setBorder(BorderFactory.createLineBorder(Color.blue,2));

    JLabel inputs=new JLabel("Input $:");
    frame.add(inputs);
    frame.add(txt_jmdAmount);
    inputs.setBounds(10,10,150,20);
    txt_jmdAmount.setBounds(110,10,150,20);
    frame.setEnabled((true));
    txt_jmdAmount.setForeground(Color.blue);
    txt_jmdAmount.setBorder(BorderFactory.createLineBorder(Color.blue,1));


    JLabel CurType=new JLabel("Currency Type:");
    frame.add(CurType);
    CurType.setBounds(10,60,90,20);
    cmdCurrency.setBounds(110,60,150,20);
    frame.add(cmdCurrency);
    cmdCurrency.setEnabled(true);
    cmdCurrency.setForeground(Color.blue);
    cmdCurrency.setBorder(BorderFactory.createLineBorder(Color.blue,1));

    JLabel lbl_Amount=new JLabel("JMD Amount $:");
    frame.add(lbl_Amount);//adding the lbl Amount to the frame
    lbl_Amount.setBounds(10,110,90,20);

    frame.add(txtSum);//adding the txtSum to the frame
    txtSum.setBounds(110,110,150,20);
    txtSum.setForeground(Color.blue); //setting the font colour
    //txtSum.setEnabled(false);
    txtSum.setBorder(BorderFactory.createLineBorder(Color.blue,1));

    frame.add(btnConvert); //adding the convert button to the frame
    btnConvert.setBounds(60,150,90,30);
    btnConvert.setBackground(Color.blue);//setting the colour of the button
    btnConvert.setForeground(Color.WHITE);//setting the font of the button

    frame.add(btnClear);//adding the clear button to the frame
    btnClear.setBounds(155,150,90,30);
    btnClear.setBackground(Color.blue); //setting the colour of the button
    btnClear.setForeground(Color.WHITE); //setting the font of the button
   // btnClear.setEnabled(true);

   frame.getContentPane().add(panel1);
    frame.pack();
    frame.setVisible(true);


    btnConvert.addActionListener(new ActionListener() { //convert button clicked
        @Override
        public void actionPerformed(ActionEvent e) {

            // txtSum.setText(cmdCurrency.getSelectedItem().toString());
            try {
                CalculateExchangeRate();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
                throw new RuntimeException(ex);

            }


        }
    });
    btnClear.addActionListener(new ActionListener() { //clear button clicked
               @Override
        public void actionPerformed(ActionEvent e) {

           txt_jmdAmount.setText("");
           txtSum.setText("");
           cmdCurrency.setSelectedIndex(0);

        }
    });


}
    public  static void main(String[] args){
        new ConvertCurrency();
    }

 public double getCurrency_Amount(String currencyType){ //initializing the currency amount and returning the value base on parameter passed

     return switch (currencyType) {
         case "US" -> 129.02;
         case "CAN" ->
                 97.50;
         default -> 164.33;
     };
 }
   public void CalculateExchangeRate() throws IOException { //calculating the exchange rate by multiplying amount entered* the currency rate
       double CurAmount=getCurrency_Amount(String.valueOf(cmdCurrency.getSelectedItem()));

     if (txt_jmdAmount.getText().equals(""))
       {
           JOptionPane.showMessageDialog(null,"Please enter the amount to be converted.", "ERROR", JOptionPane.WARNING_MESSAGE);

       }
       else
       {
               try
               {
                   double text_amount=Double.parseDouble(txt_jmdAmount.getText());
                   if(text_amount<=0){
                       JOptionPane.showMessageDialog(null,"Number entered should be greater than 0.","ERROR", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
                   DecimalFormat df = new DecimalFormat();
                   df.setMaximumFractionDigits(2);

                   double total=text_amount*CurAmount;
                   txtSum.setText(String.valueOf((df.format(total))));
               }
               catch (Exception ex) {
                   JOptionPane.showMessageDialog(null,ex.getMessage()+" Please enter a valid number to convert.","ERROR", JOptionPane.WARNING_MESSAGE);
                   throw new RuntimeException(ex);

               }


       }
   }
}
