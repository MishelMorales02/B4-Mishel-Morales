package ejemploproyecto4to;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class ventana extends JFrame {

    JPanel panelInicioSesion, panelNuevoUsuario, panelclientes, panelCrear;
    JTextField txtusuario;
    JPasswordField txtcontra;
    usuario misusuarios[] = new usuario[10];
    cliente misclientes[] = new cliente[99];

    public ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        misusuarios[0] = new usuario("Mishel", "0202");

        misclientes[0] = new cliente("741", "Cali", 24, 'M');
        misclientes[1] = new cliente("742", "Caleb", 39, 'M');
        misclientes[2] = new cliente("743", "Jared", 30, 'M');
        misclientes[3] = new cliente("744", "Ross", 24, 'M');
        misclientes[4] = new cliente("745", "Aiden", 22, 'M');
        misclientes[5] = new cliente("746", "Dayana", 27, 'F');
        misclientes[6] = new cliente("747", "Celice", 29, 'F');
        misclientes[7] = new cliente("748", "Leah", 21, 'F');
        misclientes[8] = new cliente("749", "Lili", 37, 'F');
        misclientes[9] = new cliente("750", "Ale", 27, 'M');

    }

    public void componentesInicioSesion() {
        panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(null);
        this.getContentPane().add(panelInicioSesion);
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
                if (buscarusuario(usuario, contra)) {
                    panelInicioSesion.setVisible(false);
                    componentesClientes();
                }
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
            }
        };

        btnNuevoUsuario.addActionListener(nuevoUsuario);

        panelInicioSesion.repaint();

    }

    public boolean buscarusuario(String usuario, String contra) {
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
        return encontrado;
    }

    public void componentesNuevoUsuario() {
        panelNuevoUsuario = new JPanel();
        panelNuevoUsuario.setLayout(null);
        this.getContentPane().add(panelNuevoUsuario);
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

        JPasswordField txtcontra = new JPasswordField();
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
                panelNuevoUsuario.setVisible(false);
                componentesInicioSesion();
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
    public void componentesClientes() {
        panelclientes = new JPanel();
        panelclientes.setLayout(null);
        this.getContentPane().add(panelclientes);
        this.setTitle("Dashboard de clientes");

        JLabel lblclientes = new JLabel("Clientes almacenados");
        lblclientes.setBounds(25, 10, 150, 15);
        panelclientes.add(lblclientes);

        char m = 'M';
        char f = 'F';
        int contarm = 0;
        int contarf = 0;

//creando datos de prueba
        DefaultTableModel datos = new DefaultTableModel();
        datos.addColumn("NIT");
        datos.addColumn("NOMBRE");
        datos.addColumn("EDAD");
        datos.addColumn("GENERO");
        // String prueba1[] = {"1", "uno", "1", "F"};
        //datos.addRow(prueba1);

        for (int i = 0; i < misclientes.length - 1; i++) {
            if (misclientes[i] != null) {
                String temporal[] = {misclientes[i].getNit(), misclientes[i].getNombre(), String.valueOf(misclientes[i].getEdad()), String.valueOf(misclientes[i].getGenero())};
                datos.addRow(temporal);

            }

        }

        for (int i = 0; i <= misclientes.length - 1; i++) {
            if (misclientes[i] != null) {
                if (misclientes[i].getGenero() == (m)) {
                    contarm++;
                } else if (misclientes[i].getGenero() == (f)) {
                    contarf++;
                }
            }
        }
//creando tabla
        JTable tablaClientes = new JTable(datos);
// creando Scroll para tabla
        JScrollPane barraClientes = new JScrollPane(tablaClientes);
        barraClientes.setBounds(25, 70, 350, 250);
        panelclientes.add(barraClientes);

        DefaultPieDataset generoGrafico = new DefaultPieDataset();
        generoGrafico.setValue("Masculino", contarm);
        generoGrafico.setValue("Femenino", contarf);

        JFreeChart graficoCircular = ChartFactory.createPieChart("Generos", generoGrafico, true, true, false);
        ChartPanel panelCircular = new ChartPanel(graficoCircular);
        panelCircular.setBounds(400, 70, 400, 250);
        panelclientes.add(panelCircular);
        this.setSize(950, 500);

        JButton btnClienteN = new JButton("Nuevo Cliente");
        btnClienteN.setBounds(450, 20, 150, 30);
        panelclientes.add(btnClienteN);
        ActionListener nuevocl = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearCliente();
                panelclientes.setVisible(false);
            }
        };
        btnClienteN.addActionListener(nuevocl);
        //Boton regresar
        JButton btnBack = new JButton("Volver");
        btnBack.setBounds(640, 20, 150, 30);
        panelclientes.add(btnBack);
        ActionListener volver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesInicioSesion();
                panelclientes.setVisible(false);
            }
        };
        btnBack.addActionListener(volver);

    }

    public void CrearCliente() {
        panelCrear = new JPanel();
        panelCrear.setLayout(null);
        this.getContentPane().add(panelCrear);
        this.setTitle("Nuevo Cliente");

        JLabel lblnclientes = new JLabel("Llene todos los campos");
        lblnclientes.setBounds(80, 50, 200, 15);
        panelCrear.add(lblnclientes);

        JLabel nombre = new JLabel("Nombre:");
        nombre.setBounds(80, 120, 170, 20);
        panelCrear.add(nombre);

        JTextField txtnombre = new JTextField();
        txtnombre.setBounds(150, 120, 150, 20);
        panelCrear.add(txtnombre);

        JLabel edad = new JLabel("Edad:");
        edad.setBounds(80, 160, 160, 20);
        panelCrear.add(edad);

        JTextField txtedad = new JTextField();
        txtedad.setBounds(150, 160, 150, 20);
        panelCrear.add(txtedad);

        JLabel genero = new JLabel("Genero:");
        genero.setBounds(80, 200, 170, 20);
        panelCrear.add(genero);

        JTextField txtgenero = new JTextField();
        txtgenero.setBounds(150, 200, 150, 20);
        panelCrear.add(txtgenero);

        JLabel indicar = new JLabel("Coloque: F o M");
        indicar.setBounds(320, 200, 200, 20);
        panelCrear.add(indicar);

        JLabel Nit = new JLabel("NIT: ");
        Nit.setBounds(80, 240, 170, 20);
        panelCrear.add(Nit);

        JTextField txtnit = new JTextField();
        txtnit.setBounds(150, 240, 150, 20);
        panelCrear.add(txtnit);

        JButton btnguardar = new JButton("Guardar");
        btnguardar.setBounds(300, 300, 100, 30);
        panelCrear.add(btnguardar);

        ActionListener almacenar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nit = txtnit.getText();
                String nombre = txtnombre.getText();
                int edad = Integer.parseInt(txtedad.getText());
                char genero = txtgenero.getText().charAt(0);

                if (guardarclientenuevo(nit, nombre, edad, genero)) {
                    txtnit.setText(" ");
                    txtnombre.setText(" ");
                    txtedad.setText("");
                    txtgenero.setText(" ");
                }

            }

        };

        btnguardar.addActionListener(almacenar);

        JButton btnvolver = new JButton("Volver");
        btnvolver.setBounds(150, 300, 100, 30);
        panelCrear.add(btnvolver);
        ActionListener volver = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelCrear.setVisible(false);
                componentesClientes();
            }

        };

        btnvolver.addActionListener(volver);

    }

    public boolean guardarclientenuevo(String nit, String nombre, int edad, char genero) {
        boolean guardado = false;
        if (nit.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        } else {
            if (comprobarDuplicadoNit(nit)) {
                JOptionPane.showMessageDialog(null, "NIT ya existente");
            } else {
                boolean vacio = false;
                int posicion = -1;
                for (int i = 0; i <= misclientes.length - 1; i++) {
                    if (misclientes[i] == null) {
                        vacio = true;
                        posicion = i;
                        break;
                    }

                }

                if (vacio) {
                    misclientes[posicion] = new cliente(nit, nombre, edad, genero);
                    /* System.out.println(nit);
                    System.out.println(nombre);
                    System.out.println(edad);
                    System.out.println(genero);*/
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                    guardado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Almacenamiento Lleno");
                }
            }
        }

        return guardado;
    }

    public boolean comprobarDuplicadoNit(String nit) {
        boolean duplicado = false;
        for (int i = 0; i <= misclientes.length - 1; i++) {
            if (misclientes[i] != null) {
                if (misclientes[i].getNit().equals(nit)) {
                    duplicado = true;
                    break;
                }
            }

        }
        return duplicado;

    }

}
