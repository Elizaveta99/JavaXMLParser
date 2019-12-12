package XML;

import Model.ColdSteelCollection;

/**
 * Provides parse function
 * @author Elizaveta Rudko
 * @version 1.0.0
 */
public interface ColdSteelCollectionParser {
    ColdSteelCollection parse(String fileName) throws Exception;
}

