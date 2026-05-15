/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import Interfaces.IServicioBanco;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author juanl
 */
public class ConectorBanco implements IServicioBanco{

    @Override
    public boolean procesarPago(String cuenta, double monto) {
        try (Socket socket = new Socket("localhost", 9001);
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                DataInputStream entrada = new DataInputStream(socket.getInputStream())) {

               salida.writeUTF(cuenta);
               salida.writeDouble(monto);

               return entrada.readBoolean();

           } catch (IOException e) {
               System.out.println("Error de comunicación con el banco: " + e.getMessage());
               return false; 
           }
    }
}
