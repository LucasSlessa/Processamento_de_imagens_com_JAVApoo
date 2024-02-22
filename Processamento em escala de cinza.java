package arqvuivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import javax.imageio.ImageIO;
import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Arquivo {

    public static void salvarDadosArquivo(String caminho,String dados) {
        File file = new File(caminho);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append(dados);
            writer.close();
        }catch(Exception e ){}
    }
    public static void gerarArquivoTeste() {
        char dna []     = {'A', 'C', 'G', 'T'};
        Random random   = new Random();      
        
        StringBuilder sequenciaDNA = new StringBuilder();
        int i =0;
        int sorteio =0;
        while (i<1048576){ // 1024*1024 = 1Mb
            sorteio=random.nextInt(3);
            char letraSorteada= dna[sorteio];
            sequenciaDNA.append(letraSorteada);
            i++;
        }
        File file = new File("C:\\temp\\arquivoTeste.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append(sequenciaDNA);
            writer.close();
        }catch(Exception e ){

        }
    }

    public static BufferedImage converterIamagemCinza(String caminho) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(caminho));
        } catch (IOException e) {

        }
        int altua = img.getWidth();
        int largura = img.getHeight();
        for (int linha = 0; linha < altua; linha++) {
            for (int coluna = 0; coluna < largura; coluna++) {
                float r = new Color(img.getRGB(linha, coluna)).getRed();
                float g = new Color(img.getRGB(linha, coluna)).getGreen();
                float b = new Color(img.getRGB(linha, coluna)).getBlue();
                int grayScaled = (int) (r + g + b) / 3;
                img.setRGB(linha, coluna, new Color(grayScaled, grayScaled, grayScaled).getRGB());
            }
        }
        return img;
    }

    public static void SalvarImagem(BufferedImage img, String caminho) {
        File outputfile = new File(caminho);
        try {
            ImageIO.write(img, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean moverArquivo(String origem, String destino) {

        File fileToMove = new File(origem);

        return fileToMove.renameTo(new File(destino));
    }

    public static String lerArquivo(String caminho) {
        BufferedReader buffRead;
        StringBuilder strBuilder = new StringBuilder();
        try {
            buffRead = new BufferedReader(new FileReader(caminho));
            String linha = "";
            do {
                linha = buffRead.readLine();
                if (linha != null) {
                    strBuilder.append(linha + "\n");
                }
            } while (linha != null);
            buffRead.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strBuilder.toString();

    }

    public static void main(String[] args) {

        String resposta = Arquivo.lerArquivo("c:\\temp\\arquivoTeste.txt");
        System.out.println(resposta);

        //BufferedImage imgCinza = Arquivo.converterIamagemCinza("c:\\temp\\praia.jpg");
        //Arquivo.SalvarImagem(imgCinza, "c:\\temp\\processado\\imgTeste.jpg");

        //Arquivo.salvarDadosArquivo("C:\\temp\\arquivoTeste2.txt","ola Mundo");

        //Arquivo.gerarArquivoTeste();
        // Arquivo.moverArquivo("c:\\temp\\teste.txt","c:\\temp\\processado\\teste.txt");

    }
}
