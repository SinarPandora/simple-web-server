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

### Summary

| Name                     | Concurrency |
|--------------------------|-------------|
| Single Thread            | 132191      |
| Virtual Thread (Default) | 293520      |
| 12 Threads (x1)          | 300921      |
| 24 Threads (x2)          | 340785      |
| Unlimited                | 328112      |

### Single Thread

```log
11:36:34 INFO: Taurus CLI Tool v1.16.22
11:36:34 INFO: Starting with configs: ['/home/sinar/.bzt-rc', 'performance.yml']
11:36:34 INFO: Configuring...
11:36:34 INFO: Proxy settings not set
11:36:34 INFO: Artifacts dir: /home/sinar/Development/simple-web-server/2023-06-05_11-36-34.044240
11:36:34 INFO: Preparing...
11:36:37 WARNING: Module 'console' can be only used once, will merge all new instances into single
11:36:37 INFO: Starting...
11:36:37 INFO: Waiting for results...
11:36:37 INFO: Waiting for finish...
11:36:39 WARNING: Failed to check for updates, server returned 5xx.
11:38:02 WARNING: Please wait for graceful shutdown...
11:38:02 INFO: Shutting down...
11:38:02 INFO: Post-processing...
11:38:02 INFO: Test duration: 0:01:25
11:38:02 INFO: Samples count: 132191, 0.00% failures
11:38:02 INFO: Average times: total 0.029, latency 0.028, connect 0.004
11:38:02 INFO: Percentiles:
+---------------+---------------+
| Percentile, % | Resp. Time, s |
+---------------+---------------+
|           0.0 |         0.001 |
|          50.0 |         0.025 |
|          90.0 |         0.031 |
|          95.0 |         0.035 |
|          99.0 |         0.049 |
|          99.9 |         0.638 |
|         100.0 |        30.032 |
+---------------+---------------+
11:38:02 INFO: Request label stats:
+---------------------------------------------------------+--------+---------+--------+---------------------------------------------------------------------------------------------+
| label                                                   | status |    succ | avg_rt | error                                                                                       |
+---------------------------------------------------------+--------+---------+--------+---------------------------------------------------------------------------------------------+
| http://127.0.0.1:8080                                   |  FAIL  |  99.99% |  0.032 | Non HTTP response message: Connect to 127.0.0.1:8080 [/127.0.0.1] failed: connect timed out |
| http://127.0.0.1:8080/performance.yml                   |   OK   | 100.00% |  0.028 |                                                                                             |
| http://127.0.0.1:8080/readme.md                         |  FAIL  | 100.00% |  0.029 | Non HTTP response message: Connect to 127.0.0.1:8080 [/127.0.0.1] failed: connect timed out |
| http://127.0.0.1:8080/src/main/java/liteweb/Server.java |  FAIL  | 100.00% |  0.030 | Non HTTP response message: Connect to 127.0.0.1:8080 [/127.0.0.1] failed: connect timed out |
+---------------------------------------------------------+--------+---------+--------+---------------------------------------------------------------------------------------------+
11:38:02 INFO: Test duration: 0:01:25
11:38:02 INFO: Samples count: 132191, 0.00% failures
11:38:02 INFO: Average times: total 0.029, latency 0.028, connect 0.004
11:38:02 INFO: Percentiles:
+---------------+---------------+
| Percentile, % | Resp. Time, s |
+---------------+---------------+
|           0.0 |         0.001 |
|          50.0 |         0.025 |
|          90.0 |         0.031 |
|          95.0 |         0.035 |
|          99.0 |         0.049 |
|          99.9 |         0.638 |
|         100.0 |        30.032 |
+---------------+---------------+
11:38:02 INFO: Request label stats:
+---------------------------------------------------------+--------+---------+--------+---------------------------------------------------------------------------------------------+
| label                                                   | status |    succ | avg_rt | error                                                                                       |
+---------------------------------------------------------+--------+---------+--------+---------------------------------------------------------------------------------------------+
| http://127.0.0.1:8080                                   |  FAIL  |  99.99% |  0.032 | Non HTTP response message: Connect to 127.0.0.1:8080 [/127.0.0.1] failed: connect timed out |
| http://127.0.0.1:8080/performance.yml                   |   OK   | 100.00% |  0.028 |                                                                                             |
| http://127.0.0.1:8080/readme.md                         |  FAIL  | 100.00% |  0.029 | Non HTTP response message: Connect to 127.0.0.1:8080 [/127.0.0.1] failed: connect timed out |
| http://127.0.0.1:8080/src/main/java/liteweb/Server.java |  FAIL  | 100.00% |  0.030 | Non HTTP response message: Connect to 127.0.0.1:8080 [/127.0.0.1] failed: connect timed out |
+---------------------------------------------------------+--------+---------+--------+---------------------------------------------------------------------------------------------+
11:38:02 INFO: Artifacts dir: /home/sinar/Development/simple-web-server/2023-06-05_11-36-34.044240
11:38:02 INFO: Done performing with code: 0
```

