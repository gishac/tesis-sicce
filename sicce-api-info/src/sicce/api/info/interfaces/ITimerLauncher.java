/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sicce.api.info.interfaces;

/**
 * Define los metodos a ser implementados por las clases que representan a los objetos que
 * ejecutan tareas en periodos de tiempos programados
 * @author gish@c
 */
public interface ITimerLauncher {
    
    /**
     * Ejecuta la tarea
     */
    public void run();
}
