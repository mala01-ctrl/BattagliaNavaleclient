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
    private Scanner user;

    public BattagliaNavaleClient(String serverAddress) throws IOException
    {
        socket = new Socket(serverAddress, 55555);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
        user = new Scanner(System.in);
    }

    public void Orientamento() {
        char risposta = user.next().charAt(0);
        do {
            if (!Character.isUpperCase(risposta))
                risposta = Character.toUpperCase(risposta);
            if (!checkCharacter(risposta))
                System.out.println("Digitare risposta nel formato (S/N)");
        } while (!checkCharacter(risposta));
        out.println(risposta);
    }
    
    public boolean checkCharacter(char risposta)
    {
        if (risposta == 'S')
            return true;
        if (risposta == 'N')
            return true;
        return false;
    }
    
    public void insertRighe() {
        boolean errore = false;
            try {
                int x = user.nextInt();
            } catch (Exception e) {
                errore = true;
                System.out.println("Inserire solo lettere");
            }
        /*boolea} while (errore);n errore = false;
        do {
            if (checkRighe(user.next())) {
                System.out.println("Errore");
                errore = true;
            }
            char risposta = user.next().charAt(0);
            if (!Character.isUpperCase(risposta)) {
                risposta = Character.toUpperCase(risposta);
            }
        } while (errore);*/
    }
    
    public boolean checkRighe(String risposta)
    {
        String pattern = "[a-zA-Z] *";
        return risposta.matches(pattern);
    }
    
    public Socket getSocket() {
        return socket;
    }
    
    public void selectUsername()
    {
        out.println(user.next());
    }
    
    public void play()
    {
        System.out.println(in.nextLine());
        selectUsername();
        System.out.println(in.nextLine());
        while (in.hasNextLine()) {
            String response = in.nextLine();
            System.out.println(response);
            if (response.equals("Posizionare navi in verticale?S/N")) {
                Orientamento();
            }
            if (response.equals("Digitare numero righe"))
                insertRighe();
        }
    }
    
    public static void main(String args[]) throws IOException 
    {
        BattagliaNavaleClient client = new BattagliaNavaleClient("127.0.0.1");
        client.play();
    }
    
    
}
