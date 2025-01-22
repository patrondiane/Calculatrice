import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatriceGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private double nombre1, nombre2, resultat;
    private String operation;

    public CalculatriceGUI() {
        // Créer la fenêtre
        setTitle("Calculatrice Basique");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Champ de texte pour afficher les résultats
        textField = new JTextField();
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        // Panneau pour les boutons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        // Créer les boutons
        String[] boutons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String texte : boutons) {
            JButton bouton = new JButton(texte);
            bouton.addActionListener(this);
            panel.add(bouton);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commande = e.getActionCommand();

        if (commande.charAt(0) >= '0' && commande.charAt(0) <= '9') {
            textField.setText(textField.getText() + commande);
        } else if (commande.charAt(0) == 'C') {
            textField.setText("");
            nombre1 = nombre2 = resultat = 0;
            operation = "";
        } else if (commande.charAt(0) == '=') {
            nombre2 = Double.parseDouble(textField.getText());
            switch (operation) {
                case "+":
                    resultat = nombre1 + nombre2;
                    break;
                case "-":
                    resultat = nombre1 - nombre2;
                    break;
                case "*":
                    resultat = nombre1 * nombre2;
                    break;
                case "/":
                    if (nombre2 != 0) {
                        resultat = nombre1 / nombre2;
                    } else {
                        textField.setText("Erreur");
                        return;
                    }
                    break;
            }
            textField.setText(String.valueOf(resultat));
        } else {
            if (!operation.isEmpty()) {
                return; // Si une opération est déjà sélectionnée, ne rien faire
            }
            operation = commande;
            nombre1 = Double.parseDouble(textField.getText());
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatriceGUI calculatrice = new CalculatriceGUI();
            calculatrice.setVisible(true);
        });
    }
}