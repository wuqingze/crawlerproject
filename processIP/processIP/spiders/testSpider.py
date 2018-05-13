# -*- coding: utf-8 -*-
import scrapy


class TestspiderSpider(scrapy.Spider):
    name = "testSpider"
    allowed_domains = ["www.baidu.com"]
    start_urls = ['http://www.baidu.com/']

    def start_requests(self):
        print("test start request")
        # self.processInternet()
    def parse(self, response):
        pass
