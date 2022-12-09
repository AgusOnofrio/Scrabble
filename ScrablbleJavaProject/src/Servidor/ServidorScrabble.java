package Servidor;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Partida;
import Modelo.Interfaces.IPartida;
import Serializacion.Serializador;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.servidor.Servidor;

public class ServidorScrabble {
    // public static void main(String[] args) {
		public ServidorScrabble(Object partidaRecuperada){
		ArrayList<String> ips = Util.getIpDisponibles();
		String ip = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione la IP en la que escuchará peticiones el servidor", "IP del servidor", 
				JOptionPane.QUESTION_MESSAGE, 
				null,
				ips.toArray(),
				null
		);
		String port = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione el puerto en el que escuchará peticiones el servidor", "Puerto del servidor", 
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				8888
		);
		try {
			
			IPartida modelo;
			if(partidaRecuperada!=null){
				modelo= (IPartida)partidaRecuperada;
			}else{
				modelo = new Partida();
			}
            Servidor servidor = new Servidor(ip, Integer.parseInt(port));
			servidor.iniciar(modelo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RMIMVCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
