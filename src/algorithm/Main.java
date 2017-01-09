/**
 * 
 */
package algorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

/**
 * @author shubham
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	
	
	public Main(){
		
	}
	
	
	public int init(String[] args) throws Exception {

		if (args.length != 3) {
			System.err.println("Usage: inputfile outputfile compareFile");
			return 0;
		}
		long start = System.nanoTime();
		// Creates SimHash object.
		Simhash simHash = new Simhash(new BinaryWordSeg());

		// DocHashes is a list that will contain all of the calculated hashes.
		ArrayList<Long> docHashes = Lists.newArrayList();

		// Maps 12-bit key with the documents matching the partial hash
		Map<BitSet, HashSet<Integer>> hashIndex = Maps.newHashMap();

		// Read the documents. (Each line represents a document).

		String[] docs = getNgrams(args[0]);

		int idx = 0;
		

		System.out.println("Start to build index...");
		for (String doc : docs) {
			// Calculate the document hash.
			long docHash = simHash.simhash64(doc);
//			System.out.println("Document=[" + doc + "] Hash=[" + docHash + "]");

			// Store the document hash in a list.
			docHashes.add(docHash);

			// StringBuilder keyBuilder = new StringBuilder(12);
			BitSet key = new BitSet(12);

			int step = 0;

			for (int i = 0; i < 64; ++i) {
				key.set(step, ((docHash >> i) & 1) == 1);
				if (step++ == 12) {
					/*
					 * a) Separates the hash in 12-bit keys. b) This value will
					 * be a key in hashIndex. c) hashIndex will contain sets of
					 * documents matching each key (12-bits).
					 */
					if (hashIndex.containsKey(key)) {
						hashIndex.get(key).add(idx);
					} else {
						HashSet<Integer> vector = new HashSet<Integer>();
						vector.add(idx);
						hashIndex.put(key, vector);
					}
					step = 0;
					key = new BitSet(12); // reset key holder.
				}
			}
			++idx;
		}
		System.out.println("Index has been built.");
		File output = new File(args[1]);
		idx = 0;
		BitSet bits = new BitSet(docs.length);
		
		int ans=0;
		
		String[] docs2 = getNgrams(args[2]);

		for (String doc : docs2) {
			// For each document.

			if (bits.get(idx)) {
				++idx;
				continue;
			}

			// Calculates document hash.
			long docHash = simHash.simhash64(doc);
			BitSet key = new BitSet(12);

			int step = 0;
			HashSet<Integer> docSimilarCandidates = Sets.newHashSet();
			for (int i = 0; i < 64; ++i) {
				key.set(step, ((docHash >> i) & 1) == 1);

				if (step++ == 12) {
					/*
					 * a) Separates the hash in 12-bit keys. b) This value will
					 * be a key in hashIndex. c) hashIndex will contain sets of
					 * documents matching each key (12-bits).
					 */
					if (hashIndex.containsKey(key)) {
						docSimilarCandidates.addAll(hashIndex.get(key));
					}
					step = 0;
					key = new BitSet(12);
				}
			}
			List<Integer> similarDocs = Lists.newLinkedList();
			Map<Integer, Integer> docDistances = Maps.newHashMap();
			for (Integer i : docSimilarCandidates) {
				int dist = simHash.hammingDistance(docHash, docHashes.get(i));
				if (dist <= 3) {
					similarDocs.add(i);
					bits.set(i);
					docDistances.put(i, dist);
				}
			}
			if (!similarDocs.isEmpty()) {
				Files.append("Documents similar as [" + doc + "]:\n", output, Charsets.UTF_8);
				ans++;
				for (int i : similarDocs) {
//					if (i == idx)
//						continue;
					Files.append("[" + docs[i] + "]\tDistance=[" + docDistances.get(i) + "]\n", output, Charsets.UTF_8);
				}
				Files.append("End\n", output, Charsets.UTF_8);
			}
			bits.set(idx);
			++idx;
		}

		System.out.println("Elapsed time: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
		return ans;
	}

	private static List<String> readDocs(String[] args) throws IOException {
		return Files.readLines(new File(args[0]), Charsets.UTF_8);
	}
	
	private static String[] readDocs2(String text){
		String[] split = new String[text.length()];
		return split;
	}
	
	public String[] getNgrams(String str){
		String[] ans = str.split("\\s+");
		String[] grams = new String[ans.length+1];
		
		int count=0;
		for(int i=4;i<ans.length;i++){
			grams[count++] = ans[i-4] + " " + ans[i-3] + " " + ans[i-2] + " " + ans[i-1] + " " + ans[i];
		}
		
		String[] ans2 = new String[count];
		for(int i=0;i<count;i++){
			ans2[i] = grams[i];
		}
		return ans2;
	}
	
	
}
