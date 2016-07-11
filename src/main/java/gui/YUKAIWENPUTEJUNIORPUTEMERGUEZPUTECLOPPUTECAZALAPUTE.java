package main.java.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.java.utils.FileType;
import main.java.utils.Internationalization;
import main.java.utils.Language;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class YUKAIWENPUTEJUNIORPUTEMERGUEZPUTECLOPPUTECAZALAPUTE {

    public JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    YUKAIWENPUTEJUNIORPUTEMERGUEZPUTECLOPPUTECAZALAPUTE window = new YUKAIWENPUTEJUNIORPUTEMERGUEZPUTECLOPPUTECAZALAPUTE();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * @throws ParseException 
     * @throws IOException 
     */
    public YUKAIWENPUTEJUNIORPUTEMERGUEZPUTECLOPPUTECAZALAPUTE() throws IOException, ParseException {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     * @throws ParseException 
     * @throws IOException 
     */
    private void initialize() throws IOException, ParseException {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        
        
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream dias = classLoader.getClass().getResourceAsStream("/languages/language_hu_HU.json");
        
        InputStreamReader z = new InputStreamReader(dias,Language.HU.getEncoding());
        JSONParser parser = new JSONParser();
        JSONObject map = (JSONObject) parser.parse(z);
        
        
        JLabel lblNewLabel = new JLabel((String) map.get("Previous"));
        panel.add(lblNewLabel);
        
        JButton btnPute = new JButton("PUTE");
        btnPute.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileChooser(FileType.EXCEL, null);
                
            }
        });
        frame.getContentPane().add(btnPute, BorderLayout.CENTER);
    }

    
    
    public static void openFileChooser(final FileType fileType,
            final JTextField field) {
        final JFrame frame = new JFrame(
                "PUTE YU KAIWEN PUTE");
        Container contentPane = frame.getContentPane();

        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setControlButtonsAreShown(true);
        contentPane.add(fileChooser, BorderLayout.CENTER);

        fileChooser.setAcceptAllFileFilterUsed(false);

//        FileFilter filter = new FileNameExtensionFilter(
//                fileType.getDescription(), fileType.getAcceptedExtensions());
//        fileChooser.setFileFilter(filter);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser theFileChooser = (JFileChooser) actionEvent
                        .getSource();
                String command = actionEvent.getActionCommand();
                if (command.equals(JFileChooser.APPROVE_SELECTION)) {
                    File selectedFile = theFileChooser.getSelectedFile();

                    field.setText(selectedFile.getAbsolutePath());

                    frame.dispose();
                } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
                    frame.dispose();
                }
            }
        };

        fileChooser.addActionListener(actionListener);

        frame.pack();
        frame.setVisible(true);
    }
    
}
