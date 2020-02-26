/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstacionDeServicio;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SrDeLorean
 */
public class ObservadorSucursal extends Observable implements Runnable{

    private int puerto;
    private DataInputStream dis;
    private DataOutputStream out;
    public ObservadorSucursal(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        //Host del servidor
        final String HOST = "localhost";
        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, puerto);
            dis = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            int opcion;
            double valor;
            while (true) {
                System.out.println("escuchando");
                opcion = dis.readInt();
                System.out.println("recibi:" + opcion);
                switch (opcion){
                    case 1:
                        actualizarPrecios();
                    case 2:
                        enviarTransacciones();
                    case 3:
                        test();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ObservadorSucursal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObservadorSucursal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizarPrecios() throws IOException, ClassNotFoundException{
        while (true) {
            int largo = dis.readInt();
            byte[] bytes= new byte[largo];
            for(int i=0; i<largo; i++){
                bytes[i]=dis.readByte();
            }
            ByteArrayInputStream bs= new ByteArrayInputStream(bytes); // bytes es el byte[]
            ObjectInputStream is = new ObjectInputStream(bs);
            Precios precios = (Precios)is.readObject();
            is.close();
        }
    }
    
    private void enviarTransacciones(){
        while (true) {
            
        }
    }
    
    private void test() throws IOException{
        out.writeUTF("recibi tu opcion y estoy respondiendote");
        System.out.println("paso por aca");
    }
}

