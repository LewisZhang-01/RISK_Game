import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;


public class CommandPanel extends JPanel  {
	
	private static final long serialVersionUID = 1L;
	private static final int FONT_SIZE = 14;
	
	private JTextField commandField = new JTextField(); 
	private LinkedList<String> commandBuffer = new LinkedList<String>();
	
	//Display Command Pane Background Image
	private Image image = new ImageIcon("images/CommandPanelImage.jpg").getImage();
	protected void paintComponent(Graphics g) {  
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
	}
	
	CommandPanel () {
		class AddActionListener implements ActionListener {
			   public void actionPerformed(ActionEvent event)	{
				   synchronized (commandBuffer) {
					   commandBuffer.add(commandField.getText());
					   commandField.setText("");
					   commandBuffer.notify();
				   }
		           return;
			   }
		   }
		ActionListener listener = new AddActionListener();
		commandField.addActionListener(listener);
		commandField.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
		setLayout(new BorderLayout());
		//commandField.setBackground(new Color(230,153,63));
		commandField.setBorder(getBorder());
		commandField.setOpaque(false); 
		add(commandField, BorderLayout.CENTER);
		//Terminate program button.
		JButton button_close = new JButton("Close");
		button_close.setFont(new Font("Times New Roman", Font.BOLD, FONT_SIZE+2));
		//Keep track of the close button.
		button_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_close.setForeground(Color.RED);
				System.exit(0);
			}
		});
		button_close.setForeground(Color.BLACK);
		button_close.setOpaque(false);
		button_close.setBorderPainted(false);	
		add(button_close, BorderLayout.EAST);
		return;
	}

	public String getCommand() {
		String command;
		synchronized (commandBuffer) {
			while (commandBuffer.isEmpty()) {
				try {
					commandBuffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			command = commandBuffer.pop();
		}
		return command;
	}
	
}
