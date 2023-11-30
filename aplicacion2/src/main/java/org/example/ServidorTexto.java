package org.example;

import java.io.*;
import java.net.*;
import java.util.Base64;

public class ServidorTexto {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9877);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                // Manejar cada conexión en un hilo separado
                new Thread(() -> {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        String message = reader.readLine();

                        // Extraer el texto codificado en base64 eliminando delimitadores
                        String encodedText = message.substring(1, message.length() - 1);

                        // Decodificar y escribir en un archivo
                        byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
                        String decodedText = new String(decodedBytes);

                        try (PrintWriter writer = new PrintWriter(new FileWriter("archivo.txt", true))) {
                            writer.println(decodedText);
                        }

                        clientSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
