package testObserver;

public class Tarea {
    private String descripcion;
    private String titulo;
    private boolean estado;

    public Tarea(String titulo,String descripcion){
        this.titulo= titulo;
        this.descripcion=descripcion;
        this.estado=false;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean getEstado(){
        return estado;
    }

    public boolean setEstado(){
        return estado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    

}
