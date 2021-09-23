
import requests
import json

# GET
res = requests.get('http://20.51.192.43:8080/mobility/getData?offset=0&limit=3&name=omg')
print(str(res.status_code) + " | " + res.text)


data = res.json()

print(data)
print(type(data))


data2 = res.json()['content']
print(data2)
print(type(data2))

print(data2[1])