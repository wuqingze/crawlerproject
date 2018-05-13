import re
import thulac
import datetime
import random
import time
# thul = thulac.thulac()
# xx = thul.cut("万历十五年 (黄仁宇作品系列)",text=True)
# words = xx.split(" ")
# for word in words:
#     if word.__contains__("_n") or word.__contains__("_x"):
#         print(word)
# s = set()
# s.add('123')
# s.add('fsaf')
# s.add('123')
# print(datetime.datetime.now().timestamp())
# for xx in s:
#     print(xx)

# xx = "   xxxxx   xxxx    xxx   a"
# print(xx)
# print(xx.strip())


# pattern = re.compile(r'<li><img src=\"\\./images/mail/.*?注册</a></li>')
# match = pattern.match('<li><img src=\"./images/mail/qq.png\"><a href=\"http://mail.qq.com/\">QQ邮箱</a> <a href=\"http://tieba.baidu.com/p/2314744736\" class=\"top\">(P)</a> <a href=\"./qq.htm\" target=\"_self\" style=\"color:blue\">注册</a></li>fasfafaf')
# if match:
#     print(match.group())
# for x in range(1000):
#     fdfa = random.randint(0,9)
#     if fdfa == 0:
#         print(fdfa)
#     print(random.randint(0,9))
# m = re.match("<li><img src=\"\\./images/mail/.*?注册</a></li>","fajdfjaflajflajfl<li><img src=\"./images/mail/qq.png\"><a href=\"http://mail.qq.com/\">QQ邮箱</a> <a href=\"http://tieba.baidu.com/p/2314744736\" class=\"top\">(P)</a> <a href=\"./qq.htm\" target=\"_self\" style=\"color:blue\">注册</a></li>fasfafaf")
# print(m.group(0))
# print(time.time())
# print(time.strftime('%Y-%m-%d',time.localtime(time.time())))
# price = "￥109.00"
# price.strip()
# def getTrueNum(price):
#     xx = ""
#     for ch in price:
#         if ch>='0' and ch<='9' or ch=='.':
#             print(ch)
#             xx += ch
#     return xx
# print(getTrueNum(price))
# print(price.split("￥")[1])
# print(float(price.split("￥")[1])*30)
# fa = "1,323,000.00"
# print(fa.replace(',',""))
adfa = "1213.00"
print(float(adfa)*3)