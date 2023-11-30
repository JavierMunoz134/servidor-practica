package org.example;

import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.Scanner;

public class ClienteTexto {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9877);

            // Leer texto desde la consola
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese texto:");
            String texto = scanner.nextLine();

            // Codificar en base64 y enviar al servidor
            String encodedText = Base64.getEncoder().encodeToString(texto.getBytes());
            String message = "#" + encodedText + "#";

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(message);
            writer.flush();

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
