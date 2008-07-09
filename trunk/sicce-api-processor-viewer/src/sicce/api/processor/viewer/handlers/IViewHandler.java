/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor.viewer.handlers;

/**
 *
 * @author gish@c
 */
public interface IViewHandler {

    void HandlePowerMeterVisibility(String powerMeterSerial, boolean state);
}
