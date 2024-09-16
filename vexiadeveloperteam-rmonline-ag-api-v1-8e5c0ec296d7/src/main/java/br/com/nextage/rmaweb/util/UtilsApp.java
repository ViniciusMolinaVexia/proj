package br.com.nextage.rmaweb.util;

import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.apache.commons.lang.StringUtils.EMPTY;

public class UtilsApp {
    static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Transforma um objeto tem string;
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static final <T> String string(final T obj) {
        if (obj != null) {
            return String.valueOf(obj);
        }
        return StringUtils.EMPTY;
    }

    /**
     * Transforma uma data em string com um determinado formato;
     *
     * @param obj
     * @param pattern
     * @param <T>
     * @return
     */
    public static final <T> String stringDate(final T obj, String pattern) {
        if (Strings.isNullOrEmpty(pattern)) {
            pattern = "dd/MM/yyyy";
        }
        if (obj != null && obj instanceof Date) {
            sdf.applyPattern(pattern);
            return sdf.format((Date) obj);
        }
        return StringUtils.EMPTY;
    }

    public static final <T> String stringDate(final T obj) {
        return stringDate(obj, null);
    }

    public static <T> String stringSafe(final Optional<T> obj) {
        return obj.isPresent() ? (String) obj.get() : EMPTY;
    }
    
    public static void upload(String pasta, String nomeDoArquivo, InputStream arquivoCarregado) throws FileNotFoundException { 
    	String caminhoArquivo = pasta + "/" + nomeDoArquivo; 
    	File novoArquivo = new File(caminhoArquivo); 
    	FileOutputStream saida = new FileOutputStream(novoArquivo); 
    	copiar(arquivoCarregado, saida);
    	}
    
    public static void copiar(InputStream origem, OutputStream destino) {
    	int bite = 0; byte[] 
    	tamanhoMaximo = new byte[1000000 * 8]; // 8KB
    	try { 
    	// enquanto bytes forem sendo lidos 
    	   while((bite = origem.read(tamanhoMaximo)) >= 0) { 
    	    // pegue o byte lido e escreva no destino 
    	     destino.write(tamanhoMaximo, 0, bite);
    	     } 
    	   } catch (IOException e) { 
    	     // TODO Auto-generated catch block e.printStackTrace();
    	      } 
    	  }
    
}
