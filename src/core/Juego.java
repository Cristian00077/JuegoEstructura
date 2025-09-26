package core;

import java.util.*;

public class Juego {

    private ArbolBST arbol;
    private Random random;

    public Juego() {
        this.arbol = new ArbolBST();
        this.random = new Random();
    }

    // =======================
    //  MODO 1: Simulación automática
    // =======================
    /*public void simularJuego() {
        mostrarTitulo("INICIO DE LA AVENTURA EN EL BOSQUE");

        // Evento 1-5
        mostrarSeccion("FASE 1: RECOLECCIÓN DE GEMAS");
        recolectarGema(50, "Gema del Rio", 10, 15);
        recolectarGema(30, "Gema del Viento", 5, 8);
        recolectarGema(70, "Gema del Fuego", 15, 20);
        recolectarGema(20, "Gema de la Tierra", 3, 12);
        recolectarGema(40, "Gema del Relampago", 8, 18);
        arbolGemmas.inorden();

        // Evento 6-10
        mostrarSeccion("FASE 2: ENCUENTROS CON JEFES");
        jefePideGema(25);
        jefePideGema(40);
        jefePideGema(60);

        // Evento 11-15
        mostrarSeccion("FASE 3: COFRES Y PORTALES MÁGICOS");
        abrirCofreMenorPoder();
        abrirPortalMayorPoder();
        recolectarGema(35, "Gema de la Luna", 12, 7);
        recolectarGema(55, "Gema del Sol", 18, 22);

        // Evento 16-20
        mostrarSeccion("FASE 4: TRAMPAS Y DESAFÍOS");
        caerEnTrampa();
        caerEnTrampa();
        jefePideGema(35);

        mostrarTitulo("FIN DE LA AVENTURA");
        System.out.println("Estado final del inventario: ");
        arbolGemmas.inorden();
    }*/
    // =======================
    //  MODO 2: Interactivo (usuario elige)
    // =======================
    public void Preguntar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quiere jugar con objetos aleatoriamente? (si/no): ");
        String respuesta = sc.nextLine().toLowerCase();
        if (respuesta.equals("si")) {
            iniciarJuegoInteractivo();
        } else if (respuesta.equals("no")) {
            elegirEvento();
        } else {
            System.out.println("Respuesta inválida");
        }
    }

    public void iniciarJuegoInteractivo() {
        Scanner sc = new Scanner(System.in);
        boolean jugando = true;

        mostrarTitulo("INICIO DE LA AVENTURA EN EL BOSQUE");
        int cont = 0;
        while (jugando) {

            cont = cont + 1;
            int opc;
            mostrarMenu();
            int opcion;
            int opm = sc.nextInt();
            if (opm == 2) {
                opc = 1;
            } else if (opm == 1) {
                System.out.println("Ingrese un numero entre 1, 2 y 3: ");
                opcion = sc.nextInt();
                while (opcion != 1 && opcion != 2 && opcion != 3) {
                    System.out.println("Opcion invalida, ingrese de nuevo: ");
                    opcion = sc.nextInt();
                }
                int r = random.nextInt(3) + 1;
                int valAbs = Math.abs(opcion - r);
                switch (valAbs) {
                    case 0:
                        opc = 4;
                        break;
                    case 1:
                        opc = 2;
                        break;
                    default:
                        opc = 3;
                        break;
                }
            } else {
                opc = 0;
            }

            switch (opc) {
                case 4: // Recolectar gema
                    int poder = random.nextInt(100) + 1;
                    String nombre = "Gema " + poder;
                    int x = random.nextInt(20);
                    int y = random.nextInt(20);
                    recolectarGema(poder, nombre, x, y);
                    break;

                case 2: // Cofre
                    abrirCofreMenorPoder();
                    break;

                case 3: // Portal
                    abrirPortalMayorPoder();
                    break;

                case 1: // Inventario
                    mostrarSeccion("INVENTARIO");
                    arbol.inorden();
                    break;

                case 0: // Salir
                    jugando = false;
                    break;

                default:
                    mostrarEvento("Opción inválida. Intente de nuevo");
            }
            System.out.println(cont);
            if (arbol.minimo() == null && cont > 9) {
                jugando = false;
            }

        }

        mostrarTitulo("FIN DE LA AVENTURA");
        System.out.println("Estado final del inventario: ");
        if (arbol.minimo() != null) {
            arbol.inorden();
        } else {
            System.out.println("Te has quedado sin gemas");
        }
    }

    public void elegirEvento() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        int cont = 0;
        while (continuar) {

            cont = cont + 1;
            mostrarSeccion("ESCOGER EVENTO");
            System.out.print("Seleccione el evento: \n");
            System.out.println("1. Recolectar gema");
            System.out.println("2. Jefe pide gema");
            System.out.println("3. Cofre aparece");
            System.out.println("4. Portal mágico");
            System.out.println("5. Trampa");
            System.out.println("6. Ver inventario");
            System.out.println("0. Volver al menu principal");

            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    int poder = random.nextInt(100) + 1;
                    String nombre = "Gema " + poder;
                    int x = random.nextInt(20);
                    int y = random.nextInt(20);
                    recolectarGema(poder, nombre, x, y);
                    break;
                case 2:
                    int poderJefe = random.nextInt(100) + 1;
                    System.out.println("El jefe pide la gema con poder: " + poderJefe);
                    arbol.inorden();
                    if (arbol.getRaiz() != null) {
                        int poderJugador = arbol.getRaiz().getPoder();
                        jefePideGema(poderJefe, poderJugador);
                    } else {
                        System.out.println("No tienes gemas todavia. Recoge una antes de enfrentar al jefe");
                    }
                    break;
                case 3:
                    abrirCofreMenorPoder();
                    break;
                case 4:
                    abrirPortalMayorPoder();
                    break;
                case 5:
                    caerEnTrampa();
                    break;
                case 6:
                    System.out.println("Inventario: ");
                    arbol.inorden();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    mostrarEvento("Opcion inválida");
                    break;
            }
            System.out.println(cont);
            if (arbol.minimo() == null && cont > 9) {
                continuar = false;
            }
        }
        mostrarTitulo("FIN DE LA AVENTURA");
        System.out.println("Estado final del inventario: ");
        if (arbol.minimo() != null) {
            arbol.inorden();
        } else {
            System.out.println("Te has quedado sin gemas");
        }
    }

    // =======================
    //  Decoradores ASCII
    // =======================
    private void mostrarTitulo(String mensaje) {
        String borde = "=".repeat(mensaje.length() + 8);
        System.out.println("\n" + borde);
        System.out.println("||  " + mensaje.toUpperCase() + "  ||");
        System.out.println(borde + "\n");
    }

    private void mostrarSeccion(String mensaje) {
        String borde = "-".repeat(mensaje.length() + 4);
        System.out.println("\n" + borde);
        System.out.println("| " + mensaje + " |");
        System.out.println(borde);
    }

    private void mostrarEvento(String mensaje) {
        String borde = "+" + "-".repeat(mensaje.length() + 2) + "+";
        System.out.println(borde);
        System.out.println("| " + mensaje + " |");
        System.out.println(borde);
    }

    private void mostrarMenu() {
        String borde = "+----------------------------+";
        System.out.println("\n" + borde);
        System.out.println("|           AVENTURA         |");
        System.out.println(borde);
        System.out.println("| 1. Jugar                   |");
        System.out.println("| 2. Ver inventario          |");
        System.out.println("| 0. Salir del juego         |");
        System.out.println(borde);
        System.out.println("Seleccione una opcion: ");

    }

    // =======================
    //  Eventos
    // =======================
    private void recolectarGema(int poder, String nombre, int x, int y) {
        mostrarArte("recolecta");
        mostrarEvento("Has recogido una gema brillante!");
        mostrarEvento("Encontraste la " + nombre + " (Poder: " + poder + ")");
        arbol.insertarGema(poder, nombre, x, y);
    }

    private void jefePideGema(int poderRequerido, int poderJugador) {
        mostrarArte("jefe");
        Scanner sc = new Scanner(System.in);
        mostrarEvento("Un jefe aparece y pide gema de poder " + poderRequerido);
        Nodo gema = arbol.buscar(poderRequerido);
        if (gema != null) {
            mostrarEvento("Tienes la gema exacta, no poierdes la gema: " + gema.getNombre());
            System.out.println("");
            mostrarEvento("Haz ganado un cofre, presiona 1 para abrir");
            int n = sc.nextInt();
            if (n == 1) {
                abrirCofreMenorPoder();
            }
        } else {
            mostrarEvento("No tienes la gema exacta. Buscando alternativa...");
            Nodo sucesor = arbol.sucesor(poderRequerido);
            Nodo predecesor = arbol.predecesor(poderRequerido);

            if (sucesor != null && predecesor != null) {
                int diffSucesor = sucesor.getPoder() - poderRequerido;
                int diffPredecesor = poderRequerido - predecesor.getPoder();

                if (diffSucesor < diffPredecesor) {
                    mostrarEvento("Entregas la gema más cercana: " + sucesor.getNombre());
                    arbol.eliminar(sucesor.getPoder());
                } else {
                    mostrarEvento("Entregas la gema más cercana: " + predecesor.getNombre());
                    arbol.eliminar(predecesor.getPoder());
                }
            } else if (sucesor != null) {
                mostrarEvento("Entregas la gema más cercana: " + sucesor.getNombre());
                arbol.eliminar(sucesor.getPoder());
            } else if (predecesor != null) {
                mostrarEvento("Entregas la gema más cercana: " + predecesor.getNombre());
                arbol.eliminar(predecesor.getPoder());
            } else {

                Nodo intento = arbol.buscar(poderJugador);
                if (intento != null) {
                    mostrarEvento("El jefe rechaza tu gema... ¡y se queda con ella de todos modos!: " + intento.getNombre());
                    arbol.eliminar(poderJugador);
                } else {
                    mostrarEvento("El jefe rechaza tu intento, pero ni siquiera tenías esa gema...");
                }
            }
        }
    }

    private void abrirCofreMenorPoder() {
        Scanner sc = new Scanner(System.in);
        mostrarArte("cofre-cerrado");
        mostrarEvento("Un cofre aparece, requiere la gema de menor poder");
        Nodo minimaGema = arbol.minimo();
        if (minimaGema != null) {
            mostrarEvento("Usas " + minimaGema.getNombre() + " para abrir el cofre");
            arbol.eliminar(minimaGema.getPoder());
            mostrarArte("cofre-abierto");
            mostrarEvento("El cofre se abre y revela su secreto!");
            int rand = random.nextInt(3) + 1;
            switch (rand) {
                case 1:
                    int poder = random.nextInt(100) + 1;
                    String nombre = "Gema " + poder;
                    int x = random.nextInt(20);
                    int y = random.nextInt(20);
                    recolectarGema(poder, nombre, x, y);
                    break;
                case 2:
                    mostrarArte("jefe");
                    int poderJefe = random.nextInt(100) + 1;
                    System.out.println("La gema que pide el jefe es: " + poderJefe);
                    mostrarSeccion("INVENTARIO");
                    arbol.inorden();
                    if (arbol.getRaiz() != null) {
                        int poder1 = arbol.getRaiz().getPoder();
                        jefePideGema(poderJefe, poder1);
                    } else {
                        System.out.println("No tienes gemas todavia");
                    }
                    break;
                case 3:
                    caerEnTrampa();
                    break;
            }
        } else {
            mostrarEvento("No tienes gemas para abrir el cofre");
        }
    }

    private void abrirPortalMayorPoder() {
        mostrarArte("portal");
        mostrarEvento("Un portal mágico aparece, requiere la gema de mayor poder");
        Nodo maximaGema = arbol.maximo();
        if (maximaGema != null) {
            mostrarEvento("Usas " + maximaGema.getNombre() + " para activar el portal");
            arbol.eliminar(maximaGema.getPoder());
            abrirCofreMenorPoder();
        } else {
            mostrarEvento("No tienes gemas para activar el portal");
        }
    }

    private void caerEnTrampa() {
        mostrarArte("trampa");
        mostrarEvento("¡Trampa! Pierdes una gema aleatoria");

        List<Nodo> gemas = new ArrayList<>();
        colectarGemas(arbol, gemas);

        if (!gemas.isEmpty()) {
            Nodo gemaPerdida = gemas.get(random.nextInt(gemas.size()));
            mostrarEvento("Perdiste: " + gemaPerdida.getNombre());
            arbol.eliminar(gemaPerdida.getPoder());
        } else {
            mostrarEvento("No tienes gemas que perder");
        }
    }

    private void colectarGemas(ArbolBST arbol, List<Nodo> lista) {
        colectarGemasRec(arbol.getRaiz(), lista);
    }

    private void colectarGemasRec(Nodo actual, List<Nodo> lista) {
        if (actual == null) {
            return;
        }
        colectarGemasRec(actual.getIzquierdo(), lista);
        lista.add(actual);
        colectarGemasRec(actual.getDerecho(), lista);
    }

    private void mostrarArte(String tipo) {
        switch (tipo) {
            case "portal":
                System.out.println("     .---.  ");
                System.out.println("   .'_:___\".");
                System.out.println("   |__ --==|");
                System.out.println("   [  ]  :[|");
                System.out.println("   |__| I=[|");
                System.out.println("   / / ____|");
                System.out.println("  |-/.____.' ");
                System.out.println("  /___\\ /___\\");
                break;

            case "jefe":
                System.out.println("     .-''''-.");
                System.out.println("    /        \\");
                System.out.println("   |,  .-.  ,|");
                System.out.println("   | )(_o_)( |");
                System.out.println("   |/  '-'  \\|");
                System.out.println("   |          |");
                System.out.println("   |          |");
                System.out.println("   '-.______.-'");
                break;

            case "trampa":
                System.out.println("   .-\"      \"-.");
                System.out.println("  /            \\");
                System.out.println(" |,  .-.  .-.  ,|");
                System.out.println(" | )(_o/  \\o_)( |");
                System.out.println(" |/     /\\     \\|");
                System.out.println(" (_     ^^     _)");
                System.out.println("  \\__|IIIIII|__/");
                System.out.println("   | \\IIIIII/ |");
                System.out.println("   \\          /");
                System.out.println("    `--------`");
                break;

            case "cofre-cerrado":
                System.out.println(
                        "   __________________\n"
                        + "  /                  \\\n"
                        + " /====================\\\n"
                        + "||   ____________    ||\n"
                        + "||  |            |   ||\n"
                        + "||  |    LOCK    |   ||\n"
                        + "||  |_____🔒_____|   ||\n"
                        + "||                  ||\n"
                        + " \\==================/\n"
                        + "  \\________________/"
                );
                break;

            case "cofre-abierto":
                System.out.println(
                        "     __________________\n"
                        + "    /                  \\\n"
                        + "   /====================\\\n"
                        + "  ||   ________        ||\n"
                        + "  ||  |        |       ||\n"
                        + "  ||  |  ■    |       ||\n"
                        + "  ||  |________|       ||\n"
                        + "  ||                  ||\n"
                        + "   \\==================/\n"
                        + "    \\________________/"
                );
                break;
            case "recolecta":
                System.out.println(
                        "     ***       \n"
                        + "      *         \n"
                        + "     \\O/        \n"
                        + "      |         \n"
                        + "     / \\        \n"
                );
                break;
        }
    }
}
