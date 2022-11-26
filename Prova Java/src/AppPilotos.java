import java.io.IOException;
import java.util.Scanner;

import aplicativos.*;
public class AppPilotos {

    public static void main(String[] args) throws InterruptedException, IOException {
        int MAX_ELEMENTOS = 20;
        int opcao, qtdCadastrados = 0;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
        
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }
                try {
                 //Cadastre seu piloto aqui
                    Piloto piloto = new Piloto();
                 System.out.println("Nome do piloto:" );
                 piloto.setNome(in.nextLine());
                 System.out.println("CPF do piloto:" );
                 piloto.setCpf(in.nextLine());
                 System.out.println("Breve do piloto:" );
                 piloto.setBreve(in.nextLine());
                 System.out.println("Matricula do piloto:" );
                 piloto.setMatricula(in.nextInt());
                 
                 in.nextLine();
                 
                 pilotos[qtdCadastrados] = piloto;
                qtdCadastrados++;

                 System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);
            
            } catch (Exception e) {
                in.nextLine();
                System.out.println("\nFavor insira um número.");
                
                voltarMenu(in);
            }
            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há motoristas cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                // Exiba os pilotos aqui

                for(Pessoa piloto : pilotos){
                    if(piloto != null){
                        System.out.printf("\nNome: %s\n", piloto.getNome());
                        System.out.printf("\nCPF: %s\n", piloto.getCpf());
                        System.out.printf("\nBreve: %s\n", ((Piloto) piloto).getBreve());
                        System.out.printf("\nMatricula: %s\n", ((Piloto) piloto).getMatricula());
                        
                    }
                }

                voltarMenu(in);
            
            } else if (opcao == 3) {
                try{
                    boolean key = false;
                Pessoa pilotoEncontrado = null;
                System.out.print("\nBUSCA: CPF do Piloto: ");
                String buscaCpf = in.nextLine();
                
                for(int i=0; !key && i<pilotos.length; i++){
                    key = buscaCpf.equals(pilotos[i].getCpf());
                    pilotoEncontrado = pilotos[i];        
                }

                if(key){
                    System.out.printf("CPF: " + pilotoEncontrado.getCpf() + " Cadastrado.");
                
                }
                } catch(Exception e) {
                    System.out.println("\nPiloto não cadastrado.");
                }
                voltarMenu(in);
                
            } else if (opcao == 4) {
                try{
                    int novoespaço = 0;
                    System.out.println("\nQuantos espaços você gostaria de adicionar?: ");
                    novoespaço = in.nextInt();
                    
                    in.nextLine();
                    voltarMenu(in);

                    Pessoa pilotos2 [] = new Pessoa[pilotos.length + novoespaço];
                    for (int i = 0; i < pilotos.length; i++){
                        pilotos2[i] = pilotos[i];
                    }
                    MAX_ELEMENTOS = pilotos2.length;
                    pilotos = pilotos2;
                } catch(Exception e){
                    System.out.println("Favor digite um número válido.");
                    in.nextLine();
                    voltarMenu(in);
                }
            }
                
               

            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}