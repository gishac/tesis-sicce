/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.info.interfaces;

/**
 * Define los metodos a ser implementados por las clases que representen a los resultados de una consulta simple
 * @author gish@c
 */
public interface ISimpleQueryResult {

    /**
     * Devuelve el resultado de la consulta
     * @return Resultado de la consulta
     */
    public String getResult();

    /**
     * Establece el resultado de la consulta
     * @param result Resultado de la consulta
     */
    public void setResult(String result);

}
