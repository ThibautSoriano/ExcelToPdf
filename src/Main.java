import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sun.security.action.OpenFileInputStreamAction;

public class Main {
    
    
    public static void openFileChooser(final JLabel textLabel) {
        final JFrame frame = new JFrame("JFileChooser Popup");
        
        Container contentPane = frame.getContentPane();
        
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setControlButtonsAreShown(true);
        contentPane.add(fileChooser, BorderLayout.CENTER);
        
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
              JFileChooser theFileChooser = (JFileChooser) actionEvent
                  .getSource();
              String command = actionEvent.getActionCommand();
              if (command.equals(JFileChooser.APPROVE_SELECTION)) {
                File selectedFile = theFileChooser.getSelectedFile();
                
                textLabel.setText(selectedFile.getParent()+File.separator+selectedFile.getName());
                frame.dispose();
                
              }
            }
          };
          
          fileChooser.addActionListener(actionListener);

          frame.pack();
          frame.setVisible(true);


    }

    public static void main(String[] args) {
        
        JFrame mainFrame = new JFrame("Main");
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 200);
        
        JButton b2 = new JButton("Middle button");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        //b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setText("Choose an excel file");
        
        final JLabel fileNameLabel = new JLabel("");
        
        
        b2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               openFileChooser(fileNameLabel);
            }
        });
        
        
        final JLabel imageNameLabel = new JLabel("");
        
        JButton b3 = new JButton("Choose an image file");
        b3.setVerticalTextPosition(AbstractButton.BOTTOM);
        //b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b3.setText("Choose an image");
        
        b3.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               openFileChooser(imageNameLabel);
            }
        });
        
        
        JButton b4 = new JButton("Export to pdf");
        b4.setVerticalTextPosition(AbstractButton.BOTTOM);
        //b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b4.setText("Export to pdf");
        
        
        Container c = mainFrame.getContentPane();
        c.setLayout(new GridLayout(3,2));
        c.add(b2);
        mainFrame.getContentPane().add(fileNameLabel);
        
        c.add(b3);
        c.add(imageNameLabel);
        
        c.add(b4);
        
        
        
       
        
        
        
       
        
        //mainFrame.pack();
        mainFrame.setVisible(true);

    }

}
