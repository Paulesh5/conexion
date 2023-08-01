import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Imprimir {

    static final String DB_URL="jdbc:mysql://localhost/UNIVERSIDAD";
    static final String USER="root";
    static final String PASS="root_bas3";
    static final String QUERY= "SELECT * FROM ESTUDIANTES";
    //static final String QUERY= "SELECT * FROM ESTUDIANTES where id=202020211";
    private JButton eliminarButton;
    private JPanel rootPanel;
    private JTextField textoPanel;
    private JButton registrarButton;
    private JTextField idIngreso;
    private JTextField nombreIngreso;
    private JTextField edadIngreso;
    private JTextField ciudadIngreso;
    private JLabel id;
    private JTextField cedulaIngreso;
    private JLabel nombre;
    private JLabel edad;
    private JLabel ciudad;
    private JLabel cedula;
    private JLabel password;
    private JTextField claveIngreso;
    private JTextField idIngreso2;
    private JTextField nombreIngreso2;
    private JLabel id2;
    private JLabel nombre2;
    private JButton actualizarButton;

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
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textoPanel.getText());
                String query = "DELETE FROM ESTUDIANTES WHERE id = " + id;
                try (
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement();
                ) {
                    stmt.executeUpdate(query);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idIngreso.getText());
                String nombre = nombreIngreso.getText();
                int edad = Integer.parseInt(edadIngreso.getText());
                String ciudad = ciudadIngreso.getText();
                String cedula = cedulaIngreso.getText();
                String clave = claveIngreso.getText();
                String query = "INSERT INTO ESTUDIANTES VALUES (" + id + ", '" + nombre + "', " + edad + ", '" + ciudad + "', '" + cedula + "', '" + clave + "')";
                try (
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement();
                ) {
                    stmt.executeUpdate(query);
                    System.out.println("Nuevo estudiante registrado correctamente.");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idIngreso2.getText());
                String nombre = nombreIngreso2.getText();
                String query = "UPDATE ESTUDIANTES SET NOMBRE = '" + nombre + "' WHERE ID = '" + id + "'";
                try (
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement();
                ) {
                    int rowsAffected = stmt.executeUpdate(query);
                    if (rowsAffected > 0) {
                        System.out.println("Nombre del estudiante actualizado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún estudiante con el ID especificado.");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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
