import requests
import time
import hmac
import hashlib
import base64
import urllib.parse
import json


class Config:
    """
    https://open.dingtalk.com/document/orgapp/custom-robot-access
    """

    def __init__(self, url: str, secret: str, access_token: str):
        timestamp = str(round(time.time() * 1000))
        secret_enc = secret.encode('utf-8')
        string_to_sign = '{}\n{}'.format(timestamp, secret)
        string_to_sign_enc = string_to_sign.encode('utf-8')
        hmac_code = hmac.new(secret_enc, string_to_sign_enc, digestmod=hashlib.sha256).digest()
        sign = urllib.parse.quote_plus(base64.b64encode(hmac_code))
        self.url = url + '?access_token=%s&timestamp=%s&sign=%s' % (access_token, timestamp, sign)


def notice(config: Config, data: dict):
    headers = {'Content-Type': 'application/json'}
    response = requests.post(config.url, headers=headers, data=json.dumps(data))
    print(json.loads(response.text))


if __name__ == '__main__':
    conf = Config('https://oapi.dingtalk.com/robot/send',
                  'SEC',
                  '2')

    notice(conf, {
        'msgtype': 'markdown',
        "markdown": {
            "title": "xx报表",
            "text": "# xx报表 \n "
                    "> 或许应该大概说些什么\n"
                    "\n"
                    "请查收\n"
                    "- @135xxxxxxxx\n"
                    "\n"
                    "![report]("
                    "https://5b0988e595225.cdn.sohucs.com/images/20180810/5bf26d92199346f59679d9f7837d4070.png)\n "
        },
        "at": {
            "atMobiles": [
                "135xxxxxxxx"
            ],
            "isAtAll": False
        }
    })
