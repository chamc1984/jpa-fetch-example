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
-- テスト対象テーブルDDL
CREATE TABLE MANYDATA(
  id serial not null,
  username varchar not null,
  checkflg varchar,
  PRIMARY KEY(id)
);
```

```sql
-- 大量データ投入用基底テーブルDDL
CREATE TABLE TEMP_BASEDATA (
  key integer,
  value integer,
  primary key (key)
);

-- 基底テーブルデータ投入
INSERT INTO TEMP_BASEDATA VALUES ( 0,0 );
INSERT INTO TEMP_BASEDATA VALUES ( 1,1 );
INSERT INTO TEMP_BASEDATA VALUES ( 2,2 );
INSERT INTO TEMP_BASEDATA VALUES ( 3,3 );
INSERT INTO TEMP_BASEDATA VALUES ( 4,4 );
INSERT INTO TEMP_BASEDATA VALUES ( 5,5 );
INSERT INTO TEMP_BASEDATA VALUES ( 6,6 );
INSERT INTO TEMP_BASEDATA VALUES ( 7,7 );
INSERT INTO TEMP_BASEDATA VALUES ( 8,8 );
INSERT INTO TEMP_BASEDATA VALUES ( 9,9 );

-- 100万件データ投入
INSERT INTO MANYDATA(username)
SELECT
  'ANDREW' || ROW_NUMBER() OVER ()
FROM
  TEMP_BASEDATA AS t1,
  TEMP_BASEDATA AS t2,
  TEMP_BASEDATA AS t3,
  TEMP_BASEDATA AS t4,
  TEMP_BASEDATA AS t5,
  TEMP_BASEDATA AS t6
;
```

※参考リンク [SQLで大量のテストデータ作成](https://qiita.com/cobot00/items/8d59e0734314a88d74c7)
