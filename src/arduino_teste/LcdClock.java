package arduino_teste;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.fazecast.jSerialComm.SerialPort;
import java.util.concurrent.TimeUnit;
import java.util.Scanner; 

public class LcdClock {
	Scanner scan = new Scanner(System.in);
	static SerialPort chosenPort;
	static JList list;
	static DefaultListModel model;
	static JList list2;
	static DefaultListModel model2;
	public static void main(String[] args) {
		
		// create and configure the window
		JFrame window = new JFrame();
		window.setTitle("Arduino Do Mauricio");
		window.setSize(550,450);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		// create a drop-down box and connect button, then place them at the top of the window
		
		JComboBox<String> portList = new JComboBox<String>();
		JComboBox<String> portList1 = new JComboBox<String>();
		JComboBox<String> portList2 = new JComboBox<String>();
		JComboBox<String> portList3 = new JComboBox<String>();
		final Box boxButton = Box.createVerticalBox();
		final Box boxdropdowns = Box.createVerticalBox();
		JButton connectButton = new JButton("Connect");
		JButton sendButton = new JButton("<html><p style='Color: #0099cc'>	Enviar </p></html>");
		JButton sendButton2 = new JButton("<html><p style='Color: #0099cc'>	Enviar </p></html>");
		JButton sendButton3 = new JButton("<html><p style='Color: #0099cc'>	Enviar </p></html>");
		JButton writeBTN = new JButton("<html><p style='Color: #0099cc'>  Write  </p></html>");
		JButton readBTN = new JButton("<html><p style='Color: #0099cc'>  Read   </p></html>");
		JButton zBTN = new JButton("<html><p style='Color: #0099cc'>      Z      </p></html>");
		JButton addressBTN = new JButton("<html><p style='Color: #0099cc'>Address</p></html>");
		JButton dataBTN = new JButton("<html><p style='Color: #0099cc'>Data</p></html>");
		JButton binary1BTN = new JButton("<html><p style='Color: #0099cc'>Enviar</p></html>");
		JButton binary2BTN = new JButton("<html><p style='Color: #0099cc'>Enviar</p></html>");
		
		connectButton.setBackground(Color.DARK_GRAY);
		connectButton.setForeground(Color.CYAN);
		connectButton.setToolTipText("Faça click para conectar ao arduino");
		
		sendButton.setBackground(Color.DARK_GRAY);
		sendButton.setForeground(Color.GRAY);
		sendButton.setToolTipText("Enviar.");
		
		sendButton2.setBackground(Color.DARK_GRAY);
		sendButton2.setForeground(Color.GRAY);
		sendButton2.setToolTipText("Enviar.");
		
		sendButton3.setBackground(Color.DARK_GRAY);
		sendButton3.setForeground(Color.GRAY);
		sendButton3.setToolTipText("Enviar.");
		
		binary1BTN.setBackground(Color.DARK_GRAY);
		binary1BTN.setForeground(Color.GRAY);
		binary1BTN.setToolTipText("Enviar.");
		
		binary2BTN.setBackground(Color.DARK_GRAY);
		binary2BTN.setForeground(Color.GRAY);
		binary2BTN.setToolTipText("Enviar.");
		
		writeBTN.setBackground(Color.DARK_GRAY);
		writeBTN.setForeground(Color.GRAY);
		writeBTN.setToolTipText("Click this button to disable the middle button.");
		
		readBTN.setBackground(Color.DARK_GRAY);
		readBTN.setForeground(Color.GRAY);
		readBTN.setToolTipText("Click this button to disable the middle button.");
		
		zBTN.setBackground(Color.DARK_GRAY);
		zBTN.setForeground(Color.GRAY);
		zBTN.setToolTipText("Click this button to disable the middle button.");
		
		addressBTN.setBackground(Color.DARK_GRAY);
		addressBTN.setForeground(Color.GRAY);
		addressBTN.setToolTipText("Click this button to disable the middle button.");
		
		dataBTN.setBackground(Color.DARK_GRAY);
		dataBTN.setForeground(Color.GRAY);
		dataBTN.setToolTipText("Click this button to disable the middle button.");
		
		portList.setBackground(Color.DARK_GRAY);
		portList.setForeground(Color.GRAY);
		
		portList1.setBackground(Color.DARK_GRAY);
		portList1.setForeground(Color.GRAY);
		
		portList2.setBackground(Color.DARK_GRAY);
		portList2.setForeground(Color.GRAY);
		
		portList3.setBackground(Color.DARK_GRAY);
		portList3.setForeground(Color.GRAY);
		
		
		
		
		JPanel topPanel = new JPanel();
		JPanel downPanel = new JPanel();
		JPanel eastPanel =new JPanel();
		JPanel centerPanel =new JPanel();
		JTextField text  = new JTextField(8);
		JTextField text2  = new JTextField(8);
		
		topPanel.setBackground(Color.lightGray);
		downPanel.setBackground(Color.lightGray);
		eastPanel.setBackground(Color.lightGray);
		centerPanel.setBackground(Color.lightGray);
		model = new DefaultListModel();
	    list = new JList(model);
	    model2 = new DefaultListModel();
	    list2 = new JList(model2);
	    JScrollPane pane = new JScrollPane(list);
	    JScrollPane pane2 = new JScrollPane(list2);
	    pane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
	    pane2.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
	    
		//tres dropdown
	    int randomvar=0;
	    
	    while(randomvar!=16){
	    	portList1.addItem(""+randomvar);
	    	randomvar++;
	    }
		
	    //port2
		portList2.addItem("LOAD_A");
		portList2.addItem("ADD");
		portList2.addItem("SUB");
		portList2.addItem("STORE");
		portList2.addItem("GOTO");
		portList2.addItem("GOTOIFCARRY");
		portList2.addItem("GOTO_PAGE");
		portList2.addItem("null");
		portList2.addItem("D_IN");
		portList2.addItem("D_OUT");
		portList2.addItem("A_OUT");
		portList2.addItem("OUT");
		portList2.addItem("null");
		portList2.addItem("null");
		portList2.addItem("HALT");
		
		portList3.addItem("0000");
		portList3.addItem("0001");
		portList3.addItem("0010");
		portList3.addItem("0011");
		portList3.addItem("0100");
		portList3.addItem("0101");
		portList3.addItem("0110");
		portList3.addItem("0111");
		portList3.addItem("1000");
		portList3.addItem("1001");
		portList3.addItem("1010");
		portList3.addItem("1011");
		portList3.addItem("1100");
		portList3.addItem("1101");
		portList3.addItem("1110");
		portList3.addItem("1111");

		
		topPanel.add(text);
		//tres dropdown;
		//main
		topPanel.add(sendButton);
		topPanel.add(portList);
		topPanel.add(connectButton);
		//main;
		//seconds button
		writeBTN.setPreferredSize(new Dimension(100, 100));
		readBTN.setPreferredSize(new Dimension(100, 100));
		zBTN.setPreferredSize(new Dimension(100, 100));
		addressBTN.setPreferredSize(new Dimension(100, 100));
		
		
		boxButton.add(writeBTN);
		boxButton.add(readBTN);
		boxButton.add(zBTN);
		boxButton.add(addressBTN);
		boxButton.add(dataBTN);
		
		boxdropdowns.add(text2);
		boxdropdowns.add(portList1);
		boxdropdowns.add(sendButton2);
		boxdropdowns.add(portList2);
		boxdropdowns.add(portList3);
		boxdropdowns.add(binary1BTN);
		//seconds button;
		//list
		eastPanel.add(boxdropdowns);
		
		//list;
		downPanel.add(boxButton);
		
		centerPanel.add(pane);
		centerPanel.add(pane2);
		model.addElement("***************************************************");
		model2.addElement("***************************************************");
		window.add(topPanel, BorderLayout.NORTH);
		window.add(downPanel, BorderLayout.WEST);
		window.add(eastPanel, BorderLayout.CENTER);
		window.add(centerPanel, BorderLayout.SOUTH);
		// populate the drop-down box
		System.out.println("flag1");
		SerialPort[] portNames = SerialPort.getCommPorts();
		for(int i = 0; i < portNames.length; i++)
			portList.addItem(portNames[i].getSystemPortName());
		
		// configure the connect button and use another thread to send data
		connectButton.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent arg0) {
				System.out.println("flag2");
				if(connectButton.getText().equals("Connect")) {
					// attempt to connect to the serial port
					System.out.println("flag1");
					chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
					chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
					if(chosenPort.openPort()) {
						
						connectButton.setText("Disconnect");
						portList.setEnabled(false);
						System.out.print("10");
						
						// create a new thread for sending data to the arduino
						sendButton.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
										
										// wait after connecting, so the bootloader can finish
										try {Thread.sleep(100); } catch(Exception e1) {}
										int var_whatever=0;
										var_whatever++;
										 
										String textFieldValue = text.getText();
										
										if(textFieldValue.length()==8){
										// enter an infinite loop that sends text to the arduino
										PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
											
											
											output.print(""+ textFieldValue);
											output.flush();
											
											
											model.addElement("-"+textFieldValue);
											model2.addElement("-"+textFieldValue);
										}else{
											model.addElement("-Erro");
											model2.addElement("-Erro");	
										}
										System.out.println("coisa enviada:"+textFieldValue);
								
								
							}
						});
						sendButton2.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								String textFieldValue = null ;
								 //array binario
								 String[] anArray = new String[16];
								 anArray[0] = "0000";
								 anArray[1] = "0001";
								 anArray[2] = "0010";
								 anArray[3] = "0011";
								 anArray[4] = "0100";
								 anArray[5] = "0101";
								 anArray[6] = "0110";
								 anArray[7] = "0111";
								 anArray[8] = "1000";
								 anArray[9] = "1001";
								 anArray[10] = "1010";
								 anArray[11] = "1011";
								 anArray[12] = "1100";
								 anArray[13] = "1101";
								 anArray[14] = "1110";
								 anArray[15] = "1111";
								 //array binario
								textFieldValue = text2.getText();
									if(textFieldValue.length()==4){
										int var_whatever=0;
										// enter an infinite loop that sends text to the arduino
										PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
										
											var_whatever=0;
										 	var_whatever++;
										 	textFieldValue = text2.getText();
										 	output = new PrintWriter(chosenPort.getOutputStream());
											output.print(""+ textFieldValue);
											output.flush();
											model.addElement("-"+textFieldValue);
											model2.addElement("-"+textFieldValue);
											
											
											
											
											
											
											output = new PrintWriter(chosenPort.getOutputStream());
											String varName = (String)portList1.getSelectedItem();
											int varRandom=Integer.parseInt(varName);
											textFieldValue=""+anArray[varRandom];
											System.out.println("varName:"+varName);
											System.out.println("\n array:"+textFieldValue);
											output.print(""+ textFieldValue);
											output.flush();
											model.addElement("-"+ textFieldValue);
											model2.addElement("-"+ textFieldValue);
											
										}else{
											model.addElement("-Erro");
											model2.addElement("-Erro");
										}				
								
								
							}
						});
						binary1BTN.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								String textFieldValue = null ;
								
								//array binario
								
								 String[] anArray = new String[16];
								 anArray[0] = "0000";
								 anArray[1] = "0001";
								 anArray[2] = "0010";
								 anArray[3] = "0011";
								 anArray[4] = "0100";
								 anArray[5] = "0101";
								 anArray[6] = "0110";
								 anArray[7] = "0111";
								 anArray[8] = "1000";
								 anArray[9] = "1001";
								 anArray[10] = "1010";
								 anArray[11] = "1011";
								 anArray[12] = "1100";
								 anArray[13] = "1101";
								 anArray[14] = "1110";
								 anArray[15] = "1111";
								 //array binario
								textFieldValue = text2.getText();
									if(textFieldValue.length()==4){
										int var_whatever=0;
										// enter an infinite loop that sends text to the arduino
										PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
										
										
											output = new PrintWriter(chosenPort.getOutputStream());
											String dropdowntext=portList2.getSelectedItem().toString();;
											int  varint = (int)portList2.getSelectedIndex();
											int varRandom = varint+1;
											textFieldValue=""+anArray[varRandom];
											System.out.println("varName:"+varint);
											System.out.println("\n array:"+textFieldValue);
											output.print(""+ textFieldValue);
											output.flush();
											model.addElement("-"+ textFieldValue);
											model2.addElement("-"+ dropdowntext);
											
										
											output = new PrintWriter(chosenPort.getOutputStream());
											 varint = (int)portList3.getSelectedIndex();
											varRandom = varint;
											textFieldValue=""+anArray[varRandom];
											System.out.println("varName:"+varint);
											System.out.println("\n array:"+textFieldValue);
											output.print(""+ textFieldValue);
											output.flush();
											model.addElement("-"+ textFieldValue);
											model2.addElement("-"+ textFieldValue);
											
										}else{
											model.addElement("-Erro");
											model2.addElement("-Erro");
										}				
								
								
							}
						});
						writeBTN.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.print("10");
								
								// wait after connecting, so the bootloader can finish
								try {Thread.sleep(100); } catch(Exception e1) {}
								int var_whatever=0;
								
								// enter an infinite loop that sends text to the arduino
								PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
								
								 var_whatever++;
								 String textFieldValue = "/1write";
								output.print(""+ textFieldValue);
								output.flush();
								
								model.addElement("-"+textFieldValue);
								model2.addElement("-Write");
								}
						});
						readBTN.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.print("10");
								
										// wait after connecting, so the bootloader can finish
										try {Thread.sleep(100); } catch(Exception e1) {}
										int var_whatever=0;
										// enter an infinite loop that sends text to the arduino
										PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
										
											 var_whatever++;
											 String textFieldValue = "/1read";
											output.print(""+ textFieldValue);
											output.flush();
											
											model.addElement("-"+textFieldValue);
											model2.addElement("-Read");				
								
								
							}
						});
						zBTN.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.print("10");
								
										// wait after connecting, so the bootloader can finish
										try {Thread.sleep(100); } catch(Exception e1) {}
										int var_whatever=0;
										// enter an infinite loop that sends text to the arduino
										PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
										
											 var_whatever++;
											 String textFieldValue = "/1z";
											output.print(""+ textFieldValue);
											output.flush();
											
											model.addElement("-"+textFieldValue);
											model2.addElement("-Z");				
								
								
							}
						});
						addressBTN.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.print("10");
								
										// wait after connecting, so the bootloader can finish
										try {Thread.sleep(100); } catch(Exception e1) {}
										int var_whatever=0;
										// enter an infinite loop that sends text to the arduino
										PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
										
											 var_whatever++;
											 String textFieldValue = "/1adr";
											output.print(""+ textFieldValue);
											output.flush();
											
											model.addElement("-"+textFieldValue);
											model2.addElement("-Address");				
								
								
							}
						});
						dataBTN.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.print("10");
								
										// wait after connecting, so the bootloader can finish
										try {Thread.sleep(100); } catch(Exception e1) {}
										int var_whatever=0;
										// enter an infinite loop that sends text to the arduino
										PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
										
											 var_whatever++;
											 String textFieldValue = "/1dat";
											output.print(""+ textFieldValue);
											output.flush();
											
											model.addElement("-"+textFieldValue);
											model2.addElement("-Data");				
								
								
							}
						});
					}
					
				} else {
					// disconnect from the serial port
					chosenPort.closePort();
					portList.setEnabled(true);
					connectButton.setText("Connect");
				}
			}
		});
		
		// show the window
		window.setVisible(true);
	}

}