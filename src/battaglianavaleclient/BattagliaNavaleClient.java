/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavaleclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author lorenzo
 */
public class BattagliaNavaleClient {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public BattagliaNavaleClient(String serverAddress) throws IOException
    {
        socket = new Socket(serverAddress, 55555);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void insertCoordinate() {
        Scanner user = new Scanner(System.in);
        out.println(user.next());
    }
    
    public Socket getSocket() {
        return socket;
    }
    
    public void selectUsername()
    {
        Scanner user = new Scanner(System.in);
        out.println(user.next());
    }
    
    public void play()
    {
        selectUsername();
        String giocatore = in.nextLine();
        System.out.println("Battaglia navale: Player " + giocatore);
        while (in.hasNextLine()) {
            String response = in.nextLine();
            System.out.println(response);
            if (response.equals("Inserisci le coordinate della prima nave")) {
                insertCoordinate();
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BattagliaNavaleClient client = new BattagliaNavaleClient("127.0.0.1");
        System.out.print(client.in.nextLine());
        client.play();
    }
    
}
