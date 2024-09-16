package br.com.nextage.rmaweb.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gerencia upload e download de arquivos
 *
 * @author Nextage
 */
public class GerenciadorArquivos {

    /**
     * Classe Construtora default
     */
    public GerenciadorArquivos() {
    }

    /**
     * Método que realiza o upload.
     * <br>
     * <br>
     * <b>Autor:</b> Nextage.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param bytes - byte[]
     * @param fileName - String
     * @throws Exception - Exception
     */
    public void facaUpload(byte[] bytes, String fileName) throws Exception {
        /* Pega o caminho de dirtorio temporario e concatena com o nome do arquivo */
        fileName = System.getProperty("java.io.tmpdir") + fileName;
        File arquivo = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(arquivo)) {
            fos.write(bytes);
        }
    }

    /**
     * Método que Obtem os arquivos para a realização do download.
     * <br>
     * <br>
     * <b>Autor:</b> Nextage.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * Método que Obtem a lista de arquivos
     *
     * @return resposta - List<ArquivoVO>
     * @throws Exception - Exception
     */
    public List<ArquivoVO> getListaDownload() throws Exception {
        //File dir = new File(System.getProperty("c:\\teste.txt"));
        //String[] children = dir.list();
        List<ArquivoVO> resposta = new ArrayList<>();

        //if (children != null) {
        //for (int i = 0; i < children.length; i++) {
        resposta.add(new ArquivoVO("teste.txt", returnByteArray("c://teste.txt")));
        //}
        //}
        return resposta;
    }

    /**
     * Retorna o ByteArray de um arquivo baseado no nome do mesmo. Método
     * construtor para criar um objeto.
     * <br>
     * <br>
     * <b>Autor:</b> Nextage.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param fileName - String
     * @return data - byte[]
     * @throws Exception - Exception
     */
    private byte[] returnByteArray(String fileName) throws Exception {
        byte[] data = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            FileChannel fc = fis.getChannel();
            data = new byte[(int) (fc.size())];
            ByteBuffer bb = ByteBuffer.wrap(data);
            fc.read(bb);
            return data;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
