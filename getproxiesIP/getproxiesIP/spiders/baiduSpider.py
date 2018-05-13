# -*- coding: utf-8 -*-
import scrapy
from scrapy import Spider, Request
from datetime import datetime



class BaiduspiderSpider(scrapy.Spider):
    count = 0
    name = "baiduSpider"
    allowed_domains = ["www.baidu.com"]

    def start_requests(self):
        file = open("D:/Python/scrapy_learning/getproxiesIP/getproxiesIP/files/ip.txt")
        for ip in file:
            rr = Request(url="https://baidu.com/",callback=lambda response, typeid=ip: self.parse_ipResponse(response, typeid))
            rr.meta['proxy'] = "http://" + ip
            rr.dont_filter = True
            rr.meta['time_out'] = 5
            yield rr

    def parse(self, response):
        print("hello world")
        print(response.url)
        print(response.text)

    def parse_ipResponse(self,response,typeid):
        self.count += 1
        f = open("D:/Python/scrapy_learning/getproxiesIP/getproxiesIP/files/output.txt",'a')
        result = str(self.count)+typeid
        f.write(result+'\n')
        f.close()
        print(str(self.count),typeid)
