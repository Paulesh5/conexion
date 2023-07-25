import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Imprimir {

    static final String DB_URL="jdbc:mysql://localhost/UNIVERSIDAD";
    static final String USER="root";
    static final String PASS="root_bas3";
    static final String QUERY= "SELECT * FROM ESTUDIANTES";
    private JButton imprimirButton;
    private JPanel rootPanel;
    private JTextField textoPanel;

    public Imprimir() {
        try (
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs= stmt.executeQuery(QUERY);
        ){while(rs.next()){
            System.out.println("id.: "+rs.getInt("id"));
            System.out.println("nombre: "+rs.getString("nombre"));
        }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    imprimirButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            textoPanel.setText("Hola");
        }
    });
}
    public static void main(String[] args) {
        JFrame frame = new JFrame("Imprimir");
        frame.setContentPane(new Imprimir().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
