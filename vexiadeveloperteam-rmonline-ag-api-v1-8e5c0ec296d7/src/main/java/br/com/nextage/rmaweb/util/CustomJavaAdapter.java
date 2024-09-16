/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.util;

import flex.messaging.messages.Message;
import flex.messaging.services.remoting.adapters.JavaAdapter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;

/**
 * <pre>
 *<b>author:</b> Marlos M. Novo
 *<b>date:  </b> 26/06/2012
 * </pre>
 *
 * <p>
 * Classe responsável por INTERCEPTAR as mensagens enviadas do FLEX para o JAVA,
 * onde contém informações de Cabeçalho, com dados como a Operação e
 * Serviço(service) a ser 'INVOKADO' pelo java.
 *
 * <p>
 * CUIDADO: Qualquer mudança nesta classe pode causar problemas com performance
 * nas RPC's (Chamadas de procedimento remoto) feitas pelo flex.
 */
public class CustomJavaAdapter extends JavaAdapter {

    @Override
    public Object invoke(Message message) {

        //Faz chamada para método de acordo com a message vinda do flex.
        Object obj = super.invoke(message);

        //Tenta fazer verificação e transformação de Date's para datas
        //com Hora == 12, para evitar problemas de Horário de verão
        //quando o objeto ir para o flex.
        try {
            if (obj != null) {
                //Caso seja lista percorre seus SubObjetos em busca de datas.
                if (obj instanceof List) {
                    //Itera sobre objetos da lista, verificando as datas
                    for (Object objLista : ((List) obj)) {
                        //Verifica se existe
                        boolean existeCampoData = verificaFieldsDateObj(objLista);
                        //Se não existir campo data em objeto da lista, da break
                        //para evitar processamento desnecessário.
                        if (!existeCampoData) {
                            break;
                        }
                    }
                } else {
                    //Caso não seja lista verifica apenas o objeto de retorno,
                    //para ver se há atributos de data.
                    verificaFieldsDateObj(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Retorna objeto retornado pelo método que foi requisitado pelo flex.
        return obj;
    }

    /**
     * <PRE>
     *<b>author:</b>  Marlos M. Novo
     *<b>date  :</b>  26/10/2012
     *<b>param :</b>  Object
     *<b>return :</b> boolean
     * </PRE>
     *
     * <p>
     * Verifica todos os fields com o tipo Date, para caso a data esteje com
     * hora, minuto e segundo = 0, indica ser uma apenas um Date realmente.
     * Então caso seja só uma data e não um DateTime, irá recuperar essa data
     * através de um Getter e utilizará do GragorianCallendar para fazer um Set
     * da hora para 12, evitando assim que quando chegue no flex acabe dando
     * problema com horário de verão.
     */
    private boolean verificaFieldsDateObj(Object obj) {

        boolean existDate = false;

        try {
            for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
                //Caso for private, modifica o acesso permitindo setar valores
                field.setAccessible(true);

                //Verifica se o tipo do atributo do objeto é Date(java.util)
                if (field.getType().isInstance(new Date())) {

                    try {
                        //Caso seja, recupero o meu atributo como um Getter.
                        Getter getter = PropertyAccessorFactory.getPropertyAccessor("field").getGetter(obj.getClass(), field.getName());

                        //Recupero meu objeto de data através do Getter no obj passado com parametro.
                        Date objData = (Date) getter.get(obj);

                        //Caso meu objeto seja diferente de null.
                        if (objData != null) {
                            //Seto minha data num GregorianCallendar.
                            GregorianCalendar calendar = new GregorianCalendar();
                            calendar.setTime(objData);

                            //Caso minha Hora, minuto e segundo seja == 0
                            if (calendar.get(GregorianCalendar.HOUR_OF_DAY) == 0
                                    && calendar.get(GregorianCalendar.MINUTE) == 0
                                    && calendar.get(GregorianCalendar.SECOND) == 0
                                    && calendar.get(GregorianCalendar.MILLISECOND) == 0) {

                                //Seto minha hora para 12.
                                calendar.set(GregorianCalendar.HOUR_OF_DAY, 12);
                                //Seto minha nova Date gerada no getTime() do GregorianCallendar
                                //no meu objeto de Date.
                                objData = calendar.getTime();

                                Setter setter = PropertyAccessorFactory.getPropertyAccessor("field").getSetter(obj.getClass(), field.getName());

                                setter.set(obj, objData, null);

                                existDate = true;
                            }
                        }
                    } catch (Exception e) {

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return existDate;
    }
}