### Virtual Thread (Default)

```log
11:24:52 INFO: Taurus CLI Tool v1.16.22
11:24:52 INFO: Starting with configs: ['/home/sinar/.bzt-rc', 'performance.yml']
11:24:52 INFO: Configuring...
11:24:52 INFO: Proxy settings not set
11:24:52 INFO: Artifacts dir: /home/sinar/Development/simple-web-server/2023-06-05_11-24-52.539029
11:24:52 INFO: Preparing...
11:24:58 WARNING: Failed to check for updates, server returned 5xx.
11:25:00 WARNING: Module 'console' can be only used once, will merge all new instances into single
11:25:00 INFO: Starting...
11:25:00 INFO: Waiting for results...
11:25:00 INFO: Waiting for finish...
11:26:15 WARNING: Please wait for graceful shutdown...
11:26:15 INFO: Shutting down...
11:26:15 INFO: Post-processing...
11:26:15 INFO: Test duration: 0:01:15
11:26:15 INFO: Samples count: 293520, 0.00% failures
11:26:15 INFO: Average times: total 0.013, latency 0.013, connect 0.001
11:26:15 INFO: Percentiles:
+---------------+---------------+
| Percentile, % | Resp. Time, s |
+---------------+---------------+
|           0.0 |         0.001 |
|          50.0 |         0.012 |
|          90.0 |         0.019 |
|          95.0 |         0.022 |
|          99.0 |         0.033 |
|          99.9 |         0.055 |
|         100.0 |          1.06 |
+---------------+---------------+
11:26:15 INFO: Request label stats:
+---------------------------------------------------------+--------+---------+--------+-------+
| label                                                   | status |    succ | avg_rt | error |
+---------------------------------------------------------+--------+---------+--------+-------+
| http://127.0.0.1:8080                                   |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/performance.yml                   |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/readme.md                         |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/src/main/java/liteweb/Server.java |   OK   | 100.00% |  0.013 |       |
+---------------------------------------------------------+--------+---------+--------+-------+
11:26:15 INFO: Test duration: 0:01:15
11:26:15 INFO: Samples count: 293520, 0.00% failures
11:26:15 INFO: Average times: total 0.013, latency 0.013, connect 0.001
11:26:15 INFO: Percentiles:
+---------------+---------------+
| Percentile, % | Resp. Time, s |
+---------------+---------------+
|           0.0 |         0.001 |
|          50.0 |         0.012 |
|          90.0 |         0.019 |
|          95.0 |         0.022 |
|          99.0 |         0.033 |
|          99.9 |         0.055 |
|         100.0 |          1.06 |
+---------------+---------------+
11:26:15 INFO: Request label stats:
+---------------------------------------------------------+--------+---------+--------+-------+
| label                                                   | status |    succ | avg_rt | error |
+---------------------------------------------------------+--------+---------+--------+-------+
| http://127.0.0.1:8080                                   |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/performance.yml                   |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/readme.md                         |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/src/main/java/liteweb/Server.java |   OK   | 100.00% |  0.013 |       |
+---------------------------------------------------------+--------+---------+--------+-------+
11:26:15 INFO: Artifacts dir: /home/sinar/Development/simple-web-server/2023-06-05_11-24-52.539029
11:26:15 INFO: Done performing with code: 0
```

### 12 Threads

