package com.fdmgroup.Crud;

import java.util.List;

public interface IStorage<T extends Storable>
{
	 public void create(T t);
	 public T read(int id, T t);
	 public List<T> read(String database, T t);
	 public void update(T t);
	 public void delete(T t);
}
