import os
from pyecharts import options as opts
from pyecharts.charts import Bar, Line, Liquid, Page
from pyecharts.charts.base import Base
from pyecharts.commons.utils import JsCode
from pyecharts.components import Table
from pyecharts.faker import Faker
from pyecharts.globals import CurrentConfig


class MyTable(Table):
    def __init__(self, page_title: str = CurrentConfig.PAGE_TITLE, js_host: str = ""):
        super().__init__(page_title, js_host)
        self._component_type: str = "myTable"
        self.width = "100%"
        self.height = "300px"


def base_bar() -> Bar:
    bar = (
        Bar(opts.InitOpts(page_title="Bar", width="100%", height="300px"))
        .add_xaxis(Faker.days_attrs)
        .add_yaxis("商家A", Faker.days_values)
        .add_yaxis("商家B", Faker.days_values)
        .set_global_opts(
            datazoom_opts=[opts.DataZoomOpts()])

    )
    return bar


def base_line() -> Line:
    line = (
        Line(opts.InitOpts(page_title="Line", width="100%", height="300px"))
        .add_xaxis(Faker.choose())
        .add_yaxis(
            "商家A",
            Faker.values(),
            markpoint_opts=opts.MarkPointOpts(
                data=[opts.MarkPointItem(type_="min")]),
        )
        .add_yaxis(
            "商家B",
            Faker.values(),
            markpoint_opts=opts.MarkPointOpts(
                data=[opts.MarkPointItem(type_="max")]),
        )
    )
    return line


def base_Liquid() -> Liquid:
    l = (
        Liquid(opts.InitOpts(page_title="Liquid", width="100%", height="300px"))
        .add(
            "lq",
            [0.3254],
            label_opts=opts.LabelOpts(
                font_size=20,
                formatter=JsCode(
                    """function (param) {
                        return (Math.floor(param.value * 10000) / 100) + '%';
                    }"""
                ),
                position="inside",
            ),
        )
    )
    return l


def base_Table() -> Table:
    headers = ["City name", "Area", "Population", "Annual Rainfall"]
    rows = [
        ["Brisbane", 5905, 1857594, 1146.4],
        ["Adelaide", 1295, 1158259, 600.5],
        ["Darwin", 112, 120900, 1714.7],
        ["Hobart", 1357, 205556, 619.5],
        ["Sydney", 2058, 4336374, 1214.8],
        ["Melbourne", 1566, 3806092, 646.9],
        ["Perth", 5386, 1554769, 869.4],
        ["Perth", 5386, 1554769, 869.4],
        ["Perth", 5386, 1554769, 869.4],
        ["Perth", 5386, 1554769, 869.4],
    ]
    return (
        MyTable(page_title="Table")
        .add(headers, rows, attributes={"class": "table table-hover"})
    )


if __name__ == "__main__":
    workdir = os.getcwd()
    CurrentConfig.GLOBAL_ENV.loader.searchpath.append(
        workdir+"/backend/py/dashboard")
    page = (
        Page(page_title="dashbord", is_remove_br=True)
        .add(base_bar(), base_line(), base_Liquid(), base_Table())
    )
    path = workdir+"/dist/mycharts.html"
    page.render(path, "template.html")
