package add.haslearntit.utils;

import static ch.lambdaj.Lambda.argument;

public class Utils {

    public static String property(Object argument) {
        
    	return argument(argument).getInkvokedPropertyName();
    }
	
}
