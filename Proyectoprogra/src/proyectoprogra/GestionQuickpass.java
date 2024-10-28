/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoprogra;
import javax.swing.JOptionPane;
/**
 *
 * @author allan
 */
class Quickpass {
    // Atributos Quickpass
    private String filial;
    private String codigo;
    private String placa;
    private boolean estado;

    // Constructores
    public Quickpass(String filial, String codigo, String placa) {
        this.filial = filial;
        this.codigo = codigo;
        this.placa = placa;
        this.estado = true;
    }

    //Getter y setter
    public String getFilial() {
        return filial;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public boolean isActivo() {
        return estado;
    }

    public void inactivar() {
        this.estado = false;
    }
}

public class GestionQuickpass {
    //Almacenar Quickpasses activos
    private static Quickpass[] quickpasses = new Quickpass[100];
    private static int contador = 0;

    private static Quickpass[] quickpassesEliminados = new Quickpass[100];
    private static int contadorEliminados = 0;

    // Validación de códigos
    private static boolean esCodigoValido(String codigo) {
        return codigo.matches("101\\d{7}");
    }

    // Nuevo Quickpass
    private static void agregarQuickpass() {
        String filial = JOptionPane.showInputDialog("Ingrese la filial:");
        String codigo = JOptionPane.showInputDialog("Ingrese el código de 10 dígitos (debe empezar con 101):");
        
        // Validación
        if (!esCodigoValido(codigo)) {
            JOptionPane.showMessageDialog(null, "Código inválido.");
            return;
        }

        String placa = JOptionPane.showInputDialog("Ingrese la placa:");

        // Crear y almacenar Quickpass
        quickpasses[contador] = new Quickpass(filial, codigo, placa);
        contador++;
        JOptionPane.showMessageDialog(null, "Quickpass agregado exitosamente.");
    }

    // Consulta por código
    private static void consultarQuickpass() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código a consultar:");
        for (int i = 0; i < contador; i++) {
            if (quickpasses[i].getCodigo().equals(codigo)) {
                String mensaje = quickpasses[i].isActivo() ? "Aceptado" : "Rechazado";
                JOptionPane.showMessageDialog(null, "Código " + mensaje);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Código no encontrado.");
    }

    // Eliminación
    private static void eliminarQuickpassPorCodigo() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código a eliminar:");
        for (int i = 0; i < contador; i++) {
            if (quickpasses[i].getCodigo().equals(codigo)) {
                quickpassesEliminados[contadorEliminados] = quickpasses[i];
                contadorEliminados++;
                
                quickpasses[i] = quickpasses[contador - 1];
                quickpasses[contador - 1] = null;
                contador--;

                JOptionPane.showMessageDialog(null, "Quickpass eliminado y movido a la lista de eliminados.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Código no encontrado.");
    }

    // Método principal
    public static void main(String[] args) {
        int opcion;
        do {
            String menu = "Seleccione una opción:\n"
                    + "1. Agregar Quickpass\n"
                    + "2. Consultar Quickpass\n"
                    + "3. Eliminar Quickpass por Código\n"
                    + "4. Salir";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    agregarQuickpass();
                    break;
                case 2:
                    consultarQuickpass();
                    break;
                case 3:
                    eliminarQuickpassPorCodigo();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 4);
    }
}
