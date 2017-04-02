import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    public static void main(String[] args) throws IOException {

        int port = 7777; // случайный порт (может быть любое число от 1025 до 65535)

        // создаем сокет сервера и привязываем его к вышеуказанному порту
        ServerSocket serverSocket = new ServerSocket(port);

        // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
        System.out.println("Waiting for a client...");
        Socket socketClient = serverSocket.accept();
        System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
        System.out.println();


        // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
        InputStream inputStream = socketClient.getInputStream();
        OutputStream outputStream = socketClient.getOutputStream();

        // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        String line = null;
        while (true) {

            // ожидаем пока клиент пришлет строку текста.
            line = dataInputStream.readUTF();
            System.out.println("The dumb client just sent me this line : " + line);
            System.out.println("I'm sending it back...");

            // отсылаем клиенту обратно ту самую строку текста.
            dataOutputStream.writeUTF(line);
            // заставляем поток закончить передачу данных.
            dataOutputStream.flush();
            System.out.println("Waiting for the next line...");
            System.out.println();

        }
    }
}



