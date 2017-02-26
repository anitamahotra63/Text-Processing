package lect23;

import java.util.HashMap;
import java.util.Scanner;

public class client_text_processing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Scanner scn=new Scanner(System.in);
		System.out.println("enter the first string-:");
		String str=scn.nextLine();
		System.out.println("enter the second string-:");
		String ptr=scn.nextLine();
		System.out.println(boyer_moore(str,ptr));*/
		trie t=new trie();
		t.add_word("bit");
		t.add_word("bull");
		
		t.add_word("bell");
		
		t.add_word("bite");
		
		t.add_word("stop");
		
		t.add_word("stock");
		
		t.add_word("pic");
		t.add_word("pice");
		t.add_word("nice");
		
		System.out.println(t.contains_word_recursive("bit"));
		System.out.println(t.contains_word_recursive("bite"));
		
		t.remove_word("bit");
		System.out.println(t.contains_word_recursive("bite"));
		
		

	}
	
	public static int brute_force_method(String str, String ptr)		//complexity is str.length*prt.length
	{
		int retval=-1;
		 int i=0;
		 while(i<=str.length()-ptr.length())
		 {
			 int j=0;
			 while(j<ptr.length())
			 {
				 if(str.charAt(i+j)!=ptr.charAt(j))
				 {
					 break;
				 }
				 j++;
			 }
			 if(j==ptr.length())
			 {
				 retval=i;
				 break;
			 }
			 i++;
		 }
		
		
		
		return retval;
	}

	public static int boyer_moore(String str,String ptr)	//complexity is m*n, on an average gives 25%better performance
	{
		int retval=-1;
		 int i=0;
		 HashMap<Character,Integer> char_map=charMap(ptr);
		 while(i<=str.length()-ptr.length())
		 {
			 int j=ptr.length()-1;
			 while(j>=0)
			 {
				 if(str.charAt(i+j)!=ptr.charAt(j))
				 {
					 char mis_match=str.charAt(i+j);
					 if(!char_map.containsKey(mis_match))
					 {
						 i=i+j+1;
					 }
					 else
					 {
						 Integer psi=char_map.get(mis_match);
						 if(j>psi)
						 {
							i=i+j-psi; 
						 }
						 else
						 {
							 i=i+1;
						 }
					 }
					 break;
				 }
				 j--;
			 }
			 if(j==-1)
			 {
				 retval=i;
				 break;
			 }
		 }
		
		
		
		return retval;

	}
	
	public static HashMap<Character,Integer> charMap(String ptr)
	{
		HashMap<Character,Integer> char_map=new HashMap<>();
		
		for(int i=0;i<ptr.length();i++)
		{
			char_map.put(ptr.charAt(i), i);
		}
		return char_map;
	}
	
}
