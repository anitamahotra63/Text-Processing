package lect23;

import java.util.HashMap;

public class trie {

	private class trie_node
	{
		Character data;
		HashMap<Character,trie_node> children;
		boolean is_terminal;
		
		public trie_node(Character data)
		{
			this.data=data;
			children=new HashMap<Character,trie_node>();
			is_terminal=false;
		}
		
	}
	
	private trie_node root;
	private int size;
	
	public boolean contains_word(String word)
	{
		trie_node temp=this.root;
		for(int i=0;i<word.length();i++)
		{
			trie_node child=temp.children.get(word.charAt(i));
			
			if(child==null)
			{
				return false;
			}
			temp=child;
		}
		
		return temp.is_terminal;
	}
	
	public trie()
	{
		this.root=new trie_node('\0');
		this.size=0;
	}
	
	public void add_word(String word)
	{
		this.add_word(this.root,word);
	}
	
	private void add_word(trie_node node,String word)
	{
		if(word.length()==0)
		{
			if(node.is_terminal)
			{
				return;
			}
			else{
				node.is_terminal=true;
				this.size++;
			}
			return;
		}
		
		if(node.children.containsKey(word.charAt(0)))
		{
			trie_node child=node.children.get(word.charAt(0));
			add_word(child,word.substring(1));
		}
		else
		{
			trie_node new_node=new trie_node(word.charAt(0));
			node.children.put(word.charAt(0), new_node);
			add_word(new_node,word.substring(1));
		}
	}
	
	public void remove_word(String word)
	{
		this.remove_word(this.root, word);
	}
	
	private void remove_word(trie_node node,String word)
	{
		if(word.length()==1)
		{
			if(node.children.containsKey(word.charAt(0)))
			{
				trie_node child=node.children.get(word.charAt(0));
				if(child.is_terminal==true)
				{
					child.is_terminal=false;
					this.size--;
					if(child.children.size()==0)
					{
						node.children.remove(child);
					}
				}
			}
			return;
		}
		
		
		if(node.children.containsKey(word.charAt(0)))
		{
			node=node.children.get(word.charAt(0));
			remove_word(node,word.substring(1));
		}
		else
		{
			return;
		}
		
		if(!node.is_terminal  && node.children.size()==0)
		{
			node.children.remove(node);
		}
	}

	public boolean contains_word_recursive(String word)
	{
		return contains_word_recursive(this.root,word);
	}
	
	private boolean contains_word_recursive(trie_node temp,String word)
	{
		if(word.length()==1)
		{
			if(temp.children.containsKey(word.charAt(0)) && temp.children.get(word.charAt(0)).is_terminal==true)
			{
				return true;
			}
			else{
				return false;
			}
		}
		
		char ch=word.charAt(0);
		if(temp.children.containsKey(ch))
		{
			if(contains_word_recursive(temp.children.get(ch),word.substring(1)))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
