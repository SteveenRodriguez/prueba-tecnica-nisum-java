INSERT INTO users(id_user, name, email, password, created, modified, last_login, token, is_active) VALUES('28c24628-a312-47ed-a238-b627684623f3' ,'steveen rodriguez', 'steveen@mail.com', 'MyPassword1', CURRENT_DATE, CURRENT_DATE, CURRENT_DATE, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE2ODk0MDM2MTcsImV4cCI6MTcyMDkzOTYxNywiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0.', 'true');
INSERT INTO users(id_user, name, email, password, created, modified, last_login, token, is_active) VALUES('ed838fb4-b20c-46ef-a87d-bfbedda656ef' ,'stick torres', 'stick@mail.com', 'MyPassword2', CURRENT_DATE, CURRENT_DATE, CURRENT_DATE, '
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE2ODk0MDM2MTcsImV4cCI6MTcyMDkzOTYxNywiYXVkIjoid3d3Lm5pc3N1bS5jb20iLCJzdWIiOiJqcm9ja2V0QGV4YW1wbGUuY29tIiwiR2l2ZW5OYW1lIjoiSm9obm55IiwiU3VybmFtZSI6IlJvY2tldCIsIkVtYWlsIjoic3RpY2tAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0.', 'true');

INSERT INTO phones(number, city_code, country_code, id_user) VALUES('3218762155', 12, 56, '28c24628-a312-47ed-a238-b627684623f3');
INSERT INTO phones(number, city_code, country_code, id_user) VALUES('3220983755', 13, 56, 'ed838fb4-b20c-46ef-a87d-bfbedda656ef');
INSERT INTO phones(number, city_code, country_code, id_user) VALUES('3147346749', 24, 57, 'ed838fb4-b20c-46ef-a87d-bfbedda656ef');