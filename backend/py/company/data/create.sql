DROP TABLE IF EXISTS data;
CREATE TABLE IF NOT EXISTS data
(
    id          integer PRIMARY KEY AUTOINCREMENT,
    年    INTEGER,
    月        INTEGER,
    工序    REAL,
    产品    REAL,
    无烟煤    REAL,
    贫瘦煤        REAL,
    烟煤        REAL,
    冶金焦        REAL,
    焦丁        REAL,
    柴油    REAL,
    汽油    REAL,
    焦面    REAL,
    高炉煤气    REAL,
    转炉煤气    REAL,
    电    REAL,
    蒸汽    REAL,
    新水    REAL,
    氧气    REAL,
    氮气    REAL,
    氩气    REAL,
    压缩空气    REAL
);					