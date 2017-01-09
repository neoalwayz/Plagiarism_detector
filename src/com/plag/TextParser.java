package com.plag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TextParser {
		
	
	
	public String formatString(String text){
		String ans = "";
		for(int i=0;i<text.length();i++){
//			if(i!=0 && text.charAt(i)==' ' && text.charAt(i-1)==' '){
//				continue;
//			}
			if(text.charAt(i)=='\n'){
				ans+=" ";
			} else{
				ans += text.charAt(i);
			}
		}
		
		String[] str = ans.split("\\s+");
		
//		for(int i=0;i<str.length;i++){
//			System.out.println(str[i]);
//		}
		int count=0;
		String[] str2 = new String[str.length];
		for(int i=0;i<str.length;i++){
			if(str[i].length()>1){
				str2[count++] = str[i]; 
			}
		}
		String ans2="";
		for(int i=0;i<count;i++){
			ans2+= str2[i] +" ";
		}
		
		String ans3="";
		for(int i=0;i<ans2.length();i++){
			char temp = ans2.charAt(i);
			int temp2 = (int)temp;
//			if((temp2>=65 && temp2<=90) || (temp2>=97 && temp2<=122) || (temp2>=48 && temp2<=57) || (temp==' ') || 
//					(temp=='.') || temp==',' || temp=='\'' || temp=='"' || temp=='(' || temp==')' || temp=='%' ){
//				ans3+=temp;
//			} else{
//				ans3 += ' ';
//			}
			
			if((temp2>=65 && temp2<=90) || (temp2>=97 && temp2<=122) || (temp2>=48 && temp2<=57) || (temp==' ')){
				ans3+=temp;
			} else{
				ans3 += ' ';
			}
			
			
		}
		
		ans3 = ans3.toLowerCase();
		
		String ans4="" + ans3.charAt(0);
		for(int i=1;i<ans3.length();i++){
			if(ans3.charAt(i)==' ' && ans3.charAt(i-1)==' '){
				continue;
			} else{
				ans4 += ans3.charAt(i);
			}
		}
		
		System.out.println(ans4);
		return ans4;
	}
	
	public String[] getLines(String text){
		String[] split = text.split(System.getProperty("line.separator"));
		return split;
	}
	
	
	public ArrayList<Word> getWordCounts(String text){
		ArrayList<Word> words = new ArrayList<Word>();
		
		String[] word_a = text.split("\\s+");
		ArrayList<String> all_a = new ArrayList<String>();
		
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
		for(int i=0;i<all_a.size();i++){
			boolean exists=false;
			for(int j=0;j<words.size();j++){
				String temp = words.get(j).getWord();
				if(temp.equals(all_a.get(i))){
					exists=true;
					words.get(j).setCount(words.get(j).getCount()+1);
					break;
				}
			}
			if(!exists){
				Word word = new Word();
				word.setCount(1);
				word.setWord(all_a.get(i));
				words.add(word);
			}
			
		}
		
		Collections.sort(words, new Comparator<Word>() {

			@Override
			public int compare(Word o1, Word o2) {
				// TODO Auto-generated method stub
				return o1.getWord().compareTo(o2.getWord());
			}
			
		});
		
		return words;
		
	}
	
}
