import urllib
import json
import urllib.request
import codecs
reader = codecs.getreader("utf-8")
# xx = 'https://item.jd.com/2587724.html'
# xxx = xx.split("/")
# id = xxx[len(xxx)-1]
# print(id.replace(".html",''))

url = 'http://p.3.cn/prices/mgets?skuIds=J_389561&type=1'
s = "df"
with urllib.request.urlopen(url) as jj:
    price_json = json.load(reader(jj))[0]

# p对应的价格是我们想要的
if price_json['p']:
    price = price_json['p']
    print(price)
