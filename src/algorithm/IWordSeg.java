/**
 * 
 */
package algorithm;

import java.util.List;
import java.util.Set;

/**
 * @author shubham
 *
 */
public interface IWordSeg {

	public List<String> tokens(String doc);
	
	public List<String> tokens(String doc, Set<String> stopWords);
}
