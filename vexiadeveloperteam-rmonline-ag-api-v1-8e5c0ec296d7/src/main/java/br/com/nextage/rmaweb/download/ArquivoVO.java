package br.com.nextage.rmaweb.download;

/**
 * Classe Value Object para manipular valores em um objeto.
 *
 * @author Nextage.
 */
public class ArquivoVO {

    private String nome;
    private byte[] byteArray;

    /**
     * Método construtor para criar um objeto.
     * <br>
     * <br>
     * <b>Autor:</b> Nextage.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param nome - String
     * @param byteArray - byte[]
     */
    public ArquivoVO(String nome, byte[] byteArray) {
        this.nome = nome;
        this.byteArray = byteArray;
    }

    /**
     * Método que rObtem o nome do arquivo.
     * <br>
     * <br>
     * <b>Autor:</b> Nextage.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @return the nome - String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que seta o nome do arquivo.
     * <br>
     * <br>
     * <b>Autor:</b> Nextage.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param nome - String
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que Obtem o array de bytes.
     * <br>
     * <br>
     * <b>Autor:</b> Nextage.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @return byteArray - byte[]
     */
    public byte[] getByteArray() {
        return byteArray;
    }

    /**
     * Método que seta o array de bytes.
     * <br>
     * <br>
     * <b>Autor:</b> Nextage.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param byteArray - byte[]
     */
    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

}
