package com.duan.service;


import com.duan.dto.Item;

import java.util.Collection;

public interface ShoppingCartService {
	void add(Integer id, Item entity);
	void remove(Integer id);
	void update(Integer id, int qty);
	void clear();
	Collection<Item> getItems();
	int getCount();
	double getAmount();
}
