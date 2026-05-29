import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.Random;

public class AttackModule {

    private static volatile boolean attackEnabled = false;
    private static JTextArea logArea;

    public static void main(String[] args) {

        JFrame frame = new JFrame("⚠️ Attack Module");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton toggleBtn = new JButton("Enable Attack");
        toggleBtn.setBackground(Color.RED);
        toggleBtn.setForeground(Color.WHITE);

        logArea = new JTextArea();
        logArea.setEditable(false);

        toggleBtn.addActionListener(e -> {
            attackEnabled = !attackEnabled;

            if (attackEnabled) {
                toggleBtn.setText("Disable Attack");
                toggleBtn.setBackground(Color.GREEN);
                log(" Attack ENABLED");
            } else {
                toggleBtn.setText("Enable Attack");
                toggleBtn.setBackground(Color.RED);
                log("Attack DISABLED");
            }
        });

        frame.add(toggleBtn, BorderLayout.NORTH);
        frame.add(new JScrollPane(logArea), BorderLayout.CENTER);

        frame.setVisible(true);

        //  Start server in separate thread (IMPORTANT FIX)
        new Thread(AttackModule::startServer).start();
    }

    private static void log(String msg) {
        SwingUtilities.invokeLater(() -> logArea.append(msg + "\n"));
    }

    // ================= SERVER =================
    private static void startServer() {
        try {
            ServerSocket server = new ServerSocket(5000);
            log("📡 Attack Module running on port 5000...");

            while (true) {
                Socket sender = server.accept();
                handleConnection(sender);
            }

        } catch (Exception e) {
            log("❌ Server Error: " + e.getMessage());
        }
    }

    private static void handleConnection(Socket senderSocket) {
        new Thread(() -> {
            try {
                ObjectInputStream in = new ObjectInputStream(senderSocket.getInputStream());

                String encrypted = (String) in.readObject();
                String signature = (String) in.readObject();
                byte[] sharedKey = (byte[]) in.readObject();
                byte[] publicKeyBytes = (byte[]) in.readObject();

                log("\n📥 Message intercepted");

                if (attackEnabled) {
                    log(" Tampering message...");
                    encrypted = tamper(encrypted);
                } else {
                    log(" Passing message unchanged");
                }

                // Forward to receiver
                Socket forward = new Socket("127.0.0.1", 6000);
                ObjectOutputStream out = new ObjectOutputStream(forward.getOutputStream());

                out.writeObject(encrypted);
                out.writeObject(signature);
                out.writeObject(sharedKey);
                out.writeObject(publicKeyBytes);

                senderSocket.close();
                forward.close();

                log("📡 Forwarded to Receiver");

            } catch (Exception e) {
                log("Connection Error: " + e.getMessage());
            }
        }).start();
    }

    private static String tamper(String data) {
        try {
            byte[] bytes = Base64.getDecoder().decode(data);
            Random rand = new Random();

            int index = rand.nextInt(bytes.length);
            bytes[index] ^= 0xFF;

            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            return data;
        }
    }
}