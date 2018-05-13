package bean;

public class SecondTag extends Tag
{
	private int firstTagId;
	
	public SecondTag(int tagId,String tagName,int firstTagId)
	{
		super(tagId, tagName);
		this.firstTagId = firstTagId;
	}

	public int getFirstTagId()
	{
		return firstTagId;
	}

	public void setFirstTagId(int firstTagId)
	{
		this.firstTagId = firstTagId;
	}
	
}
