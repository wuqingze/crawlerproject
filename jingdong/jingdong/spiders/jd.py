# -*- coding: utf-8 -*-
import random
import scrapy
from scrapy import Spider, Request
from datetime import datetime
import urllib.request
import json
import codecs
reader = codecs.getreader("utf-8")

class JdSpider(scrapy.Spider):
    name = "jd"
    allowed_domains = ["www.jd.com","p.3.cn"]
    goodsCount = 0

    def start_requests(self):
        file1 = open("D:\Python\scrapy_learning\jingdong\jingdong\spiders\category\categoryResult1.txt",'r')
        file2 = open("D:\Python\scrapy_learning\jingdong\jingdong\spiders\category\categoryResult2.txt",'r')

        urls = []
        goodsIDs = []
        for url in file1:
            if len(url.strip()) !=0:
                urls.append(url)
        for goodsID in file2:
            goodsIDs.append(goodsID)

        file1.close()
        file2.close()

        for url in urls:
            yield Request("http://"+url,self.parse_classfiedPage)

        for goodsID in goodsIDs:
            yield  Request("https://list.jd.com/list.html?cat="+goodsID,self.parse_classfiedPage)


    def parse_classfiedPage(self, response):
        goodsList = response.xpath("//div[@class='gl-i-wrap j-sku-item']/div[@class='p-img']/a[@target='_blank']/@href").extract()
        nextPages = response.xpath("//a[@class='pn-next']/@href").extract()
        if len(nextPages) > 0:
            print("newpages is not null")
            for nextPage in nextPages:
                yield Request("https://list.jd.com"+nextPage,self.parse_classfiedPage)
        else:
            print("nextpage is null")

        if len(goodsList) > 0:
            for goods in goodsList:
                xx = "https:"+goods
                temp = goods.split("/")
                temp1 = temp[len(temp)-1]
                goods_ID = temp1.replace(".html","")
                yield Request('http://p.3.cn/prices/mgets?skuIds=J_' + goods_ID + '&type=1',callback=lambda response,id=goods_ID,goods_url=xx:self.parse_price(response,goods_ID,goods_url))
        else:
            print("goodsList is null")

    def parse_goodspage(self,response,goods_price):
        print("goods page ")
        price = goods_price
        productTitle = response.xpath("//div[@class='sku-name']/text()").extract()
        description = response.xpath("//ul[@class='parameter2 p-parameter-list']/li/text()").extract()
        review = response.xpath("//div[contains(@class,'comment-con')]/text()").extract()
        picture = response.xpath("//img/@data-origin").extract()

        f = open("d:/jingdongGoods.txt", 'a')
        time = str(datetime.now())
        f.write("time:   " + time + '\n')
        self.goodsCount += 1
        f.write("goods count: " + str(self.goodsCount) + '\n')
        print("time:   ", datetime.now())
        print('productTitle--------------------------------------------')
        f.write('productTitle--------------------------------------------' + '\n')
        for xx in productTitle:
            print(xx)
            f.write(xx + '\n')
        print('price-----------------------------------------------')
        f.write('price-----------------------------------------------' + '\n')

        f.write(price+"\n")
        print(price)
        print('description--------------------------------------------')
        f.write('description--------------------------------------------' + '\n')

        for xx in description:
            f.write(xx + '\n')
            print(xx)
        print('picture---------------------------------------------')
        f.write('picture---------------------------------------------' + '\n')

        for xx in picture:
            f.write(xx + '\n')
            print(xx)
        print('review----------------------------------------------')
        f.write('review----------------------------------------------' + '\n')
        for xx in review:
            # temp = xx.encode("gbk",'ignore')
            f.write(xx + '\n')
            print(xx)
        f.close()

    def parse_price(self,response,id,goods_url):
        # xx = response.url.split("/")
        # xxx = xx[len(xx) - 1]
        # result = xxx.replace(".html", "")
        # url = 'http://p.3.cn/prices/mgets?skuIds=J_' + result + '&type=1'
        # with urllib.request.urlopen(url) as jj:
        #     price_json = json.load(reader(jj))[0]
        print('-----------------------------')
        print('-----------------------------')
        print('-----------------------------')
        print('-----------------------------')
        print('get price -------------------------')
        # with response.text as jj:
        # price_json = json.load(reader(response.body))[0]
        t = response.text
        print(t)
        price = t.split(",")[1].split(":")[1].replace("\"","")
        print(price)
        # if price_json['p']:
        #     price = price_json['p']
        # else:
        #     price = '99.00'
        # print("get price successfully")
        yield Request(goods_url, callback=lambda response,goods_price=price: self.parse_goodspage(response,goods_price), dont_filter=True)
