package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

import java.util.regex.Pattern;



public class Diccionario implements Serializable {

    private static final String[] abecedario ={".","a","b","c","ch","d","e","f","g","h","i","j","k","l","ll","m","n",
    "ñ","o","p","q","r","rr","s","t","u","v","w","x","y","z"};
   
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
            if(inicial=='.'){
                path= path+"/especial/"+palabra.length()+".txt";
            }else{
                path= path+"/"+inicial+"/"+palabra.length()+".txt";
            }
        }else{
            if(inicial=='.'){
                path= path+"/especial/0"+palabra.length()+".txt";
            }else{
            path= path+"/"+inicial+"/0"+palabra.length()+".txt";
            }
        }

        File archivo = new File(path);
        FileReader archivoFileReader =new FileReader(archivo);
        BufferedReader brLinea = new BufferedReader(archivoFileReader); //<- asigno la linea como nula
        String linea;
        String[] palabras;
        linea = brLinea.readLine(); // <- inicio la variable br
        
        Pattern pat = Pattern.compile(palabra);
    


        while (linea != null && !valida){
     
            palabras = linea.split("\\s");
            
            //Datos del registro:
            // System.out.println(palabras[0]);

            java.util.regex.Matcher mat = pat.matcher(palabras[0]);
            
            if(palabras[0].equals(palabra) || mat.find()) valida=true;


            //leo la siguiente linea:
            linea = brLinea.readLine();
            
            }

        brLinea.close();
        archivoFileReader.close();
        
        return valida; 
    }

    public static boolean estaEnAbecedario(String s){
        boolean esta= false;
        int i=0;
        s=s.toLowerCase();
        while(i<abecedario.length && !esta){
            if(abecedario[i].equals(s) )esta=true;
            i++;

        }
        return esta;
    }

    }    


