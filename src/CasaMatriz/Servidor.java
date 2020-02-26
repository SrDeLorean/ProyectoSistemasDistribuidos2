package CasaMatriz;

import java.net.*;
import java.io.*;
import EstacionDeServicio.Precios;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SrDeLorean
 */
public class Servidor extends Observable implements Runnable{
    
    private ArrayList<Socket> estacionesdeservicio;
    private int puerto;

    public Servidor(int puerto) {
        this.estacionesdeservicio = new ArrayList<>();
        this.puerto = puerto;
    }

    @Override
    public void run() {
        ServerSocket servidor=null;
        Socket sc= null;
        DataInputStream in;
        try{
            servidor = new ServerSocket(puerto);
            while(true){
                sc = servidor.accept();
                System.out.println("estacion de servicio conectada");
                this.estacionesdeservicio.add(sc);
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarInformacionDelPrecioDeUnaEstacion(int idEstacion, Precios precios){
        try {
            DataOutputStream dos = new DataOutputStream(this.estacionesdeservicio.get(idEstacion).getOutputStream());
            dos.write(1);
            ByteArrayOutputStream bs= new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream (bs);
            os.writeObject(precios);
            os.close();
            byte[] bytes =  bs.toByteArray();
            dos.write(bytes.length);
            for(byte byte1 : bytes){
                dos.write(byte1);
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarInformacionDelPrecioDeTodasLasEstaciones(Precios precios) {
        for (Socket sock : this.estacionesdeservicio) {
            try {
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                dos.write(1);
                ByteArrayOutputStream bs= new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream (bs);
                os.writeObject(precios);
                os.close();
                dos.write(bs.toByteArray());
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void obtenerInformacionDeLaEstacion(int idEstacion){
        try {
            DataOutputStream dos = new DataOutputStream(this.estacionesdeservicio.get(idEstacion).getOutputStream());
            dos.write(2);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void test(){
        try {
            DataInputStream in = new DataInputStream(this.estacionesdeservicio.get(0).getInputStream());
            DataOutputStream dos = new DataOutputStream(this.estacionesdeservicio.get(0).getOutputStream());
            dos.writeInt(3);
            System.out.println(in.readUTF());
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Socket> getEstacionesdeservicio() {
        return estacionesdeservicio;
    }
    
    
    
}
