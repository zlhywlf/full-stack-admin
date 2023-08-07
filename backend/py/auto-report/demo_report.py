import sqlite3

if __name__ == '__main__':
    conn = sqlite3.connect('./data/data.db')
    cursorA = conn.cursor()
    sql = ('CREATE TABLE students('
           'id int PRIMARY KEY, '
           'name varchar(20) NOT NULL , '
           'age int NOT NULL)')
    cursorA.execute(sql)
    cursorA.close()
    conn.close()
    print('hello world')
