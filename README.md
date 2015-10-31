# NSNJSON Java Driver
## NSNJSON - Not So Normal JSON

![nsnjson_logo](https://raw.githubusercontent.com/wiki/nsnjson/nsnjson-driver/images/nsnjson_logo.png)

## Why?

Have you ever think about JSON with only 4 types: **number**, **string**, **array**, **object**? :)

What if your programming language or some another ecosystem doesn't support **null** or **true**/**false** as a types or a special values?

If it is your case then NSNJSON comes to help you! :)

The NSNJSON operates only 4 types - **number**, **string**, **array**, **object**!

Also, it defines types mapping table:

| JSON type    | null   | number | string | true   | false  | array  | object |
|:-------------|:------:|:------:|:------:|:------:|:------:|:------:|:------:|
| NSNJSON type | 0      | 1      | 2      | 3      | 3      | 4      | 5      |


## How?

For every JSON type NSNJSON defines special presentation which is absolutely valid JSON.

NSNJSON presentation is always JSON object.

Such object always contains field **"t"** (which means **type** of source JSON type).

Also, for all JSON types except **null** the object always contains field **"v"** (which means **value** of source JSON type).

JSON object field has a name. So, NSNJSON saves this information in field **"n"** (which means **name** of field JSON object).

More info on <a href="https://github.com/nsnjson/nsnjson-driver">NSNJSON Driver</a> page.

## Install

Use [jitpack.io](https://jitpack.io)!

If you are using [**Maven**](http://maven.apache.org), then add JitPack repository to your [**pom.xml**](http://maven.apache.org/ref/3.3.3/maven-model/maven.html):
```xml  
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```
Now, you can specify dependency:
```xml
<dependency>
  <groupId>com.github.nsnjson</groupId>
  <artifactId>nsnjson-java-driver</artifactId>
  <version>v0.0.2</version>
</dependency>
```