# -*- coding: utf-8 -*-
import scrapy
from scrapy import Spider, Request
from datetime import datetime
import time
import random

class ProcessIpSpider(scrapy.Spider):
    name = "process_ip"
    allowed_domains = ["www.baidu.com"]
    ip_pool = []
    requestCount = 0
    webs = ["http://cn.bing.com/","https://www.sogou.com/","http://www.qq.com/","http://www.sohu.com/","http://www.163.com/","http://www.ifeng.com/","http://xinhuanet.com/","https://www.google.com/"]
    user_agent = []

    def __init__(self):
        f = open("d:/user-agent.txt")
        for line in f:
            self.user_agent.append(line.strip())
        f.close()


    def start_requests(self):
        while True:
            file = open("d:/IP/ip.txt")
            ipList = []
            print("test file")
            for ip in file:
                ipList.append(ip)
            file.close()
            ipSize = len(ipList)
            for xx in ipList:
                ip = str(xx)
                if ip.__contains__(":"):
                    self.requestCount += 1
                    rr = Request(url=random.choice(self.webs),callback=lambda response, typeid=ip.strip(): self.parse_ipResponse(response, typeid))
                    rr.meta['proxy'] = "http://" + ip.strip()
                    rr.headers.setdefault('User-Agent', random.choice(self.user_agent))
                    print("ip test-----------------------")
                    print(ip.strip())
                    print(str(self.requestCount))
                    print("------------------------------")
                    print("------------------------------")
                    print("------------------------------")
                    print("------------------------------")
                    rr.dont_filter = True
                    # rr.meta['download_timeout'] = 5
                    yield rr

    def newThread(self):
        file = open("d:/IP/availableIP.txt","w")
        for ip in self.ip_pool:
            file.write(ip+'\n')
        file.close()
        self.ip_pool = []
        file = open("d:/IP/ip.txt")
        ipList = []
        print("test file")
        for ip in file:
            ipList.append(ip)
        file.close()
        ipSize = len(ipList)
        for xx in ipList:
            ip = str(xx)
            if ip.__contains__(":"):
                self.requestCount += 1
                rr = Request(url="https://baidu.com/",
                             callback=lambda response, typeid=ip.strip(): self.parse_ipResponse(response, typeid))
                rr.meta['proxy'] = "http://" + ip.strip()
                print("ip test-----------------------")
                print(ip.strip())
                print(str(self.requestCount))
                print("------------------------------")
                print("------------------------------")
                print("------------------------------")
                print("------------------------------")
                rr.dont_filter = True
                rr.meta['DOWNLOAD_TIMEOUT'] = 5
                if self.requestCount == ipSize:
                    self.requestCount = 0
                    self.newThread()
                    break
                yield rr

    def parse_ipResponse(self, response, typeid):
        self.ip_pool.append(typeid)
        print("ip_pool len is ",str(self.ip_pool.__len__()))
        if self.ip_pool.__len__() == 20:
            file = open("d:/IP/availableIP.txt", "w")
            for ip in self.ip_pool:
                file.write(ip + '\n')
            file.close()
            self.ip_pool = []




