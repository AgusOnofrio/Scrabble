
public enum Letra {
    A("A",1,12),
    B("B",3,2),
    C("C",3,4),
    CH("CH",5,1),
    D("D",2,5),
    E("E",1,12),
    F("F",4,1),
    G("G",2,2),
    H("H",4,2),
    I("I",1,6),
    J("J",8,1),
    K("K",8,1),
    L("L",1,4),
    LL("LL",8,1),
    M("M",3,2),
    N("N",1,5),
    Ñ("Ñ",8,1),
    O("O",1,9),
    P("P",3,2),
    Q("Q",5,1),
    R("R",1,5),
    RR("RR",8,1),
    S("S",1,6),
    T("T",1,4),
    U("U",1,5),
    V("V",4,1),
    W("W",8,1),
    X("X",8,1),
    Y("Y",4,1),
    Z("Z",10,1),
    ESPECIAL("#",0,2);




    public final  String label;
    public final  Integer value;
    public final  Integer quantity;

    Letra(String label, Integer value, Integer quantity){
        this.label=label;
        this.value= value;
        this.quantity= quantity;
    }

    // private Integer getValor(String nombreLetra) {
    //     Integer valor;

    //     switch (nombreLetra.toLowerCase()) {
    //         case "a":
    //         case "e":
    //         case "i":
    //         case "l":
    //         case "n":
    //         case "o":
    //         case "r":
    //         case "s":
    //         case "t":
    //         case "u":
    //             valor=1;
    //             break;
    //         case "d":
    //         case "g":
    //             valor=2;
    //             break;
    //         case "b":
    //         case "c":
    //         case "m":
    //         case "p":
    //             valor=3;
    //             break;
    //         case "f":
    //         case "h":
    //         case "v":
    //         case "y":
    //             valor=4;
    //             break;
    //         case "ch":
    //         case "q":
    //             valor=5;
    //             break;
    //         case "j":
    //         case "ll":
    //         case "ñ":
    //         case "rr":
    //         case "x":
    //         case "k":
    //         case "w":
    //             valor=8;
    //             break;
    //         case "z":
    //             valor=10;
    //             break;

    //         default:
    //             valor=0;
    //             break;
    //     }


    //     return valor;
    // }






}
