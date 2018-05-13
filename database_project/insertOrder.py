#
# 插入订单数据
#
import random
import thulac
import mysql.connector
import datetime
import time
num = [1,2,3,4,5,6,7,8,9]
conn = mysql.connector.connect(user='root', password='root', database='database_project')
cursor = conn.cursor()
orderSQL = "insert into orders(ordersID,orderDate,goodsPrice,totalPrice ,goodsNum ,customerID ,goodsID ,receiverPhone ,receiverName,receiverPostcode,receiverAddress,receiverEmail) value(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"

def getTrueNum(price):
    xx = ""
    for ch in price:
        if ch>='0' and ch<='9' or ch=='.':
            xx += ch
    return xx


def getOrderID(XX):
    return hash(XX)
def getNum():
    return random.choice(num)
def getDate():
    return time.strftime('%Y-%m-%d',time.localtime(time.time()))
def getTotalPrice(price,number):
    print("price:"+getTrueNum(price))
    return str(float(getTrueNum(price))*number)

cursor.execute("select * from customer")
customer_vlues = cursor.fetchall()
cursor.execute("select * from goods")
goods_valuescursor = cursor.fetchall()
for xxx in range(10):
    for customer in customer_vlues:
        goods = random.choice(goods_valuescursor)
        goodsID = goods[0]
        price = goods[2]
        customerID = customer[0]
        name = customer[3]
        phone = customer[4]
        email =customer[5]
        city =customer[8]
        address =customer[6]
        postcode =customer[7]
        number = getNum()
        print("xprice"+price)
        cursor.execute(orderSQL,[getOrderID(customerID+goodsID+str(time.time())),getDate(),price,getTotalPrice(price,number),number,customerID,goodsID,phone,name,postcode,address,email])
conn.commit()
print("dfafa")

