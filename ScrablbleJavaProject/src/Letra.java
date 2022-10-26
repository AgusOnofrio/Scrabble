
public class Letra {
    private String nombreLetra;
    private Integer valor;


    public Letra(String nombreLetra){
        this.nombreLetra=nombreLetra;
        this.valor= this.getValor(nombreLetra);
    }

    private Integer getValor(String nombreLetra) {
        Integer valor;

        switch (nombreLetra.toLowerCase()) {
            case "a":
            case "e":
            case "i":
            case "l":
            case "n":
            case "o":
            case "r":
            case "s":
            case "t":
            case "u":
                valor=1;
                break;
            case "d":
            case "g":
                valor=2;
                break;
            case "b":
            case "c":
            case "m":
            case "p":
                valor=3;
                break;
            case "f":
            case "h":
            case "v":
            case "y":
                valor=4;
                break;
            case "ch":
            case "q":
                valor=5;
                break;
            case "j":
            case "ll":
            case "ñ":
            case "rr":
            case "x":
            case "k":
            case "w":
                valor=8;
                break;
            case "z":
                valor=10;
                break;

            default:
                valor=0;
                break;
        }


        return valor;
    }






}
