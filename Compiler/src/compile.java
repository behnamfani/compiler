import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.PanelUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;



public class compile {
	
	static JPanel Panel = new JPanel();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Compiler");
		JTextArea textArea = new JTextArea();
		JTextArea textArea1 = new JTextArea();
		JButton btnSend = new JButton("Compile");
		Font font = new Font("MoolBoran", Font.BOLD, 20);
		JLabel a = new JLabel("Code section");
		JLabel b = new JLabel("Answer section");
		a.setFont(font);
		a.setBounds(900, 20, 300, 50);
		a.setForeground(Color.WHITE);
		b.setFont(font);
		b.setBounds(220,20,300,50);
		b.setForeground(Color.white);
		Panel.add(b,BorderLayout.NORTH);
		Panel.add(a , BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 1500, 1000);
		frame.setVisible(true);
		
	
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Panel.setLayout(null);
		
		textArea.setBounds(100, 100, 500, 700);
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(595, 80, 700, 700);
		Panel.add(sp);
		textArea.setFont(font);
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		textArea1.setBounds(50, 100, 500, 700);
		JScrollPane sp1 = new JScrollPane(textArea1);
		sp1.setBounds(50, 80, 500, 700);
		textArea1.setFont(font);
		textArea1.setEditable(false);
		textArea1.setLineWrap(true);
		textArea1.setWrapStyleWord(true);
		Panel.add(sp1);
		
		btnSend.setForeground(Color.WHITE);
		btnSend.setFont(new Font("Castellar", Font.BOLD, 15));
		btnSend.setBackground(Color.GRAY);
		btnSend.setBounds(880, 800, 120, 70);
		Panel.add(btnSend);
		Panel.setBackground(Color.BLACK);
		frame.add(Panel);
		Highlighter h = textArea.getHighlighter();
		
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				String[] read;
				String str1 = null ;
				h.removeAllHighlights();
				str1 = textArea.getText();
				System.out.println(str1.length());
				if(str1.length() > 0){
				int count=0 ;
				while(str1.charAt(count) == ' ')
					count++;
				String str = str1.substring(count);
				read = str.split("\\s+");	
				textArea1.setText(null);
				textArea1.append("Start to compile.... \n");
				
				parser pars = new parser();
				boolean b = pars.Parser(read);
				if(b == false && !parser.string.equals("missing"))
				{
					String error = parser.string;
					textArea1.setForeground(Color.red);
					textArea1.append(error);
					System.out.println(read[parser.highlight]);
					int x =0 ;
					boolean flag = true;
					for (int i = 0 ; i < str1.length() ; i++)
					{
						if(str1.charAt(i) == read[parser.highlight].charAt(0))
						{
							x = i+1;
							for(int j = 1 ; j < read[parser.highlight].length() ; j++)
								if( str1.charAt(x) == read[parser.highlight].charAt(j))
								{
									x++ ;
									System.out.print(str1.charAt(x));
								}
								else
									break;
						}
					}
					int l1 = x - read[parser.highlight].length();
					int l2 = read[parser.highlight].length();
					try {
						h.addHighlight(l1, l1+l2, DefaultHighlighter.DefaultPainter);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if ( b == false && parser.string.equals("missing"))
				{
					textArea1.setForeground(Color.red);
					if(parser.check1 > parser.check2)
						textArea1.append("Error : missing )");
					else if(parser.check1 < parser.check2)
						textArea1.append("Error : missing (");
					else
						textArea1.append("missing }");
				}
				if (b == true)
				{
					textArea1.setForeground(Color.green);
					textArea1.append("Accepted");
				}	
				
				}	
				else
					textArea1.setText("Empty");
			}
		});
	}
}

