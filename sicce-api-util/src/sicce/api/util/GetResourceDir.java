

package sicce.api.util;

import java.net.URL;
import javax.swing.Icon;

/**
 *
 * @author Karu
 */
public class GetResourceDir
{
    public URL getResourceDir(String pResource)
    {
        URL resource = getClass().getResource(pResource);
        if (resource == null)
            resource = this.getClass().getClassLoader().getResource(pResource);
        return resource;
    }
    
    public Icon obtenerIconoRecurso(String pResource)
    {
        return new javax.swing.ImageIcon(getResourceDir(pResource));
        
    }
    
}
