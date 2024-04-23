package GUIExample;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingScreen extends JFrame {

    private Timer timer;

    public LoadingScreen() {
        setTitle("Loading");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);

        JLabel loadingLabel = new JLabel();
        ImageIcon loadingIcon = new ImageIcon("images/loading.gif");
        loadingLabel.setIcon(loadingIcon);

        JLabel textLabel = new JLabel("Shuffling...");
        textLabel.setFont(new Font("Arial", Font.BOLD, 18));
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);

        contentPane.add(loadingLabel, BorderLayout.CENTER);
        contentPane.add(textLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public void showLoadingScreen() {
        setVisible(true);
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the loading screen after 3 seconds
                GUIExample guiExample = new GUIExample();
                guiExample.run();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
