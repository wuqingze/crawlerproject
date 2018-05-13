# -*- coding: utf-8 -*-
import scrapy
import time
from scrapy import Spider, Request
from datetime import datetime
class GetipSpider(scrapy.Spider):
    name = "getIP"
    allowed_domains = ["api.xicidaili.com"]
    start_urls = ['http://api.xicidaili.com']
    resourceURL = "http://api.xicidaili.com/"
    fileCount = 1
    xxtime = datetime.now().timestamp()
    def start_requests(self):
        # print(self.time)
        yield Request(self.resourceURL,self.parse_resourcePage)

    def parse_resourcePage(self,response):
        print('-------ip file version: '+str(self.fileCount)+'----------------------------------')
        print('-----------------------------------------')
        print('-----------------------------------------')
        print('-----------------------------------------')
        print('-----------------------------------------')
        print('-----------------------------------------')
        f = open("d:/IP/ip.txt",'w')
        f.write(response.text)
        f.close()
        self.fileCount += 1
        time.sleep(60)
        PR = Request(
            self.resourceURL,
            callback=self.parse_resourcePage,
            dont_filter=True
        )
        yield PR

    def parse(self, response):
        print(response.url)
        print(response.text)