```log
11:27:18 INFO: Taurus CLI Tool v1.16.22
11:27:18 INFO: Starting with configs: ['/home/sinar/.bzt-rc', 'performance.yml']
11:27:18 INFO: Configuring...
11:27:18 INFO: Proxy settings not set
11:27:18 INFO: Artifacts dir: /home/sinar/Development/simple-web-server/2023-06-05_11-27-18.740289
11:27:18 INFO: Preparing...
11:27:21 WARNING: Module 'console' can be only used once, will merge all new instances into single
11:27:21 INFO: Starting...
11:27:21 INFO: Waiting for results...
11:27:22 INFO: Waiting for finish...
11:27:24 WARNING: Failed to check for updates, server returned 5xx.
11:28:37 WARNING: Please wait for graceful shutdown...
11:28:37 INFO: Shutting down...
11:28:37 INFO: Post-processing...
11:28:37 INFO: Test duration: 0:01:16
11:28:37 INFO: Samples count: 300921, 0.00% failures
11:28:37 INFO: Average times: total 0.013, latency 0.013, connect 0.001
11:28:37 INFO: Percentiles:
+---------------+---------------+
| Percentile, % | Resp. Time, s |
+---------------+---------------+
|           0.0 |           0.0 |
|          50.0 |         0.012 |
|          90.0 |         0.019 |
|          95.0 |         0.022 |
|          99.0 |         0.033 |
|          99.9 |         0.054 |
|         100.0 |         1.051 |
+---------------+---------------+
11:28:37 INFO: Request label stats:
+---------------------------------------------------------+--------+---------+--------+-------+
| label                                                   | status |    succ | avg_rt | error |
+---------------------------------------------------------+--------+---------+--------+-------+
| http://127.0.0.1:8080                                   |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/performance.yml                   |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/readme.md                         |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/src/main/java/liteweb/Server.java |   OK   | 100.00% |  0.013 |       |
+---------------------------------------------------------+--------+---------+--------+-------+
11:28:37 INFO: Test duration: 0:01:16
11:28:37 INFO: Samples count: 300921, 0.00% failures
11:28:37 INFO: Average times: total 0.013, latency 0.013, connect 0.001
11:28:37 INFO: Percentiles:
+---------------+---------------+
| Percentile, % | Resp. Time, s |
+---------------+---------------+
|           0.0 |           0.0 |
|          50.0 |         0.012 |
|          90.0 |         0.019 |
|          95.0 |         0.022 |
|          99.0 |         0.033 |
|          99.9 |         0.054 |
|         100.0 |         1.051 |
+---------------+---------------+
11:28:37 INFO: Request label stats:
+---------------------------------------------------------+--------+---------+--------+-------+
| label                                                   | status |    succ | avg_rt | error |
+---------------------------------------------------------+--------+---------+--------+-------+
| http://127.0.0.1:8080                                   |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/performance.yml                   |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/readme.md                         |   OK   | 100.00% |  0.013 |       |
| http://127.0.0.1:8080/src/main/java/liteweb/Server.java |   OK   | 100.00% |  0.013 |       |
+---------------------------------------------------------+--------+---------+--------+-------+
11:28:37 INFO: Artifacts dir: /home/sinar/Development/simple-web-server/2023-06-05_11-27-18.740289
11:28:37 INFO: Done performing with code: 0
```

### 24 Threads

```log
1:30:13 INFO: Taurus CLI Tool v1.16.22
11:30:13 INFO: Starting with configs: ['/home/sinar/.bzt-rc', 'performance.yml']
11:30:13 INFO: Configuring...
11:30:13 INFO: Proxy settings not set
11:30:13 INFO: Artifacts dir: /home/sinar/Development/simple-web-server/2023-06-05_11-30-13.132928
11:30:13 INFO: Preparing...
11:30:16 WARNING: Module 'console' can be only used once, will merge all new instances into single
11:30:16 INFO: Starting...
11:30:16 INFO: Waiting for results...
11:30:16 INFO: Waiting for finish...
11:30:19 WARNING: Failed to check for updates, server returned 5xx.
11:31:30 WARNING: Please wait for graceful shutdown...
11:31:30 INFO: Shutting down...
11:31:30 INFO: Post-processing...
11:31:30 INFO: Test duration: 0:01:14
11:31:30 INFO: Samples count: 340785, 0.00% failures
11:31:30 INFO: Average times: total 0.011, latency 0.011, connect 0.001
11:31:30 INFO: Percentiles:
+---------------+---------------+
| Percentile, % | Resp. Time, s |
+---------------+---------------+
|           0.0 |           0.0 |
|          50.0 |         0.008 |
|          90.0 |         0.019 |
|          95.0 |         0.029 |
|          99.0 |         0.051 |
|          99.9 |         0.081 |
|         100.0 |         1.073 |
+---------------+---------------+
11:31:30 INFO: Request label stats:
+---------------------------------------------------------+--------+---------+--------+-------+
| label                                                   | status |    succ | avg_rt | error |
+---------------------------------------------------------+--------+---------+--------+-------+
| http://127.0.0.1:8080                                   |   OK   | 100.00% |  0.011 |       |
| http://127.0.0.1:8080/performance.yml                   |   OK   | 100.00% |  0.011 |       |
| http://127.0.0.1:8080/readme.md                         |   OK   | 100.00% |  0.011 |       |
| http://127.0.0.1:8080/src/main/java/liteweb/Server.java |   OK   | 100.00% |  0.011 |       |
+---------------------------------------------------------+--------+---------+--------+-------+
11:31:30 INFO: Test duration: 0:01:14
11:31:30 INFO: Samples count: 340785, 0.00% failures
11:31:30 INFO: Average times: total 0.011, latency 0.011, connect 0.001
11:31:30 INFO: Percentiles:
+---------------+---------------+
| Percentile, % | Resp. Time, s |
+---------------+---------------+
|           0.0 |           0.0 |
|          50.0 |         0.008 |
|          90.0 |         0.019 |
|          95.0 |         0.029 |
|          99.0 |         0.051 |
|          99.9 |         0.081 |
|         100.0 |         1.073 |
+---------------+---------------+
11:31:30 INFO: Request label stats:
+---------------------------------------------------------+--------+---------+--------+-------+
| label                                                   | status |    succ | avg_rt | error |
+---------------------------------------------------------+--------+---------+--------+-------+
| http://127.0.0.1:8080                                   |   OK   | 100.00% |  0.011 |       |
| http://127.0.0.1:8080/performance.yml                   |   OK   | 100.00% |  0.011 |       |
| http://127.0.0.1:8080/readme.md                         |   OK   | 100.00% |  0.011 |       |
| http://127.0.0.1:8080/src/main/java/liteweb/Server.java |   OK   | 100.00% |  0.011 |       |
+---------------------------------------------------------+--------+---------+--------+-------+
11:31:30 INFO: Artifacts dir: /home/sinar/Development/simple-web-server/2023-06-05_11-30-13.132928
11:31:30 INFO: Done performing with code: 0
```

