# -*- coding: utf-8 -*-

# Define here the models for your spider middleware
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/spider-middleware.html

from datetime import datetime
from scrapy import signals
import random
import base64
from scrapy.downloadermiddlewares.useragent import UserAgentMiddleware
import scrapy
import time
from scrapy import Spider, Request
from datetime import datetime

class RotateUserAgentMiddleware(UserAgentMiddleware):
    offsetTime = 60
    user_agent_list = []
    ip = []

    def __init__(self, user_agent=''):
        self.user_agent = user_agent
        # self.offtime = datetime.now().timestamp()
        f = open("d:/user-agent.txt")
        for line in f:
            self.user_agent_list.append(line.strip())
        f.close()
        f = open("d:/IP/availableIP.txt")
        for line in f:
            self.ip.append(line.strip())
        f.close()
        self.tempTime = datetime.now().timestamp()
        # self.requestCount = 0

    def process_request(self, request, spider):
        ua = random.choice(self.user_agent_list)
        # request.request.meta['proxy'] = "http://%s" % proxy['ip_port']
        if ua:
            print(ua, '-----------------yyyyyyyyyyyyyyyyyyyyyyyyy')
            request.headers.setdefault('User-Agent', ua)
        tempIP = self.get_randomIP()
        request.meta['proxy'] = "http://%s" % tempIP

    # for more user agent strings,you can find it in http://www.useragentstring.com/pages/useragentstring.php
    def get_randomIP(self):
        if datetime.now().timestamp()-self.tempTime > self.offsetTime:
            self.ip = []
            f = open("d:/IP/availableIP.txt")
            for line in f:
                self.ip.append(line.strip())
            f.close()
            self.tempTime = datetime.now().timestamp()
        return random.choice(self.ip)

class RandomUserAgent(object):
    def __init__(self, agents):
        self.agents = agents

    @classmethod
    def from_crawler(cls, crawler):
        return cls(crawler.settings.getlist('USER_AGENTS'))

    def process_request(self, request, spider):
        print('-------------------------------------------')
        print('-------------------------------------------')
        print('-------------------------------------------')
        print('-------------------------------------------')
        print('-------------------------------------------')
        print('-------------------------------------------')
        print('-------------------------------------------')
        print('-------------------------------------------')
        request.headers.setdefault('User-Agent', random.choice(self.agents))

# class ProxyMiddleware(object):
#     def process_request(self, request, spider):
#         proxy = random.choice(PROXIES)
#         if proxy['user_pass'] is not None:
#             request.meta['proxy'] = "http://%s" % proxy['ip_port']
#             encoded_user_pass = base64.encodestring(proxy['user_pass'])
#             request.headers['Proxy-Authorization'] = 'Basic ' + encoded_user_pass
#         else:
#             request.meta['proxy'] = "http://%s" % proxy['ip_port']

class Amazontest1SpiderMiddleware(object):
    # Not all methods need to be defined. If a method is not defined,
    # scrapy acts as if the spider middleware does not modify the
    # passed objects.

    @classmethod
    def from_crawler(cls, crawler):
        # This method is used by Scrapy to create your spiders.
        s = cls()
        crawler.signals.connect(s.spider_opened, signal=signals.spider_opened)
        return s

    def process_spider_input(response, spider):
        # Called for each response that goes through the spider
        # middleware and into the spider.

        # Should return None or raise an exception.
        return None

    def process_spider_output(response, result, spider):
        # Called with the results returned from the Spider, after
        # it has processed the response.

        # Must return an iterable of Request, dict or Item objects.
        for i in result:
            yield i

    def process_spider_exception(response, exception, spider):
        # Called when a spider or process_spider_input() method
        # (from other spider middleware) raises an exception.

        # Should return either None or an iterable of Response, dict
        # or Item objects.
        pass

    def process_start_requests(start_requests, spider):
        # Called with the start requests of the spider, and works
        # similarly to the process_spider_output() method, except
        # that it doesn’t have a response associated.

        # Must return only requests (not items).
        for r in start_requests:
            yield r

    def spider_opened(self, spider):
        spider.logger.info('Spider opened: %s' % spider.name)

