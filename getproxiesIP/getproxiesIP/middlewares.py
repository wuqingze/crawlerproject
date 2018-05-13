# -*- coding: utf-8 -*-

# Define here the models for your spider middleware
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/spider-middleware.html

from datetime import datetime
from scrapy import signals
import random
import base64
# from settings import PROXIES
from scrapy.downloadermiddlewares.useragent import UserAgentMiddleware

class RotateUserAgentMiddleware(UserAgentMiddleware):
    offsetTime = 8*60
    user_agent_list = []
    ip = []

    def __init__(self, user_agent=''):
        self.user_agent = user_agent
        # self.offtime = datetime.now().timestamp()
        # f = open("d:/user-agent.txt")
        # for line in f:
        #     self.user_agent_list.insert(len(self.user_agent_list),line)
        # f.close()
        # f = open("d:/ip/ip.txt")
        # for line in f:
        #     self.ipinsert(len(self.ip), line)
        # f.close()
        # self.tempTime = datetime.now().timestamp()

    def process_request(self, request, spider):
        request.meta['proxy']="http://123.148.83.8:80"
        # ua = random.choice(self.user_agent_list)
        # # request.request.meta['proxy'] = "http://%s" % proxy['ip_port']
        # if ua:
        #     print(ua, '-----------------yyyyyyyyyyyyyyyyyyyyyyyyy')
        #     request.headers.setdefault('User-Agent', ua)
        # tempIP = self.get_randomIP()
        # request.meta['proxy'] = "http://%s" % tempIP
            # the default user_agent_list composes chrome,I E,firefox,Mozilla,opera,netscape

    # for more user agent strings,you can find it in http://www.useragentstring.com/pages/useragentstring.php
    def get_randomIP(self):
        if datetime.now().timestamp()-self.tempTime > self.offsetTime:
            self.ip = []
            f = open("d:/ip/ip.txt")
            for line in f:
                self.ip.insert(len(self.ip),line)
            f.close()
            self.tempTime = datetime.now().timestamp()
        return random.choice(self.ip)

class GetproxiesipSpiderMiddleware(object):
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


class ProxyMiddleware(object):
    # overwrite process request
    def process_request(self, request, spider):
        # Set the location of the proxy
        # request.meta['proxy'] = "http://97.90.251.98:3128"
        print('-----------------------------------------')
        print('-----------------------------------------')
        print('-----------------------------------------')
        print('-----------------------------------------')
        print('-----------------------------------------')
        print('---testing--------------------------------')
        # Use the following lines if your proxy requires authentication
        # proxy_user_pass = "Nongxiaolang:wswqzj"
        # # setup basic authentication for the proxy
        # encoded_user_pass =  base64.encodestring(proxy_user_pass.encode())
        # print(encoded_user_pass.decode())
        # request.headers['Proxy-Authorization'] =  proxy_user_pass
        print('--------user pass----------------------------')