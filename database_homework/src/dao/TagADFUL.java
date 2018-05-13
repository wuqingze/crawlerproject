package dao;

import java.util.ArrayList;

import bean.Tag;

public interface TagADFUL
{
	public void add(Tag tag);
	public void  delete();
	public Tag find();
	public void update(Tag tag);
	public ArrayList<Tag> list();
}
