package bean;

public class GoodsComparator implements Comparable<GoodsComparator>
{
	private D_Goods goods;
	private int weight = 0;
	
	@Override
	public int compareTo(GoodsComparator o)
	{
		if(this.weight > o.weight)
		{
			return 1;
		}
		else if (this.weight <o.weight)
		{
			return -1;
		}
		else
		{
			return 0;			
		}

	}

	public D_Goods getGoods()
	{
		return goods;
	}

	public void setGoods(D_Goods goods)
	{
		this.goods = goods;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

}
