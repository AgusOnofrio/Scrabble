package Modelo;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Diccionario {

    private static String[] palabrasDiccionario ={"a","b","c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                                             "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static boolean validarPalabra(String palabra) throws IOException{

        if(palabra.length()<2) return false;


        boolean valida=false;
        palabra=palabra.toLowerCase();
        String path="src/diccionario";
         
        char inicial= palabra.toLowerCase().toCharArray()[0];
        if(palabra.length()>9){
            path= path+"/"+inicial+"/"+palabra.length()+".txt";
        }else{
            path= path+"/"+inicial+"/0"+palabra.length()+".txt";
        }

        File archivo = new File(path);
        FileReader archivoFileReader =new FileReader(archivo);
        BufferedReader brLinea = new BufferedReader(archivoFileReader); //<- asigno la linea como nula
        String linea;
        String[] palabras;
        linea = brLinea.readLine(); // <- inicio la variable br
        
        while (linea != null){
     
            palabras = linea.split("\\s");
            
            //Datos del registro:
            System.out.println(palabras[0]);
            
            if(palabras[0].equals(palabra)) valida=true;


            //leo la siguiente linea:
            linea = brLinea.readLine();
            
            }

        brLinea.close();
        archivoFileReader.close();
        
        return valida; 
    }

    }    


