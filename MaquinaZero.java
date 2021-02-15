import java.io.BufferedWriter;
import java.io.FileWriter;

public class MaquinaZero {
    String[] estado;
    char[] fita;
    int tamF;
    int cabeca;
    int estadoAtual;
    String arquivoSaida;

    public MaquinaZero (char[] fita, String arquivoSaida) {
        this.fita = fita;
        this.arquivoSaida = arquivoSaida;
        tamF = fita.length;
        cabeca = 0;
        estadoAtual = 0;
        estado = new String[5];
        estado[0] = "q0"; estado[1] = "q1"; estado[2] = "q2"; estado[3] = "q3"; estado[4] = "q4";
        iniciar();
    }

    private void iniciar () {
        imprimirFita();
        //--------------ESTADO Q0------------------
        if (cabeca != -1) { // B/B R -> q1
            if (fita[cabeca] == 'B') {

                fita[cabeca] = 'B';
                cabeca++;
                estadoAtual = 1; //entra em q1
                imprimirFita();
            }
            else {
                System.out.println("TRAVOU");
                imprimirFita();
                cabeca = -1;
            }
        }

        //--------------ESTADO Q1------------------
        if (cabeca != -1) {
            while (fita[cabeca] == '1') { // 1/1 R
                fita[cabeca] = '1';
                cabeca++;
                imprimirFita();
            }
            if (fita[cabeca] == 'B') { // B/B L
                fita[cabeca] = 'B';
                cabeca--;
                estadoAtual = 2; //entra em q2
                imprimirFita();
            }
            else {
                System.out.println("TRAVOU");
                imprimirFita();
                cabeca = -1;
            }
        }
        //--------------ESTADO Q2------------------
        if (cabeca != -1) {
            while (fita[cabeca] == '1') { // 1/B L
                fita[cabeca] = 'B';
                cabeca--;
                imprimirFita();
            }
            if (fita[cabeca] == 'B') { // B/B R
                fita[cabeca] = 'B';
                cabeca++;
                estadoAtual = 3; //entra em q3
                imprimirFita();
            }
            else {
                System.out.println("TRAVOU");
                imprimirFita();
                cabeca = -1;
            }
        }
        //--------------ESTADO Q3------------------
        if (cabeca != -1) {
            if (fita[cabeca] == 'B') { // B/1 L
                fita[cabeca] = '1';
                cabeca--;
                estadoAtual = 4; //entra em q4 (estado final)
                imprimirFita();
            }
        }
        //-------------ESTADO Q4 (FIM)--------------
    }

    private void imprimirFita() {
        String linha = "";
        for (int i=0; i < tamF; i++) {
            if (cabeca == i) {
                //System.out.printf("{%s}",estado[estadoAtual]);
                linha = linha + "{" + estado[estadoAtual] + "}";
            }
            //System.out.print(fita[i]);
            linha = linha + fita[i];
        }
        //System.out.println();
        escrever("argumento2.txt", linha);
    }

    private void escrever(String path, String texto) {
        try {
            BufferedWriter conexao = new BufferedWriter(new FileWriter(path,true));
            conexao.write(texto);
            conexao.newLine();
            conexao.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}