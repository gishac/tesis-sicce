/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.processor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sicce.api.businesslogic.PowerMeterBizObject;
import sicce.api.info.ConstantsProvider.RequestType;
import sicce.api.info.exceptions.InvalidModbusResponseException;
import sicce.api.info.interfaces.IModbusResponse;
import sicce.api.info.interfaces.IPowerMeter;
import sicce.api.info.interfaces.IPowerMeterWatcher;

/**
 *
 * @author gish@c
 */
public class PowerMeterWatcher extends Observable implements IPowerMeterWatcher {

    /**
     * Medidor a ser observado
     */
    private IPowerMeter powerMeter;

    public IPowerMeter getPowerMeter() {
        return this.powerMeter;
    }
    /**
     * Gestor de logica para el medidor
     */
    private PowerMeterBizObject powerMeterBizObject;

    public PowerMeterBizObject getpowerMeterBizObject() {
        return powerMeterBizObject;
    }

    /**
     * 
     * @param powerMeter
     */
    public PowerMeterWatcher(IPowerMeter powerMeter) {
        this.powerMeter = powerMeter;
        this.powerMeterBizObject = new PowerMeterBizObject();
    }

    /**
     * Realiza el proceso de lectura del medidor
     */
    public void Watch() {
        try {
//            //Lee los datos del medidor y los guarda en la base\
//            String[] ip = this.powerMeter.getIpAddress().split(".");
//            byte[] ipb = new byte[4];
//            ipb[0] = (byte)192;
//            ipb[1] = (byte)168;
//            ipb[2] = (byte)8;
//            ipb[3] = (byte)42;
//
//            InetAddress ipaddress = InetAddress.getByAddress(this.powerMeter.getDescription(), ipb);
//            Socket xocket = new Socket(ipaddress, 2101);
//
//            OutputStream output = xocket.getOutputStream();
//            InputStream input = xocket.getInputStream();
//            BufferedInputStream binput = new BufferedInputStream(xocket.getInputStream());
//            String[] trama = {"70", "01", "03", "03", "00", "00", "62", "C4", "67"};
//            trama[1] = this.powerMeter.getSerial();
//
//            write(output, trama);
//
//
//            read(xocket.getInputStream(), 200);
//
//            xocket.close();


            /*int lectura = new Random().nextInt(10000);
            setChanged();
            notifyObservers(lectura);*/

            HashMap<RequestType, IModbusResponse> powerMeterData = powerMeterBizObject.ReadPowerMeterData(this.powerMeter);
            powerMeterBizObject.ProcessPowerMeterData(powerMeterData);


        } catch (UnknownHostException ex) {
            Logger.getLogger(PowerMeterWatcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PowerMeterWatcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidModbusResponseException ex) {
            Logger.getLogger(PowerMeterWatcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PowerMeterWatcher.class.getName()).log(Level.SEVERE, null, ex);



        /*int lectura = new Random().nextInt(10000);
        setChanged();
        notifyObservers(lectura);*/
        }
    }

    /**
     * 
     * @param observer
     */
    public void AddObserver(Observer observer) {
        addObserver(observer);
    }

    private static int getIntValue(String value) {
        int result;
        result = getHexValue(value.charAt(0)) * 16 + getHexValue(value.charAt(1));
        return result;
    }

    private static int read(InputStream in, int num_chars) throws Exception {
        int valuex = 0;
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < num_chars; i++) {
            valuex = in.read();
            String toHex = Integer.toHexString(valuex).toUpperCase();
            toHex = (toHex.length() == 1 ? "0" + toHex : toHex);
            //System.out.println((i<10?"0"+i:""+i)+": "+toHex);
            buf.append(toHex);
        }
        System.out.println(buf.toString());
        return valuex;
    }

    public static void write(OutputStream out, String[] trama) throws Exception {
        for (int i = 0; i < trama.length; i++) {
            System.out.print(" " + trama[i]);
            out.write(getIntValue(trama[i]));
        }
        System.out.println("");
    }

    public static int getHexValue(char value) {
        int result = 0;
        switch (value) {
            case '0':
                result = 0;
                break;
            case '1':
                result = 1;
                break;
            case '2':
                result = 2;
                break;
            case '3':
                result = 3;
                break;
            case '4':
                result = 4;
                break;
            case '5':
                result = 5;
                break;
            case '6':
                result = 6;
                break;
            case '7':
                result = 7;
                break;
            case '8':
                result = 8;
                break;
            case '9':
                result = 9;
                break;
            case 'A':
                result = 10;
                break;
            case 'B':
                result = 11;
                break;
            case 'C':
                result = 12;
                break;
            case 'D':
                result = 13;
                break;
            case 'E':
                result = 14;
                break;
            case 'F':
                result = 15;
                break;
        }
        return result;
    }
}
