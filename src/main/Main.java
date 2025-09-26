package main;

import core.ArbolBST;
import core.Juego;
import core.Nodo;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Juego juego = new Juego();
        Scanner leer = new Scanner(System.in);
        int n = 0;
        while (n != 3) {
            System.out.println("\n");
            System.out.println("--------------------------");
            System.out.println("");
            System.out.println("");
            System.out.println("\n+----------------------+");
            System.out.println("|   MENU PRINCIPAL     |");
            System.out.println("+----------------------+");
            System.out.println("| 1. Iniciar Juego     |");
            System.out.println("| 2. Salir             |");
            System.out.println("+----------------------+");
            System.out.print("Seleccione una opción: ");
            n = leer.nextInt();
            
            if (n == 1) {
                juego.Preguntar();
            } else if (n == 2) {
                System.out.println("¡Hasta pronto aventurero!");
                break;
            } else {
                System.out.println("Opción inválida.");
                
                        
            }
        }
    }
}
