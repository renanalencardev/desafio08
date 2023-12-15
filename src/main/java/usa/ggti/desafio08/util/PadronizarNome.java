package usa.ggti.desafio08.util;

import java.util.Arrays;
import java.util.List;

public class PadronizarNome {
    public String primeiraLetraMaiuscula(String nomeCompleto){
        nomeCompleto = nomeCompleto.toLowerCase();
        List<String> preposicoes = Arrays.asList("de", "do", "da", "dos", "das");

        String[] nomes = nomeCompleto.split(" ");
        String[] nomesCorrigidos = new String[nomes.length];
        String nomeCompletoCorrigido = "";

        for (int i = 0; i < nomes.length; i++) {
            if (!preposicoes.contains(nomes[i])) {
                nomesCorrigidos[i] = nomes[i].substring(0, 1).toUpperCase() + nomes[i].substring(1);
            } else {
                nomesCorrigidos[i] = nomes[i];
            }
        }
        for (String nome: nomesCorrigidos) {
            nomeCompletoCorrigido += nome + " ";
        }
        return nomeCompletoCorrigido.trim();
    }
}
