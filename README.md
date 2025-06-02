# Steps

- Start the spring boot app
- Start a wiremock server with mocks available in [mocks](./mocks)
- Api call with spring-cloud-gateway route config
```bash
curl -X GET "http://localhost:8080/otel/realm" \
  -H "Connection: keep-alive" \
  -H "X-Request-ID: 726c7d86-85fd-47e0-bcd3-84552cfea394" \
  -H "X-Username: NOW" \
  -H "X-Tenant-Id: YSS" \
  -H "Content-Type: application/json"
```
- Simple api call (_without spring-cloud-gateway_)
```bash
curl -X GET "http://localhost:8080/otel/person" \
  -H "Connection: keep-alive" \
  -H "X-Request-ID: cf75f972-771c-4ab4-bd32-3c53efeb51ad" \
  -H "X-Username: URH" \
  -H "X-Tenant-Id: VXP" \
  -H "Content-Type: application/json"
```