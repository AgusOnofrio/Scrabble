package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Diccionario implements Serializable {

    private static final String[] abecedario ={"a","b","c","ch","d","e","f","g","h","i","j","k","l","ll","m","ñ","o","p","q","r","rr","x","y","z"};
   
    public static boolean validarPalabra(String palabra) throws IOException{

        palabra=palabra.toLowerCase();

        if(palabra.length()<2) return false;
        
        for (String letra : abecedario) {
            if(letra.equals(palabra)) return false;
        }

        boolean valida=false;
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


