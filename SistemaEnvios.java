import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SistemaEnvios {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Envio> enTransito = new HashMap<>();
        ArrayList<Envio> entregados = new ArrayList<>();
        int contador = 1001;
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n===== SERVIENTREGA =====");
            System.out.println("1. Registrar envio");
            System.out.println("2. Consultar envio");
            System.out.println("3. Entregar envio");
            System.out.println("4. Listar entregados");
            System.out.println("5. Salir");
            System.out.print("Elija una opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 1) {
                System.out.print("Servicio (1 nacional, 2 express, 3 internacional): ");
                int tipo = Integer.parseInt(sc.nextLine());
                System.out.print("Nombre del destinatario: ");
                String nombre = sc.nextLine();
                System.out.print("Ciudad de destino: ");
                String ciudad = sc.nextLine();
                System.out.print("Peso en kilos: ");
                double peso = Double.parseDouble(sc.nextLine());

                if (peso <= 0) {
                    System.out.println("El peso debe ser mayor que cero. No se registro nada.");
                } else {
                    Envio envio = null;

                    if (tipo == 1 || tipo == 2) {
                        String letras;
                        if (tipo == 1) {
                            letras = "NAC";
                        } else {
                            letras = "EXP";
                        }
                        String guia = letras + String.valueOf(contador);

                        System.out.print("Entrega a domicilio (S/N): ");
                        boolean domicilio = sc.nextLine().equalsIgnoreCase("S");

                        if (tipo == 1) {
                            envio = new EnvioNacional(guia, nombre, ciudad, peso, domicilio);
                        } else {
                            System.out.print("El cliente eligio entrega en 24 horas (S/N): ");
                            boolean veinticuatro = sc.nextLine().equalsIgnoreCase("S");
                            if (veinticuatro) {
                                envio = new EnvioExpress(guia, nombre, ciudad, peso, domicilio, 24);
                            } else {
                                envio = new EnvioExpress(guia, nombre, ciudad, peso, domicilio);
                            }
                        }
                    } else if (tipo == 3) {
                        String guia = "INT" + String.valueOf(contador);
                        System.out.print("Valor de la compra en dolares: ");
                        double valor = Double.parseDouble(sc.nextLine());
                        EnvioInternacional inter = new EnvioInternacional(guia, nombre, ciudad, peso, valor);
                        if (inter.cumple4x4()) {
                            envio = inter;
                        } else {
                            System.out.println("Envio rechazado: no cumple la regla 4x4 de aduana.");
                        }
                    } else {
                        System.out.println("Servicio invalido.");
                    }

                    if (envio != null) {
                        enTransito.put(envio.getGuia(), envio);
                        contador++;
                        System.out.println("Envio registrado. Guia: " + envio.getGuia());
                        System.out.printf("Costo: %.2f%n", envio.calcularCosto());
                    }
                }

            } else if (opcion == 2) {
                System.out.print("Ingrese la guia: ");
                String guia = sc.nextLine();
                if (enTransito.containsKey(guia)) {
                    Envio e = enTransito.get(guia);
                    System.out.println(e.describir());
                    System.out.printf("Costo: %.2f%n", e.calcularCosto());
                } else {
                    System.out.println("No existe un envio en transito con esa guia.");
                }

            } else if (opcion == 3) {
                System.out.print("Ingrese la guia: ");
                String guia = sc.nextLine();
                if (enTransito.containsKey(guia)) {
                    Envio e = enTransito.remove(guia);
                    entregados.add(e);
                    System.out.println("Envio " + guia + " entregado.");
                } else {
                    System.out.println("No existe un envio en transito con esa guia.");
                }

            } else if (opcion == 4) {
                if (entregados.isEmpty()) {
                    System.out.println("Todavia no hay envios entregados.");
                } else {
                    double total = 0;
                    for (Envio e : entregados) {
                        System.out.println(e.describir());
                        System.out.printf("Costo: %.2f%n", e.calcularCosto());
                        total = total + e.calcularCosto();
                    }
                    System.out.printf("Total recaudado: %.2f%n", total);
                }

            } else if (opcion == 5) {
                System.out.println("\n Saliendo...");
            } else {
                System.out.println("Opción inválida");
            }
        }
        sc.close();
    }
}
