import java.util.Iterator;

import static com.gslab.pepper.input.FieldDataFunctions.*;
import static com.gslab.pepper.input.CustomFunctions.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import com.gslab.pepper.model.PlaintextMessage;

public class {{JAVA_CLASS_PLACEHOLDER}} implements Iterator<Object> {

 private static {{OBJ_CLASS}} serializedObj = new {{OBJ_CLASS}}();
 private static StringBuilder builder = new StringBuilder();

 @Override
 public boolean hasNext() {
     return true;
 }

 @Override
 public Object next() {

     {{MESSAGE_PLACE_HOLDER}}

     try {
         Method setHeaders = {{OBJ_CLASS}}.class.getMethod("setHeaders", new Class[] { String.class });
         builder.setLength(0);
         String headers = {{HEADER_PLACE_HOLDER}};
         setHeaders.invoke(serializedObj, headers);
     } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {

     }

     return serializedObj;
 }

 @Override
 public void remove() {
 }
}