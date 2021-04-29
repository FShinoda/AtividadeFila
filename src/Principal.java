import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;

public class Principal {
    public static void main(String[] args) throws Exception {
        

        Fila<Reserva> listaReserva = new Fila<Reserva>();
        Fila<Reserva> listaEspera = new Fila<Reserva>();

        int opcao;
        int posRes = 0, posEsp = 0;

        do{
            try{
                opcao = parseInt(showInputDialog(mostrarMenu()));

                switch(opcao){
                    case 1: // Reservar mesa

                    break;
                    case 2: //Pesquisar reserva

                    break;
                    case 3: //Imprimir Reservas   

                    break;
                    case 4: //Imprimir Espera

                    break;
                    case 5: //Cancelar reserva  
                        
                    break;
                    case 6: //Finaliza o programa
                    break;

                    default:
                        showMessageDialog(null, "Opção inválida! Por favor tente novamente");
                }
                
            } catch(NumberFormatException e){
                showMessageDialog(null, "Por favor digite um número!");
                opcao = 0;
            }


        } while(opcao != 6);
    }
        
        
        
    

    public static String mostrarMenu(){
        String menu = "";

        menu += "1. Reservar mesa\n";
        menu += "2. Pesquisar reserva\n";
        menu += "3. Imprimir reservas\n";
        menu += "4. Imprimir lista de espera\n";
        menu += "5. Cancelar reserva\n";
        menu += "6. Finalizar\n";

        return menu;
    }
}
