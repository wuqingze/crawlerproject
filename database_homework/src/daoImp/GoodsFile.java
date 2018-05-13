package daoImp;

import java.io.File;

public class GoodsFile {
	public static File getFile(String root,String fileName)
	{
		System.out.println("-----"+root);
		File file = new File(root+"/Goods/"+fileName+"/bean.txt");
		return file;
	} 
}
