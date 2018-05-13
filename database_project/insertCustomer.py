#
# 插入用户数据
#
import random
import thulac
import mysql.connector
import datetime
gender=["male","female"]
email = []
city = []
address = []
def initeDate():
    f_a = open("files/address.txt")
    f_c = open("files/city.txt")
    f_m = open("files/mail.txt")
    for line in f_m:
        if len(line.strip())!= 0:
            email.append(line.strip())

    for line in f_c:
        if len(line.strip()) != 0:
            c = line.split("：")
            x1 = c[0]
            x = c[1].split(" ")
            for xx in x:
                city.append(x1+"/"+xx)
    a = 0
    qu = ""
    for line in f_a:
        if len(line.strip()) != 0:
            a = (a+1)%2
            if a == 1:
                qu = line
            elif a== 0:
                jiedao = line.split(" ")
                for jie in jiedao:
                    address.append(qu.split("（")[0]+"/"+jie)
    f_a.close()
    f_c.close()
    f_m.close()


def getEmail():
    return random.choice(email)

def getCustomerID(xx):
    return hash(xx)

def getCustomerName(name):
    return name

def getSex():
    return gender[random.randint(1,2)%2]

def getPassword():
    password = ""
    for x in range(16):
        password += str(random.randint(0,9))
    return password

def getPhone():
    Phone = "1"
    for x in range(10):
        Phone += str(random.randint(0, 9))
    return Phone

def getCity():
    return random.choice(city)

def getAddress():
    return random.choice(address)

def getPostcod():
    postcode = str()
    for x in range(6):
        postcode+=str(random.randint(0,9))
    return postcode

initeDate()
conn = mysql.connector.connect(user='root', password='root', database='database_project')
cursor = conn.cursor()
customerSql = "insert into customer(customerID,sex,password,name,phone,email,city,address,postcode) value(%s,%s,%s,%s,%s,%s,%s,%s,%s)"

_customerID= ""
_sex= ""
_password= ""
_name= ""
_phone= ""
_email= ""
_city= ""
_address= ""
_postcode= ""

for x in range(1000):
    _sex = getSex()
    _password= getPassword()
    _phone= getPhone()
    _name = _phone+"@"+getEmail()+".com"
    _email= _name
    _city= getCity()
    _address= getAddress()
    _postcode= getPostcod()
    _customerID = getCustomerID(_sex+_password+_name+_phone+_email+_city+_address+_postcode)
    cursor.execute(customerSql,[_customerID,_sex,_password,_name,_phone,_email,_city,_address,_postcode])

conn.commit()
print("dfafa")
