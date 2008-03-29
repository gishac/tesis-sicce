/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sicce.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gish@c
 */
public class RegexValidator {
    
    private static String regexIPAddress = "\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";

    public static int ValidateIPAddress(String value) {
        return Match(regexIPAddress, value);
    }

    private static int Match(String regex, String value) {
        int matches = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }
}
