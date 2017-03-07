package com.model.implementation;

/**
 * contains 2 value in the class<br>
 * and have method that check contains method
 * <ol>
 * <li>key - can be duplication</li>
 * <li>value - can be duplication</li>
 * </ol>
 *
 * @author kamontat
 * @version 1.0
 * @since Sun 05/Mar/2017 - 3:29 AM
 */
public class Pair<K, V> {
	private K key;
	private V value;
	
	/**
	 * easy create the pair.
	 *
	 * @param key
	 * 		key or first element.
	 * @param value
	 * 		value or second element.
	 * @param <K>
	 * 		key type/class.
	 * @param <V>
	 * 		value type/class.
	 * @return Pair that contains key and value.
	 */
	public static <K, V> Pair<K, V> create(K key, V value) {
		return new Pair<K, V>(key, value);
	}
	
	public Pair() {
		key = null;
		value = null;
	}
	
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public V getValue() {
		return value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public boolean isKeyContains(K key) {
		return this.key.equals(key);
	}
	
	public boolean isValueContains(V value) {
		return this.value.equals(value);
	}
	
	public boolean isContains(Object obj) {
		return obj.equals(key) || obj.equals(value);
	}
}
