import pathlib
import xlrd


def get_path(name: str):
    """
    获取数据文件绝对路径
    :param name: 文件名称
    :return: 文件绝对路径
    """
    return str(pathlib.Path.home()) + r'/Downloads/' + name


if __name__ == '__main__':
    wb = xlrd.open_workbook(get_path("2021.xls"))
    for i in range(wb.nsheets):
        ws = wb.sheet_by_index(i)
        print(ws.name)
        for j in range(ws.nrows):
            rows = ws.row(j)
            print([cell.value for cell in rows])
            # for cell in rows:
            #     print(cell.ctype, cell.value)
            # break
        break
