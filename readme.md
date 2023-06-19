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

## Changes

### LRU Cache

* liteweb/cache/Node.java
* liteweb/cache/EmptyNode.java
* liteweb/cache/SortedByUsageLinkedList.java
* liteweb/cache/LRUCache.java
* liteweb/streaming/FileServer.java

## Testing results

### Summary

| Name                     | Concurrency |
|--------------------------|-------------|
| Cached 24 Threads (x2)   | 347977      |
| 24 Threads (x2)          | 317300      |
| 12 Threads (x1)          | 294167      |
| Cached 12 Threads (x1)   | 288219      |
| Virtual Thread (Default) | 286172      |
| Cached Virtual Thread    | 281412      |
