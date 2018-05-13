package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import bean.D_Goods;
import daoImp.FindGoods;
import util.DBUtil;

public class Test
{

	public static void main(String[] args)
	{
		
		test20();
		System.out.println("end------------------");
	}
	
	
	//为Tag添加数据
	public static void test0()
	{
		Connection connection = DBUtil.open();
		File file = new File("files/category.txt");
		FileReader fr = null;
		BufferedReader br= null;
		try
		{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String firstTag = br.readLine();
			String secondTag = br.readLine();
			while(firstTag != null && secondTag != null)
			{
				if(firstTag.contains(":"))
				{
					if(secondTag.contains(","))
					{
						firstTag = firstTag.substring(firstTag.indexOf('-')+1,firstTag.indexOf(':'));
						String[] secondTags = secondTag.split(",");
						String sql1 = "insert into first_tag(first_tag_name) values(?)";
						String sql2 = "insert into second_tag(second_tag_name,first_tag_id) values(?,?)";
						PreparedStatement preparedStatement1 = (PreparedStatement) connection.prepareStatement(sql1);
						PreparedStatement preparedStatement2 =  (PreparedStatement) connection.prepareStatement(sql2);
						Statement statement = (Statement) connection.createStatement();
						preparedStatement1.setString(1, firstTag);
						preparedStatement1.execute();
						ResultSet resultSet = statement.executeQuery("select first_tag_id from first_tag where first_tag_name='"+firstTag+"';");
						int first_tag_id = 0;
						if(resultSet.next())
						{
							first_tag_id = resultSet.getInt(1);
						}
						for (String string : secondTags)
						{
							preparedStatement2.setString(1, string);
							preparedStatement2.setInt(2, first_tag_id);
							preparedStatement2.execute();
						}
					}
					firstTag = br.readLine();
					secondTag = br.readLine();
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				br.close();
				fr.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DBUtil.close(connection);
		System.out.println("success");
	}

	//为Goods添加数据，同时在Tag中加入相应的数据
	public static void test1()
	{
		Connection connection = DBUtil.open();
		String insertGoodsSQL = "insert into Goods(productTitle,description,price,review) values(?,?,?,?);";
		String insertTagSQL = "insert into Tag(tagName,goodsID) values(?,?);";
		DBUtil.close(connection);
	}

	
	//处理数据文件，将原始的数据提取出来
	public static void test2()
	{
		File file = new File("files/jingdongGoods.txt");
		File outputFile = new File("files/output2.txt");
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try
		{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			
			String line = null;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine())!=null)
			{
				sb.append(line);
			}
			
			String regex1 = "productTitle-.*?time:";
			String regex2 = "productTitle-.*?price-";
			String regex3 = "price-.*?description-";
			String regex4 = "description-.*?picture-";
			String regex5 = "picture-.*?review-";
			String regex6 = "review-.*?time:";
			
			Pattern p1 = Pattern.compile(regex1);
			Pattern p2 = Pattern.compile(regex2);
			Pattern p3 = Pattern.compile(regex3);
			Pattern p4 = Pattern.compile(regex4);
			Pattern p5 = Pattern.compile(regex5);
			Pattern p6 = Pattern.compile(regex6);
			
			Matcher m1 = p1.matcher(sb.toString());
			int count = 1;
			while(m1.find())
			{
				String goodsData = m1.group();
				Matcher m2 = p2.matcher(goodsData);
				if(m2.find())
				{
					String temp2 = m2.group();
					String productTitle = temp2.replaceAll("productTitle--------------------------------------------", "").replaceAll("price-","").trim();
					if(!"".equals(productTitle))
					{
						bw.write("start-------------------------------------"+"\r\n");
						bw.write("productTitle:    "+productTitle+"\r\n");
						System.out.print("productTitle:   "+productTitle);
						
						bw.write("price:    ");
						Matcher m3 = p3.matcher(goodsData);
						if(m3.find())
						{
							String temp3 = m3.group().replaceAll("price-----------------------------------------------", "").replace("description-", "");
							
							String priceRegex = "￥[0-9,\\.]+";
							Pattern pricePattern = Pattern.compile(priceRegex);
							Matcher priceMatcher = pricePattern.matcher(temp3);
							if(priceMatcher.find())
							{
								bw.write(priceMatcher.group());
//								System.out.print("    price:"+priceMatcher.group());
							}
						}
						bw.write("\r\n");
						
						bw.write("description:    ");
						Matcher m4 = p4.matcher(goodsData);
						if(m4.find())
						{
							String description = m4.group().replaceAll("description--------------------------------------------", "").replaceAll("picture-", "").trim();
							bw.write(description);
							System.out.print("   description:  "+description);
						}
						bw.write("\r\n");
						
						
						bw.write("picture:    ");
						Matcher m5 = p5.matcher(goodsData);
						if(m5.find())
						{
							String picture = m5.group().replaceAll("picture---------------------------------------------", "").replaceAll("review-", "").trim();
							if(picture.contains("http"))
							bw.write(picture);
							System.out.print("    picture:   "+picture);
						}
						bw.write("\r\n");
						
						bw.write("review:    ");
						Matcher m6 = p6.matcher(goodsData);
						if(m6.find())
						{
							String review = m6.group().replaceAll("review----------------------------------------------", "").replaceAll("time:", "").trim();
							bw.write(review);
							System.out.println("   review:   "+review);
						}
						bw.write("\r\n");
						bw.write("end-----------------------------------"+"\r\n");
					}
				}
			}
			System.out.println("success");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				br.close();
				fr.close();
				bw.close();
				fw.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}

	
	//测试从数据库查询数据
	public static void test3()
	{
		String sql = "select * from Goods where goodsID in (select goodsID from Tag where tagName='Apple');";
		ArrayList<D_Goods> list = FindGoods.find(sql);
		for (D_Goods d_Goods : list)
		{
			System.out.println(d_Goods.getProductTitle());
		}
	}
	
	
	//测试comparator接口
	public static void test4()
	{
		ArrayList<Integer>  list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(list.get(0));
	}

	public static void test5()
	{
		String xx = "我是人发啊付款就啊龙卷风";
		System.out.println(xx.length());
		System.out.println(xx.substring(0, 2));
	}
	
	//初步处理京东商品目录
	public static void test6()
	{
		File file = new File("files/category");
		File outputFile = new File("files/categoryResult.txt");
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try
		{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			
			StringBuilder sb = new StringBuilder();
			String temp = null;
			while((temp = br.readLine())!= null)
			{
				sb.append(temp);
			}
			String regex = "\\{.n.:.*?0\\}";
			Pattern p = Pattern.compile(regex);
			
			Matcher matcher = p.matcher(sb.toString());
			while(matcher.find())
			{
				bw.write(matcher.group()+"\r\n");
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				br.close();
				fr.close();
				bw.close();
				fw.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
	//进一步处理京东商品目录
	public static void test7()
	{
		File file = new File("files/categoryResult.txt");
		File outputFile = new File("files/categoryResult1.txt");
		File outputFile1 = new File("files/categoryResult2.txt");
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		FileWriter fw1 = null;
		BufferedWriter bw = null;
		BufferedWriter bw1 = null;
		try
		{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			fw1 = new FileWriter(outputFile1);
			bw1 = new BufferedWriter(fw1);
			
			String temp = null;
			while((temp = br.readLine())!= null)
			{
				String temp1 = temp.replace("{\"n\":\"", "").split("\\|\\|")[0].split("\\|")[0];
				if(temp1.contains("cat="))
				{
					bw.write(temp1+"\r\n");
				}
				else if(temp1.contains("-") &&!temp1.contains(".html") && !temp1.contains("/"))
				{
					bw1.write(temp1.replaceAll("-", ",")+"\r\n");
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				br.close();
				fr.close();
				bw.close();
				fw.close();
				bw1.close();
				fw1.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
	//处理京东最终数据
	public static void test8()
	{
		File file = new File("files/jingdongGoods.txt");
		File outputFile = new File("files/output2.txt");
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try
		{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			
			String line = null;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine())!=null)
			{
				if(line.contains("：") && !line.contains("使用心得"))
				{
						sb.append(line+"decriptonMark/");
				}
				else
				{
					sb.append(line);
				}
			}
			
			String regex1 = "productTitle-.*?time:";
			String regex2 = "productTitle-.*?price-";
			String regex3 = "price-.*?description-";
			String regex4 = "description-.*?picture-";
			String regex5 = "picture-.*?review-";
			String regex6 = "review-.*?time:";
			
			Pattern p1 = Pattern.compile(regex1);
			Pattern p2 = Pattern.compile(regex2);
			Pattern p3 = Pattern.compile(regex3);
			Pattern p4 = Pattern.compile(regex4);
			Pattern p5 = Pattern.compile(regex5);
			Pattern p6 = Pattern.compile(regex6);
			
			Matcher m1 = p1.matcher(sb.toString());
			int count = 1;
			while(m1.find())
			{
				String goodsData = m1.group();
				Matcher m2 = p2.matcher(goodsData);
				if(m2.find())
				{
					String temp2 = m2.group();
					String productTitle = temp2.replaceAll("productTitle--------------------------------------------", "").replaceAll("price-","").trim();
					if(!"".equals(productTitle))
					{
						bw.write("start-------------------------------------"+"\r\n");
						bw.write("productTitle:    "+productTitle+"\r\n");
						System.out.print("productTitle:   "+productTitle);
						
						bw.write("price:    ");
						Matcher m3 = p3.matcher(goodsData);
						if(m3.find())
						{
							String temp3 = m3.group().replaceAll("price-----------------------------------------------", "").replace("description-", "");
							
							String priceRegex = "[0-9,\\.]+";
							Pattern pricePattern = Pattern.compile(priceRegex);
							Matcher priceMatcher = pricePattern.matcher(temp3);
							if(priceMatcher.find())
							{
								bw.write("￥"+priceMatcher.group());
//								System.out.print("    price:"+priceMatcher.group());
							}
							else
							{
								bw.write("￥1024.00");
							}
						}
						bw.write("\r\n");
						
						bw.write("description:    ");
						Matcher m4 = p4.matcher(goodsData);
						if(m4.find())
						{
							String description = m4.group().replaceAll("description--------------------------------------------", "").replaceAll("picture-", "").trim();
							String[] xx = description.split("decriptonMark/");
							StringBuilder stringBuilder = new StringBuilder();
							for (int i = 0; i < xx.length; i++)
							{
								stringBuilder.append(xx[i]+" ");
							}
							bw.write(stringBuilder.toString());
							System.out.print("   description:  "+stringBuilder.toString());
						}
						bw.write("\r\n");
						
						
						bw.write("picture:    ");
						Matcher m5 = p5.matcher(goodsData);
						if(m5.find())
						{
							String picture = m5.group().replaceAll("picture---------------------------------------------", "").replaceAll("review-", "").trim();
							if(picture.contains(".jpg"))
							bw.write("http:"+picture);
							System.out.print("    picture:   "+picture);
						}
						bw.write("\r\n");
						
						bw.write("review:    ");
						Matcher m6 = p6.matcher(goodsData);
						if(m6.find())
						{
							String review = m6.group().replaceAll("review----------------------------------------------", "").replaceAll("time:", "").trim();
							bw.write(review.replaceAll("使用心得：", " "));
							System.out.println("   review:   "+review.replaceAll("使用心得：", "  "));
						}
						bw.write("\r\n");
						bw.write("end-----------------------------------"+"\r\n");
					}
				}
			}
			System.out.println("success");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				br.close();
				fr.close();
				bw.close();
				fw.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}

	public static void test9()
	{
		int len = 15;
		for(int i=0;i<len/4+1;i++)
		{
			for(int j=0;i*4+j+1<=len && j<4;j++)
			{
				System.out.print(j+",");
			}
				System.out.println("");
		}
		
	}

	//处理网易云音乐歌单
	public static void test10()
	{
		File file = new File("files/我喜欢的歌曲.txt");
		File outputFile = new File("files/musicList.txt");
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try
		{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			
			String line = null;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine())!=null)
			{
				sb.append(line);
			}
			
			String trRegex = "<tr.*?</tr>";
			String musicNameRegex = "<b title=\".*?</b>";
			String authorRegex = "artist.*?</a>";
			String albumRegex = "href=\"/album.*?</a>";
			
			Pattern p1 = Pattern.compile(trRegex);
			Pattern p2 = Pattern.compile(musicNameRegex);
			Pattern p3 = Pattern.compile(authorRegex);
			Pattern p4 = Pattern.compile(albumRegex);
			Matcher m1 = p1.matcher(sb.toString());
			int musicCount = 0;
			while(m1.find())
			{
				String tr = m1.group();
				Matcher m2 = p2.matcher(tr);
				Matcher m3 = p3.matcher(tr);
				Matcher m4 = p4.matcher(tr);
				if(m2.find())
				{
					bw.write("======================================"+"\r\n");
					musicCount ++;
					bw.write(musicCount+":  "+m2.group().split(">")[1].replaceAll("</b", "").replaceAll("&nbsp;", " ").replaceAll("&amp;","&")+"\r\n");
					bw.write("author:   ");
				}
				
				while(m3.find())
				{
					bw.write(m3.group().split(">")[1].replaceAll("</a", "").replaceAll("&nbsp;", " ").replaceAll("&amp;","&")+"/");
				}
				
				if(m4.find())
				{
					bw.write("\r\n");
					bw.write("album:   "+m4.group().split(">")[1].replaceAll("</a", "").replaceAll("&nbsp;", " ").replaceAll("&amp;","&")+"\r\n");
					bw.write("-------------------------------------"+"\r\n");
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
				fr.close();
				bw.close();
				fw.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	//处理邮箱
	public static void test11()
	{
		File file = new File("files/email_.txt");
		File outputFile = new File("files/email.txt");
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try
		{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			
			String line = null;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine())!=null)
			{
				sb.append(line);
			}
			
			
			String regex1 = "<li><img src=\"\\./images/mail/.*?注册</a></li>";
			Pattern p1 = Pattern.compile(regex1);
			
			Matcher m1 = p1.matcher(sb.toString());
			while(m1.find())
			{
				System.out.println(m1.group().split("\\.png\">")[0].split("/mail/")[1]);
			}
		}
		 catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				br.close();
				fr.close();
				bw.close();
				fw.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void test20()
	{
		File file = new File("files/database_project.txt");
		File outputFile = new File("files/email.txt");
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try
		{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			
			String line = null;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine())!=null)
			{
				sb.append(line);
			}

			String regex = "create table.*?;";
			Pattern pattern = Pattern.compile(regex);
			Matcher m = pattern.matcher(sb.toString());
			while(m.find())
			{
				System.out.println(m.group());
				Connection connection = DBUtil.open();
				Statement statement = (Statement)connection.createStatement();
				statement.execute(m.group());
				DBUtil.close(connection);
			}
		}
			 catch (Exception e)
				{
					e.printStackTrace();
				}
				finally 
				{
					try
					{
						br.close();
						fr.close();
						bw.close();
						fw.close();
					} catch (Exception e)
					{
						e.printStackTrace();
					}
	
				}
		
	}
	
	
	public class SortGoods implements Comparator<Integer>
	{
		public int weight = 0;
		public D_Goods goods;
		
		public void setWeight(int w)
		{
			this.weight = w;
		}
		public SortGoods()
		{
			
		}
		public SortGoods(D_Goods goods)
		{
			this.goods = goods;
		}
		@Override
		public int compare(Integer o1, Integer o2)
		{
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
}
