package com.company.taxfiler.util;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TtlHashMap<K, V> implements Map<K, V> {

	private final HashMap<K, V> store = new HashMap<>();
	private final HashMap<K, Long> timestamps = new HashMap<>();
	//this is to supprt employee and super_admin session with 12Hrs validity
	private final List<K> otherSessionIds = new ArrayList<>();
	private final long ttl;
	private final long otherTTL;

	public TtlHashMap(TimeUnit ttlUnit, long ttlValue, long otherTTLValue) {
		this.ttl = ttlUnit.toNanos(ttlValue);
		this.otherTTL = ttlUnit.toNanos(otherTTLValue);
	}

	@Override
	public V get(Object key) {
		V value = this.store.get(key);

		if (value != null && expired(key, value)) {
			store.remove(key);
			timestamps.remove(key);
			otherSessionIds.remove(key);
			return null;
		} else {
			return value;
		}
	}

	private boolean expired(Object key, V value) {
		if(otherSessionIds.contains(key)) {
			return (System.nanoTime() - timestamps.get(key)) > this.otherTTL;
		}else {
		return (System.nanoTime() - timestamps.get(key)) > this.ttl;
		}
	}

	@Override
	public V put(K key, V value) {
		timestamps.put(key, System.nanoTime());
		return store.put(key, value);
	}

	@Override
	public int size() {
		clearExpired();
		return store.size();
	}

	@Override
	public boolean isEmpty() {
		return store.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return store.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return store.containsValue(value);
	}

	@Override
	public V remove(Object key) {
		timestamps.remove(key);
		if(otherSessionIds.contains(key))
			otherSessionIds.remove(key);
		return store.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
			this.put(e.getKey(), e.getValue());
		}
	}

	@Override
	public void clear() {
		timestamps.clear();
		store.clear();
		otherSessionIds.clear();
	}

	@Override
	public Set<K> keySet() {
		clearExpired();
		return unmodifiableSet(store.keySet());
	}

	@Override
	public Collection<V> values() {
		clearExpired();
		return unmodifiableCollection(store.values());
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		clearExpired();
		return unmodifiableSet(store.entrySet());
	}

	private void clearExpired() {
		for (K k : store.keySet()) {
			this.get(k);
		}
	}

	public List<K> getOtherSessionIds() {
		return otherSessionIds;
	}
	
	

}