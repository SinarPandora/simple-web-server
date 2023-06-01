# Simple Web Server
![Coverage](.github/badges/jacoco.svg)

![Branches](.github/badges/branches.svg)

A simplex HTTP 1.0 Server implemented in Java for educational
purposes based on W3C specifications (http://www.w3.org/Protocols/):

* [W3](https://www.w3.org/Protocols/HTTP/AsImplemented.html) Hypertext Transfer Protocol -- HTTP/0.9
* [RFC 1945](http://www.ietf.org/rfc/rfc1945.txt) Hypertext Transfer Protocol -- HTTP/1.0
* [RFC 2616](http://www.ietf.org/rfc/rfc2616.txt) Hypertext Transfer Protocol -- HTTP/1.1
* [RFC 2617](http://www.ietf.org/rfc/rfc2617.txt) HTTP Authentication: Basic and Digest Access Authentication
* [RFC 6265](http://tools.ietf.org/html/rfc6265) HTTP State Management Mechanism (Cookies)

## Build
```bash
./gradlew jar 
```

## Run
```bash
java -cp build/libs/simple-web-server-1.0.jar liteweb.Server
```

## Performance test
```bash
bzt performance.yml
```

## Testing results
```log
[2023-05-23 14:06:36,990 INFO Engine.final-stats] Test duration: 0:01:17
[2023-05-23 14:06:36,990 INFO Engine.final-stats] Samples count: 230431, 0.00% failures
[2023-05-23 14:06:36,990 INFO Engine.final-stats] Average times: total 0.016, latency 0.016, connect 0.001
[2023-05-23 14:06:36,991 INFO Engine.final-stats] Percentiles:
+---------------+---------------+
| Percentile, % | Resp. Time, s |
+---------------+---------------+
|           0.0 |         0.001 |
|          50.0 |         0.013 |
|          90.0 |         0.027 |
|          95.0 |         0.035 |
|          99.0 |         0.054 |
|          99.9 |         0.391 |
|         100.0 |         1.136 |
+---------------+---------------+
[2023-05-23 14:06:36,991 INFO Engine.final-stats] Request label stats:
+---------------------------------------------------------+--------+---------+--------+-------+
| label                                                   | status |    succ | avg_rt | error |
+---------------------------------------------------------+--------+---------+--------+-------+
| http://127.0.0.1:8080                                   |   OK   | 100.00% |  0.016 |       |
| http://127.0.0.1:8080/performance.yml                   |   OK   | 100.00% |  0.016 |       |
| http://127.0.0.1:8080/readme.md                         |   OK   | 100.00% |  0.016 |       |
| http://127.0.0.1:8080/src/main/java/liteweb/Server.java |   OK   | 100.00% |  0.016 |       |
```
