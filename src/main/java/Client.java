import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        int serverPort = 7777;
        String address = "127.0.0.1";

        InetAddress ipAddress = InetAddress.getByName(address);

        //Socket socket = new Socket("127.0.0.1",7777);
        Socket socket = new Socket(ipAddress, serverPort);

        // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        // Создаем поток для чтения с клавиатуры.
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
        System.out.println();
        String line = null;

        while (true) {
            // ждем пока пользователь введет что-то и нажмет кнопку Enter.
            line = bufferedReader.readLine();

            System.out.println("Sending this line to the server...");
            // отсылаем введенную строку текста серверу.
            dataOutputStream.writeUTF(line);
            // заставляем поток закончить передачу данных.
            dataOutputStream.flush();

            line = dataInputStream.readUTF();
            System.out.println("The server was very polite. It sent me this : " + line);
            System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
            System.out.println();

        }


    }
};
