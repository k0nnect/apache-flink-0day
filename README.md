# Apache Flink SSRF PoC

This repository shows a Server-Side Request Forgery (SSRF) vulnerability in Apache Flink (v2.0.0) through its REST API endpoints (`/jars/upload`, `/jars/run`). The vulnerability allows attackers to upload a malicious JAR file that can make HTTP requests to internal services, potentially leading to unauthorized access or information leakage if the payload is configurated differently.

## Vulnerability Details

- **Product**: Apache Flink
- **Component**: REST API (`/jars/upload`, `/jars/run`)
- **Version**: 2.0.0 (tested with `flink:2.0.0` Docker image) so whatevers most up to date as of april 24th
- **Vulnerability Type**: Server-Side Request Forgery (SSRF)
- **Status**: not reported, do it for me
- **Researcher**: [k0nnect](https://github.com/k0nnect)
- **Discovered**: April 2025

## Description

Apache Flink is a distributed stream-processing framework, and an SSRF vulnerability exists in the `/jars/upload` and `/jars/run` endpoints of its REST API. When authentication is disabled (which is the default in many Docker setups), an attacker can upload a malicious JAR file containing a Flink job that makes HTTP requests to internal services, such as `http://172.17.0.1:8080`. This can lead to unauthorized access or network enumeration.

### Impact:
- **Internal Service Access**: Attackers can target internal services to access sensitive data.
- **Network Enumeration**: SSRF can be used to map internal networks or cloud metadata.
- **Potential RCE**: Malicious JARs could execute arbitrary code.

### Severity: High (estimated CVSS score 7.5-8.5)

## Prerequisites

- **System**: Linux-based system (e.g., Arch Linux)
- **Tools**: Docker, Maven (for Java), Python (for HTTP server)
- **Flink Setup**: Apache Flink 2.0.0 cluster running on port `8081`
- **Target Service**: HTTP server running on `http://172.17.0.1:8080`
-----

- this is tested and worked. 
- thank you violent vira for the music :3
- (yes ai did the xml)
- ![image](https://github.com/user-attachments/assets/c932d7f6-b4c2-4215-9aca-d5ef4875db60)

- ![image](https://github.com/user-attachments/assets/5e76160c-7f5c-4725-bbbe-64b3f414c917)


