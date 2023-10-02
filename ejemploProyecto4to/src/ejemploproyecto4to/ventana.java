package ejemploproyecto4to;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ventana extends JFrame {

    JPanel panelInicioSesion, panelNuevoUsuario, panelclientes;
    JTextField txtusuario;
    JPasswordField txtcontra;
    usuario misusuarios[] = new usuario[10];

    public ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        misusuarios[0] = new usuario("admin", "123");
        misusuarios[1] = new usuario("otro", "321");

    }

    public void iniciarComponentes() {
        panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(null);
        this.getContentPane().add(panelInicioSesion);
        componentesInicioSesion();

        panelNuevoUsuario = new JPanel();
        panelNuevoUsuario.setLayout(null);
        this.getContentPane().add(panelNuevoUsuario);
        panelNuevoUsuario.setVisible(false);

        panelclientes = new JPanel();
        panelclientes.setLayout(null);
        this.getContentPane().add(panelclientes);
        panelclientes.setVisible(false);

    }

    public void componentesInicioSesion() {
        this.setTitle("Inicio de sesion");
        JLabel lblusuario = new JLabel("Ingrese su usuario");
        lblusuario.setBounds(50, 15, 150, 15);
        panelInicioSesion.add(lblusuario);

        JLabel lblcontra = new JLabel("Ingrese su contraseña");
        lblcontra.setBounds(50, 50, 150, 15);
        panelInicioSesion.add(lblcontra);

        txtusuario = new JTextField();
        txtusuario.setBounds(210, 15, 150, 20);
        panelInicioSesion.add(txtusuario);

        txtcontra = new JPasswordField();
        txtcontra.setBounds(210, 50, 150, 20);
        panelInicioSesion.add(txtcontra);

        JButton btniniciar = new JButton("Ingresar");
        btniniciar.setBounds(110, 150, 100, 30);
        panelInicioSesion.add(btniniciar);
        ActionListener iniciarSesion = new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                String usuario = txtusuario.getText();
                String contra = txtcontra.getText();
                buscarusuario(usuario, contra);
            }
        };

        btniniciar.addActionListener(iniciarSesion);

        //--------------------> BOTON INICIAR SESION
        JButton btnNuevoUsuario = new JButton("Registrar");
        btnNuevoUsuario.setBounds(250, 150, 100, 30);
        panelInicioSesion.add(btnNuevoUsuario);
        ActionListener nuevoUsuario = new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                componentesNuevoUsuario();
                panelInicioSesion.setVisible(false);
                panelNuevoUsuario.setVisible(true);
            }
        };

        btnNuevoUsuario.addActionListener(nuevoUsuario);

        panelInicioSesion.repaint();

    }

    public void buscarusuario(String usuario, String contra) {
        boolean encontrado = false;
        String nombre = "";
        for (int i = 0; i <= misusuarios.length - 1; i++) {
            if (misusuarios[i] != null) {
                if (misusuarios[i].getnombre().equals(usuario) && misusuarios[i].getcontra().equals(contra)) {
                    encontrado = true;
                    nombre = misusuarios[i].getnombre();
                    break;
                }
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, " Bienvenido " + nombre);
        } else {
            JOptionPane.showMessageDialog(null, "⚠ Datos Incorrectos ⚠");
        }

    }

    public void componentesNuevoUsuario() {
        this.setTitle("Crear nueva cuenta");
        JLabel nuevoNombre = new JLabel("Ingrese nombre de usuario: ");
        nuevoNombre.setBounds(80, 25, 170, 20);
        panelNuevoUsuario.add(nuevoNombre);

        JTextField txtnombre = new JTextField();
        txtnombre.setBounds(250, 25, 150, 20);
        panelNuevoUsuario.add(txtnombre);

        JLabel nuevacontra = new JLabel("Ingrese contraseña: ");
        nuevacontra.setBounds(80, 65, 170, 20);
        panelNuevoUsuario.add(nuevacontra);

        JTextField txtcontra = new JTextField();
        txtcontra.setBounds(250, 65, 150, 20);
        panelNuevoUsuario.add(txtcontra);

        JButton btnguardar = new JButton("Guardar");
        btnguardar.setBounds(300, 200, 100, 30);
        panelNuevoUsuario.add(btnguardar);
        ActionListener almacenar = new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                String nombre = txtnombre.getText();
                String contra = txtcontra.getText();

                if (guardarusuario(nombre, contra)) {
                    txtnombre.setText("");
                    txtcontra.setText("");
                }

            }
        };

        btnguardar.addActionListener(almacenar);

        JButton btnvolver = new JButton("Volver");
        btnvolver.setBounds(150, 200, 100, 30);
        panelNuevoUsuario.add(btnvolver);
        ActionListener volver = new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                componentesInicioSesion();

                panelInicioSesion.setVisible(true);
                panelNuevoUsuario.setVisible(false);
            }
        };

        btnvolver.addActionListener(volver);

    }

    public boolean guardarusuario(String nombre, String contra) {
        boolean guardado = false;
        if (nombre.equals("") || contra.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        } else {
            if (comprobarDuplicado(nombre)) {
                JOptionPane.showMessageDialog(null, "Usuario ya existente");
            } else {
                boolean vacio = false;
                int posicion = -1;
                for (int i = 0; i <= 9; i++) {
                    if (misusuarios[i] == null) {
                        vacio = true;
                        posicion = i;
                        break;

                    }
                }

                if (vacio) {
                    misusuarios[posicion] = new usuario(nombre, contra);
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                    guardado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Almacenamiento Lleno");
                }
            }

        }
        return guardado;

    }

    public boolean comprobarDuplicado(String nombre) {
        boolean duplicado = false;
        for (int i = 0; i <= 9; i++) {
            if (misusuarios[i] != null) {
                if (misusuarios[i].getnombre().equals(nombre)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }

//------------------ componentes para mostrar los clientes------
}