### Unlimited

```log
11:34:16 INFO: Taurus CLI Tool v1.16.22
11:34:16 INFO: Starting with configs: ['/home/sinar/.bzt-rc', 'performance.yml']
11:34:16 INFO: Configuring...
11:34:16 INFO: Proxy settings not set
11:34:16 INFO: Artifacts dir: /home/sinar/Development/simple-web-server/2023-06-05_11-34-16.271118
11:34:16 INFO: Preparing...
11:34:19 WARNING: Module 'console' can be only used once, will merge all new instances into single
11:34:19 INFO: Starting...
11:34:19 INFO: Waiting for results...
11:34:19 INFO: Waiting for finish...
11:34:22 WARNING: Failed to check for updates, server returned 5xx.
11:35:35 WARNING: Please wait for graceful shutdown...
11:35:35 INFO: Shutting down...
11:35:35 INFO: Post-processing...
11:35:35 INFO: Test duration: 0:01:16
11:35:35 INFO: Samples count: 328112, 0.00% failures
11:35:35 INFO: Average times: total 0.012, latency 0.012, connect 0.002
11:35:35 INFO: Percentiles:
+---------------+---------------+
| Percentile, % | Resp. Time, s |
+---------------+---------------+
|           0.0 |           0.0 |
|          50.0 |         0.009 |
|          90.0 |         0.017 |
|          95.0 |         0.022 |
|          99.0 |         0.036 |
|          99.9 |         1.022 |
|         100.0 |         3.042 |
+---------------+---------------+
11:35:35 INFO: Request label stats:
+---------------------------------------------------------+--------+---------+--------+-------+
| label                                                   | status |    succ | avg_rt | error |
+---------------------------------------------------------+--------+---------+--------+-------+
| http://127.0.0.1:8080                                   |   OK   | 100.00% |  0.012 |       |
| http://127.0.0.1:8080/performance.yml                   |   OK   | 100.00% |  0.012 |       |
| http://127.0.0.1:8080/readme.md                         |   OK   | 100.00% |  0.011 |       |
| http://127.0.0.1:8080/src/main/java/liteweb/Server.java |   OK   | 100.00% |  0.012 |       |
+---------------------------------------------------------+--------+---------+--------+-------+
11:35:35 INFO: Test duration: 0:01:16
11:35:35 INFO: Samples count: 328112, 0.00% failures
11:35:35 INFO: Average times: total 0.012, latency 0.012, connect 0.002
11:35:35 INFO: Percentiles:
+---------------+---------------+
| Percentile, % | Resp. Time, s |
+---------------+---------------+
|           0.0 |           0.0 |
|          50.0 |         0.009 |
|          90.0 |         0.017 |
|          95.0 |         0.022 |
|          99.0 |         0.036 |
|          99.9 |         1.022 |
|         100.0 |         3.042 |
+---------------+---------------+
11:35:35 INFO: Request label stats:
+---------------------------------------------------------+--------+---------+--------+-------+
| label                                                   | status |    succ | avg_rt | error |
+---------------------------------------------------------+--------+---------+--------+-------+
| http://127.0.0.1:8080                                   |   OK   | 100.00% |  0.012 |       |
| http://127.0.0.1:8080/performance.yml                   |   OK   | 100.00% |  0.012 |       |
| http://127.0.0.1:8080/readme.md                         |   OK   | 100.00% |  0.011 |       |
| http://127.0.0.1:8080/src/main/java/liteweb/Server.java |   OK   | 100.00% |  0.012 |       |
+---------------------------------------------------------+--------+---------+--------+-------+
11:35:35 INFO: Artifacts dir: /home/sinar/Development/simple-web-server/2023-06-05_11-34-16.271118
11:35:35 INFO: Done performing with code: 0
```
