package testObserver;

import java.util.Scanner;

public class Main {
    
    public static int menu(){
        
        System.out.println("------------------Lista de tareas----------------------");
        System.out.println();
        System.out.println("1-Mostrar tareas");
        System.out.println("2-Agregar tarea");
        System.out.println("3-Modificar estado de tarea");
        System.out.println("4-Eliminar tarea");
        Scanner sc = new Scanner(System.in);
        int opcion= sc.nextInt();
        
        switch (opcion) {
            case 1:
                //TODO mostrar tareas
                break;
        
                case 2:
                //TODO Agregar tareas
                break;
                case 3:
                //TODO Modificar tareas
                break;
                case 4:
                //TODO eliminar tareas
                break;
                case 0:
                System.out.println("Nos vemos");
                break;
        
            default:
            System.out.println("opcion incorrecta");
                break;
        }

        return opcion;
    }



    public static void main(String[] args) {
        ListaDeTareas toDoList = new ListaDeTareas();

        System.out.printf(toDoList.mostrarTareas());

        toDoList.agregarTarea("TP Poo", "Hacer el trabajo de Poo antes del 30 de noviembre");

        int opcion=1;
        while (opcion!=0){
            opcion=menu();
        }





    }
}
