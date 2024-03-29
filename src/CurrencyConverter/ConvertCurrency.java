package CurrencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ConvertCurrency {
    public int intvalue=0;
    private JPanel panel1;
    private JTextField txtjmdAmnt;
    private JComboBox cmdCurrency;
    private JTextField txtSum;
    private JButton btnConvert;
    private JButton btnClear;
public ConvertCurrency() {


 JFrame frame = new JFrame("Currency Converter");
 frame.setForeground(Color.blue);
    ///// mainMenu IS ALWAYS NULL WITHOUT THE NEXT LINE!!!!
    this.panel1 = new JPanel();  //recreating a new panel to overite the one created
  //  frame.setContentPane(this.panel1);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   frame.setMaximumSize(new Dimension(700,250));
   frame.setMinimumSize(new Dimension(300, 250));
    frame.setBounds(400,100,400,380);
    panel1.setBackground(Color.lightGray);
    panel1.setBorder(BorderFactory.createLineBorder(Color.blue,2));

    JLabel inputs=new JLabel("Input $:");
    frame.add(inputs);
    frame.add(txtjmdAmnt);
    inputs.setBounds(10,10,150,20);
    txtjmdAmnt.setBounds(110,10,150,20);
    frame.setEnabled((true));
    txtjmdAmnt.setForeground(Color.blue);
    txtjmdAmnt.setBorder(BorderFactory.createLineBorder(Color.blue,1));


    JLabel CurType=new JLabel("Currency Type:");
    frame.add(CurType);
    CurType.setBounds(10,60,90,20);
    cmdCurrency.setBounds(110,60,150,20);
    frame.add(cmdCurrency);
    cmdCurrency.setEnabled(true);
    cmdCurrency.setForeground(Color.blue);
    cmdCurrency.setBorder(BorderFactory.createLineBorder(Color.blue,1));

    JLabel lblAmnt=new JLabel("JMD Amount $:");
    frame.add(lblAmnt);
    lblAmnt.setBounds(10,110,90,20);
    frame.add(txtSum);
    txtSum.setBounds(110,110,150,20);
    txtSum.setForeground(Color.blue);
    //txtSum.setEnabled(false);
    txtSum.setBorder(BorderFactory.createLineBorder(Color.blue,1));

    frame.add(btnConvert);
   // btnConvert.setEnabled(true);
    btnConvert.setBounds(60,150,90,30);
    btnConvert.setBackground(Color.blue);
    btnConvert.setForeground(Color.WHITE);
    frame.add(btnClear);
    btnClear.setBounds(155,150,90,30);
    btnClear.setBackground(Color.blue);
    btnClear.setForeground(Color.WHITE);
   // btnClear.setEnabled(true);

   frame.getContentPane().add(panel1);
    frame.pack();
    frame.setVisible(true);


    btnConvert.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            intvalue=0;
            // txtSum.setText(cmdCurrency.getSelectedItem().toString());
            try {
                CalculateExchangeRate();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
                throw new RuntimeException(ex);

            }


        }
    });
    btnClear.addActionListener(new ActionListener() {
               @Override
        public void actionPerformed(ActionEvent e) {
                   intvalue=1;
           txtjmdAmnt.setText("");
           txtSum.setText("");
           cmdCurrency.setSelectedIndex(0);

        }
    });


}
    public  static void main(String[] args){
        new ConvertCurrency();
    }

 public double getCurrencyAmnt(String currencyType){
      double currencyAmount;

      if(currencyType.equals("US"))
      {
          currencyAmount=129.02;
      }
      else if(currencyType.equals("CAN")) {
          currencyAmount=97.50;
      }
      else if(currencyType.equals("Euro")) {
          currencyAmount=164.33;
      }
      else{
          currencyAmount=0;
      }
      return currencyAmount;
 }
   public void CalculateExchangeRate() throws IOException {
       double CurAmount=getCurrencyAmnt(cmdCurrency.getSelectedItem().toString());
     if (intvalue>0)
       return;

       if (txtjmdAmnt.getText().equals(""))
       {
           JOptionPane.showMessageDialog(null,"Please enter the amount to be converted.", "ERROR", JOptionPane.WARNING_MESSAGE);

       }
       else
       {
               try
               {
                   double text_amount=Double.parseDouble(txtjmdAmnt.getText());
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
