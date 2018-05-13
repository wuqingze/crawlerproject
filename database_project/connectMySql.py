#
# 将商品数据插入数据库；处理title得到商品标签；
#
import thulac
import mysql.connector
import datetime
conn = mysql.connector.connect(user='root', password='root', database='database_project')
cursor = conn.cursor()
file = open("C:/文档/数据库系统及应用/database_project/output2.txt")
goodsSql = "insert into Goods(goodsID,productTitle,price,description ,picture,review) value(%s,%s,%s,%s,%s,%s)"
tagSql = "insert into Tag(tagName,goodsID) values(%s,%s)"
getGoodsID = "select goodsID from goods "
productTitle = ""
description  = ""
price = ""
review = ""
picture = ""
thul = thulac.thulac()
for line in file:
    if line.__contains__("productTitle:") is True:
        productTitle = line.split("productTitle:    ")[1].strip()
    elif line.__contains__("price:") is True:
        price = line.split("price:")[1].strip()
    elif line.__contains__("description:") is True:
        description = line.split("description:")[1].strip()
    elif line.__contains__("picture:") is True:
        picture = line.split("picture:")[1].strip()
    elif line.__contains__("review:") is True:
        review = line.split("review:")[1].strip()
    elif line.__contains__("end-----------------------------------") is True:
        hashString = productTitle+str(datetime.datetime.now().timestamp())
        hashKey = hash(hashString)
        cursor.execute(goodsSql,[hashKey,productTitle,price,description,picture,review])
        tagSet = set()
        #
        # 通过商品title得到商品的标签
        # tag1是通过处理空格和数字和特殊字符得到的标签
        # tag2是通过thul中文工具包的到的标签
        # 最后对tag1和tag2去重，插入数据库
        tag1 = productTitle.replace("“","").replace("”","").replace("(","").replace(")","").replace("（","").replace("）","").replace("【","").replace("】","").replace("[","").replace("]","").split(" ")
        tag2 = thul.cut(productTitle.replace("“","").replace("”","").replace("(","").replace(")","").replace("（","").replace("）","").replace("【","").replace("】","").replace("[","").replace("]",""),text=True).split(" ")
        for tag in tag1:
           if tag.strip() != "":
               tagSet.add(tag.strip().lower())
        for temp in tag2:
            if temp.__contains__("_n") or temp.__contains__("_x"):
                tagSet.add(temp.split("_")[0].lower())
        for xx in tagSet:
            print(xx)
            cursor.execute(tagSql,[xx,hashKey])
        print("--------------------------")
        productTitle = ""
        description = ""
        price = ""
        review = ""
        picture = ""
conn.commit()
conn.close()