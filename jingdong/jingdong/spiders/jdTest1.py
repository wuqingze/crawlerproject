# -*- coding: utf-8 -*-
import scrapy
import json
import urllib.request
import codecs
reader = codecs.getreader("utf-8")

class Jdtest1Spider(scrapy.Spider):
    name = "jdTest1"
    allowed_domains = ["www.jd.com"]
    # start_urls = ['http://p.3.cn/prices/mgets?skuIds=J_10021361289&type=1']
    start_urls = ['https://item.jd.com/389561.html']


    def parse(self, response):
        # 照片picture = response.xpath("//img/@data-origin").extract()
        # description = response.xpath("//ul[@class='parameter2 p-parameter-list']/li/text()").extract()
        # price = response.text
        # 评论# title = response.xpath("//div[contains(@class,'comment-con')]/text()").extract()
        # for str in title:
        #     print(str)
        # goodsList = response.xpath("//div[@class='gl-i-wrap j-sku-item']/div[@class='p-img']/a[@target='_blank']/@href").extract()
        # nextpage = response.xpath("//a[@class='pn-next']/@href").extract()
        # print(len(title))
        # f = open("d:/jd.txt",'w')
        # str1 = response.text
        #
        # # t = str.decode()
        # f.write(str1)
        # f.close()
        # count = 0
        # for page in nextpage:
        #     count += 1
        #     print(str(count),page)
        xx = response.url.split("/")
        xxx = xx[len(xx)-1]
        result = xxx.replace(".html","")
        url = 'http://p.3.cn/prices/mgets?skuIds=J_' + result + '&type=1'
        s = "df"
        with urllib.request.urlopen(url) as jj:
            price_json = json.load(reader(jj))[0]
        # price_json = json.load(s)[0]
        #
        # # p对应的价格是我们想要的
        if price_json['p']:
            price = price_json['p']
            print(price)
