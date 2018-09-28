# jpa-fetch-example

RDBへのアクセス時にfetch sizeを設定して、DB接続の負荷を検証する。

## Spec

### フレームワーク

- SpringBoot2
- JAX-RS (Jersey)
- Spring JPA (EclipseLink)

### ビルドツール

- Gradle

### DB

- Postgres

## 前提

- localhostでPostgresが立ち上がっていること
- 以下のテーブルが作成し、大量の（100万件程度）データを登録しておく
    - データベース名：postgres
    - テーブル名：MANYDATA

| カラム名 | 型 |
| - | - |
| id | SERIAL |
| username | VARCHAR |
| checkflg | VARCHAR |

```sql
-- DDL
CREATE TABLE MANYDATA(
  id serial not null,
  username varchar not null,
  checkflg varchar,
  PRIMARY KEY(id)
);
```

```sql
-- 100万件データ投入
INSERT INTO MANYDATA(username)
SELECT
  'ANDREW' || ROW_NUMBER() OVER ()
FROM
  MANYDATA AS t1,
  MANYDATA AS t2,
  MANYDATA AS t3,
  MANYDATA AS t4,
  MANYDATA AS t5,
  MANYDATA AS t6,
  MANYDATA AS t7
;
```

※参考リンク [SQLで大量のテストデータ作成](https://qiita.com/cobot00/items/8d59e0734314a88d74c7)
