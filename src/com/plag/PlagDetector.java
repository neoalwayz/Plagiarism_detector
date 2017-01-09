package com.plag;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import algorithm.Main;

public class PlagDetector {
	
	public void compare4(){
		
	}
	
	public double compareString(String a, String b, int flag) throws Exception{
		String[] agram = getNgrams(a);
		String[] bgram = getNgrams(b);
		
		
		int count1=0, count2=0, total1 = agram.length, total2 = bgram.length;
		for(int i=0;i<agram.length;i++){
			for(int j=0;j<bgram.length;j++){
				if(agram[i].compareTo(bgram[j])==0){
					count1++;
					break;
				}
			}
		}
		
		for(int i=0;i<bgram.length;i++){
			for(int j=0;j<agram.length;j++){
				if(bgram[i].compareTo(agram[j])==0){
					count2++;
					break;
				}
			}
		}
		
		double ans2 = ((double)(count2))/total2;
		System.out.println("SImilarity: " + ans2);
		
		double ans = ((double)(count1))/total1;
		System.out.println("Similarity: " + ans);
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		Main simhash = new Main();
		int same_count = simhash.init(new String[]{a, "dumpfile_" +flag, b});
		
		double hash_ans = (double)same_count/agram.length;
		double hash_ans2 = (double)same_count/bgram.length;
		System.out.println("Similarity hashes: " + hash_ans + "\t" + hash_ans2);
		System.out.println("Count: " + same_count + "\nSimple Count: " + count1 + "\nLength: " + bgram.length );
		
		if(ans<1.0){
			if(hash_ans2>ans){
				return hash_ans2;
			} else{
				return ans;
			}
		} else if(ans2<=1.0){
			if(hash_ans2>ans2){
				return hash_ans2;
			} else{
				return ans2;
			}
		} else{
			return hash_ans2;
		}
		
//		return hash_ans2;
		
//		simhash.init(new String[]{b, "output2.txt"});
		
//		SimHash simhash = new SimHash();
//		
//		int hash1 = simhash.hash(a);
//		int hash2 = simhash.hash(b);
//		System.out.println(hash1);
//		System.out.println(hash2);
//
//		int diff = 0;
//
//		diff = simhash.diffBitsOfNums(hash1,hash2);
//		System.out.println("Document 1 and 2 differ by " + diff);
		
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
	
	
	
	public void compare2(String a, String b){
		String[] word_a = a.split("\\s+");
		String[] word_b = b.split("\\s+");
		ArrayList<String> all_a = new ArrayList<String>();
		ArrayList<String> all_b = new ArrayList<String>();
		
		for(int i=0;i<word_a.length;i++){
			if(word_a[i].length()>1){
				char temp = word_a[i].charAt(word_a[i].length()-1);
				if((temp>=65 && temp<=90) || (temp>=97 && temp<=112) ){
					all_a.add(word_a[i]);
				} else{
					word_a[i] = word_a[i].substring(0,  word_a[i].length()-1);
					all_a.add(word_a[i]);
				}
			}
			
		}
		
		for(int i=0;i<word_b.length;i++){
			if(word_b[i].length()>1){
				char temp = word_b[i].charAt(word_b[i].length()-1);
				if((temp>=65 && temp<=90) || (temp>=97 && temp<=122) || (temp>=48 && temp<=57) ){
					all_b.add(word_b[i]);
				} else{
					word_b[i] = word_b[i].substring(0,  word_b[i].length()-1);
					all_b.add(word_b[i]);
				}
			} 
		}
		int counter1=0, counter2=0;
		for(int i=0;i<all_a.size();i++){
			for(int j=0;j<all_b.size();j++){
				if(all_a.get(i).equals(all_b.get(j))){
					counter1++;
					break;
				}
			}
		}
		for(int i=0;i<all_b.size();i++){
			for(int j=0;j<all_a.size();j++){
				if(all_b.get(i).equals(all_a.get(j))){
					counter2++;
					break;
				}
			}
		}
		System.out.println(all_a.size() + "\t" + all_b.size());
		double ans = (double)counter1/all_a.size()*100;
		double ans2= (double)counter2/all_b.size()*100;
		System.out.println(ans + "\n" + ans2);
		
	}
	
	public boolean compareStrings (String a, String b){
      boolean checkForPlagiarism = true;
      String[] piecesA = a.split("\\s+");
      String[] piecesB = b.split("\\s+");

      int count1 = 0;
      int count2 = 0;
      for (int counter = 0; counter <= piecesA.length - 1; counter++)
       {
         for(int counter2 = 0; counter<= piecesB.length - 1; counter++)
         {
             if(piecesA[counter].equals(piecesB[counter2]))
             {
             count1++;
             }
         }
       }
      for (int counter = 0; counter <= piecesB.length - 1; counter++)
       {
         for(int counter2 = 0; counter <= piecesA.length - 1; counter++)
         {
             if(piecesA[counter].equals(piecesB[counter]))
             {
             count2++;
             }
         }
       }

      if((count1/(int)piecesA.length)*100 >= 90 && (count2/(int)piecesB.length)*100 >= 90)
      {
        checkForPlagiarism = false;
      }    
       return checkForPlagiarism;
     }
	
}
