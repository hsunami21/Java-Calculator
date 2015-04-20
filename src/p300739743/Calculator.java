package p300739743;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Calculator extends JFrame {

    private JButton btn[] = new JButton[20];
    private JButton bsp= new JButton("Backspace");
    private JButton ce= new JButton("CE");
    private JButton clear= new JButton("Clear");
    int index  = 0;

    private String  lblName[]= {
                                "7","8","9","/","exp",
                                "4","5","6","*","%",
                                "1","2","3","-","1/x",
                                "0","+/-",".","+","="
                               };
    
    TextHandler txtHandler = new TextHandler();
    ButtonHandler btnHandler = new ButtonHandler();
    private boolean function[] = new boolean[6];
    private double num1, num2;



    private JTextField tDisplay = new JTextField(20);

    public Calculator() {

    	// Initialize function array
    	for (int i = 0; i < function.length; i++)
    	{
    		function[i] = false;
    	}
    	
	    //Set the GridBagLayout for the frame
	    GridBagLayout gridBag = new GridBagLayout();
	    setLayout(gridBag);
	
	    //constraints for the text field display
	    GridBagConstraints Constr1 = new GridBagConstraints();
	    Constr1.gridx = 0; //first column
	    Constr1.gridy = 0; //first row
	    Constr1.gridwidth = 5; //number of cells in the row that will be covered
	    Constr1.gridheight = 1; //number of cells in the column
	    Constr1.fill = GridBagConstraints.HORIZONTAL; //resize the component horizontally
	    // add the text field
	    gridBag.setConstraints(tDisplay, Constr1); //apply the constraints to the grid
	    add(tDisplay);
	    tDisplay.addActionListener(txtHandler);
	
	
	    //constraints for the backspace button
	    GridBagConstraints Constr2 = new GridBagConstraints();
	    Constr2.gridx = 0; //first column
	    Constr2.gridy = 1; //second row
	
	    Constr2.gridwidth = 2; //number of cells in the row that will be covered
	    Constr2.gridheight = 1; //number of cells in the column
	    Constr2.fill = GridBagConstraints.HORIZONTAL; //resize the component horizontally
	    // //add the backspace button
	    gridBag.setConstraints(bsp, Constr2);
	    add(bsp);
	    bsp.addActionListener(btnHandler);
	
	    // constraints for the CE button
	    GridBagConstraints Constr3 = new GridBagConstraints();
	    Constr3.gridx = 2; //starts at third column
	    Constr3.gridy = 1; //second row
	
	    Constr3.gridwidth = 1; //number of cells in the row that will be covered
	    Constr3.gridheight = 1; //number of cells in the column
	    Constr3.fill = GridBagConstraints.HORIZONTAL; //resize the component horizontally
	    // add the CE button
	    gridBag.setConstraints(ce, Constr3);
	    add(ce);
	    ce.addActionListener(btnHandler);
	
	    // constraints the clear button
	    GridBagConstraints Constr4 = new GridBagConstraints();
	    Constr4.gridx = 3; //starts at fourth column
	    Constr4.gridy = 1; //second row
	
	    Constr4.gridwidth = 2;
	    Constr4.gridheight = 1;
	    Constr4.fill = GridBagConstraints.BOTH; //make the component fill its display area entirely
	    Constr4.insets = new Insets(3, 3, 3, 3);
	
	    // add the clear button
	    gridBag.setConstraints(clear, Constr4);
	    add(clear);
	    clear.addActionListener(btnHandler);
	
	    //add the numerical pad
	    for(int i = 1; i<=4;i++)
	    {
	      for(int j=1;j<=5;j++)
	      {
	
	          GridBagConstraints constrNum = new GridBagConstraints();
	          constrNum.gridx = j-1; //column number
	          constrNum.gridy = i+1; //row number
	          constrNum.gridwidth = 1;
	          constrNum.gridheight = 1;
	          constrNum.fill = GridBagConstraints.BOTH;
	          //determine the space between the component and the edges of its display area
	          constrNum.insets = new Insets(3, 3, 3, 3); //inset from the top,left,bottom, right
	
	          btn[index] = new JButton(lblName[index]);
	
	          gridBag.setConstraints(btn[index], constrNum);
	          add(btn[index]);
	          btn[index].addActionListener(btnHandler);
	          index = index+1;
	          
	
		    }
	     }
	    
	  

  } // constructor
    
    // Method to check if input is numeric
    private boolean isNumeric(String str)
	  {
		  if (str.equalsIgnoreCase(""))
		  {
			  return true;
		  }
		  else
		  {
			  try 
			  {
				  double num = Double.parseDouble(str);
			  }
			  catch (NumberFormatException nfe)
			  {
				  return false;
			  }
			  
			  return true;
		  }
		 
	  }
    
    private class TextHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			String str = tDisplay.getText();
			
			if (isNumeric(str) == false)
			{
				tDisplay.setText("Error");
			}
			else
			{
				// Event handler for hitting "Enter" in the textbox
				if (event.getSource() == tDisplay)
				{
					try
					{
						double num;
						num2 = Double.parseDouble(str);
						
						if (function[0] == true)
						{
							num = num1 + num2;
							tDisplay.setText(Double.toString(num));
						}
						else if (function[1] == true)
						{
							num = num1 - num2;
							tDisplay.setText(Double.toString(num));
						}
						else if (function[2] == true)
						{
							num = num1 * num2;
							tDisplay.setText(Double.toString(num));
						}
						else if (function[3] == true)
						{
							if (num2 != 0)
							{
								num = num1 / num2;
								tDisplay.setText(Double.toString(num));
							}
							else
							{
								tDisplay.setText("Error");
							}
							
						}
						else if (function[4] == true)
						{
							num = Math.pow(num1, num2);
							tDisplay.setText(Double.toString(num));
						}
						else if (function[5] == true)
						{
							if (num2 != 0)
							{
								num = num1 % num2;
								tDisplay.setText(Double.toString(num));
							}
							else
							{
								tDisplay.setText("Error");
							}
						}
						else
						{
							tDisplay.setText(str);
						}
						
						for (int i = 0; i < function.length; i++)
							function[i] = false;
					}
					catch (Exception e)
					{
						tDisplay.setText("");
					}
				}
			}
		}
    	
    }
    
  private class ButtonHandler implements ActionListener {
	  
	@Override
	public void actionPerformed(ActionEvent event) {
		
		String str = tDisplay.getText();
		
		// Event handler for backspace button
		if (event.getSource() == bsp)
		{
			try
			{
				str = str.substring(0, str.length() - 1);
				tDisplay.setText(str);
				tDisplay.requestFocus();
			}
			catch (Exception e)
			{
				tDisplay.setText("Error");
			}
		
		}
		
		// Event handler for clear error button
		else if (event.getSource() == ce)
		{
			try
			{
				if (str.equalsIgnoreCase("Error"))
				{
					tDisplay.setText("");
					tDisplay.requestFocus();
				}
				else
				{
					tDisplay.requestFocus();
				}
			}
			catch (Exception e)
			{
				tDisplay.setText("Error");
			}
		}
		
		// Event handler for clear button
		else if (event.getSource() == clear)
			{
				try
				{
					tDisplay.setText("");
					tDisplay.requestFocus();
					
					num1 = 0;
					num2 = 0;
					
					for (int i = 0; i < function.length; i++)
					{
						function[i] = false;
					}
				}
				catch (Exception e)
				{
					tDisplay.setText("Error");
				}
			}
		else
		{
			// Event handler for non-digit input
			if (isNumeric(str) == false)
			{
				tDisplay.setText("Error");
			}
			else
			{
				
				// Event handling for number buttons
				if (event.getSource() == btn[0])
				{
					str += lblName[0];
					tDisplay.setText(str);
				}
				
				if (event.getSource() == btn[1])
				{
					str += lblName[1];
					tDisplay.setText(str);
				}
				
				if (event.getSource() == btn[2])
				{
					str += lblName[2];
					tDisplay.setText(str);
				}
				
				if (event.getSource() == btn[5])
				{
					str += lblName[5];
					tDisplay.setText(str);
				}
				
				if (event.getSource() == btn[6])
				{
					str += lblName[6];
					tDisplay.setText(str);
				}
				
				if (event.getSource() == btn[7])
				{
					str += lblName[7];
					tDisplay.setText(str);
				}
				
				if (event.getSource() == btn[10])
				{
					str += lblName[10];
					tDisplay.setText(str);
				}
				
				if (event.getSource() == btn[11])
				{
					str += lblName[11];
					tDisplay.setText(str);
				}
				
				if (event.getSource() == btn[12])
				{
					str += lblName[12];
					tDisplay.setText(str);
				}
				
				if (event.getSource() == btn[15])
				{
					str += lblName[15];
					tDisplay.setText(str);
				}
				
				// Event handler for +/- button
				if (event.getSource() == btn[16])
				{
						
					try
					{
						double num = Double.parseDouble(str);
						
						if (num != 0)
						{
							num = num * (-1);
							tDisplay.setText(Double.toString(num));
							tDisplay.requestFocus();
						}
					}
					catch (Exception e)
					{
						tDisplay.setText("Error");
					}
					

				}
				
				// Event handler for . button
				if (event.getSource() == btn[17])
				{
					try
					{
						if (str.substring(0).equalsIgnoreCase(""))
						{
							str = "0" + lblName[17];
							tDisplay.setText(str);
							tDisplay.requestFocus();
						}
						else if (str.substring(str.length() - 1, str.length()).equalsIgnoreCase("."))
						{
							str += "";
							tDisplay.setText(str);
							tDisplay.requestFocus();
						}
						else
						{
							str += lblName[17];
							tDisplay.setText(str);
							tDisplay.requestFocus();
						}
					}
					catch (Exception e)
					{
						tDisplay.setText("Error");
					}
					
				}
								
				// Event handler for 1/x button
				if (event.getSource() == btn[14])
				{
					try
					{
						double num = Double.parseDouble(str);
						if (num != 0)
						{
							num = 1 / num;
							tDisplay.setText(Double.toString(num));
							tDisplay.requestFocus();
						}
						else
						{
							tDisplay.setText("Error");
						}
						
					}
					catch (NumberFormatException nfe)
					{
						tDisplay.setText("Error");
					}
				}
				
				// Event handler for + button
				if (event.getSource() == btn[18])
				{
					try
					{
						num1 = Double.parseDouble(str);
						tDisplay.setText("");
						tDisplay.requestFocus();
						function[0] = true;
						for (int i = 1; i < function.length; i++)
							function[i] = false;
					}
					catch (Exception e)
					{
						tDisplay.setText("Error");
					}
					
				}
				
				// Event handler for - button
				if (event.getSource() == btn[13])
				{
					try 
					{
						num1 = Double.parseDouble(str);
						tDisplay.setText("");
						tDisplay.requestFocus();
						function[1] = true;
						for (int i = 0; i < 1; i++)
							function[i] = false;
						for (int i = 2; i < function.length; i++)
							function[i] = false;
					}
					catch (Exception e)
					{
						tDisplay.setText("Error");
					}
				}
				
				// Event handler for * button
				if (event.getSource() == btn[8])
				{
					try
					{
						num1 = Double.parseDouble(str);
						tDisplay.setText("");
						tDisplay.requestFocus();
						function[2] = true;
						for (int i = 0; i < 2; i++)
							function[i] = false;
						for (int i = 3; i < function.length; i++)
							function[i] = false;
					}
					catch (Exception e)
					{
						tDisplay.setText("Error");
					}
				}
				
				// Event handler for / button
				if (event.getSource() == btn[3])
				{
					try
					{
						num1 = Double.parseDouble(str);
						tDisplay.setText("");
						tDisplay.requestFocus();
						function[3] = true;
						for (int i = 0; i < 3; i++)
							function[i] = false;
						for (int i = 4; i < function.length; i++)
							function[i] = false;
					}
					catch (Exception e)
					{
						tDisplay.setText("Error");
					}
				}
				
				// Event handler for exp button
				if (event.getSource() == btn[4])
				{
					try
					{
						num1 = Double.parseDouble(str);
						tDisplay.setText("");
						tDisplay.requestFocus();
						function[4] = true;
						for (int i = 0; i < 4; i++)
							function[i] = false;
						for (int i = 5; i < function.length; i++)
							function[i] = false;
					}
					catch (Exception e)
					{
						tDisplay.setText("Error");
					}
				}
				
				// Event handler for % button
				if (event.getSource() == btn[9])
				{
					try
					{
						num1 = Double.parseDouble(str);
						tDisplay.setText("");
						tDisplay.requestFocus();
						function[5] = true;
						for (int i = 0; i < 5; i++)
							function[i] = false;
					}
					catch (Exception e)
					{
						tDisplay.setText("Error");
					}
				}
				
				// Event handler for = button
				if (event.getSource() == btn[19])
				{
					try
					{
						double num;
						num2 = Double.parseDouble(str);
						
						if (function[0] == true)
						{
							num = num1 + num2;
							tDisplay.setText(Double.toString(num));
						}
						else if (function[1] == true)
						{
							num = num1 - num2;
							tDisplay.setText(Double.toString(num));
						}
						else if (function[2] == true)
						{
							num = num1 * num2;
							tDisplay.setText(Double.toString(num));
						}
						else if (function[3] == true)
						{
							if (num2 != 0)
							{
								num = num1 / num2;
								tDisplay.setText(Double.toString(num));
							}
							else
							{
								tDisplay.setText("Error");
							}
							
						}
						else if (function[4] == true)
						{
							num = Math.pow(num1, num2);
							tDisplay.setText(Double.toString(num));
						}
						else if (function[5] == true)
						{
							if (num2 != 0)
							{
								num = num1 % num2;
								tDisplay.setText(Double.toString(num));
							}
							else
							{
								tDisplay.setText("Error");
							}
						}
						else
						{
							tDisplay.setText(str);
						}
						
						for (int i = 0; i < function.length; i++)
							function[i] = false;
						
						tDisplay.requestFocus();
					}
					catch (Exception e)
					{
						tDisplay.setText("");
						tDisplay.requestFocus();
					}
				}
				
			}
		}
				
	}
	  
  }
  
  public static void main(String[] args) {

	    // Apply nimbus look and feel
	  	try
  		{
	  		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	  
		Calculator calc = new Calculator();
		calc.setTitle("SETCalculator");
		calc.setSize(300,300);
		calc.setVisible(true);
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


} //end of Calculator


