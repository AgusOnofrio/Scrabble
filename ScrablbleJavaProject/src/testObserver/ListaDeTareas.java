package testObserver;

import java.util.ArrayList;

public class ListaDeTareas {
    
    public ListaDeTareas(){
        this.listaDeTareas= new ArrayList<Tarea>();
    }
   
    private ArrayList<Tarea> listaDeTareas;

    public String mostrarTareas(){
        String lista="";
        int i=1;
        for (Tarea tarea : listaDeTareas) {
            lista+="\n"+i+"-"+tarea.getTitulo()+"\n  -"+tarea.getDescripcion();
            lista+="\t"+tarea.getEstado()+"\n\n";
        }
        return lista;
    }

    public void agregarTarea(String titulo, String descripcion){
        try {
            Tarea tareaAgregada= new Tarea(titulo,descripcion);
            this.listaDeTareas.add(tareaAgregada);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Fallo en agregar tarea");
        }

    }



}
