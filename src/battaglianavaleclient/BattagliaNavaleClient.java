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
        char risposta;
        do {
            risposta = user.next().charAt(0);
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
    
    public void insertRighe() 
    {
        char risposta;
        do {
            risposta = this.user.next().charAt(0);
            if (!Character.isLetter(risposta))
                System.out.println("Digitare una lettera (A-V)");
            if (!Character.isUpperCase(risposta)) {
                risposta = Character.toUpperCase(risposta);
            }
        } while (!Character.isLetter(risposta));
        out.println(risposta);
    }
    
    public void insertColonne()
    {
        char risposta;
        do{
            risposta = user.next().charAt(0);
            if (!Character.isDigit(risposta))
                System.out.println("Errore");
        }while(!Character.isDigit(risposta));
        out.println(risposta);
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
            if (response.equals("Posizionare navi in verticale?S/N"))
                Orientamento();
            if (response.equals("Digitare lettera delle colonne"))
                insertRighe();
            if (response.equals("Digitare numero delle righe"))
                insertColonne();
            
        }
    }
    
    public static void main(String args[]) throws IOException 
    {
        BattagliaNavaleClient client = new BattagliaNavaleClient("127.0.0.1");
        client.play();
    }
    
    
}
