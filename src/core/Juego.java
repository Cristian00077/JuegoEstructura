package core;

import java.util.*;

public class Juego {

    private ArbolBST arbolGemmas;
    private Random random;

    public Juego() {
        this.arbolGemmas = new ArbolBST();
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
    
    public void Preguntar(){
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

        while (jugando) {
            int cont = 0;
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
                    if (poder % 2 == 0) {
                        String nombre = "Gema " + poder;
                        int x = random.nextInt(20);
                        int y = random.nextInt(20);
                        recolectarGema(poder, nombre, x, y);
                    } else {
                        System.out.println("No se encontro ninguna gema, intente de nuevo!!");
                    }

                    break;

                case 2: // Cofre
                    abrirCofreMenorPoder();
                    break;

                case 3: // Portal
                    abrirPortalMayorPoder();
                    break;

                case 1: // Inventario
                    mostrarSeccion("INVENTARIO");
                    arbolGemmas.inorden();
                    break;

                case 0: // Salir
                    jugando = false;
                    break;

                default:
                    mostrarEvento("Opción inválida. Intente de nuevo.");
            }
            if (arbolGemmas.minimo() == null && cont > 7) {
                jugando = false;
            }

        }

        mostrarTitulo("FIN DE LA AVENTURA");
        System.out.println("Estado final del inventario: ");
        if (arbolGemmas.minimo() != null) {
            arbolGemmas.inorden();
        } else {
            System.out.println("Te has quedado sin gemas");
        }
    }

    public void elegirEvento(){
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        
        while(continuar){
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

            switch(opcion){
                case 1: 
                    int poder = random.nextInt(100) + 1;
                        if (poder % 2 == 0) {
                            String nombre = "Gema " + poder;
                            int x = random.nextInt(20);
                            int y = random.nextInt(20);
                            recolectarGema(poder, nombre, x, y);
                        }
                        break;
                case 2: 
                    int poderJefe = random.nextInt(100) + 1;
                    System.out.println("El jefe pide la gema con poder: " + poderJefe);
                    arbolGemmas.inorden();
                    System.out.print("Inserte el poder de la gema que desea entregar: ");
                    int poderJugador = sc.nextInt();
                    jefePideGema(poderJefe, poderJugador);
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
                    arbolGemmas.inorden();
                    break;
                case 0: 
                    continuar = false;
                    break;
                default: 
                    mostrarEvento("Opcion inválida.");
                    break;
            }
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
        mostrarEvento("¡Has recogido una gema brillante!");
        mostrarEvento("Encontraste la " + nombre + " (Poder: " + poder + ")");
        arbolGemmas.insertarGema(poder, nombre, x, y);
    }

    private void jefePideGema(int poderRequerido, int poderJugador) {
        Scanner sc = new Scanner(System.in);
        mostrarEvento("Un jefe aparece y pide gema de poder " + poderRequerido);
        Nodo gema = arbolGemmas.buscar(poderRequerido);
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
            Nodo sucesor = arbolGemmas.sucesor(poderRequerido);
            Nodo predecesor = arbolGemmas.predecesor(poderRequerido);

            if (sucesor != null && predecesor != null) {
                int diffSucesor = sucesor.getPoder() - poderRequerido;
                int diffPredecesor = poderRequerido - predecesor.getPoder();

                if (diffSucesor < diffPredecesor) {
                    mostrarEvento("Entregas la gema más cercana: " + sucesor.getNombre());
                    arbolGemmas.eliminar(sucesor.getPoder());
                } else {
                    mostrarEvento("Entregas la gema más cercana: " + predecesor.getNombre());
                    arbolGemmas.eliminar(predecesor.getPoder());
                }
            } else if (sucesor != null) {
                mostrarEvento("Entregas la gema más cercana: " + sucesor.getNombre());
                arbolGemmas.eliminar(sucesor.getPoder());
            } else if (predecesor != null) {
                mostrarEvento("Entregas la gema más cercana: " + predecesor.getNombre());
                arbolGemmas.eliminar(predecesor.getPoder());
            } else {
                // 🔥 Caso nuevo: el jefe quita la gema que intentó entregar el jugador
                Nodo intento = arbolGemmas.buscar(poderJugador);
                if (intento != null) {
                    mostrarEvento("El jefe rechaza tu gema... ¡y se queda con ella de todos modos!: " + intento.getNombre());
                    arbolGemmas.eliminar(poderJugador);
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
        Nodo minimaGema = arbolGemmas.minimo();
        if (minimaGema != null) {
            mostrarEvento("Usas " + minimaGema.getNombre() + " para abrir el cofre");
            arbolGemmas.eliminar(minimaGema.getPoder());
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
                    arbolGemmas.inorden();
                    System.out.print("Inserte el poder de la gema que desea entregar: ");
                    int poder1 = sc.nextInt();
                    jefePideGema(poderJefe, poder1);
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
        Nodo maximaGema = arbolGemmas.maximo();
        if (maximaGema != null) {
            mostrarEvento("Usas " + maximaGema.getNombre() + " para activar el portal");
            arbolGemmas.eliminar(maximaGema.getPoder());
            abrirCofreMenorPoder();
        } else {
            mostrarEvento("No tienes gemas para activar el portal");
        }
    }

    private void caerEnTrampa() {
        mostrarArte("trampa");
        mostrarEvento("¡Trampa! Pierdes una gema aleatoria");

        List<Nodo> gemas = new ArrayList<>();
        colectarGemas(arbolGemmas, gemas);

        if (!gemas.isEmpty()) {
            Nodo gemaPerdida = gemas.get(random.nextInt(gemas.size()));
            mostrarEvento("Perdiste: " + gemaPerdida.getNombre());
            arbolGemmas.eliminar(gemaPerdida.getPoder());
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
                        "     ■         \n"
                        + "                \n"
                        + "    \\O/        \n"
                        + "     |         \n"
                        + "    / \\        \n"
                );
                break;
        }
    }
}
