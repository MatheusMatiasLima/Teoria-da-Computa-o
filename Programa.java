import java.io.*;

public class Programa {
    public static void main(String[] args) {

        String linha = lerUltimaLinha(args[0]);
        char[] fita = transformarEmVetorDeChar(linha);
        MaquinaZero zero = new MaquinaZero(fita, args[1]);
    
    }

    private static String lerUltimaLinha(String nomeDoArquivo) {
        String Ulinha = "Vazio";
        try {
            BufferedReader arq = new BufferedReader(new FileReader(nomeDoArquivo));
            String linha = arq.readLine();
            while (linha != null) {
                Ulinha = linha;
                linha = arq.readLine();
            }
            arq.close();
        } catch (FileNotFoundException e) {
            System.out.print("Falha ao abrir o arquivo: " + e);
        } catch (IOException e) {
            System.out.print("Falha na leitura do arquivo: " + e);
        }
        return Ulinha;
    }

    private static char[] transformarEmVetorDeChar(String linha) {
        char[] letras = linha.toCharArray();
        return letras;
    }
}